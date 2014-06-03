package br.edu.ifpb.praticas.beans;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


/**
 *
 * @author Fernando
 */
@Entity
@SequenceGenerator (name = "seq_aposta", sequenceName = "Sequencia_de_Aposta", allocationSize = 1, initialValue = 0)
public class Aposta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aposta")
    private int id;
    private Set numeros;
    @ManyToOne(cascade = CascadeType.ALL)
    private Concurso concurso;
    
    public Aposta() {
    }

    public Aposta(Set numeros) {
        this.numeros = numeros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set getNumeros() {
        return numeros;
    }

    public void setNumeros(Set numeros) {
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
