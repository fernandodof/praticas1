
import br.edu.ifpb.praticas.beans.Pessoa;
import bt.edu.ifpb.praticas.dao.GenericoDAO;
import bt.edu.ifpb.praticas.dao.GenericoDAOJPA;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.spi.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
public class App {

    public static void main(String[] args) {
        //GenericoDAOJPA genericoDAOJPA = new GenericoDAOJPA();
        GenericoDAO genericoDAO = new GenericoDAOJPA();

        Pessoa pessoa = new Pessoa("Fernando");
        genericoDAO.salvar(pessoa);

    }
}
