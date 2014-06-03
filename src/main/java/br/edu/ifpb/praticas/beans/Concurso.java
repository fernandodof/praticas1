/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.praticas.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernando
 */
@Entity
@SequenceGenerator(name = "seq_concurso", sequenceName = "Sequencia_de_concurso", allocationSize = 1, initialValue = 0)
@NamedQueries({
    @NamedQuery(name="Concurso.proximos", query = "SELECT c FROM Concurso c WHERE c.dataHora > CURRENT_TIMESTAMP and c.realizado = FALSE")})
public class Concurso implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_concurso")
    private Long id;
    private SortedSet numeros;
    @Column(unique = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    private boolean realizado;
    
    public Concurso() {
    }

    public Concurso(Date dataHora) {
        this.dataHora = dataHora;
        this.realizado = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

