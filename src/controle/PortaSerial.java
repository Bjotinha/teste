
package controle;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class PortaSerial {
	private CommPort commPort;
	private SerialPort serialPort;

	public synchronized void comunicacao() {

		ConteudoArquivo conteudoArquivo = new ConteudoArquivo();
		try {
			String conteudo = conteudoArquivo.getConteudoCompleto();
			Impressora impressora = new Impressora();
			impressora.imprimir(conteudo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect() throws Exception {
		String portName = PropertiesReader.getInstance().getPortaSerial();
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			throw new Exception("Error: Port is currently in use");
		} else {
			commPort = portIdentifier.open(this.getClass().getName(), 2000);
			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;

				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				InputStream in = serialPort.getInputStream();

				(new Thread(new SerialReader(in))).start();

			} else {
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
					if (value != null && value.contains("A")) {
						comunicacao();
						serialPort.close();
						commPort.close();
						wait(500);
						connect();
					}
				}
			} catch (IOException e) {
				System.out.println("Error: Only serial ports are handled by this example." +e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Error: Only serial ports are handled by this example." +e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
