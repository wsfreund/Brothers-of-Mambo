import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.ComponentOrientation;
public class SlayerPanel{
	SlayerPanel(CombatScript yourCombatStript) {
	combatScript = yourCombatStript;
	
	
		}
	private CombatScript combatScript;
	private JTabbedPane DruidSlayerPanel = null;
	private JPanel PrincipalPanel = null;
	private JLabel jLabel = null;
	private JLabel LabelLoot = null;
	private JTextField Op��o1 = null;
	private JTextField Op��o2 = null;
	private JTextField Op��o3 = null;
	private JTextField Op��o4 = null;
	private JTextField Op��o5 = null;
	private JTextField Op��o6 = null;
	private JTextField Op��o7 = null;
	private JTextField Op��o9 = null;
	private JTextField Op��o10 = null;
	private JPanel Secund�rio1 = null;
	private JLabel jLabel1 = null;
	private JLabel LabelLoot1 = null;
	private JLabel LabelKeep1 = null;
	private JTextField toKeep1 = null;
	private JTextField toKeep2 = null;
	private JTextField toKeep21 = null;
	private JTextField toKeep4 = null;
	private JTextField toKeep5 = null;
	private JTextField toKeep6 = null;
	private JTextField toKeep7 = null;
	private JTextField toKeep8 = null;
	private JTextField toKeep9 = null;
	private JPanel StartUp = null;
	private JTextPane jTextPane = null;
	private JButton SaveButton = null;
	private JButton StarWithLastButton = null;
	private JButton StartWithCurrentButton = null;
	private JButton StartWithDefaultButton = null;
	private JPanel OtherConfigs = null;
	private JLabel jLabel2 = null;
	private JRadioButton BuryBones = null;
	private JLabel jLabel3 = null;
	private JTextField BoneName = null;
	private JLabel jLabel31 = null;
	private JRadioButton LogoutIfPlayer = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JRadioButton LogoutInTime = null;
	private JTextField TimeToStop = null;
	private JRadioButton ILoveStephan = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="30,9"
	private JPanel Principal = null;
	
	//TODO a��es.
	private void bonesSetup() {
		if(BuryBones.isContentAreaFilled()) {
			String boneName = BoneName.getText();
			if(boneName==null||boneName.isEmpty())
				combatScript.setBuryBones("Bone");
			else combatScript.setBuryBones(boneName);
		}
	}
	private String[] getLoots() {
		ArrayList<String> names = new ArrayList<String>();
		checkAndAdd(Op��o1.getText(),names);
		checkAndAdd(Op��o2.getText(),names);
		checkAndAdd(Op��o3.getText(),names);
		checkAndAdd(Op��o4.getText(),names);
		checkAndAdd(Op��o5.getText(),names);
		checkAndAdd(Op��o6.getText(),names);
		checkAndAdd(Op��o7.getText(),names);
		checkAndAdd(Op��o9.getText(),names);
		checkAndAdd(Op��o10.getText(),names);
		if(names.isEmpty()) return null;
		return (String[]) names.toArray();
	}
	private String[] getNotToDrop(){
		ArrayList<String> notToDrop = new ArrayList<String>();
		checkAndAdd(toKeep1.getName(),notToDrop);
		checkAndAdd(toKeep2.getName(),notToDrop);
		checkAndAdd(toKeep4.getName(),notToDrop);
		checkAndAdd(toKeep5.getName(),notToDrop);
		checkAndAdd(toKeep6.getName(),notToDrop);
		checkAndAdd(toKeep7.getName(),notToDrop);
		checkAndAdd(toKeep8.getName(),notToDrop);
		checkAndAdd(toKeep9.getName(),notToDrop);
		if(notToDrop.isEmpty()) return null;
		return (String[]) notToDrop.toArray();
	}
	private void checkAndAdd(String curName,ArrayList<String> names) {
		if(!(curName!=null || curName.isEmpty()))
		names.add(curName);
	}
	
	/**
	 * This method initializes DruidSlayerPanel	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getDruidSlayerPanel() {
		if (DruidSlayerPanel == null) {
			DruidSlayerPanel = new JTabbedPane();
			DruidSlayerPanel.setName("Principal");
			DruidSlayerPanel.addTab(null, null, getPrincipalPanel(), null);
			DruidSlayerPanel.addTab(null, null, getSecund�rio1(), null);
			DruidSlayerPanel.addTab(null, null, getStartUp(), null);
			DruidSlayerPanel.addTab(null, null, getOtherConfigs(), null);
		}
		return DruidSlayerPanel;
	}

	/**
	 * This method initializes PrincipalPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPrincipalPanel() {
		if (PrincipalPanel == null) {
			LabelLoot = new JLabel();
			LabelLoot.setText("Escreva Abaixo os Itens que voc� deseja pegar");
			jLabel = new JLabel();
			jLabel.setText("Bem Vindo Ao DruidSlayer. Configure as op��es antes de come�ar.");
			jLabel.setForeground(new Color(153, 153, 0));
			jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			PrincipalPanel = new JPanel();
			PrincipalPanel.setLayout(new BoxLayout(getPrincipalPanel(), BoxLayout.Y_AXIS));
			PrincipalPanel.add(jLabel, null);
			PrincipalPanel.add(LabelLoot, null);
			PrincipalPanel.add(getOp��o1(), null);
			PrincipalPanel.add(getOp��o2(), null);
			PrincipalPanel.add(getOp��o3(), null);
			PrincipalPanel.add(getOp��o5(), null);
			PrincipalPanel.add(getOp��o4(), null);
			PrincipalPanel.add(getOp��o6(), null);
			PrincipalPanel.add(getOp��o7(), null);
			PrincipalPanel.add(getOp��o9(), null);
			PrincipalPanel.add(getOp��o10(), null);
		}
		return PrincipalPanel;
	}

	/**
	 * This method initializes Op��o1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o1() {
		if (Op��o1 == null) {
			Op��o1 = new JTextField();
		}
		return Op��o1;
	}

	/**
	 * This method initializes Op��o2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o2() {
		if (Op��o2 == null) {
			Op��o2 = new JTextField();
		}
		return Op��o2;
	}

	/**
	 * This method initializes Op��o3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o3() {
		if (Op��o3 == null) {
			Op��o3 = new JTextField();
		}
		return Op��o3;
	}

	/**
	 * This method initializes Op��o4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o4() {
		if (Op��o4 == null) {
			Op��o4 = new JTextField();
		}
		return Op��o4;
	}

	/**
	 * This method initializes Op��o5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o5() {
		if (Op��o5 == null) {
			Op��o5 = new JTextField();
		}
		return Op��o5;
	}

	/**
	 * This method initializes Op��o6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o6() {
		if (Op��o6 == null) {
			Op��o6 = new JTextField();
		}
		return Op��o6;
	}

	/**
	 * This method initializes Op��o7	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o7() {
		if (Op��o7 == null) {
			Op��o7 = new JTextField();
		}
		return Op��o7;
	}

	/**
	 * This method initializes Op��o9	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o9() {
		if (Op��o9 == null) {
			Op��o9 = new JTextField();
		}
		return Op��o9;
	}

	/**
	 * This method initializes Op��o10	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOp��o10() {
		if (Op��o10 == null) {
			Op��o10 = new JTextField();
		}
		return Op��o10;
	}

	/**
	 * This method initializes Secund�rio1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSecund�rio1() {
		if (Secund�rio1 == null) {
			LabelKeep1 = new JLabel();
			LabelKeep1.setText("");
			LabelLoot1 = new JLabel();
			LabelLoot1.setText("Escreva Abaixo os Itens que voc� deseja pegar");
			LabelLoot1.setToolTipText("Obs: n�o esque�a de adicionar os itens que voc� quer pegar.");
			jLabel1 = new JLabel();
			jLabel1.setForeground(new Color(153, 153, 0));
			jLabel1.setText("Bem Vindo Ao DruidSlayer. Configure as op��es antes de come�ar.");
			jLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			Secund�rio1 = new JPanel();
			Secund�rio1.setLayout(new BoxLayout(getSecund�rio1(), BoxLayout.Y_AXIS));
			Secund�rio1.add(jLabel1, null);
			Secund�rio1.add(LabelLoot1, null);
			Secund�rio1.add(LabelKeep1, null);
			Secund�rio1.add(getToKeep1(), null);
			Secund�rio1.add(getToKeep2(), null);
			Secund�rio1.add(getToKeep3(), null);
			Secund�rio1.add(getToKeep9(), null);
			Secund�rio1.add(getToKeep5(), null);
			Secund�rio1.add(getToKeep8(), null);
			Secund�rio1.add(getToKeep6(), null);
			Secund�rio1.add(getToKeep7(), null);
			Secund�rio1.add(getToKeep4(), null);
		}
		return Secund�rio1;
	}

	/**
	 * This method initializes toKeep1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep1() {
		if (toKeep1 == null) {
			toKeep1 = new JTextField();
		}
		return toKeep1;
	}

	/**
	 * This method initializes toKeep2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep2() {
		if (toKeep2 == null) {
			toKeep2 = new JTextField();
		}
		return toKeep2;
	}

	/**
	 * This method initializes toKeep21	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep3() {
		if (toKeep21 == null) {
			toKeep21 = new JTextField();
		}
		return toKeep21;
	}

	/**
	 * This method initializes toKeep4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep4() {
		if (toKeep4 == null) {
			toKeep4 = new JTextField();
		}
		return toKeep4;
	}

	/**
	 * This method initializes toKeep5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep5() {
		if (toKeep5 == null) {
			toKeep5 = new JTextField();
		}
		return toKeep5;
	}

	/**
	 * This method initializes toKeep6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep6() {
		if (toKeep6 == null) {
			toKeep6 = new JTextField();
		}
		return toKeep6;
	}

	/**
	 * This method initializes toKeep7	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep7() {
		if (toKeep7 == null) {
			toKeep7 = new JTextField();
		}
		return toKeep7;
	}

	/**
	 * This method initializes toKeep8	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep8() {
		if (toKeep8 == null) {
			toKeep8 = new JTextField();
		}
		return toKeep8;
	}

	/**
	 * This method initializes toKeep9	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getToKeep9() {
		if (toKeep9 == null) {
			toKeep9 = new JTextField();
		}
		return toKeep9;
	}

	/**
	 * This method initializes StartUp	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStartUp() {
		if (StartUp == null) {
			StartUp = new JPanel();
			StartUp.setLayout(null);
			StartUp.add(getJTextPane(), null);
			StartUp.add(getSaveButton(), null);
			StartUp.add(getStarWithLastButton(), null);
			StartUp.add(getStartWithCurrentButton(), null);
			StartUp.add(getStartWithDefaultButton(), null);
		}
		return StartUp;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setBounds(new Rectangle(-1, 38, 324, 79));
			jTextPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			jTextPane.setForeground(new Color(51, 51, 255));
			jTextPane.setText("Antes de qualquer coisa, certifique-se que preencheu os campos da maneira correta. Para come�ar a Script deixe a tela do Runescape toda a mostra e aperte numa das 3 maneiras de come�ar abaixo. ");
		}
		return jTextPane;
	}

	/**
	 * This method initializes SaveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveButton() {
		if (SaveButton == null) {
			SaveButton = new JButton();
			SaveButton.setBounds(new Rectangle(-1, 0, 325, 42));
			SaveButton.setToolTipText("Salva suas op��es para usar mais tarde");
			SaveButton.setText("Salvar Op��es");
			SaveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//TODO//Fazer m�todo para salvar.
				}
			});
		}
		return SaveButton;
	}

	/**
	 * This method initializes StarWithLastButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStarWithLastButton() {
		if (StarWithLastButton == null) {
			StarWithLastButton = new JButton();
			StarWithLastButton.setBounds(new Rectangle(0, 117, 324, 35));
			StarWithLastButton.setToolTipText("Come�a a Script com as configura��es que voc� salvou da ultima vez.");
			StarWithLastButton.setText("Come�ar com as ultimas configs Salvas.");
			StarWithLastButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					bonesSetup();
					
					 // TODO Auto-generated Event stub actionPerformed()
				}
				});
		}
		return StarWithLastButton;
	}

	/**
	 * This method initializes StartWithCurrentButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStartWithCurrentButton() {
		if (StartWithCurrentButton == null) {
			StartWithCurrentButton = new JButton();
			StartWithCurrentButton.setBounds(new Rectangle(0, 150, 324, 40));
			StartWithCurrentButton.setToolTipText("Come�a a Script com as configura��es digitadas agora.");
			StartWithCurrentButton.setText("Come�ar com as configura��es atuais.");
			StartWithCurrentButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					bonesSetup(); 
					combatScript.setWhatToKeep(getNotToDrop());
					combatScript.setLootName(getLoots());
				}
			});
		}
		return StartWithCurrentButton;
	}

	/**
	 * This method initializes StartWithDefaultButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStartWithDefaultButton() {
		if (StartWithDefaultButton == null) {
			StartWithDefaultButton = new JButton();
			StartWithDefaultButton.setBounds(new Rectangle(0, 188, 324, 37));
			StartWithDefaultButton.setToolTipText("Come�a com as configura��es defaut. S� pegando ervas economicamente interessantes, e sem comer comida.");
			StartWithDefaultButton.setText("Come�ar com as configura��es default.");
			StartWithDefaultButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
				}
			});
		}
		return StartWithDefaultButton;
	}

	/**
	 * This method initializes OtherConfigs	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOtherConfigs() {
		if (OtherConfigs == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(35, 183, 216, 16));
			jLabel7.setText("Sim, assumo-as, inclusive banimento.");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(2, 143, 326, 33));
			jLabel6.setText("Assumo TODAS as responsabilidades por usar esta APP.");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(-1, 98, 325, 16));
			jLabel5.setFont(new Font("Lucida Bright", Font.BOLD, 10));
			jLabel5.setText("Encerrar a aplica��o em uma determinada hora? Digite a hora");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(36, 78, 134, 16));
			jLabel4.setText("Sim, desejo dar logout");
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(2, 57, 325, 16));
			jLabel31.setFont(new Font("Lucida Bright", Font.BOLD, 10));
			jLabel31.setText("Logar caso haja indicios de outro jogador ca�ando os monstros?");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(14, 15, 302, 21));
			jLabel3.setFont(new Font("Lucida Bright", Font.BOLD, 10));
			jLabel3.setText("Enterrar Ossos? Caso sim entre o nome do OSSO.");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(0, 0, 323, 14));
			jLabel2.setForeground(new Color(153, 153, 0));
			jLabel2.setText("Bem Vindo Ao DruidSlayer. Configure as op��es antes de come�ar.");
			jLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			OtherConfigs = new JPanel();
			OtherConfigs.setLayout(null);
			OtherConfigs.add(jLabel2, null);
			OtherConfigs.add(getBuryBones(), null);
			OtherConfigs.add(jLabel3, null);
			OtherConfigs.add(getBoneName(), null);
			OtherConfigs.add(jLabel31, null);
			OtherConfigs.add(getLogoutIfPlayer(), null);
			OtherConfigs.add(jLabel4, null);
			OtherConfigs.add(jLabel5, null);
			OtherConfigs.add(getLogoutInTime(), null);
			OtherConfigs.add(getTimeToStop(), null);
			OtherConfigs.add(getILoveStephan(), null);
			OtherConfigs.add(jLabel6, null);
			OtherConfigs.add(jLabel7, null);
		}
		return OtherConfigs;
	}

	/**
	 * This method initializes BuryBones	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getBuryBones() {
		if (BuryBones == null) {
			BuryBones = new JRadioButton();
			BuryBones.setBounds(new Rectangle(14, 36, 21, 21));
		}
		return BuryBones;
	}

	/**
	 * This method initializes BoneName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getBoneName() {
		if (BoneName == null) {
			BoneName = new JTextField();
			BoneName.setBounds(new Rectangle(42, 36, 229, 22));
			BoneName.setText("Entre com o nome do osso aqui.");
		}
		return BoneName;
	}

	/**
	 * This method initializes LogoutIfPlayer	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getLogoutIfPlayer() {
		if (LogoutIfPlayer == null) {
			LogoutIfPlayer = new JRadioButton();
			LogoutIfPlayer.setBounds(new Rectangle(14, 75, 21, 21));
		}
		return LogoutIfPlayer;
	}

	/**
	 * This method initializes LogoutInTime	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getLogoutInTime() {
		if (LogoutInTime == null) {
			LogoutInTime = new JRadioButton();
			LogoutInTime.setBounds(new Rectangle(16, 116, 21, 21));
		}
		return LogoutInTime;
	}

	/**
	 * This method initializes TimeToStop	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTimeToStop() {
		if (TimeToStop == null) {
			TimeToStop = new JTextField();
			TimeToStop.setBounds(new Rectangle(41, 116, 105, 22));
			TimeToStop.setText("h:min:seg");
			TimeToStop.setToolTipText("Entre as horas no da seguinte forma - HORA(DOIS PONTOS)MINUTOS(DOIS POINTOS)SEGUNDOS");
		}
		return TimeToStop;
	}

	/**
	 * This method initializes ILoveStephan	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getILoveStephan() {
		if (ILoveStephan == null) {
			ILoveStephan = new JRadioButton();
			ILoveStephan.setBounds(new Rectangle(16, 177, 21, 21));
		}
		return ILoveStephan;
	}

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(342, 287));
			jFrame.setTitle("RoboT - Script");
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Documents and Settings/Usuario/Desktop/icone.JPG"));
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jFrame.setVisible(true);
			jFrame.setContentPane(getPrincipal());
			jFrame.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e) {
					if ((e.getPropertyName().equals("enabled"))) {
						System.out.println("propertyChange(enabled)"); // TODO Auto-generated property Event stub "enabled" 
					}
				}
			});
		}
		return jFrame;
	}

	/**
	 * This method initializes Principal	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPrincipal() {
		if (Principal == null) {
			Principal = new JPanel();
			Principal.setLayout(new CardLayout());
			Principal.add(getDruidSlayerPanel(), getDruidSlayerPanel().getName());
		}
		return Principal;
	}

}
