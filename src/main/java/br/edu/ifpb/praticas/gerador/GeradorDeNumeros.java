/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.gerador;

import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Fernando
 */
public class GeradorDeNumeros {

    private static Random r = new Random();
       
    public static SortedSet getSeisNumerosEntreUmESessenta() {
        int high = 60;
        SortedSet numbers = new TreeSet();
        for (int i = 0; i < 6; i++) {
            numbers.add(r.nextInt(high) + 1);
        }
        return numbers;
    }

}
