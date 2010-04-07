import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import java.awt.Cursor;
import java.io.IOException;

public class RoboTPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextArea jTextArea = null;
	private JTextPane Nome = null;
	private JPasswordField Password = null;
	private JTextPane jTextPane = null;
	private JPasswordField LUGARONDEENTRAOPASS = null;
	private JTextArea jTextArea1 = null;
	private JTextField NomeDaScript = null;
	private JTextPane jTextPane1 = null;
	private JButton Startbutton = null;
	private JTextPane jTextPane2 = null;
	private JTextField LogFileLocation = null;
	/**
	 * This is the default constructor
	 */
	public RoboTPanel() {
		super();
		initialize();
		setVisible(true);
		setAlwaysOnTop(true);
	}
	//TODO adicione aqui o nome das Scripts, e o nome da Script nova.
	private String getScriptsNames() {
		return "Opções: 1)MuggerSlayer";
	}
	private void runScript(String scriptName) {
		ScreenGetter.set();
		if (scriptName.equals("MuggerSlayer")) {
			MuggerSlayer mug = new MuggerSlayer();
			mug.run();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 359); //TODO mudar icone para dentro do projeto.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		this.setBackground(new Color(153, 0, 153));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Documents and Settings/Usuario/Desktop/icone.JPG"));
		this.setContentPane(getJContentPane());
		this.setTitle("RoboT");
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.add(getJTextArea(), null);
			jContentPane.add(getNome(), null);
			jContentPane.add(getPassword(), null);
			jContentPane.add(getJTextPane(), null);
			jContentPane.add(getLUGARONDEENTRAOPASS(), null);
			jContentPane.add(getJTextArea1(), null);
			jContentPane.add(getNomeDaScript(), null);
			jContentPane.add(getJTextPane2(), null);
			jContentPane.add(getLogFileLocation(), null);
			jContentPane.add(getJTextPane1(), null);
			jContentPane.add(getStartbutton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setText("Bem Vindo ao RoboT o pior BOT jamais feito.");
			jTextArea.setForeground(new Color(153, 153, 0));
		}
		return jTextArea;
	}

	/**
	 * This method initializes Nome	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getNome() {
		if (Nome == null) {
			Nome = new JTextPane();
			Nome.setText("Entre abaixo com o nome do  seu boneco:");
			Nome.setEditable(false);
		}
		return Nome;
	}

	/**
	 * This method initializes Password	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPassword() {
		if (Password == null) {
			Password = new JPasswordField();
		}
		return Password;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setText("Entre abaixo com o password do seu boneco");
			jTextPane.setEditable(false);
		}
		return jTextPane;
	}

	/**
	 * This method initializes LUGARONDEENTRAOPASS	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getLUGARONDEENTRAOPASS() {
		if (LUGARONDEENTRAOPASS == null) {
			LUGARONDEENTRAOPASS = new JPasswordField();
		}
		return LUGARONDEENTRAOPASS;
	}

	/**
	 * This method initializes jTextArea1	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1.setText("Entre com o nome da Script:");
			jTextArea1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			jTextArea1.setEditable(false);
		}
		return jTextArea1;
	}

	/**
	 * This method initializes NomeDaScript	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomeDaScript() {
		if (NomeDaScript == null) {
			NomeDaScript = new JTextField();
			NomeDaScript.setToolTipText(getScriptsNames());
		}
		return NomeDaScript;
	}

	/**
	 * This method initializes jTextPane1	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane1() {
		if (jTextPane1 == null) {
			jTextPane1 = new JTextPane();
			jTextPane1.setText("Tudo pronto? Deixe a tela do runescape a amostra e click em Start");
			jTextPane1.setEditable(false);
			jTextPane1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		return jTextPane1;
	}

	/**
	 * This method initializes Startbutton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStartbutton() {
		if (Startbutton == null) {
			Startbutton = new JButton();
			Startbutton.setText("START SCRIPT");
			Startbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			Startbutton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					RuneMethods.setPlayerName(getPassword().getText());
					RuneMethods.setPlayerPass(getLUGARONDEENTRAOPASS().getText());
					try {
						RuneMethods.setLogToFile(getLogFileLocation().getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setVisible(false);
					setAlwaysOnTop(false);
					runScript(getNomeDaScript().getText());
					

					
				}
			});
		}
		return Startbutton;
	}
	/**
	 * This method initializes jTextPane2	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane2() {
		if (jTextPane2 == null) {
			jTextPane2 = new JTextPane();
			jTextPane2.setText("Escreva o lugar onde será deixado o log(obs: use // ao invés de /). Ex C://");
			jTextPane2.setEditable(false);
		}
		return jTextPane2;
	}
	/**
	 * This method initializes LogFileLocation	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLogFileLocation() {
		if (LogFileLocation == null) {
			LogFileLocation = new JTextField();
			LogFileLocation.setToolTipText("Não esqueça de trocar \"/\" por \"//\"!!!");
		}
		return LogFileLocation;
	}

}  //  @jve:decl-index=0:visual-constraint="66,1"
