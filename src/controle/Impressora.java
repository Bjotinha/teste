/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.Desktop;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

/**
 *
 * @author Bruno
 */
public class Impressora {

	public List<String> getListaImpressoras() {
		List<String> listaImpressoras = new ArrayList<String>();

		PrintService[] printServices = PrinterJob.lookupPrintServices();
		for (PrintService printService : printServices) {
			String nomeImpressora = printService.getName();
			listaImpressoras.add(nomeImpressora);
		}
		return listaImpressoras;
	}
	
	public void imprimir(String conteudo) throws Exception {
		File f = new File("impressaoTemp.txt");
		PrintWriter out = new PrintWriter(f);
		out.print(conteudo);
		out.close();
		Desktop.getDesktop().print(f);
	}
	
	@Deprecated
	public void imprimiiNaoFunciona(String conteudo) throws Exception {

		PrintService printService = getImpressora();
		
		InputStream stream = new ByteArrayInputStream(conteudo.getBytes());
		PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();  
		printerAttributes.add(new Copies(1)); 
		
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		HashDocAttributeSet attributes = new HashDocAttributeSet();
		Doc doc = new SimpleDoc(stream, flavor, attributes);
		
		DocPrintJob dpj = printService.createPrintJob();
		dpj.print(doc, printerAttributes);
	   
	}


	private PrintService getImpressora() throws Exception {
		String impressoraDefault = PropertiesReader.getInstance().getImpressoraPadrao();

		if (impressoraDefault == null || impressoraDefault.isEmpty()) {
			throw new Exception("Nenhuma impressora default configurada, processo de impressão cancelado.");
		}

		PrintService[] printServices = PrinterJob.lookupPrintServices();
		for (PrintService printService : printServices) {
			if (printService.getName().equals(impressoraDefault)) {
				return printService;
			}
		}

		throw new Exception(
				"Impressora default configurada não foi encontrada no windows, verifique suas configurações\nImpressora Padrao: "
						+ impressoraDefault);
	}

	

}
