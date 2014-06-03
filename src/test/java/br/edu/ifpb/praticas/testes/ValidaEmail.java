
package br.edu.ifpb.praticas.testes;

import java.util.StringTokenizer;

/**
 *
 * @author Duda Lira
 */
class ValidaEmail {

    public static boolean isEmailValido(String email) {
        if(email == null)
            return false;
        
        if(!email.contains("@"))
            return false;
        if(!email.contains("."))
            return false;
        if(ultimoCampo(email) == false)
            return false;
        return true;
    }

    private static boolean ultimoCampo(String email) {
        if(email == null)
            return false;
        StringTokenizer st = new StringTokenizer(email, ".");
        String ultimoToken = null;
        while (st.hasMoreTokens()) {
            ultimoToken = st.nextToken();            
        }
        if(ultimoToken.length() >= 2){
            return true;
        }
        else
            return false;
    }
    
}
