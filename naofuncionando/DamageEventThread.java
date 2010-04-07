import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class DamageEventThread extends Thread {
	/**
	 * Uma tarefa responsável ppor informar ocorrencia de dano.
	 * 
	 */
	private PointGetter pWGet = new PointGetter(ScreenGetter.getPlayScrArea(),
			Color.WHITE, 9, 0, 0, true);
	private DamageReader dmgR = new DamageReader();
	private DamageListener hisListener;
	private int freq;

	/**
	 * Informe no contrutor a quem esta tarefa deve informar a ocorrencia de
	 * dano, e a frequencia com que ela deve checar.
	 * 
	 * @param yourListener
	 *            - a quem informar a ocorrencia de dano.
	 * @param freq
	 *            - a frequencia com que se deve checar (quantas vezes por
	 *            segundo)
	 */
	public DamageEventThread(DamageListener yourListener, int freq) {
		this.freq = freq;
		hisListener = yourListener;
	}

	public void run() {
		long time = (long) (1000 / freq);
		for (;;) {
			try {
				if (checkForDamage()) {
					sleep(1000);

				} else {
					sleep(time);
				}

			} catch (InterruptedException e) {
				// TODO log!
			}
		}

	}

	/**
	 */
	private boolean checkForDamage() {
		Rectangle lRec = pWGet.getPointsRectangle();
		if (lRec == null) {
			System.out.println("Retangulo nulo, nao foi achado ponto...");
			return false;
		}
		dmgR.setLineBeginX(lRec.x);
		dmgR.setLineBeginY(lRec.y);
		dmgR.setLineWidht(lRec.width);
		PixelHandler.renderBufImg(PixelHandler.printScreen(new Rectangle(
				lRec.x, lRec.y, lRec.width, 9)), "C://", "testeLinhaAttack",
				"bmp");
		int damage = dmgR.getValue();
		if (damage > 0) {
			Point position = new Point(lRec.x + (int) (lRec.width / 2), lRec.y
					+ (int) (lRec.height / 2));
			informDamage(position, damage, hisListener);
			return true;
		}
		return false;
	}

	private void informDamage(Point position, int amount, DamageListener his) {
		his.damageEventRecived(amount, position);
	}

}
