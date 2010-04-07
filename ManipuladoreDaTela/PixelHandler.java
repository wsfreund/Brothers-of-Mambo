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
	 * Classe estática responsável pela análise dos pixels da tela.
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
	 *            o local do sistem (obs usar // ao invés de /)
	 * 
	 * @param file
	 *            o nome do arquivo
	 * 
	 * @param format
	 *            formato: (jpeg, bmp...)
	 * @return um Boolean: true se sucesso, false caso contrário.
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
	 * Esse método tira uma "foto" da área representada pelo retângulo e retorna
	 * ao usuário.
	 * 
	 * @param rect
	 *            um retangulo que representa a area da tela para "tirar foto".
	 * @return BufferedImage - a imagem da tela contida naquele retangulo (obs:
	 *         vale lembrar que os pontos não são transaladados, por isso a
	 *         imagem começara com cordenada 0,0 e não o x,y do retangulo).
	 */
	public static BufferedImage printScreen(Rectangle rect) {
		return SystemTools.getRobot().createScreenCapture(rect);
	}

	/**
	 * Esse método tira uma "foto" da tela.
	 * 
	 * @return BufferedImage - a imagem da tela..
	 */
	public static BufferedImage printScreen() {
		return SystemTools.getRobot().createScreenCapture(
				new Rectangle(SystemTools.getSystemScreenDimension()));
	}

	/**
	 * Método reponsável por analizar uma determinada área e retornar apenas os
	 * pontos cuja a cor é "parecida" com a dada. O critério de semelhança é
	 * dado pelo tamanho do filtro (ou seja o quão prómixa uma cor deve ser da
	 * outra) e pelo tipo de comparação do filtro -SimpleDelta- (caso true, ou
	 * seja filtro simples, a distância é x + y + z, caso contrario é (x² + y² +
	 * z²)*1/2).
	 * 
	 * @param area
	 *            - um retângulo informando a área a ser verificada.
	 * @param color
	 *            - uma cor de referência para comparar com as demais.
	 * @param filterSize
	 *            - a "distância" máxima entre as cores.
	 * @param simpleDelta
	 *            - se essa distância é simples (isto é x + y + z) ou (x² + y² +
	 *            z²)*1/2).
	 * 
	 * @return Uma ArrayList<Point> contendo os pontos que satisfazem as
	 *         condições.
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
	 * Método reponsável por analizar uma determinada área e retornar apenas os
	 * pontos cuja a cor é "parecida" com a dada. O critério de semelhança é
	 * dado pelo tamanho do filtro (ou seja o quão prómixa uma cor deve ser da
	 * outra) e pelo tipo de comparação do filtro -SimpleDelta- (caso true, ou
	 * seja filtro simples, a distância é x + y + z, caso contrario é (x² + y² +
	 * z²)*1/2). Diferente do método <code> filterGetAreaColorPoints </code> ele
	 * não vai checar toda uma area. Aqui você pode acrescentar um retangulo
	 * onde ele não vai verificar.
	 * 
	 * @param area
	 *            - um retângulo informando a área a ser verificada.
	 * @param exlude
	 *            - um retângulo informando a área que não deve ser verificada.
	 * @param color
	 *            - uma cor de referência para comparar com as demais.
	 * @param filterSize
	 *            - a "distância" máxima entre as cores.
	 * @param simpleDelta
	 *            - se essa distância é simples (isto é x + y + z) ou (x² + y² +
	 *            z²)*1/2).
	 * 
	 * @return Uma ArrayList<Point> contendo os pontos que satisfazem as
	 *         condições.
	 */
	public static ArrayList<Point> filterGetAreaColorPointsExcludePts(
			Rectangle area, Rectangle exclude, Color color, int filterSize,
			boolean simpleDelta) {
		BufferedImage image = printScreen(area);
		Color fColors = color;
		// A partir deste 2 retangulos cria outros 4 que serão percorridos pelo
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
	 * filto, cujo tamanho está compreendido entre o temanho máximo e minimo
	 * informados e retorna os pontos iniciais e finais desta reta.
	 * 
	 * @param area
	 *            a área para procurar por linhas.
	 * @param color
	 *            a cor da linha.
	 * @param filterSize
	 *            o temanho do filtro.
	 * @param simpleDelta
	 *            se o método de comparação é simples(o método fica mais
	 *            rápido), ou mais próximo do real(o método é mais lento).
	 * @param minSize
	 *            o tamanho minimo da linha
	 * @param maxSize
	 *            o temanho máximo da linha.
	 * @return os pontos iniciais e finais das linhas. Obs: sempre retornará
	 *         primeiro os dois pontos da linha com o menor x, sendo que desses
	 *         sempre será apresentado o de menor y.
	 * 
	 */

	/**
	 * 
	 * Atenção - este método não foi criado para uma aplicação prática, apenas
	 * para fins de teste.
	 * 
	 * Método usado para imprimir os pontos de uma ArrayList<Point> numa imagem
	 * "bmp".
	 * 
	 * @param inputFile
	 *            - um arquivo contendo a imagem
	 * @param points
	 *            - a lista que contém os pontos
	 * @param outputFile
	 *            - um outro arquivo onde a imagem será armazenada.
	 * @return boolean - true se sucesso, false caso contrário.
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
	 * Atenção - este método não foi criado para uma aplicação prática, apenas
	 * para fins de teste.
	 * 
	 * Método usado para imprimir os pontos de uma ArrayList<Point> numa imagem
	 * "bmp".
	 * 
	 * @param BufferedImage
	 *            - uma a imagem onde será impresso os pontos.
	 * @param points
	 *            - a lista que contém os pontos
	 * @param outputFile
	 *            - um outro arquivo onde a imagem será armazenada.
	 * @return boolean - true se sucesso, false caso contrário.
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
	 * Atenção - este método não foi criado para uma aplicação prática, apenas
	 * para fins de teste. Método usado para imprimir os pontos de uma
	 * ArrayList<Point> numa imagem. Esse método gera a imagem "testeImage.bmp"
	 * em "C:/".
	 * 
	 * @param BufferedImage
	 *            - uma a imagem onde será impresso os pontos.
	 * @param points
	 *            - a lista que contém os pontos
	 * @return boolean - true se sucesso, false caso contrário.
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
	 * Atenção - este método não foi criado para uma aplicação prática, apenas
	 * para fins de teste.
	 * 
	 * Método usado para imprimir os retangulos de uma ArrayList<Rectangle> numa
	 * imagem. Esse método gera a imagem "testeImage.bmp" em "C:/".
	 * 
	 * @param BufferedImage
	 *            - uma a imagem onde será impresso os pontos.
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
	 * Procura e retorna por linhas vericais que atendam os critérios dados.
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
					init.y = y + 1; // O ponto seguinte será o adicionado
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
					init.x = x + 1; // O ponto seguinte será o adicionado
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
