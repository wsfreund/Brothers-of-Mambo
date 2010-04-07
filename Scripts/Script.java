import java.io.IOException;

public abstract class Script extends Thread implements ClipMensageListener {
	/**
	 * M�todo b�sico para cria��o de novas Scripts.
	 */

	ClipMensageEventThread clip;

	Script() {
		LogFrame frameLog = new LogFrame();
		frameLog.setVisible(true);
		frameLog.setAlwaysOnTop(true);
	}

	/**
	 * Lida com as mensagens recebidas
	 */
	public void mensageEventRecived(String mensage) {
		if (toLogMsg())
			RuneMethods.logFile(mensage);
		checkMensage(mensage);
		checkMensageEvent(mensage);
	}

	public void run() {
		clip = new ClipMensageEventThread(this, msgListFreq());
		onStart();
		onFinish();
		while (loop() >= 0) {
			RuneMethods.wait(loop());
		}
	}

	/**
	 * Aqui o programa ir� realizar verifica��es padr�es nas mensagens,
	 * verificando ocorrencia de eventos, ou preparando respostas para perguntas
	 * sobre skill e etc..
	 * 
	 */
	private void checkMensage(String mensage) {
	}

	/**
	 * M�todo a ser reescrito pelas Scripts. Deixar em branco caso n�o haja
	 * interesse adicional nas informa��es.
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
	 * avisa se a Script deve ou n�o logar as mensagens recebidas. O m�todo deve
	 * ser reescrito.
	 * 
	 * @return
	 */
	abstract boolean toLogMsg();

	/**
	 * M�todo respons�vel pelo loop a Script.
	 * 
	 * @return - deve retornar o tempo a esperar entre cada loop.
	 */
	abstract int loop();

	/**
	 * M�todo a ser reescrito que retorna a frequencia com que a script vai ler
	 * as mensagens na clip board. Obs: frequencia significa quantas vezes por
	 * segundo.
	 * 
	 * @return
	 */
	abstract int msgListFreq();

}
