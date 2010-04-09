import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Dimension;

public class CodecGenPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JButton GoButton = null;
	/**
	 * @param owner
	 */
	public CodecGenPanel(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(229, 121);
		this.setTitle("RoboT - Codec");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Click GO!");
			jLabel2 = new JLabel();
			jLabel2.setText("Deixe o seu inventório amostra.");
			jLabel1 = new JLabel();
			jLabel1.setText("Deixe a janela do Runescape a mostra.");
			jLabel = new JLabel();
			jLabel.setText("RoboT - Gerador de Codec:");
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getGoButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes GoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGoButton() {
		if (GoButton == null) {
			GoButton = new JButton();
			GoButton.setText("GO");
			GoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//TODO fazer aqui método para começar a codificar.
				}
			});
		}
		return GoButton;
	}

}  //  @jve:decl-index=0:visual-constraint="108,37"
