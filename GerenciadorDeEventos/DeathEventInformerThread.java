import java.awt.Color;
import java.awt.Point;

public class DeathEventInformerThread extends Thread {
	private DeathListener his;
	private int freq;

	
	/**
	 * Tarefa responsavel por vigiar nas </strong>redondesas</> do jogador e
	 * procurar por uma morte e inform�-la a uma death event listener.
	 * 
	 * @param yours
	 *            - O parametro deve conter um obbjeto DeathLister para o qual o
	 *            analisador de eventos ir� enviar as informa��es.
	 * 
	 * @param str
	 *            nome da tread. n�o se preocupe muito com este campo...
	 * @param maxS
	 *            como n�o foi informado funcionar� at� o m�todo stop for
	 *            acionado.
	 * 
	 * @param freq
	 *            a frequ�ncia (quantas vezes por segundo) ele vai procurar.
	 */
	public DeathEventInformerThread(DeathListener yours, String str, int freq) {
		super(str);
		his = yours;
		this.freq = freq;
	}

	public void run() {
		long time = (long) (1000/freq);
			for (;;) {
				try {
					if (execute()) {
						sleep(3000);
					} else {
						sleep(time);
					}

				} catch (InterruptedException e) {
					// TODO log!
				}
			}
		}

	/**
	 * m�todo de execu��o
	 */
	private boolean execute() {
		Point p = PointGetter.getProbPoint(ScreenGetter.getNearPlayer(),
				new Color(60, 8, 3), 8, 0, 109, true);
		if (p != null) {
			Point lastDeathPos = new Point(p.x, p.y + 15);
			informDeath(lastDeathPos, his);
			return true;
		}
		return false;
	}

	/**
	 * informando DeathListener
	 */
	private void informDeath(Point p, DeathListener deth) {
		deth.deathOcurredEvent(p);
	}

}
