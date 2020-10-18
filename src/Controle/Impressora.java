/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.awt.Desktop;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.print.PrintService;

/**
 *
 * @author Bruno
 */
public class Impressora {
    
        public static List list_impressoras() {
        List list = new ArrayList();

        if (PrinterJob.lookupPrintServices() != null) {
            PrintService[] impressoras = PrinterJob.lookupPrintServices();

            for (int i = 0; i < impressoras.length; i++) {
                PrintService p = impressoras[i];
                
                list.add(p.getName());
            }
        }
        return list;
    }
        
    public void imprimir() throws IOException{
       /* Desktop desk = Desktop.getDesktop();
        Properties prop = new Properties();
        FileReader ler = new FileReader("./Properties/config.properties");
        prop.load(ler);
        File arquivo = new File(prop.getProperty("prop.url"));
        desk.print(arquivo);*/
        
    }
}
