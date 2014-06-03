/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.testes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fernando
 */
public class ValidaData {

    public boolean validaData(String data) {
        if (data != null) {
            Pattern pattern = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
            Matcher matcher = pattern.matcher(data);

            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

}
