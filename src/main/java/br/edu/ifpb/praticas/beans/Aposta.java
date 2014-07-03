package br.edu.ifpb.praticas.beans;

import java.util.Set;
import java.util.SortedSet;


/**
 *
 * @author Fernando
 */
public class Aposta {
    private String id;
    private SortedSet numeros;
    private Concurso concurso;
    
    public Aposta() {
    }

    public Aposta(SortedSet numeros) {
        this.numeros = numeros;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set getNumeros() {
        return numeros;
    }

    public void setNumeros(SortedSet numeros) {
        this.numeros = numeros;
    }
    
    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
    
    public Set getResultado(){
        return this.concurso.getNumeros();
    }
    
}
