package com.mycompany.empresabd;

import com.mycompany.empresabd.jdbc.Conexao;
import com.mycompany.empresabd.jdbc.FuncionarioDAO;
import com.mycompany.empresabd.modelo.Funcionario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class EmpresaBD {

    private static void imprimir(List<Funcionario> lista) {
        for (Funcionario f : lista) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        Conexao conexao = null;
        try {
            conexao = Conexao.getConexao();
            System.out.println("Conectado!");
            FuncionarioDAO dao = new FuncionarioDAO();

            System.out.println("\n--- INSERIR ---");
            Funcionario novo = new Funcionario("Mel", "Programador Pleno I", new BigDecimal("7345.44"));
            dao.inserir(conexao, novo);
            System.out.println("Inserido com id " + novo.getId());
            Funcionario segundo = new Funcionario("Caio", "Analista de Dados", new BigDecimal("5200.00"));
            dao.inserir(conexao, segundo);

            System.out.println("\n--- LISTAR ---");
            imprimir(dao.listarFuncionarios(conexao));

            System.out.println("\n--- BUSCAR POR ID (" + novo.getId() + ") ---");
            Funcionario encontrado = dao.buscarPorId(conexao, novo.getId());
            System.out.println(encontrado != null ? encontrado : "Funcionario nao encontrado.");

            System.out.println("\n--- ATUALIZAR ---");
            if (encontrado == null) {
                return;
            }
            encontrado.setCargo("Programador Senior");
            encontrado.setSalario(new BigDecimal("9800.00"));
            System.out.println(dao.atualizar(conexao, encontrado) ? "Atualizado!" : "Nada atualizado.");
            System.out.println(dao.buscarPorId(conexao, encontrado.getId()));

            System.out.println("\n--- EXCLUIR ---");
            System.out.println(dao.excluir(conexao, novo.getId()) ? "Excluido!" : "Nada excluido.");
            dao.excluir(conexao, segundo.getId()); // limpa a tabela para poder reexecutar

            System.out.println("\n--- LISTAR FINAL ---");
            List<Funcionario> restantes = dao.listarFuncionarios(conexao);
            if (restantes.isEmpty()) {
                System.out.println("Nenhum funcionario cadastrado.");
            }
            imprimir(restantes);

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) {
                    conexao.fechar();
                    System.out.println("Encerrado!");
                }
            } catch (SQLException e) {
                System.out.println("Falha ao fechar: " + e.getMessage());
            }
        }
    }
}
