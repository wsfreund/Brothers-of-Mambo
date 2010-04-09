import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class MouseHandler {
	static boolean shouldStop = false;

	/**
	 * Classe est�tica contendo m�todos uteis para obter informa��o e posi��o do
	 * mouse.
	 */
	private MouseHandler() {
	}

	static double midInt = 0;

	static private boolean moveMouseSimple(ArrayList<Point> points) {
		int size = points.size();
		int maxDelay = 2;
		for (int i = 0; i < size; i++) {
			if (shouldStop) { // talves remover isto
				shouldStop = false;
				return false;
			}
			if (Meth.doubleRandom(0, 1) > 0.20) {
				Point p = points.get(i);
				SystemTools.getRobot().mouseMove(p.x, p.y);
				if (Meth.doubleRandom(0, 1) > 0.55) {
					int delay = 1;
					SystemTools.getRobot().delay(delay);
				}
			}
		}
		return true;
	}

	/**
	 * Arrasta o mouse para um determinado ponto da maneira especificada pelo
	 * usu�rio deste m�todo.
	 * 
	 * Parametros:
	 * 
	 * @param Point
	 *            p: o ponto para onde o mouse ser� arrastado.
	 * 
	 * @param int rand: o fator randomico a ser adicionado ao ultimo ponto.
	 * 
	 * @param boolean byPass: se o mouse deve passar por pontos randomicos
	 *        pr�ximo aos do caminho original durante o percurso.
	 * 
	 * @param moveAfterFactor
	 *            : parametro que simula o reflexo humano, deixe 0 se voc� n�o
	 *            deseja simula-lo. Factor: O fator de recuo m�ximo. ex: se voce
	 *            usar 0.2 o mouse passar� o ponto final em 0.2 x a dist�ncia e
	 *            depois retornar� ao ponto fina.
	 * 
	 * @return retorna verdade se o mouse percorreu todo o percurso desejado
	 *         pelo usu�rio do m�todo.
	 */
	static public boolean dragMouse(Point p, int rand, boolean byPass,
			double moveAfterFactor) {
		Point pF = p;
		if (rand > 0) {
			pF.x += Meth.intRandom(-rand, +rand);
			pF.y += Meth.intRandom(-rand, +rand);
		}
		if (!byPass) {
			if (moveAfterFactor <= 0) {
				ArrayList<Point> points = PathMaker.generateRandomPath(
						getMousePos(), pF);
				if (!moveMouseSimple(points))
					return false;
			} else {
				Point recoil = generateRecoil(getMousePos(), pF,
						moveAfterFactor);
				ArrayList<Point> points = PathMaker.generateRandomPath(
						getMousePos(), recoil);
				if (!moveMouseSimple(points))
					return false;
				if (shouldStop) {
					shouldStop = false;
					return false;
				}
				SystemTools.getRobot().delay(Meth.intRandom(20, 80));
				points = PathMaker.generateRandomPath(getMousePos(), pF);
				if (!moveMouseSimple(points))
					return false;
			}
		} else {
			if (moveAfterFactor <= 0) {
				ArrayList<Point> points = PathMaker
						.makeRandomPathByArrayPoints(getMousePos(), pF, 20);
				if (!moveMouseSimple(points))
					return false;
			} else {
				Point recoil = generateRecoil(getMousePos(), pF,
						moveAfterFactor);
				ArrayList<Point> points = PathMaker
						.makeRandomPathByArrayPoints(getMousePos(), recoil, 20);
				if (!moveMouseSimple(points))
					return false;
				if (shouldStop) {
					shouldStop = false;
					return false;
				}
				SystemTools.getRobot().delay(Meth.intRandom(20, 80));
				points = PathMaker.generateRandomPath(getMousePos(), pF);
				if (!moveMouseSimple(points))
					return false;
			}
		}
		return true;
	}

	public static Point getMousePos() {
		return MouseInfo.getPointerInfo().getLocation();
	}

	/**
	 * Gera um ponto de recuo para ser usado no m�todo DragMouse. Para maior
	 * descri��o leira sobre o recuo no m�todo DragMouse
	 */
	static private Point generateRecoil(Point init, Point last, double factor) {
		int dx = last.x - init.x;
		int dy = last.y - init.y;
		double c = factor; // o m�ximo de recuo � 0.3 da distancia
		int factorx = (int) (dx > 0 ? last.x * c * Math.random() : -last.x * c
				* Math.random());
		int factory = (int) (dy > 0 ? last.y * c * Math.random() : -last.y * c
				* Math.random());
		int x = last.x + factorx;
		int y = last.y + factory;
		return (new Point(x, y));
	}

	// TODO esse m�todo est� um lixo! Falta adicionar o wait! Ningu�m clica e
	// solta no mesmo intante...
	/**
	 * Este metodo faz o mouse clicar com o bot�o esquerdo ou direito.
	 * 
	 * @param boolean rightClick - Caso true o mouse clicara com o boot�o
	 *        direito, caso contrario com o esquerdo
	 * 
	 * @return boolean - um boolean avisando se o click foi bem sucedido ou n�o,
	 *         usando o m�todo que verifica a cor da cruz (amarela ou vermelha)
	 *         q aparece no ch�o.
	 */
	public static boolean MouseLeftClick(boolean checkTargetted) {

		SystemTools.getRobot().mousePress(InputEvent.BUTTON1_MASK);
		boolean sucess = sucessAction(Meth.intRandom(30, 60));
		SystemTools.getRobot().mouseRelease(InputEvent.BUTTON1_MASK);
		if (!sucess) {
			sucess = sucessAction(200);
		}
		return sucess;
	}

	/**
	 * Este metodo faz o mouse clicar com o bot�o direito.
	 */
	public static boolean MouseRightClick() {
		SystemTools.getRobot().mousePress(InputEvent.BUTTON3_MASK);
		RuneMethods.wait(Meth.intRandom(30, 60));
		SystemTools.getRobot().mouseRelease(InputEvent.BUTTON3_MASK);
		return true;
	}

	/**
	 * Avisa se o click do mouse foi bem sucedido ou n�o, verificando se a cruz
	 * que apareceu no ch�o foi vermelha.
	 * 
	 * @return
	 */
	private static boolean sucessAction(int maxTime) { // TODO verificar waiting
														// time e
		// funcionamento.
		int wid = 20;
		int hei = 20;
		long time = 0;
		long finalTime = System.currentTimeMillis() + maxTime;

		while (time < finalTime) {
			Rectangle mouseArea = new Rectangle(getMousePos().x - hei,
					getMousePos().y - wid, wid * 2, hei * 2);
			ArrayList<Point> points = PixelHandler.filterGetAreaColorPoints(
					mouseArea, Color.RED, 0, true);
			if (points.size() != 0)
				return true;
			time = System.currentTimeMillis();
			RuneMethods.wait(30);
		}

		return false;

	}

}