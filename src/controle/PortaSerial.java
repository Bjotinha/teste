
package controle;


import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;


 
import gnu.io.*;


public class PortaSerial {

	private String[] namePort;
	private String[] tipoPort;
	Enumeration listPort;

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
			System.out.println("Error: Port is currently in use");
		} else {
			CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
                                
                                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);


				InputStream in = serialPort.getInputStream();

				(new Thread(new SerialReader(in))).start();

			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
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
			int len= 0;
                        
			try {
                                while ((len =this.in.read(buffer))!=0 ) {
                                    System.out.println(len);
                                    System.out.println(buffer);
                                       // comunicacao();
					break;
                                    
				}
			} catch (IOException e) {
				System.out.println("Error: Only serial ports are handled by this example.");
				e.printStackTrace();
			}
		}
	}

}
/*int len = -1;
			try {
				while ((len = this.in.read(buffer)) > -1) {
					comunicacao();
					break;
				}*/