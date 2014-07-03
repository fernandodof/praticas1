/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.praticas.beans;

import java.util.Date;
import java.util.SortedSet;

/**
 *
 * @author Fernando
 */

public class Concurso{
    private String id;
    private SortedSet numeros;
    private Date dataHora;
    private boolean realizado;
    
    public Concurso() {
    }

    public Concurso(Date dataHora) {
        this.dataHora = dataHora;
        this.realizado = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SortedSet getNumeros() {
        return numeros;
    }

    public void setNumeros(SortedSet numeros) {
        this.numeros = numeros;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

}

