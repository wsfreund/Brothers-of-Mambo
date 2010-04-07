

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
public class BotPrincipal {
	BotPrincipal() {
		jFrame.setVisible(true);
	}

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="87,14"
	private JPanel jContentPane = null;
	private JTextPane jTextPane = null;
	private JLabel jLabel = null;
	private JPasswordField Name = null;
	private JLabel jLabel1 = null;
	private JPasswordField Password = null;
	private JLabel jLabel2 = null;
	private JTextField ScriptName = null;
	private JToggleButton GoButton = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(215, 198));
			jFrame.setTitle("RoboT");
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Documents and Settings/Usuario/Desktop/icone.JPG"));
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Digite o nome da Script e aperte GO.");
			jLabel2.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 10));
			jLabel1 = new JLabel();
			jLabel1.setText("Entre com o Password do seu Boneco");
			jLabel1.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 10));
			jLabel = new JLabel();
			jLabel.setText("Entre com o Nome do seu Boneco");
			jLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 10));
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.add(getJTextPane(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getName(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getPassword(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getScriptName(), null);
			jContentPane.add(getGoButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setText("Bem Vindo Ao RoboT v.b");
			jTextPane.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
			jTextPane.setForeground(new Color(204, 204, 0));
		}
		return jTextPane;
	}

	/**
	 * This method initializes Name	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getName() {
		if (Name == null) {
			Name = new JPasswordField();
		}
		return Name;
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
	 * This method initializes ScriptName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getScriptName() {
		if (ScriptName == null) {
			ScriptName = new JTextField();
		}
		return ScriptName;
	}

	/**
	 * This method initializes GoButton	
	 * 	
	 * @return javax.swing.JToggleButton	
	 */
	private JToggleButton getGoButton() {
		if (GoButton == null) {
			GoButton = new JToggleButton();
			GoButton.setText("GO");
			GoButton.setAlignmentX(0.2F);
			GoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			GoButton.setHorizontalTextPosition(SwingConstants.CENTER);
			GoButton.setToolTipText("Só aperte este botão após ter entrado com TODOS os valores do campo acima corretamente.");
			GoButton.setBackground(new Color(153, 153, 153));
		}
		return GoButton;
	}

}
