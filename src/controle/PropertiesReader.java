
package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class PropertiesReader {
    private static final String FILE_PROPERTIES = "config.properties";

	private static final String URL_ARQUIVO_ZPL = "url.arquivo.zpl";
	private static final String PORTA_SERIAL = "porta.serial";
	private static final String IMPRESSORA_PADRAO = "impressora.padrao";
	private static final String TAG_REPLACE = "tag.replace";

	private static PropertiesReader instance;
	private final Properties properties;


	public static PropertiesReader getInstance() throws IOException {
		if (instance == null) {
			instance = new PropertiesReader();
		}
		return instance;
	}

	private PropertiesReader() throws IOException {
		properties = new Properties();
		properties.load(getInputStream());
	}

	public void atualizarArquivoProperties() throws IOException {
		PrintWriter out = new PrintWriter(new File(FILE_PROPERTIES));
		properties.store(out, "Gravando");
		out.close();
	}

	private InputStream getInputStream() {
		return PropertiesReader.class.getClassLoader().getResourceAsStream(FILE_PROPERTIES);
	}

	private String getProperties(String key) {
		return this.properties.getProperty(key);
	}

	private void setProperties(String key, String value) {
		this.properties.setProperty(key, value);
	}

	public void setUrlArquivoZPL(String value) {
		setProperties(URL_ARQUIVO_ZPL, value);
	}

	public void setPortaSerial(String value) {
		setProperties(PORTA_SERIAL, value);
	}

	public void setImpressoraPadrao(String value) {
		setProperties(IMPRESSORA_PADRAO, value);
	}

	public void setTagReplace(String value) {
		setProperties(TAG_REPLACE, value);
	}

	public String getUrlArquivoZPL() {
		return getProperties(URL_ARQUIVO_ZPL);
	}

	public String getPortaSerial() {
		return getProperties(PORTA_SERIAL);
	}

	public String getImpressoraPadrao() {
		return getProperties(IMPRESSORA_PADRAO);
	}
	
	public String getTagReplace() {
		return getProperties(TAG_REPLACE);
	}

}
