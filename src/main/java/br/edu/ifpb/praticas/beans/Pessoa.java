/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */

public class Pessoa{
    private String id;
    private String nome;
    private String email;
    private String senha;
    private List<Aposta> aposta;
    //se verdadeiro o usuário é administrador
    private boolean adm;
      
    public Pessoa() {
    }

    public Pessoa(String nome, String email, String senha, boolean adm) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.aposta = new ArrayList();
        this.adm = adm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Aposta> getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = (List<Aposta>) aposta;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    
}
