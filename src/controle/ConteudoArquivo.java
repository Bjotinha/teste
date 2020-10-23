
package controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class ConteudoArquivo {
	final static Logger logger = Logger.getLogger(ConteudoArquivo.class);

	public String getConteudoCompleto() throws IOException {
		logger.info("obtendo conteudo do arquivo...");
		String conteudo = getConteudoArquivo();
		logger.info("obtendo conteudo do arquivo...");
		String dataFormatada = getDataFormatada();
		logger.info("dataFormatada="+dataFormatada);
		String tagReplace = PropertiesReader.getInstance().getTagReplace();
		logger.info("tagReplace="+tagReplace);
		return conteudo.replace(tagReplace, dataFormatada);
	}

	private String getConteudoArquivo() throws IOException {
		String urlArquivo = PropertiesReader.getInstance().getUrlArquivoZPL();
		FileReader fileReader = new FileReader(urlArquivo);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String linha;
		StringBuilder arquivoCompleto = new StringBuilder();
		while ((linha = bufferedReader.readLine()) != null) {
			arquivoCompleto.append(linha).append("\r\n");
		}
		bufferedReader.close();
		fileReader.close();

		return arquivoCompleto.toString();
	}

	private String getDataFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
