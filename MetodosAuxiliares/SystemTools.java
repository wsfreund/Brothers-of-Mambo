import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

public class SystemTools {
	/**
	 * Esta classe estatica é responsavel pela criação de um "Robot" e por
	 * captar as dimensões da tela. Ainda que a classe só possua métodos
	 * estáticos, ela deve ser inicializada usando SystemTools.set() (apenas 1
	 * vez...) antes de seu uso. A intensão é evitar os diversos acessos a
	 * ferramentas que involvam erros do sistema, evitando assim repetições e
	 * verificando antes da execussão do programa se ele pode ou não acessar as
	 * restrições de segurança do sistema.
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
					.println("O acesso ao sistema falhou, tente novamente, se não funcionar é porque as configurações de segurança do seu pc não permitem que o programa rode no seu PC.");
			System.out
					.println("-------------------------------------------------");
			System.out.println("Encerrando programa...");
			System.exit(1);
		}
	}

	/**
	 * Retorna o robot do sistema. Por mais que este método seja estatico, não
	 * deve ser chamado sem que a classe SystemTools seja antes inicializada!
	 * Para mais informações veja a descrição da classe.
	 * 
	 * @return Robot - um robo responsavel por diversas funções do sistema.
	 */
	static Robot getRobot() {
		return bot;
	}
	/**
	 * Retorna o robot 2 do sistema. Por mais que este método seja estatico, não
	 * deve ser chamado sem que a classe SystemTools seja antes inicializada!
	 * Para mais informações veja a descrição da classe.
	 * 
	 * @return Robot - um robo responsavel por diversas funções do sistema.
	 */
	static Robot getRobot2() {
		return bot2;
	}

	/**
	 * Retorna as dimensões da tela. Por mais que este método seja estatico, não
	 * deve ser chamado sem que a classe SystemTools seja antes inicializada!
	 * Para mais informações veja a descrição da classe.
	 * 
	 * @return Dimensio - as dimensões da tela do sistema.
	 */
	static Dimension getSystemScreenDimension() {
		return screenDim;
	}

}
