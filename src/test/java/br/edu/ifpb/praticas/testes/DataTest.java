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
public class DataTest {
    @Test
    public void testDataValida(){
        ValidaData validaData = new ValidaData();
        assertEquals(validaData.validaData("2014-06-03"), true);
    }
    
    @Test
    public void testDataInvalida(){
        ValidaData validaData = new ValidaData();
        assertEquals(validaData.validaData("2014-06-"), false);
    }
}
