import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PixelHandler {

	/**
	 * Classe est�tica respons�vel pela an�lise dos pixels da tela.
	 */
	private PixelHandler() {
	}

	/**
	 * Render BufferedImage num arquivo.
	 * 
	 * @param img
	 *            a "BufferedImage"
	 * 
	 * @param place
	 *            o local do sistem (obs usar // ao inv�s de /)
	 * 
	 * @param file
	 *            o nome do arquivo
	 * 
	 * @param format
	 *            formato: (jpeg, bmp...)
	 * @return um Boolean: true se sucesso, false caso contr�rio.
	 */
	public static boolean renderBufImg(BufferedImage img, String place,
			String fileName, String format) {
		try {
			ImageIO.write(img, format,
					new File(place + fileName + "." + format));
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Esse m�todo tira uma "foto" da �rea representada pelo ret�ngulo e retorna
	 * ao usu�rio.
	 * 
	 * @param rect
	 *            um retangulo que representa a area da tela para "tirar foto".
	 * @return BufferedImage - a imagem da tela contida naquele retangulo (obs:
	 *         vale lembrar que os pontos n�o s�o transaladados, por isso a
	 *         imagem come�ara com cordenada 0,0 e n�o o x,y do retangulo).
	 */
	public static BufferedImage printScreen(Rectangle rect) {
		return SystemTools.getRobot().createScreenCapture(rect);
	}

	/**
	 * Esse m�todo tira uma "foto" da tela.
	 * 
	 * @return BufferedImage - a imagem da tela..
	 */
	public static BufferedImage printScreen() {
		return SystemTools.getRobot().createScreenCapture(
				new Rectangle(SystemTools.getSystemScreenDimension()));
	}

	/**
	 * M�todo repons�vel por analizar uma determinada �rea e retornar apenas os
	 * pontos cuja a cor � "parecida" com a dada. O crit�rio de semelhan�a �
	 * dado pelo tamanho do filtro (ou seja o qu�o pr�mixa uma cor deve ser da
	 * outra) e pelo tipo de compara��o do filtro -SimpleDelta- (caso true, ou
	 * seja filtro simples, a dist�ncia � x + y + z, caso contrario � (x� + y� +
	 * z�)*1/2).
	 * 
	 * @param area
	 *            - um ret�ngulo informando a �rea a ser verificada.
	 * @param color
	 *            - uma cor de refer�ncia para comparar com as demais.
	 * @param filterSize
	 *            - a "dist�ncia" m�xima entre as cores.
	 * @param simpleDelta
	 *            - se essa dist�ncia � simples (isto � x + y + z) ou (x� + y� +
	 *            z�)*1/2).
	 * 
	 * @return Uma ArrayList<Point> contendo os pontos que satisfazem as
	 *         condi��es.
	 */
	public static ArrayList<Point> filterGetAreaColorPoints(Rectangle area,
			Color color, int filterSize, boolean simpleDelta) {
		BufferedImage image = printScreen(area);
		Color fColors = color;
		ArrayList<Point> points = new ArrayList<Point>();
		if (fColors == null)
			return null;
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		for (int x = image.getMinX(); x < imgWidth; x++) {
			for (int y = image.getMinY(); y < imgHeight; y++) {
				int compare = Meth.getDelta(new Color(image.getRGB(x, y)),
						fColors, simpleDelta);
				if (compare <= filterSize) {
					// System.out.println("Found new match:" + x + "," + y);
					points.add(new Point(x + area.x, y + area.y));
				}
			}

		}
		return points;
	}

	/**
	 * M�todo repons�vel por analizar uma determinada �rea e retornar apenas os
	 * pontos cuja a cor � "parecida" com a dada. O crit�rio de semelhan�a �
	 * dado pelo tamanho do filtro (ou seja o qu�o pr�mixa uma cor deve ser da
	 * outra) e pelo tipo de compara��o do filtro -SimpleDelta- (caso true, ou
	 * seja filtro simples, a dist�ncia � x + y + z, caso contrario � (x� + y� +
	 * z�)*1/2). Diferente do m�todo <code> filterGetAreaColorPoints </code> ele
	 * n�o vai checar toda uma area. Aqui voc� pode acrescentar um retangulo
	 * onde ele n�o vai verificar.
	 * 
	 * @param area
	 *            - um ret�ngulo informando a �rea a ser verificada.
	 * @param exlude
	 *            - um ret�ngulo informando a �rea que n�o deve ser verificada.
	 * @param color
	 *            - uma cor de refer�ncia para comparar com as demais.
	 * @param filterSize
	 *            - a "dist�ncia" m�xima entre as cores.
	 * @param simpleDelta
	 *            - se essa dist�ncia � simples (isto � x + y + z) ou (x� + y� +
	 *            z�)*1/2).
	 * 
	 * @return Uma ArrayList<Point> contendo os pontos que satisfazem as
	 *         condi��es.
	 */
	public static ArrayList<Point> filterGetAreaColorPointsExcludePts(
			Rectangle area, Rectangle exclude, Color color, int filterSize,
			boolean simpleDelta) {
		BufferedImage image = printScreen(area);
		Color fColors = color;
		// A partir deste 2 retangulos cria outros 4 que ser�o percorridos pelo
		// for:

		ArrayList<Rectangle> rectangles = Meth.getAreaMinusAreaRec(area,
				exclude);
		ArrayList<Point> points = new ArrayList<Point>();
		if (rectangles.size() == 0)
			return points;
		if (fColors == null)
			return null;
		for (int i = 0; i < rectangles.size(); i++) {
			Rectangle tempRec = rectangles.get(i);
			for (int x = 0; x < tempRec.width; x++) {
				for (int y = 0; y < tempRec.height; y++) {
					int compare = Meth.getDelta(new Color(image.getRGB(x, y)),
							fColors, simpleDelta);
					if (compare <= filterSize) {
						// System.out.println("Found new match:" + x + "," + y);
						points.add(new Point(x + tempRec.x, y + tempRec.y));
					}
				}
			}

		}
		return points;
	}

	/**
	 * Este metodo pega todas as linhas verticais da cor informada + tamanho do
	 * filto, cujo tamanho est� compreendido entre o temanho m�ximo e minimo
	 * informados e retorna os pontos iniciais e finais desta reta.
	 * 
	 * @param area
	 *            a �rea para procurar por linhas.
	 * @param color
	 *            a cor da linha.
	 * @param filterSize
	 *            o temanho do filtro.
	 * @param simpleDelta
	 *            se o m�todo de compara��o � simples(o m�todo fica mais
	 *            r�pido), ou mais pr�ximo do real(o m�todo � mais lento).
	 * @param minSize
	 *            o tamanho minimo da linha
	 * @param maxSize
	 *            o temanho m�ximo da linha.
	 * @return os pontos iniciais e finais das linhas. Obs: sempre retornar�
	 *         primeiro os dois pontos da linha com o menor x, sendo que desses
	 *         sempre ser� apresentado o de menor y.
	 * 
	 */

	/**
	 * 
	 * Aten��o - este m�todo n�o foi criado para uma aplica��o pr�tica, apenas
	 * para fins de teste.
	 * 
	 * M�todo usado para imprimir os pontos de uma ArrayList<Point> numa imagem
	 * "bmp".
	 * 
	 * @param inputFile
	 *            - um arquivo contendo a imagem
	 * @param points
	 *            - a lista que cont�m os pontos
	 * @param outputFile
	 *            - um outro arquivo onde a imagem ser� armazenada.
	 * @return boolean - true se sucesso, false caso contr�rio.
	 */
	public static boolean testPrintPoints(File inputFile,
			ArrayList<Point> points, File outputFile) {
		try {
			BufferedImage im = ImageIO.read(inputFile);
			for (int i = 0; i < points.size(); i++) {
				im.setRGB(points.get(i).x, points.get(i).y, 4304033);
			}
			ImageIO.write(im, "bmp", outputFile);
			return true;
		} catch (IOException e) {
			return true;
		}
	}

	/**
	 * Aten��o - este m�todo n�o foi criado para uma aplica��o pr�tica, apenas
	 * para fins de teste.
	 * 
	 * M�todo usado para imprimir os pontos de uma ArrayList<Point> numa imagem
	 * "bmp".
	 * 
	 * @param BufferedImage
	 *            - uma a imagem onde ser� impresso os pontos.
	 * @param points
	 *            - a lista que cont�m os pontos
	 * @param outputFile
	 *            - um outro arquivo onde a imagem ser� armazenada.
	 * @return boolean - true se sucesso, false caso contr�rio.
	 */
	public static boolean testPrintPoints(BufferedImage im,
			ArrayList<Point> points, File outputFile) {
		try {
			for (int i = 0; i < points.size(); i++) {
				im.setRGB(points.get(i).x, points.get(i).y, 34534534);
			}
			ImageIO.write(im, "teste.bmp", outputFile);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Aten��o - este m�todo n�o foi criado para uma aplica��o pr�tica, apenas
	 * para fins de teste. M�todo usado para imprimir os pontos de uma
	 * ArrayList<Point> numa imagem. Esse m�todo gera a imagem "testeImage.bmp"
	 * em "C:/".
	 * 
	 * @param BufferedImage
	 *            - uma a imagem onde ser� impresso os pontos.
	 * @param points
	 *            - a lista que cont�m os pontos
	 * @return boolean - true se sucesso, false caso contr�rio.
	 */
	public static boolean testPrintPoints(BufferedImage im,
			ArrayList<Point> points) {
		try {
			BufferedImage ima = im;
			for (int i = 0; i < points.size(); i++) {
				ima.setRGB(points.get(i).x, points.get(i).y, 3493494);
			}
			ImageIO.write(ima, "bmp", new File("C://testeImage.bmp"));
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Aten��o - este m�todo n�o foi criado para uma aplica��o pr�tica, apenas
	 * para fins de teste.
	 * 
	 * M�todo usado para imprimir os retangulos de uma ArrayList<Rectangle> numa
	 * imagem. Esse m�todo gera a imagem "testeImage.bmp" em "C:/".
	 * 
	 * @param BufferedImage
	 *            - uma a imagem onde ser� impresso os pontos.
	 * @param rectangles
	 *            - uma ArrayList<Rectangle> contendo todos os retangulos a
	 *            serem impressos.
	 * 
	 * @return BufferedImage - a imagem com os retangulos impressos.
	 */
	public static BufferedImage PrintRectangle(ArrayList<Rectangle> rectangles,
			BufferedImage im) {
		BufferedImage ima = im;
		int col = 20444;
		for (int i = 0; i < rectangles.size(); i++) {
			Rectangle rec = rectangles.get(i);
			for (int x = rec.x; x < rec.x + rec.width; x++) {
				ima.setRGB(x, rec.y, col);
				ima.setRGB(x, rec.y + rec.height, col);
			}
			for (int y = rec.y; y < rec.y + rec.height; y++) {
				ima.setRGB(rec.x, y, col);
				ima.setRGB(rec.x + rec.width, y, col);
			}
			col = col + 35000;
		}
		return ima;
	}

	/**
	 * Procura e retorna por linhas vericais que atendam os crit�rios dados.
	 * 
	 * @param area
	 * @param color
	 * @param filterSize
	 * @param simpleDelta
	 * @param minSize
	 * @param maxSize
	 * @return Uma lista contendoum vetor de pontos com o ponto incial e final.
	 *         Sendo o primeiro com o menor Y.
	 */
	public static ArrayList<Point[]> getVerticalLine(Rectangle area,
			Color color, int filterSize, boolean simpleDelta, int minSize,
			int maxSize) {

		ArrayList<Point[]> linePoints = new ArrayList<Point[]>();
		BufferedImage image = printScreen(area);
		int size = 0;
		for (int x = 0; x < area.width; x++) {
			Point init = new Point(x, 0);
			for (int y = 0; y < area.height; y++) {
				int compare = Meth.getDelta(new Color(image.getRGB(x, y)),
						color, simpleDelta);
				if (compare <= filterSize) {
					size++;
				} else {
					if (size >= minSize && size <= maxSize) {
						Point[] those = {
								new Point(area.x + x, area.y + init.y),
								new Point(area.x + x, area.y + init.y + size) };
						linePoints.add(those);
						System.out
								.println("quebra de pontos tamanho da linha: "
										+ size
										+ "logo adicionado pontos: "
										+ new Point(area.x + x, area.y + init.y)
										+ new Point(area.x + x, area.y + init.y
												+ size));
					}
					size = 0;
					init.y = y + 1; // O ponto seguinte ser� o adicionado
					// por + 1 no for...
				}
			}
		}
		if (linePoints.isEmpty()) {
			System.out.println("vazio");
			return null;
		}
		return linePoints;
	}

	public static ArrayList<Point[]> getHorizontalLine(Rectangle area,
			Color color, int filterSize, boolean simpleDelta, int minSize,
			int maxSize) {

		ArrayList<Point[]> linePoints = new ArrayList<Point[]>();
		BufferedImage image = printScreen(area);
		int size = 0;
		for (int y = 0; y < area.height; y++) {
			Point init = new Point(0, y);
			for (int x = 0; x < area.width; x++) {
				int compare = Meth.getDelta(new Color(image.getRGB(x, y)),
						color, simpleDelta);
				if (compare <= filterSize) {
					size++;
				} else {
					if (size >= minSize && size <= maxSize) {
						Point[] those = {
								new Point(area.x + init.x, y),
								new Point(area.x + init.x + size, y) };
						linePoints.add(those);
						System.out
								.println("quebra de pontos tamanho da linha: "
										+ size
										+ "logo adicionado pontos: "
										+ new Point(area.x + init.x, y)
										+ new Point(area.x + init.x + size, y));
					}
					size = 0;
					init.x = x + 1; // O ponto seguinte ser� o adicionado
					// por + 1 no for...
				}
			}
		}
		if (linePoints.isEmpty()) {
			System.out.println("vazio");
			return null;
		}
		return linePoints;
	}

}
