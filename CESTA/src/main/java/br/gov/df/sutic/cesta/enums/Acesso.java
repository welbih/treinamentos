/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.enums;

/**
 *
 * @author welber.fernandes
 */
public enum Acesso {
    ROLE_ADMINISTRADOR("Administrador"),
    ROLE_USUARIO("Usuario");
    
    private String nome;
    
    Acesso(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
