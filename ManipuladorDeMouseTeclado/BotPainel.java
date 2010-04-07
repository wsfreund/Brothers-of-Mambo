

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JWindow;
import java.awt.BorderLayout;
public class BotPainel {

	private JPanel StephanRobot = null;  //  @jve:decl-index=0:visual-constraint="5,2"
	private JTextPane InfoPanel = null;
	private JLabel NameLabel = null;
	private JPasswordField passFieldName = null;
	private JLabel Passabel = null;
	private JPasswordField passFielPassword = null;
	/**
	 * This method initializes StephanRobot	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStephanRobot() {
		if (StephanRobot == null) {
			NameLabel = new JLabel();
			NameLabel.setBounds(new Rectangle(0, 45, 166, 16));
			NameLabel.setText("Entre com o seu nome: ");
			StephanRobot = new JPanel();
			StephanRobot.setLayout(null);
			StephanRobot.setSize(new Dimension(362, 348));
			StephanRobot.add(getInfoPanel(), null);
			StephanRobot.add(NameLabel, null);
			StephanRobot.add(getPassFieldName(), null);
			StephanRobot.add(getPassabel(), null);
			StephanRobot.add(getPassFielPassword(), null);
		}
		return StephanRobot;
	}
	/**
	 * This method initializes InfoPanel	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getInfoPanel() {
		if (InfoPanel == null) {
			InfoPanel = new JTextPane();
			
			
			InfoPanel.setBounds(new Rectangle(0, 0, 361, 30));
			InfoPanel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
			InfoPanel.setForeground(new Color(204, 204, 204));
			InfoPanel.setText("Bem vindo ao Robô Sinistro versão beta 1.0");
		}
		return InfoPanel;
	}
	/**
	 * This method initializes passFieldName	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPassFieldName() {
		if (passFieldName == null) {
			passFieldName = new JPasswordField();
			passFieldName.setBounds(new Rectangle(165, 45, 196, 16));
			passFieldName.setForeground(new Color(51, 51, 130));
		}
		return passFieldName;
	}
	/**
	 * This method initializes Passabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getPassabel() {
		if (Passabel == null) {
			Passabel = new JLabel();
			Passabel.setText("Entre com o seu password: ");
			Passabel.setBounds(new Rectangle(0, 60, 166, 16));
		}
		return Passabel;
	}
	/**
	 * This method initializes passFielPassword	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPassFielPassword() {
		if (passFielPassword == null) {
			passFielPassword = new JPasswordField();
			passFielPassword.setBounds(new Rectangle(165, 60, 196, 16));
		}
		return passFielPassword;
	}

}
