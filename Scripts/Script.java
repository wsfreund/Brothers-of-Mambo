import java.io.IOException;

public abstract class Script extends Thread implements ClipMensageListener {
	/**
	 * Método básico para criação de novas Scripts.
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
	 * Aqui o programa irá realizar verificações padrões nas mensagens,
	 * verificando ocorrencia de eventos, ou preparando respostas para perguntas
	 * sobre skill e etc..
	 * 
	 */
	private void checkMensage(String mensage) {
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

}
