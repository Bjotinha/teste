
package Controle;

import Modelo.DataFormato;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class EditaArquivo {
   
    public void reescrever() throws IOException{
        DataFormato df = new DataFormato();
        df.formatData();
        df.formatHora();    
        //carregando properties
        Properties prop = new Properties();
        FileReader ler = new FileReader("./Properties/config.properties");
        prop.load(ler);
        //pegando key no properties
        File arquivo = new File(prop.getProperty("prop.url"));
        //olhando dentro do arquivo
        InputStream dentro = new FileInputStream(arquivo); 
        //lendo
        InputStreamReader lendo = new InputStreamReader(dentro);
        //carregando
        BufferedReader carregando = new BufferedReader(lendo);
        String a = carregando.readLine();
        while(a != null){
            a = carregando.readLine();
        }
        System.out.println(a);
    }
}
