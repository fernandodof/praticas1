
import br.edu.ifpb.praticas.beans.Aposta;
import br.edu.ifpb.praticas.beans.Concurso;
import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.ApostaDAO;
import br.edu.ifpb.praticas.dao.ConcursoDAO;
import br.edu.ifpb.praticas.dao.PessoaDAO;
import br.edu.ifpb.praticas.exceptions.ErroAconteceuException;
import br.edu.ifpb.praticas.gerador.GeradorDeNumeros;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        PessoaDAO pessoaDAO = new PessoaDAO();
        ConcursoDAO concursoDAO = new ConcursoDAO();
        ApostaDAO apostaDAO = new ApostaDAO();
        Pessoa pessoa = new Pessoa("Fernando", "fernando@gmail.com", "123456", true);
//        Aposta aposta = new Aposta(GeradorDeNumeros.getSeisNumerosEntreUmESessenta());
        try {
          pessoaDAO.excluirConta("#13:8", "123456");
            //concursoDAO.getById("#15:4");
//            Aposta aposta = new Aposta(GeradorDeNumeros.getSeisNumerosEntreUmESessenta());
//            apostaDAO.insert(aposta);
            //pessoaDAO.insert(pessoa);
//            pessoa = pessoaDAO.getById("#13:3");
//            
//            List<Aposta> apostas = pessoa.getApostas();
//            for (Aposta aposta : apostas) {
//                System.out.println(aposta);
//            }
            //System.out.println(apostas.size());
//            Concurso concurso = concursoDAO.getById("#13:7");
//            System.out.println(concurso.getDataHora());
//            System.out.println(concurso.getId());
//            System.out.println(concurso.getNumeros());
//            Pessoa pessoa = pessoaDAO.getById((String) "#12:18");
//            System.out.println(pessoa.getId());
//            Pessoa pessoa = pessoaDAO.getById("#12:18");
//            System.out.println(pessoa.getNome());
//           apostaDAO.insert(aposta);
//            Concurso concurso = concursoDAO.getProximoConcurso();
//            System.out.println(concurso.getDataHora());
//            System.out.println(pessoaDAO.insert(pessoa));
//            Pessoa pessoa = pessoaDAO.logar("fernandodof@gmail.com", "123456");
//            System.out.println(pessoa.getNome());
//            System.out.println(pessoa.getId());
//            System.out.println(pessoa.isAdm());
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ErroAconteceuException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
