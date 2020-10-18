
package Controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
public class ManipulandoProperties {
    
    private String url, serial, impressora;

        
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"./Properties/config.properties");              
		props.load(file);
		return props;
        }

        public void setProp() throws IOException{
            Properties prop = new Properties();
            
            prop.setProperty("prop.url", url.toString());
            prop.setProperty("prop.serial", serial.toString());
            prop.setProperty("prop.impressora", impressora.toString());
            File file = new File("./Properties/config.properties");
            FileOutputStream fos = new FileOutputStream(file);
           // PrintWriter pr= new PrintWriter(file);
           // pr.println(url);
           // pr.println(serial);
           // pr.println(impressora);
            prop.store(fos, "UTF-8");
            fos.close();
           // pr.close();
            
        }
        
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getImpressora() {
        return impressora;
    }

    public void setImpressora(String impressora) {
        this.impressora = impressora;
    }
    
                
 
}
