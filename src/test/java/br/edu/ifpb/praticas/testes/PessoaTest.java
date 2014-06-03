
package br.edu.ifpb.praticas.testes;

import br.edu.ifpb.praticas.beans.Pessoa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Duda Lira
 */
public class PessoaTest {
    Pessoa pessoa;
    
    @Before
    public void criaPessoa(){
       pessoa = new Pessoa("Amanda", "amanda@gmail.com", "123", false);
    }
    
    @Test
    public void testaPessoa(){
        Assert.assertEquals("Não é essa pessoa!", "Amanda", pessoa.getNome());
    }
    
}
