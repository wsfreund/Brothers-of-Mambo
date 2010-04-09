import javax.swing.JWindow;
import java.awt.Window;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JScrollBar;


public class LogWindow extends JWindow {

	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;

	
	
	
	/**
	 * This method initializes 
	 * 
	 */
	public LogWindow() {
		super();
		this.setFocusable(false);
		this.setForeground(new Color(0,0,0));
		initialize();
		this.setAlwaysOnTop(true);
	}
	public void addLog(String log) {
		String newline = System.getProperty("line.separator");
		jTextArea.setText(log + newline + jTextArea.getText());
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(428, 54));
        this.setName("Window");
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setForeground(Color.white);
        this.setVisible(true);
        this.setContentPane(getJScrollPane());
        
			
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			JScrollBar jScrollBar = new JScrollBar();
			jScrollBar.setAutoscrolls(true);
			jScrollPane = new JScrollPane();
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPane.setViewportBorder(null);
			jScrollPane.setOpaque(false);
			jScrollPane.setRequestFocusEnabled(false);
			jScrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jScrollPane.setBackground(Color.white);
			jScrollPane.setForeground(Color.white);
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			jScrollPane.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
	
			jTextArea = new JTextArea();
			jTextArea.setText("Log Started");
			jTextArea.setCaretColor(new Color(35,200,99)); //TODO verificar isso
			jTextArea.setEditable(false);
			jTextArea.setFocusCycleRoot(true);
			jTextArea.setFont(new Font("Times New Roman", Font.BOLD, 10));
			jTextArea.setForeground(Color.black);
			jTextArea.setOpaque(false);
			jTextArea.setRequestFocusEnabled(false);
			jTextArea.setLineWrap(true);
			jTextArea.setWrapStyleWord(true);
		}
		return jTextArea;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,2"
