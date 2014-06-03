/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.testes;

import br.edu.ifpb.praticas.gerador.GeradorDeNumeros;
import java.util.SortedSet;

/**
 *
 * @author Fernando
 */
public class TesteNumeros {

    public boolean testaNumerosDiferentes() {
        SortedSet numeros = GeradorDeNumeros.getSeisNumerosEntreUmESessenta();
        for (Object object : numeros) {
            if(!numeros.contains(object)){
                return false;
            }
        }
        return true;
    }
}
