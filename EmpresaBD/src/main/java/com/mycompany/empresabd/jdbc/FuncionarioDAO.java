package com.mycompany.empresabd.jdbc;

import com.mycompany.empresabd.modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    // CREATE
    public void inserir(Conexao conexao, Funcionario f) throws SQLException {
        try {
            String sql = "INSERT INTO funcionarios (nome, cargo, salario) VALUES (?, ?, ?)";
            connection = conexao.getConnection();
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getCargo());
            ps.setBigDecimal(3, f.getSalario());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                f.setId(rs.getInt(1));
            }
        } finally {
            fecharRecursos();
        }
    }

    // READ (todos)
    public List<Funcionario> listarFuncionarios(Conexao conexao) throws SQLException {
        try {
            List<Funcionario> lista = new ArrayList<>();
            String sql = "SELECT id, nome, cargo, salario FROM funcionarios";
            connection = conexao.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setSalario(rs.getBigDecimal("salario"));
                lista.add(f);
            }
            return lista;
        } finally {
            fecharRecursos();
        }
    }

    // READ (por id)
    public Funcionario buscarPorId(Conexao conexao, int id) throws SQLException {
        try {
            String sql = "SELECT id, nome, cargo, salario FROM funcionarios WHERE id = ?";
            connection = conexao.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setSalario(rs.getBigDecimal("salario"));
                return f;
            }
            return null;
        } finally {
            fecharRecursos();
        }
    }

    // UPDATE
    public boolean atualizar(Conexao conexao, Funcionario f) throws SQLException {
        try {
            String sql = "UPDATE funcionarios SET nome = ?, cargo = ?, salario = ? WHERE id = ?";
            connection = conexao.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getCargo());
            ps.setBigDecimal(3, f.getSalario());
            ps.setInt(4, f.getId());
            return ps.executeUpdate() > 0;
        } finally {
            fecharRecursos();
        }
    }

    // DELETE
    public boolean excluir(Conexao conexao, int id) throws SQLException {
        try {
            String sql = "DELETE FROM funcionarios WHERE id = ?";
            connection = conexao.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } finally {
            fecharRecursos();
        }
    }

    private void fecharRecursos() throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (ps != null) {
            ps.close();
            ps = null;
        }
    }
}
