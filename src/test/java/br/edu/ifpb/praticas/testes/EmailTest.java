
package br.edu.ifpb.praticas.testes;

import static br.edu.ifpb.praticas.testes.ValidaEmail.isEmailValido;
import static org.junit.Assert.*;
import org.junit.Test;


public class EmailTest {
    
   @Test
   public void testEmailVazio(){
        assertEquals(isEmailValido(""), false);
   }
   
   @Test
   public void testEmailNull(){
       assertEquals(isEmailValido(null), false);
   }
   
   @Test
   public void testEmailInvalidoSemPonto(){
       assertEquals(isEmailValido("dudalira@outlook"), false);
   }
   
   @Test
   public void testEmailInvalidoSemArroba(){
       assertEquals(isEmailValido("dudalira.com"), false);
   }
   
   @Test
   public void testEmailSemArrobaSemPonto(){
       assertEquals(isEmailValido("dudalira"), false);
   }
   
   @Test
   public void testEmailIncompleto(){
       assertEquals(isEmailValido("dudalira@outlook.c"), false);
       assertEquals(isEmailValido("dudalira@bol.oom.b"), false);
   }
   
   @Test
   public void testEmailOK(){
       assertEquals(isEmailValido("dudalira@bol.oom.br"), true);
   }
   
   
    
}
