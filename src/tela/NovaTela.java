package tela;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controle.PortaSerial;
import controle.PropertiesReader;

public class NovaTela extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		NovaTela field = new NovaTela();
		field.testaJFormattedTextField();
	}

	JTextField jTxtUrl = new JTextField();
	JTextField jTxtSerial = new JTextField();
	JTextField jTxtTag = new JTextField();

	JLabel labelTag = new JLabel("Tag Replace: ");
	JLabel labelSerial = new JLabel("Porta Serial: ");

	JLabel labelComunicaoOk = new JLabel("Sem comunicação...");
	JButton btnSalvar = new JButton();
	JButton jBarquivo = new JButton();
	JButton jComunicar = new JButton();
	JButton jImprimir = new JButton();

	private void testaJFormattedTextField() {
		Container janela = getContentPane();
		setLayout(null);

		carregarConteudoComponentes();

		jBarquivo.setText("Arquivo");
		jBarquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jBarquivoActionPerformed(evt);
			}
		});

		btnSalvar.setText("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jBsalvarActionPerformed(evt);
			}
		});

		jComunicar.setText("Iniciar Comunicação");
		jComunicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				comunicar();
			}
		});

		jImprimir.setText("Imprimir");
		jImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				imprimir();
			}

		});

		// Define os rótulos dos botões
		jBarquivo.setBounds(50, 40, 80, 20);
		labelTag.setBounds(50, 80, 100, 20);
		labelSerial.setBounds(50, 120, 100, 20);
		// labelData.setBounds(50, 160, 100, 20);

		jTxtUrl.setBounds(150, 40, 350, 20);
		jTxtTag.setBounds(150, 80, 350, 20);
		jTxtSerial.setBounds(150, 120, 350, 20);
		// jFormattedTextData.setBounds(150, 160, 100, 20);

		btnSalvar.setBounds(50, 170, 150, 20);
		jImprimir.setBounds(210, 170, 130, 20);
		jComunicar.setBounds(350, 170, 150, 20);

		labelComunicaoOk.setBounds(200, 220, 150, 20);
		// Adiciona os rótulos e os campos de textos com máscaras na tela
		janela.add(jBarquivo);
		janela.add(labelTag);
		janela.add(labelSerial);
		janela.add(jTxtUrl);
		janela.add(jTxtTag);
		janela.add(jTxtSerial);
		janela.add(btnSalvar);
		janela.add(jComunicar);
		janela.add(jImprimir);
		janela.add(labelComunicaoOk);
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void carregarConteudoComponentes() {
		try {
			PropertiesReader pr = PropertiesReader.getInstance();
			jTxtUrl.setText(pr.getUrlArquivoZPL());
			jTxtSerial.setText(pr.getPortaSerial());
			jTxtTag.setText(pr.getTagReplace());

//			Impressora imp = new Impressora();
//			for(String impressora: imp.getListaImpressoras()) {
//				jCimpressora.addItem(impressora);
//			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro leitura Arquivo properties " + e);
		}

	}

	private void jBarquivoActionPerformed(ActionEvent evt) {

		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo", "zpl", "ZPL", "Zpl", "zPl", "zpL");
		fc.setFileFilter(filter);
		fc.showOpenDialog(this);
		File f = fc.getSelectedFile();
		if (f != null) {
			jTxtUrl.setText(f.getPath());
		}
	}

	private void jBsalvarActionPerformed(ActionEvent evt) {
		if (jTxtUrl.getText() == null || jTxtUrl.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione a porta Serial");
			return;
		}

		if (jTxtSerial.getText() == null || jTxtSerial.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione a porta Serial");
			return;
		}

		if (jTxtTag.getText() == null || jTxtTag.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione a tag para substituir");
			return;
		}

		try {
			PropertiesReader mp = PropertiesReader.getInstance();
			mp.setUrlArquivoZPL(jTxtUrl.getText());
			mp.setPortaSerial(jTxtSerial.getText());
			mp.setTagReplace(jTxtTag.getText());
			mp.atualizarArquivoProperties();
			JOptionPane.showMessageDialog(null, "Atualizações salvas!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERRO " + ex);
		}
	}

	private void comunicar() {

		PortaSerial portaSerial = new PortaSerial();
		try {
			portaSerial.connect();
			labelComunicaoOk.setText("Comunicaçao iniciada...");
			JOptionPane.showMessageDialog(null, "Comunicação inicializada!");
		} catch (Exception e) {
			labelComunicaoOk.setText("ERRO " + e);
			JOptionPane.showMessageDialog(null, "ERRO " + e);
		}
	}

	private void imprimir() {
		new PortaSerial().comunicacao();
	}

}