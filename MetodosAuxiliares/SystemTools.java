import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

public class SystemTools {
	/**
	 * Esta classe estatica � responsavel pela cria��o de um "Robot" e por
	 * captar as dimens�es da tela. Ainda que a classe s� possua m�todos
	 * est�ticos, ela deve ser inicializada usando SystemTools.set() (apenas 1
	 * vez...) antes de seu uso. A intens�o � evitar os diversos acessos a
	 * ferramentas que involvam erros do sistema, evitando assim repeti��es e
	 * verificando antes da execuss�o do programa se ele pode ou n�o acessar as
	 * restri��es de seguran�a do sistema.
	 * 
	 */

	private static Robot bot;
	private static Robot bot2;
	private static Dimension screenDim;

	private SystemTools() {
	}

	public static void set() {
		try {
			bot = new Robot();
			bot2 = new Robot();
			screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		} catch (Exception e) { // TODO substituir print por LOG.
			e.printStackTrace();
			System.out
					.println("O acesso ao sistema falhou, tente novamente, se n�o funcionar � porque as configura��es de seguran�a do seu pc n�o permitem que o programa rode no seu PC.");
			System.out
					.println("-------------------------------------------------");
			System.out.println("Encerrando programa...");
			System.exit(1);
		}
	}

	/**
	 * Retorna o robot do sistema. Por mais que este m�todo seja estatico, n�o
	 * deve ser chamado sem que a classe SystemTools seja antes inicializada!
	 * Para mais informa��es veja a descri��o da classe.
	 * 
	 * @return Robot - um robo responsavel por diversas fun��es do sistema.
	 */
	static Robot getRobot() {
		return bot;
	}
	/**
	 * Retorna o robot 2 do sistema. Por mais que este m�todo seja estatico, n�o
	 * deve ser chamado sem que a classe SystemTools seja antes inicializada!
	 * Para mais informa��es veja a descri��o da classe.
	 * 
	 * @return Robot - um robo responsavel por diversas fun��es do sistema.
	 */
	static Robot getRobot2() {
		return bot2;
	}

	/**
	 * Retorna as dimens�es da tela. Por mais que este m�todo seja estatico, n�o
	 * deve ser chamado sem que a classe SystemTools seja antes inicializada!
	 * Para mais informa��es veja a descri��o da classe.
	 * 
	 * @return Dimensio - as dimens�es da tela do sistema.
	 */
	static Dimension getSystemScreenDimension() {
		return screenDim;
	}

}
