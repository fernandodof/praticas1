
import br.edu.ifpb.praticas.beans.Aposta;
import br.edu.ifpb.praticas.beans.Concurso;
import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.GenericoDAO;
import br.edu.ifpb.praticas.dao.GenericoDAOJPA;
import br.edu.ifpb.praticas.gerador.GeradorDeNumeros;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
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
        GenericoDAO genericoDAO = new GenericoDAOJPA();
        Pessoa pessoa = new Pessoa("Fernando", "fernandodof@gmail.com", "123456", false);
        Concurso concurso = new Concurso(Date.valueOf("2014-06-03"));
//        genericoDAO.save(pessoa);
//        genericoDAO.save(concurso);
        
        System.out.println(genericoDAO.getSingleResultOfNamedQuery("Concurso.proximos"));
//        Map<String, Object> loginParms = new HashMap();
//        loginParms.put("email", "fernandodof@gmail.com");
//        loginParms.put("senha", "123456");
//        pessoa = (Pessoa) genericoDAO.getSingleResultOfNamedQuery("Pessoa.login",loginParms);
//        
//        System.out.println(pessoa.getEmail());
       
//        pessoa = (Pessoa) genericoDAO.getById(Pessoa.class, 1);
//        List<Aposta> apostas = pessoa.getAposta();
//        for (Aposta aposta : apostas) {
//            System.out.println(aposta.getNumeros());
//        }
////        
//        Pessoa pessoa1 = (Pessoa) genericoDAO.getById(Pessoa.class, 1);
//        Aposta aposta = new Aposta(GeradorDeNumeros.getSeisNumerosEntreUmESessenta());
//
//        pessoa1.getAposta().add(aposta);
//        Iterator it = aposta.getNumeros().iterator();
//        System.out.println("Imprimindo numeros gerados");
//        while (it.hasNext()) {
//            Object object = it.next();
//            System.out.println(object);
//        }
//
//        genericoDAO.update(pessoa1);
//        System.out.println("Imprimindo tudo do banco");
//        pessoa = (Pessoa) genericoDAO.getById(Pessoa.class, 1);
//        List<Aposta> apostas1 = pessoa.getAposta();
//        Set numbers;
//        for (Aposta aposta1 : apostas) {
//            numbers = aposta1.getNumeros();
//            Iterator it1 = numbers.iterator();
//            while (it1.hasNext()) {
//                Object object = it1.next();
//                System.out.println(object);
//            }
//        }
    }
}
