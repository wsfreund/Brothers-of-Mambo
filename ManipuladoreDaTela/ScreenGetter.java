import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ScreenGetter {

	private static Rectangle sysScrArea;
	private static Rectangle playScrArea;
	// private Rectangle javaScrArea; // TODO se for util.
	private static Point screenCenter;
	private static Rectangle mensageBox;
	private static ArrayList<Rectangle> slotList;
	private static Rectangle mensageBoxLine;
	private static Rectangle actionsLine;
	private static Rectangle hpLine;
	private static Rectangle prayerNumbersLine;
	private static Rectangle runNumbersLine;
	private static Rectangle nearPlayer;
	private static Rectangle playerAreaNorth;
	private static Rectangle playerAreaLeft;
	private static Rectangle playerAreaRight;
	private static Rectangle playerAreaSouth;
	private static Rectangle playerAreaCenter;
	private static Rectangle javaAppArea;

	/**
	 * Classe estatica responsável por pegar as "coordenadas" importantes da
	 * tela do Runescape. Atualmente só funciona com as configurações minimas do
	 * jogo. Antes de utiliza-la, o metodo Set() deve ser chamado com a tela do
	 * Runescape visivel. Note que caso a tela do Runescape for mudada de lugar,
	 * o método Set() deve ser chamado novamente caso contrário o programa não
	 * irá funcionar. Se o jogo não estiver na tela, o programa para de rodar.
	 */
	private ScreenGetter() {
	}

	static Rectangle getScreenArea() {
		Dimension screenDim = SystemTools.getSystemScreenDimension();
		int widht = (int) screenDim.getWidth();
		int height = (int) screenDim.getHeight();
		sysScrArea = new Rectangle(widht, height);
		return sysScrArea;
	}

	static private Rectangle setMensageBox() {

		ArrayList<Point[]> points = PixelHandler.getVerticalLine(
				getScreenArea(), Color.BLACK, 1, true, 130, 150);
		if (points.size() != 2)
			throw new ExceptionInInitializerError(
					"Não foi possivel pegar a área do retangulo de mensagens, foram achadas "
							+ points.size() + " linhas ao invés de 2");
		Point topLeft = points.get(0)[0];
		Point botLeft = points.get(0)[1];
		Point topRight = points.get(1)[0];
		int width = Math.abs(topLeft.x - topRight.x);
		int height = Math.abs(topLeft.y - botLeft.y);
		Rectangle msb = new Rectangle(width - 31, height - 31); // 27 -> 23
		// widht - 4
		msb.x = topLeft.x + 10; // 7 - > x +2
		msb.y = topLeft.y + 9;
		return msb;
	}

	static private Rectangle setPlayScreenArea() {
		int areaY = mensageBox.y - 343;
		int areaX = mensageBox.x - 6;
		int height = 334;
		int width = mensageBox.width + 24;
		boolean checkHeight = areaX + height < getScreenArea().x
				+ getScreenArea().height;
		boolean checkWidth = areaY + width < getScreenArea().y
				+ getScreenArea().width;
		boolean checkX = areaX > getScreenArea().x;
		boolean checkY = areaY > getScreenArea().y;
		if (checkHeight && checkWidth && checkX && checkY)
			return new Rectangle(areaX, areaY, width, height);
		else {
			throw new ExceptionInInitializerError(
					"A área de jogo sai da tela, tente deixar o programa mais visivel na próxima execução"
							+ checkHeight + checkWidth + checkX + checkY);
		}

	}

	static private ArrayList<Rectangle> setSlots() {
		int slotsWidht = 34 + 8; // distancia entre eles: 9
		int slotsHeight = 34 + 2; // distancia entre eles = 3
		int distx = 64; // a distâncias entre msg de texto e slot. Depois
		// aumentar com res.
		int disty = -246;
		int beginX = mensageBox.x + mensageBox.width + distx;
		int beginY = mensageBox.y + mensageBox.height + disty;
		if (beginX + slotsWidht * 4 > sysScrArea.width)
			throw new ExceptionInInitializerError(
					"Não foi possivel pegar a área do inventorio");
		ArrayList<Rectangle> slotList = new ArrayList<Rectangle>();
		for (int y = beginY; y < beginY + slotsHeight * 7; y = y + slotsHeight) {
			for (int x = beginX; x < beginX + slotsWidht * 4; x = x
					+ slotsWidht) {
				Rectangle slot = new Rectangle(x, y, 34, 34);
				System.out.println("Add novo rec: " + slot);
				slotList.add(slot);
			}
		}
		return slotList;
	}

	static private Rectangle setMensageLine() {
		int lineHeight = 15;
		int lineWidht = mensageBox.width;
		int lineBeginX = mensageBox.x;
		int lineBeginY = mensageBox.y + mensageBox.height - 13;
		return new Rectangle(lineBeginX, lineBeginY, lineWidht, lineHeight);
	}

	static private Rectangle setTopScrActionsLine() {
		int dx = 3;
		int dy = 4;
		int lineHeight = 17;
		int lineBeginX = playScrArea.x + dx;
		int lineWidht = playScrArea.width - dx;
		int lineBeginY = playScrArea.y + dy;
		return new Rectangle(lineBeginX, lineBeginY, lineWidht, lineHeight);
	}

	static private Rectangle setHpNumbersLine() {
		int dx = 207;
		int dy = 26;
		int lineHeight = 9;
		int lineWidht = 21;
		int lineBeginX = playScrArea.x + playScrArea.width + dx;
		int lineBeginY = playScrArea.y + dy;
		return new Rectangle(lineBeginX, lineBeginY, lineWidht, lineHeight);
	}

	static private Rectangle setPrayerNumbersLine() {
		int dx = 16;
		int dy = 39;
		int lineHeight = 9;
		int lineBeginX = hpLine.x + dx;
		int lineWidht = 17;
		int lineBeginY = hpLine.y + dy;
		return new Rectangle(lineBeginX, lineBeginY, lineWidht, lineHeight);
	}

	static private Rectangle setRunNumbersLine() {
		int dx = 14;
		int dy = 78;
		int lineHeight = 9;
		int lineBeginX = hpLine.x + dx;
		int lineWidht = 21;
		int lineBeginY = hpLine.y + dy;
		return new Rectangle(lineBeginX, lineBeginY, lineWidht, lineHeight);
	}

	private static Rectangle setNearPlayer() {
		int width = 100; // Largura sobre 2 na verdade
		int height = 100; // Altura sobre 2...
		int dx = 0;
		int dy = 0;
		return new Rectangle((int) getPlayScrArea().getCenterX() + dx - width,
				(int) getPlayScrArea().getCenterY() + dy - height, width * 2,
				height * 2);
	}

	private static Rectangle setCenterPlayerArea() {
		int width = 30; // Largura sobre 2 na verdade
		int height = 30; // Altura sobre 2...
		int dx = 0;
		int dy = 0;
		return new Rectangle((int) getPlayScrArea().getCenterX() + dx - width,
				(int) getPlayScrArea().getCenterY() + dy - height, width * 2,
				height * 2);
	}

	private static Rectangle setNorthPlayerArea() {
		int width = 30; // Largura sobre 2 na verdade
		int height = 30; // Altura sobre 2...
		return new Rectangle((int) getCenterlayerArea().x,
				getCenterlayerArea().y - height * 2, width * 2, height * 2);
	}

	private static Rectangle setLeftPlayerArea() {
		int width = 30; // Largura sobre 2 na verdade
		int height = 30; // Altura sobre 2...
		return new Rectangle((int) getCenterlayerArea().x - width * 2,
				getCenterlayerArea().y, width * 2, height * 2);
	}

	private static Rectangle setRightPlayerArea() {
		int width = 30; // Largura sobre 2 na verdade
		int height = 30; // Altura sobre 2...
		return new Rectangle((int) getCenterlayerArea().x
				+ getCenterlayerArea().width, getCenterlayerArea().y,
				width * 2, height * 2);
	}

	private static Rectangle setSouthPlayerArea() {
		int width = 30; // Largura sobre 2 na verdade
		int height = 30; // Altura sobre 2...

		return new Rectangle((int) getCenterlayerArea().x,
				getCenterlayerArea().y + getCenterlayerArea().height,
				width * 2, height * 2);
	}

	private static Point setScreenCenter() {
		return new Point((int) Math.round(playScrArea.getCenterX()), (int) Math
				.round(playScrArea.getCenterY()));

	}
	
	private static Rectangle setJavaAppArea(){
		int x = playScrArea.x-4;
		int y = playScrArea.y-4;
		int height = 500;
		int width = 700 + 66;
		return new Rectangle(x,y,width,height);
		
	}

	// TODO procurar por elipse... para usar no mapa!

	/**
	 * O metodo Set() deve ser chamado com a tela do Runescape visivel. Ele vai
	 * setar todas as coordenadas do Runescape. Note que caso a tela do
	 * Runescape for mudada de lugar, o método Set() deve ser chamado novamente
	 * caso contrário o programa não irá funcionar. Se o jogo não estiver na
	 * tela, o programa para de rodar. O mesmo irá acontecer para qualquer tipo
	 * de erro encontrado.
	 */
	static public void set() {
		try {
			sysScrArea = getScreenArea();
			mensageBox = setMensageBox();
			playScrArea = setPlayScreenArea();
			slotList = setSlots();
			mensageBoxLine = setMensageLine();
			actionsLine = setTopScrActionsLine();
			hpLine = setHpNumbersLine();
			prayerNumbersLine = setPrayerNumbersLine();
			runNumbersLine = setRunNumbersLine();
			playerAreaCenter = setCenterPlayerArea();
			nearPlayer = setNearPlayer();
			playerAreaNorth = setNorthPlayerArea();
			playerAreaLeft = setLeftPlayerArea();
			playerAreaRight = setRightPlayerArea();
			playerAreaSouth = setSouthPlayerArea();
			screenCenter = setScreenCenter();
			javaAppArea = setJavaAppArea();

		} catch (Exception e) { // TODO botar num log...
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1); // Encerra o programa.
		}
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo contendo a ultima linha de mensagen da caixa de
	 *         informação e conversação locaizada no sul a esquerda.
	 */
	static public Rectangle getMensageBoxLine() {
		return mensageBoxLine;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo contendo a area efetiva de jogo, onde aparecem os
	 *         montros e etc... Obs: a tela não contém a caixa de mensagens.
	 */
	static public Rectangle getPlayScrArea() {
		return playScrArea;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return uma ArrayList<Rectangle> - contendo cada um dos 28 espaços do
	 *         inventório. A ordenação é dada da seguinte forma: linha a linha,
	 *         e da esquerda para a direita: </nl> 0 1 2 3 </nl> 4 5 6 7 </nl> 8
	 *         9 10 11 </nl> 12 13 14 15 </nl> 16 17 18 19 </nl> 20 21 22 23
	 *         </nl> 24 25 26 27
	 * 
	 */
	static public ArrayList<Rectangle> getSlotList() {
		return slotList;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo contendo a linha do canto superior esquerdo da tela
	 *         onde o jogo informa as opções possíveis a serem executadas na
	 *         posição em que os mouse está(move here, attack, enfim...).
	 */
	static public Rectangle getTopScrActionsLine() {
		return actionsLine;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo contendo a linha onde o jogo informa a vida do
	 *         personagem (ao lado do coração, perto do mapa).
	 */
	static public Rectangle getHpLine() {
		return hpLine;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo contendo a linha onde o jogo informa os pontos de
	 *         "prayer" do personagem (ao lado do circulo azul com uma cruz,
	 *         próximo ao mapa).
	 */
	static public Rectangle getPrayerNumbersLine() {
		return prayerNumbersLine;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo contendo a linha onde o jogo informa a energia do
	 *         personagem (ao lado do circulo que contém uma botinha).
	 */
	static public Rectangle getRunNumbersLine() {
		return runNumbersLine;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo contendo as proximidades do jogador, para ser usado
	 *         em verificações próximas ao jogador.
	 */
	public static Rectangle getNearPlayer() {
		return nearPlayer;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo que engloba a area ao norte do jogador.
	 */
	public static Rectangle getNorthPlayerArea() {
		return playerAreaNorth;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo que engloba a area a esquerda do jogador.
	 */
	public static Rectangle getLeftlayerArea() {
		return playerAreaLeft;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo que engloba a area ao direita do jogador.
	 */
	public static Rectangle getRightlayerArea() {
		return playerAreaRight;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo que engloba a area ao sul do jogador.
	 */
	public static Rectangle getSouthlayerArea() {
		return playerAreaSouth;
	}

	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * 
	 * @return um retangulo que engloba a area do jogador apenas.
	 */
	public static Rectangle getCenterlayerArea() {
		return playerAreaCenter;
	}
	
	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * @return um ponto com o centro da tela.
	 */
	public static Point getPlayScreenCenter() {
		return screenCenter;
	}
	
	/**
	 * --Atenção só use este método se você já tiver setado o ScreenGetter (isto
	 * é usado ScreenGetter.set()! pelo menos uma vez) </nl>
	 * @return um retangulo contendo toda a aplicação java.
	 */
	public static Rectangle getJavaAppArea(){
		int x = playScrArea.x-3;
		int y = playScrArea.y-3;
		int height = 600;
		int width = 400;
		return javaAppArea;
		
	}
}
