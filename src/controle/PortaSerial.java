
package controle;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class PortaSerial {
	final static Logger logger = Logger.getLogger(PortaSerial.class);
	private CommPort commPort;
	private SerialPort serialPort;

	public synchronized void comunicacao() {

		ConteudoArquivo conteudoArquivo = new ConteudoArquivo();
		try {
			String conteudo = conteudoArquivo.getConteudoCompleto();
			Impressora impressora = new Impressora();
			impressora.imprimir(conteudo);
		} catch (IOException e) {
			logger.error("Erro ao enviar arquivo para impressao", e);
		} catch (Exception e) {
			logger.error("Erro ao enviar arquivo para impressao 2", e);
		}
	}

	public void connect() throws Exception {
		String portName = PropertiesReader.getInstance().getPortaSerial();
		logger.info("portName="+portName);
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		logger.info("portIdentifier="+portIdentifier);
		if (portIdentifier.isCurrentlyOwned()) {
			logger.error("Error: Port is currently in use");
			throw new Exception("Error: Port is currently in use");
		} else {
			logger.info("Abrindo porta serial...");
			commPort = portIdentifier.open(this.getClass().getName(), 2000);
			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;

				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
				logger.info("Obtendo Stream...");
				InputStream in = serialPort.getInputStream();
				logger.info("Iniciando thread de comunicação...");
				(new Thread(new SerialReader(in))).start();

			} else {
				logger.error("Error: Only serial ports are handled by this example.");
				throw new Exception("Error: Only serial ports are handled by this example.");
			}
		}
	}

	public class SerialReader implements Runnable {
		InputStream in;

		public SerialReader(InputStream in) {
			this.in = in;
		}

		public void run() {
			byte[] buffer = new byte[1024];
			try {
				while (this.in.read(buffer) > -1) {
					String value = new String(buffer);
					logger.info("while comunicação porta -> "+ value);
					if (value != null && value.contains("A")) {
						logger.info("Recebido caracter A partindo para impressao");
						comunicacao();
						logger.info("fechando serial");
						serialPort.close();
						logger.info("fechando comunicação com a porta");
						commPort.close();
						logger.info("conectando novamente");
						connect();
						break;
					}
				}
			} catch (IOException e) {
				logger.error("primeiro cath da classe serialReader",e);
			} catch (Exception e) {
				logger.error("erro no segundo cath da classe serialReader",e);
			}
		}
	}

}
