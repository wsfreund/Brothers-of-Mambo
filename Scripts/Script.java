import java.io.IOException;

public abstract class Script extends Thread implements ClipMensageListener {
	/**
	 * Método básico para criação de novas Scripts.
	 */

	private boolean stopLoop = false;
	ClipMensageEventThread clip = new ClipMensageEventThread(this,
			msgListFreq());

	Script() {
	}

	private static String[] scriptNames = {"MuggerSlayer"};
	/**
	 * @return - o nome de todas as Scripts.
	 */
	public static String[] getNames() {
		return scriptNames;
		}
	public static void runScript(String scriptName) {
		ScreenGetter.set();
		if(scriptName.equals("MuggerSlayer")) {
			MuggerSlayer muggerSlayer = new MuggerSlayer();
			muggerSlayer.start();
		}
	}
	
	
	/**
	 * Lida com as mensagens recebidas
	 */
	public void mensageEventRecived(String mensage) {
		System.out.println(mensage);
		if (toLogMsg())
			RuneMethods.logFile(mensage);
		checkMensage(mensage);
		checkMensageEvent(mensage);
	}

	public void run() {
		clip.start();
		onStart();
		onFinish();
		while (loop() >= 0 && !stopLoop) {
			RuneMethods.wait(loop());
		}
	}

	/**
	 * Aqui o programa irá realizar verificações padrões nas mensagens,
	 * verificando ocorrencia de eventos, ou preparando respostas para perguntas
	 * sobre skill e etc..
	 * 
	 */
	private void checkMensage(String mensage) {
		RuneMethods.logFile(mensage);
		if (mensage.toLowerCase().contains(
				RuneMethods.getPlayerName().toLowerCase())
				&& mensage.toLowerCase().contains("tchau")) {
			stopLoop();
			RuneMethods.log("O jogador se despediu, saindo do Bot...");
			RuneMethods.closeLog();
			System.exit(0);
		}
	}

	/**
	 * Método a ser reescrito pelas Scripts. Deixar em branco caso não haja
	 * interesse adicional nas informações.
	 * 
	 * @param mensage
	 */
	abstract void checkMensageEvent(String mensage);

	abstract void onStart();

	abstract void onFinish();

	protected void wait(int ms) {
		RuneMethods.wait(ms);
	}

	/**
	 * avisa se a Script deve ou não logar as mensagens recebidas. O método deve
	 * ser reescrito.
	 * 
	 * @return
	 */
	abstract boolean toLogMsg();

	/**
	 * Método responsável pelo loop a Script.
	 * 
	 * @return - deve retornar o tempo a esperar entre cada loop.
	 */
	abstract int loop();

	/**
	 * Método a ser reescrito que retorna a frequencia com que a script vai ler
	 * as mensagens na clip board. Obs: frequencia significa quantas vezes por
	 * segundo.
	 * 
	 * @return
	 */
	abstract int msgListFreq();

	public void stopLoop() {
		stopLoop = true;
	}
}
