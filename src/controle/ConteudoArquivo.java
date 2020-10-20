
package controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConteudoArquivo {

	public String getConteudoCompleto() throws IOException {
		String conteudo = getConteudoArquivo();
		String dataFormatada = getDataFormatada();
		String tagReplace = PropertiesReader.getInstance().getTagReplace();
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
