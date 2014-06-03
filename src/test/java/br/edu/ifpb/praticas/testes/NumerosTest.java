/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.praticas.testes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando
 */
public class NumerosTest {
    @Test
    public void testNumeros(){
        TesteNumeros testeNumeros = new TesteNumeros();
        assertEquals(testeNumeros.testaNumerosDiferentes(), true);
    }
    
    
}
