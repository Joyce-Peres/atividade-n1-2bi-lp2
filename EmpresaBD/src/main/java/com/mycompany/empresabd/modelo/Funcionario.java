/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empresabd.modelo;

import java.math.BigDecimal;
/**
 *
 * @author 081240037
 */
public class Funcionario {
    private int id;
    private String nome;
    private String cargo;
    private BigDecimal salario;
    
    public Funcionario(){
        
    }
    public Funcionario(String nome, String cargo, BigDecimal salario){
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", salario=" + salario + '}';
    }
    
}
