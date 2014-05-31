/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.praticas.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Fernando
 */
@Entity
@SequenceGenerator(name = "seq_concurso", sequenceName = "Sequencia_de_concurso", allocationSize = 1, initialValue = 0)
public class Concurso implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_concurso")
    private Long id;
    private Set numeros;
    private Date data; 

    public Concurso() {
    }

    public Concurso(Set numeros) {
        this.numeros = numeros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set getNumeros() {
        return numeros;
    }

    public void setNumeros(Set numeros) {
        this.numeros = numeros;
    }
    
}

