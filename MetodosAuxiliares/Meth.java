import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Meth {
	/**
	 * Classe estática de métodos para auxilio das demais classes.
	 */
	private Meth() {
	}

	/**
	 * Gera um valor real randomico dados minimo e máximo.
	 * 
	 * ATENÇÃO ---- Certifiquece de botar primeiro o máximo e depois o minimo!
	 * 
	 * @param min
	 *            - o valor minimo.
	 * @param max
	 *            - o valor máximo.
	 * @return um valor real (double) randomico compreendido entre o minimo e o
	 *         máximo.
	 */
	public static double doubleRandom(int min, int max) {
		return min + Math.random() * (max - min);
	}

	/**
	 * Gera um valor real randomico dados minimo e máximo.
	 * 
	 * ATENÇÃO ---- Certifiquece de botar primeiro o máximo e depois o minimo!
	 * 
	 * @param min
	 *            - o valor minimo.
	 * @param max
	 *            - o valor máximo.
	 * @return um valoer real (double) randomico compreendido entre o minimo e o
	 *         máximo.
	 */
	public static double doubleRandom(double min, double max) {
		return min + Math.random() * (max - min);
	}

	/**
	 * Gera um valor inteiro randomico dados minimo e máximo.
	 * 
	 * ATENÇÃO ---- Certifiquece de botar primeiro o máximo e depois o minimo!
	 * 
	 * @param min
	 *            - o valor minimo.
	 * @param max
	 *            - o valor máximo.
	 * @return um valor inteiro randomico compreendido entre o minimo e o
	 *         máximo.
	 */
	public static int intRandom(int min, int max) {
		return (int) min + ((int) (Math.round(Math.random() * (max - min))));
	}

	/**
	 * Gera um valor inteiro randomico dados minimo e máximo.
	 * 
	 * ATENÇÃO ---- Certifiquece de botar primeiro o máximo e depois o minimo!
	 * 
	 * @param min
	 *            - o valor minimo.
	 * @param max
	 *            - o valor máximo.
	 * @return um valor inteiro randomico compreendido entre o minimo e o
	 *         máximo.
	 */
	public static int intRandom(double min, double max) {
		return (int) Math.round(min + Math.random() * (max - min));
	}

	/**
	 * Retorna a distândia entre 2 cores dado o critério de semelhança. O
	 * critério de semelhança é dado pelo tamanho do filtro (ou seja o quão
	 * prómixa uma cor deve ser da outra) e pelo tipo de comparação do filtro
	 * -SimpleDelta- (caso true, ou seja filtro simples, a distância é x + y +
	 * z, caso contrario é (x² + y² + z²)*1/2).
	 * 
	 * @param color1
	 *            - uma cor das cores a ser comparada.
	 * @param color2
	 *            - a outra cor a ser comparada.
	 * @param SimpleDelta
	 *            - se o filtro é simples ou não.
	 */
	public static int getDelta(Color color1, Color color2, boolean SimpleDelta) {

		int a = (color1.getRed() - color2.getRed());
		a = a >= 0 ? a : -a;
		int b = (color1.getBlue() - color2.getBlue());
		b = b >= 0 ? b : -b;
		int c = (color1.getGreen() - color2.getGreen());
		c = c >= 0 ? c : -c;
		if (SimpleDelta) {
			return (a + b + c);
		}
		return (int) Math
				.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
	}

	/**
	 * Pega uma área retangular, e remove dela uma área retangular. Devolve
	 * retangulos que representam a área efetiva.
	 * 
	 * @param area
	 *            - A area total
	 * @param minusarea
	 *            - A area a ser "removida".
	 * 
	 * @return - uma <code> ArrayList </code> de retangulos contendo retangulos
	 *         que representam a área efetiva. (devolve no máximo 4 retângulos)
	 */
	public static ArrayList<Rectangle> getAreaMinusAreaRec(Rectangle area,
			Rectangle minusarea) {
		ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
		// Cordenadas retangulo área:
		int xIa = area.x;
		int xFa = area.x + area.width;
		int yIa = area.y;
		int yFa = area.y + area.height;
		
		// Cordenadas retangulo a ser removido:
		int xIb = minusarea.x > xIa ? minusarea.x : xIa;
		int xFb = minusarea.x + minusarea.width;
		xFb = xFb < xFa ? xFb : xFa;
		int yIb = minusarea.y > yIa ? minusarea.y : yIa;
		int yFb = minusarea.y + minusarea.height;
		yFb = yFb < yFa ? yFb : yFa;

		// .xIa,yIa xFa,yIa.
		// 
		// xIb,yIb . .xFb,yIb
		//
		// xFb,yFb . . xFb,yFb
		//
		// .xIa,yFa xFa,yFa.
		Rectangle recTop = new Rectangle(xIa, yIa, xFa - xIa, yIb - yIa);
		if (checkCondicions(recTop)) rectangles.add(recTop);
		Rectangle recRight = new Rectangle(xIa, yIb, xIb - xIa, yFb - yIb);
		if (checkCondicions(recRight)) rectangles.add(recRight);
		Rectangle recLeft = new Rectangle(xFb, yIb, xFa - xFb, yFb - yIb);
		if (checkCondicions(recLeft)) rectangles.add(recLeft);
		Rectangle recBot = new Rectangle(xIa, yFb, xFa - xIa, yFb - yFa);
		if (checkCondicions(recBot)) rectangles.add(recBot);

		return rectangles;
	}
	
	private static boolean checkCondicions (Rectangle rec) {
		return (rec.x > 0 && rec.y >0 && rec.height > 0 && rec.width > 0);
	}

}
