import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class CheckCombateEvent {
	/**
	 * Classe contendo métodos para vigiar as rendodesas do jogador e procurar
	 * por uma barra verde de vida, ou seja, um combate.
	 * 
	 */

	private DamageReader dmgR = new DamageReader();

	/**
	 * Procura por pontos verdes que correspondem a barra de vida e informa a
	 * posição destes pontos. Como podem ser encontradas mais de uma barra de
	 * vida, os pontos são devolvidas numa lista, ou nulo caso nada seja
	 * encontrado.
	 * 
	 * @return Uma lista contendo o local aproximado da barra de vida, ou nulo
	 *         caso nada seja encontrado.
	 */
	public ArrayList<Point> getCombatEventBars() {
		ArrayList<Point> points;
		//(new Color(0, 41, 0), 10, 0, 20, true); // TODO melhorar, afina o
														// cara não pode ficar
														// funcionando apenas
														// com uma determinada
														// vida.
		points = PointGetter.getAllPoints(ScreenGetter.getNearPlayer(),new Color(0, 41, 0), 10, 0, 20, true);
		return points;
	}

	/**
	 * Procura por uma morte, devolve nulo, ou uma lista contendo os pontos caso
	 * encontre algo.
	 */
	public ArrayList<Point> lookForDeath() {
		//pt.set(new Color(60, 8, 3), 10, 0, 118, true);
		ArrayList<Point> points;
		points = PointGetter.getAllPoints(ScreenGetter.getNearPlayer(),new Color(60, 8, 3), 10, 0, 118, true);
		// TODO temporário, depois evolver só os retangulos.
		if (points == null)
			return null;
		if (!points.isEmpty())
			return points;
		return null;
	}

	/**
	 * @return Retorna um PointDamage informado os locais ou local onde
	 *         ocorreram danos, caso nenhum dano tenha ocorrido retorna nulo
	 */
	public ArrayList<PointDamage> checkForDamage() {
		//pt.set(Color.WHITE, 9, 0, 0, true);
		ArrayList<Rectangle> lRec = PointGetter.getPointsRectangle(ScreenGetter.getNearPlayer(),Color.WHITE, 9, 5, 0, true);
		ArrayList<PointDamage> pointDamageList = new ArrayList<PointDamage>();
		if (lRec == null || lRec.isEmpty()) {
			System.out
					.println("A lista de retangulos não contém nenhum retângulo");
			return pointDamageList;
		}

		for (int i = 0; i < lRec.size(); i++) {
			Rectangle tempRec = lRec.get(i);
			System.out.println("Retangggggggggggggggggulo: " + tempRec);
			dmgR.setLineBeginX(tempRec.x -3);
			dmgR.setLineBeginY(tempRec.y -2);
			dmgR.setLineWidht(tempRec.width + 3);
			
			int damage = dmgR.getValue();
			RuneMethods.log("damage" + damage);
			
			if (damage >= 0) {
				Point position = new Point(tempRec.x
						+ (int) (tempRec.width / 2), tempRec.y
						+ (int) (tempRec.height / 2));
				PointDamage p = new PointDamage(position.x, position.y, damage);
				pointDamageList.add(p);
			}
		}
		return pointDamageList;
	}

}
