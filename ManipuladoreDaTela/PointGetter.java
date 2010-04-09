import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class PointGetter {

	/**
	 * Esta classe cont�m m�todos para verificar a imagem da tela e atrav�s
	 * desta analise achar pontos de interesse do usu�rio. O construtor
	 * caracterisa como os pontos devem ser agrupados (assim achando possiveis
	 * padr�es). Definir a classe da maneira correta � fundamental para o
	 * reconhecimento dos padr�es!
	 * 
	 * </nl> M�todo padr�o de inicializa��o... Verifique tamb�m os outros
	 * m�todos.
	 * 
	 * </nl> /** Esta classe cont�m m�todos para verificar a imagem da tela e
	 * atrav�s desta analise achar pontos de interesse do usu�rio. O construtor
	 * caracterisa como os pontos devem ser agrupados (assim achando possiveis
	 * padr�es). Definir a classe da maneira correta � fundamental para o
	 * reconhecimento dos padr�es!
	 * 
	 * @param Rectangle
	 *            searchArea a area em que os pontos devem ser procurados.
	 * 
	 * @param int maxDist - a dist�ncia m�xima entre os pontos que atendem as
	 *        caracteristicas de cor. (Uma dist�ncia pequena s� agrupa pontos
	 *        pr�ximos).
	 * 
	 * @param Color
	 *            col - a cor de refer�ncia que os pontos devem ter.
	 * 
	 * @param int filterSize - a "dist�ncia" m�xima entre as cores.
	 * 
	 * @param int minSize - o minimo de pontos que o conjunto deve ter. Ex se
	 *        for 5, s� os grupos com pelo - cinco pontos ser�o considerados nos
	 *        m�todos da classe.
	 * @param simpleDelta
	 *            - se o filtro � simples ou n�o.
	 * @see Meth - para uma descri��o melhor do filtro (parametros filterSize e
	 *      simpleDelta ). </nl> ---As caracteristicas dos pontos podem ser
	 *      mudadas usando o m�todo .Set, entretanto a �rea da tela ser� sempre
	 *      a mesa (afinal esta classe foi feita para pegar os pontos em uma
	 *      regi�o da tela).
	 */
	private PointGetter() {
	}

	/**
	 * Ordena uma lista de acordo com a proximidade entre os pontos
	 * 
	 * @return A ArrayList ordenada.
	 */
	static private ArrayList<Point> sortByClosest(ArrayList<Point> list) {
		ArrayList<Point> pointList = list;
		int pos = -1;
		int minDelta = 9999999;
		for (int i = 1; i < list.size(); i++) {
			for (int j = 1; j < i; j++) {
				int thisDelta = (int) pointList.get(j - 1).distance(
						pointList.get(j));
				if (thisDelta < minDelta) {
					minDelta = thisDelta;
					pos = j - 1;
				}
			}
			if (pos != -1) {
				Point tempPoint = pointList.get(i);
				pointList.remove(i);
				pointList.add(pos, tempPoint);
				pos = -1;
			}
			minDelta = 9999999;

		}
		return pointList;

	}

	/**
	 * Procura na tela pelo maior conjunto de pontos que atendam as condi��es
	 * definidas nos parametros abaixo:
	 * 
	 * @return um Ponto que representa o centro deste conjunto.
	 */
	static public Point getProbPoint(Rectangle searchArea, Color col,
			int maxDist, int filterSize, int minSize, boolean simpleDelta) {
		ArrayList<Point> possiblePoints;
		possiblePoints = PixelHandler.filterGetAreaColorPoints(searchArea, col,
				filterSize, simpleDelta);
		if (possiblePoints.isEmpty())
			return null;
		possiblePoints = sortByClosest(possiblePoints);
		int listSize = 1;
		int indBegin = 0;
		int indCurrent = 0;
		for (int i = 1; i < possiblePoints.size(); i++) {
			int thisDelta = (int) possiblePoints.get(i - 1).distance(
					possiblePoints.get(i));
			if (thisDelta > maxDist) { // Temos ent�o uma quebra na lista e um
				// novo conjunto de pontos
				int thisSize = i - indCurrent;
				if (listSize < thisSize) { // Se o tamanho da lista for maior
					// usamos ent�o esta nova lista
					listSize = thisSize;
					indBegin = indCurrent;
				}
				indCurrent = i;
			}
		}
		if (listSize <= minSize) {
			return null;
		}
		int px = 0;
		int py = 0;
		for (int j = indBegin; j < indBegin + listSize; j++) {
			px = px + possiblePoints.get(j).x;
			py = py + possiblePoints.get(j).y;

		}
		px = (int) (px / listSize);
		py = (int) (py / listSize);
		return new Point(px, py);

	}

	/**
	 * Procura na tela pelo maior conjunto de pontos na proximida de um
	 * "ret�ngulo" informado com as propriedades definidas no construtor.
	 * 
	 * @param center
	 *            - um Ponto que representa o "centro do ret�ngulo".
	 * 
	 * @param rangex
	 *            - a maior distancia X que um ponto qualquer (especificado no
	 *            construtor) pode estar do ponto central (ou em outras palavras
	 *            aproximadamente a metade da base).
	 *@param rangex
	 *            - a maior distancia Y que um ponto qualquer (especificado no
	 *            construtor) pode estar do ponto central (ou em outras palavras
	 *            aproximadamente a metade da altura).
	 * 
	 * 
	 * @return um Ponto que representa o centro deste conjunto.
	 */
	static public Point getProbPointWithingRange(Point center, int rangex,
			int rangey, Rectangle searchArea, Color col, int maxDist,
			int filterSize, int minSize, boolean simpleDelta) {
		Rectangle area = searchArea;
		ArrayList<Point> possiblePoints;
		// Possible situations of error:
		if (center.x > searchArea.x)
			throw new NumberFormatException("Point.x is out of searching area");
		if (center.y > searchArea.y)
			throw new NumberFormatException("Point.y is out of searching area");
		if (rangex <= 0 && rangex <= 0)
			throw new NumberFormatException("Range must be > 0");

		int tempxi = center.x - rangex;
		int tempwidth = rangex * 2;
		int tempyi = center.y - rangey;
		int tempheight = rangey * 2;

		area.x = tempxi > area.x ? tempxi : area.x;
		area.width = tempwidth < area.width ? tempwidth : area.width;
		area.y = tempyi > area.y ? tempyi : area.y;
		area.height = tempheight < area.height ? tempheight : area.height;
		possiblePoints = PixelHandler.filterGetAreaColorPoints(area, col,
				filterSize, simpleDelta);
		if (possiblePoints.isEmpty())
			return null;
		possiblePoints = sortByClosest(possiblePoints);
		int listSize = 1;
		int indCurrent = 0;
		for (int i = 1; i < possiblePoints.size(); i++) {
			int thisDelta = (int) possiblePoints.get(i - 1).distance(
					possiblePoints.get(i));
			if (thisDelta > maxDist) { // Temos ent�o uma quebra na lista e um
				// novo conjunto de pontos
				int thisSize = i - indCurrent;
				if (listSize < thisSize) { // Se o tamanho da lista for maior
					// usamos ent�o esta nova lista
					listSize = thisSize;
				}
				indCurrent = i;
			}
		}
		if (listSize <= minSize) {
			System.out.println("ListSize <= minSize");
			return null;
		}
		int px = 0;
		int py = 0;
		px = (int) (px / listSize);
		py = (int) (py / listSize);
		return new Point(px, py);

	}

	/**
	 * Procura na tela por todos os conjunto de pontos com as propriedades
	 * definidas no construtor.
	 * 
	 * @return uma lista que representa o centro destes conjuntos.
	 */
	static public ArrayList<Point> getAllPossiblePoints(Rectangle searchArea,
			Color col, int maxDist, int filterSize, int minSize,
			boolean simpleDelta) {
		ArrayList<Point> possiblePoints;
		possiblePoints = PixelHandler.filterGetAreaColorPoints(searchArea, col,
				filterSize, simpleDelta);

		if (possiblePoints.isEmpty())
			return null;

		possiblePoints = sortByClosest(possiblePoints);
		ArrayList<Integer> ind = new ArrayList<Integer>();
		ArrayList<Point> points = new ArrayList<Point>();
		int indBegin = 0;

		for (int i = 1; i < possiblePoints.size(); i++) {
			int thisDelta = (int) possiblePoints.get(i - 1).distance(
					possiblePoints.get(i));
			if (thisDelta >= maxDist) {
				int thisSize = i - indBegin;
				if (thisSize > minSize) {
					ind.add(i);
					// ArrayList<Point> temp = new ArrayList<Point>();
					// for (int u = indBegin; u < i; u++) {
					// temp.add(possiblePoints.get(u));
					// }
					// Point testPoint = getCenterOf(temp);
					// if (testPoint != null)
					// points.add(testPoint);
				}
				indBegin = i;
			}
		}
		if (ind.isEmpty()) {
			ArrayList<Point> temp = new ArrayList<Point>();
			for (int i = 0; i < possiblePoints.size(); i++) {
				temp.add(possiblePoints.get(i));
			}
			Point testPoint = getCenterOf(temp);
			points.add(testPoint);
		} else {

			if (ind.size() == 1) {
				ArrayList<Point> temp = new ArrayList<Point>();
				for (int i = 0; i < ind.get(0); i++) {
					temp.add(possiblePoints.get(i));
				}
				Point testPoint = getCenterOf(temp);
				points.add(testPoint);

				for (int i = ind.get(0); i < possiblePoints.size(); i++) {
					temp.add(possiblePoints.get(i));
				}
				testPoint = getCenterOf(temp);
				points.add(testPoint);

			} else {
				ArrayList<Point> temp = new ArrayList<Point>();
				for (int i = 0; i < ind.get(0); i++) {
					temp.add(possiblePoints.get(i));
				}
				Point testPoint = getCenterOf(temp);
				points.add(testPoint);
				for (int j = 1; j < ind.size() - 1; j++) {
					ArrayList<Point> temp2 = new ArrayList<Point>();
					for (int i = ind.get(j - 1); i < ind.get(j); i++) {
						temp2.add(possiblePoints.get(i));
					}
					Point testPoint2 = getCenterOf(temp2);
					points.add(testPoint2);
				}

				for (int i = ind.get(ind.size() - 1); i < possiblePoints.size(); i++) {
					temp.add(possiblePoints.get(i));
				}
				testPoint = getCenterOf(temp);
				points.add(testPoint);
			}
		}
		//

		if (points.isEmpty()) {
			return null;
		}
		return points;
	}

	/**
	 * Procura na tela por todos os conjunto de pontos na proximida de um
	 * "ret�ngulo" informado com as propriedades definidas no construtor.
	 * 
	 * @param center
	 *            - um Ponto que representa o "centro do ret�ngulo".
	 * 
	 * @param rangex
	 *            - a maior distancia X que um ponto qualquer (especificado no
	 *            construtor) pode estar do ponto central (ou em outras palavras
	 *            aproximadamente a metade da base).
	 *@param rangex
	 *            - a maior distancia Y que um ponto qualquer (especificado no
	 *            construtor) pode estar do ponto central (ou em outras palavras
	 *            aproximadamente a metade da altura).
	 * 
	 * 
	 * @return uma lista com todos os Pontos que representa o centro destes
	 *         conjuntos.
	 */
	static public ArrayList<Point> getAllPossiblePointsWithingRange(
			Point center, int rangex, int rangey, Rectangle searchArea,
			Color col, int maxDist, int filterSize, int minSize,
			boolean simpleDelta) {
		Rectangle area = searchArea;
		ArrayList<Point> possiblePoints;
		// Possible situations of error:
		if (center.x > searchArea.x)
			throw new NumberFormatException("Point.x is out of searching area");
		if (center.y > searchArea.y)
			throw new NumberFormatException("Point.y is out of searching area");
		if (rangex <= 0 && rangex <= 0)
			throw new NumberFormatException("Range must be > 0");

		int tempxi = center.x - rangex;
		int tempwidth = rangex * 2;
		int tempyi = center.y - rangey;
		int tempheight = rangey * 2;

		area.x = tempxi > area.x ? tempxi : area.x;
		area.width = tempwidth < area.width ? tempwidth : area.width;
		area.y = tempyi > area.y ? tempyi : area.y;
		area.height = tempheight < area.height ? tempheight : area.height;
		possiblePoints = PixelHandler.filterGetAreaColorPoints(area, col,
				filterSize, simpleDelta);
		if (possiblePoints.isEmpty())
			return null;
		possiblePoints = sortByClosest(possiblePoints);
		ArrayList<Point> points = new ArrayList<Point>();
		int indBegin = 0;
		ArrayList<Integer> ind = new ArrayList<Integer>();
		for (int i = 1; i < possiblePoints.size(); i++) {
			int thisDelta = (int) possiblePoints.get(i - 1).distance(
					possiblePoints.get(i));
			if (thisDelta >= maxDist) {
				int thisSize = i - indBegin;
				if (thisSize > minSize) {
					ind.add(i);
					// ArrayList<Point> temp = new ArrayList<Point>();
					// for (int u = indBegin; u < i; u++) {
					// temp.add(possiblePoints.get(u));
					// }
					// Point testPoint = getCenterOf(temp);
					// if (testPoint != null)
					// points.add(testPoint);
				}
				indBegin = i;
			}
		}
		if (ind.isEmpty())
			return null;
		if (ind.size() == 1) {
			ArrayList<Point> temp = new ArrayList<Point>();
			for (int i = 0; i < ind.get(0); i++) {
				temp.add(possiblePoints.get(i));
			}
			Point testPoint = getCenterOf(temp);
			points.add(testPoint);
		} else {
			ArrayList<Point> temp = new ArrayList<Point>();
			for (int i = 0; i < ind.get(0); i++) {
				temp.add(possiblePoints.get(i));
			}
			Point testPoint = getCenterOf(temp);
			points.add(testPoint);
			for (int j = 1; j < ind.size() - 1; j++) {
				ArrayList<Point> temp2 = new ArrayList<Point>();
				for (int i = ind.get(j - 1); i < ind.get(j); i++) {
					temp2.add(possiblePoints.get(i));
				}
				Point testPoint2 = getCenterOf(temp2);
				points.add(testPoint2);
			}
		}
		if (points.isEmpty()) {
			System.out.println("No points found");
			return null;
		}
		return points;
	}

	/**
	 * Procura na tela pelo conjunto de pontos com as propriedades definidas no
	 * construtor mais pr�ximo de um ponto de refer�ncia informado.
	 * 
	 * @param p
	 *            - o ponto de ref�ncia.
	 * 
	 * @return um Ponto que representa o centro deste conjunto.
	 */
	static public Point getNearestToPoint(Point p, Rectangle searchArea,
			Color col, int maxDist, int filterSize, int minSize,
			boolean simpleDelta) {
		// ArrayList<Point> points = getAllPossiblePoints(searchArea, col,
		// maxDist, filterSize, minSize,
		// simpleDelta);
		ArrayList<Point> points = getAllPoints(searchArea, col, maxDist,
				filterSize, minSize, simpleDelta);

		if (points == null) {
			System.out.println("No points found");
			return null;
		}
		if (points.isEmpty())
			return null;
		int index = 0;
		int closest = 999999999;
		for (int i = 0; i < points.size(); i++) {
			int delta = (int) points.get(i).distance(p);
			if (delta < closest) {
				closest = delta;
				index = i;
			}
		}
		return points.get(index);
	}

	/**
	 * Procura na tela por todos os conjunto de pontos com as propriedades
	 * definidas no construtor mais pr�ximo de um ponto de refer�ncia informado
	 * na proximida de um ponto de refer�ncia.
	 * 
	 * @param center
	 *            - um Ponto que representa o "centro do ret�ngulo".
	 * 
	 * @param p
	 *            - o ponto de ref�ncia.
	 * 
	 * @param rangex
	 *            - a maior distancia X que um ponto qualquer (especificado no
	 *            construtor) pode estar do ponto central (ou em outras palavras
	 *            aproximadamente a metade da base).
	 *@param rangex
	 *            - a maior distancia Y que um ponto qualquer (especificado no
	 *            construtor) pode estar do ponto central (ou em outras palavras
	 *            aproximadamente a metade da altura).
	 * 
	 * 
	 * @return o ponto mais pr�ximo.
	 */
	static public Point getNearestToPointWithingRange(Point p, Point center,
			int rangex, int rangey, Rectangle searchArea, Color col,
			int maxDist, int filterSize, int minSize, boolean simpleDelta) {
		ArrayList<Point> points = getAllPossiblePointsWithingRange(center,
				rangex, rangey, searchArea, col, maxDist, filterSize, minSize,
				simpleDelta);
		if (points == null)
			return null;
		if (points.isEmpty())
			return null;
		int index = 0;
		int closest = 999999999;
		for (int i = 0; i < points.size(); i++) {
			int delta = (int) points.get(i).distance(p);
			if (delta < closest) {
				closest = delta;
				index = i;
			}
		}
		return points.get(index);
	}

	/**
	 * M�todo privado que acha o centro de massa de um conjunto de pontos.
	 * 
	 * @param points
	 *            - a ArrayList que cont�m os pontos.
	 * @return
	 */
	static private Point getCenterOf(ArrayList<Point> points) {
		int px = 0;
		int py = 0;
		if (points == null)
			return null;
		if (points.size() == 0)
			return null;
		for (int j = 0; j < points.size(); j++) {
			px = px + points.get(j).x;
			py = py + points.get(j).y;
		}
		px = (int) (px / points.size());
		py = (int) (py / points.size());
		return new Point(px, py);
	}

	/**
	 * Ccaracterisa como os pontos devem ser agrupados (assim achando possiveis
	 * padr�es). Definir a classe da maneira correta � fundamental para o
	 * reconhecimento dos padr�es!
	 * 
	 * @param Rectangle
	 *            searchArea a area em que os pontos devem ser procurados.
	 * 
	 * @param int maxDist - a dist�ncia m�xima entre os pontos que atendem as
	 *        caracteristicas de cor. (Uma dist�ncia pequena s� agrupa pontos
	 *        pr�ximos).
	 * 
	 * @param Color
	 *            col - a cor de refer�ncia que os pontos devem ter.
	 * 
	 * @param int filterSize - a "dist�ncia" m�xima entre as cores.
	 * 
	 * @param int minSize - o minimo de pontos que o conjunto deve ter. Ex se
	 *        for 5, s� os grupos com pelo - cinco pontos ser�o considerados nos
	 *        m�todos da classe.
	 * @param simpleDelta
	 *            - se o filtro � simples ou n�o.
	 * @see Meth - para uma descri��o melhor do filtro (parametros filterSize e
	 *      simpleDelta ). </nl> ---As caracteristicas dos pontos podem ser
	 *      mudadas usando este m�todo novamente, entretanto a �rea da tela ser�
	 *      sempre a mesa (afinal esta classe foi feita para pegar os pontos em
	 *      uma regi�o da tela).
	 */

	/**
	 * Procura na tela por todos os conjunto de pontos na proximida de um
	 * "ret�ngulo" informado com as propriedades definidas no construtor.
	 * Retorna um retangulo que cont�m todos os pontos achados.
	 * 
	 * @param center
	 *            - um Ponto que representa o "centro do ret�ngulo".
	 * 
	 * @param rangex
	 *            - a maior distancia X que um ponto qualquer (especificado no
	 *            construtor) pode estar do ponto central (ou em outras palavras
	 *            aproximadamente a metade da base).
	 *@param rangex
	 *            - a maior distancia Y que um ponto qualquer (especificado no
	 *            construtor) pode estar do ponto central (ou em outras palavras
	 *            aproximadamente a metade da altura).
	 * 
	 * 
	 * @return um retangulo que engloba todos os pontos ou nulo se n�o for
	 *         achado nenhum ponto (ou se achado mais de um).
	 */
	static public ArrayList<Rectangle> getPointsRectangle(Rectangle searchArea,
			Color col, int maxDist, int filterSize, int minSize,
			boolean simpleDelta) {
		ArrayList<Point> possiblePoints;
		possiblePoints = PixelHandler.filterGetAreaColorPoints(searchArea, col,
				filterSize, simpleDelta);
		ArrayList<Rectangle> pointsRec = new ArrayList<Rectangle>();

		if (possiblePoints.isEmpty() || possiblePoints.size() < minSize) {
			System.out.println("N�o foi achado pontos suficientes");
			return null;
		}
		possiblePoints = sortByClosest(possiblePoints);
		int minX = 999999999;
		int minY = 999999999;
		int maxX = -1;
		int maxY = -1;
		int index = 0;
		int count = 0;
		while (count < possiblePoints.size()) { // TODO usar o mesmo no
			// m�todo acima!
			index = count;
			for (int i = index; i < possiblePoints.size(); i++) {

				count++;
				// System.out.println(count);
				Point actual = possiblePoints.get(i);
				if (actual.x < minX)
					minX = actual.x;
				if (actual.x > maxX)
					maxX = actual.x;
				if (actual.y < minY)
					minY = actual.y;
				if (actual.y > maxY)
					maxY = actual.y;

				int thisDelta;
				if (i < possiblePoints.size() - 1) {
					thisDelta = (int) possiblePoints.get(i + 1)
							.distance(actual);
				} else {
					thisDelta = 999999999;
				}
				if (thisDelta > maxDist || i == possiblePoints.size()) {
					// System.out.println("I - INDEX: " + (i - index));
					if (i - index >= minSize) {
						Rectangle rec = new Rectangle(minX, minY, maxX-minX,maxY-minY);
						pointsRec.add(rec);
						minX = 999999999;
						minY = 999999999;
						maxX = -1;
						maxY = -1;
						break;
					}
				}
			}
		}
		return pointsRec;
	}

	static public ArrayList<Point> getAllPoints(Rectangle searchArea,
			Color col, int maxDist, int filterSize, int minSize,
			boolean simpleDelta) {
		ArrayList<Point> possiblePoints;
		possiblePoints = PixelHandler.filterGetAreaColorPoints(searchArea, col,
				filterSize, simpleDelta);
		ArrayList<Point> points = new ArrayList<Point>();

		if (possiblePoints.isEmpty() || possiblePoints.size() < minSize) {
			return null;
		}
		if (possiblePoints.size() == 1) {
			points.add(possiblePoints.get(0));
			return points;
		}
		possiblePoints = sortByClosest(possiblePoints);
		int minX = 999999999;
		int minY = 999999999;
		int maxX = -1;
		int maxY = -1;
		int index = 0;
		int count = 0;
		while (count < possiblePoints.size()) { // TODO usar o mesmo no
			// m�todo acima!
			index = count;
			for (int i = index; i < possiblePoints.size(); i++) {

				count++;
				// System.out.println(count);
				Point actual = possiblePoints.get(i);
				if (actual.x < minX)
					minX = actual.x;
				if (actual.x > maxX)
					maxX = actual.x;
				if (actual.y < minY)
					minY = actual.y;
				if (actual.y > maxY)
					maxY = actual.y;

				int thisDelta;
				if (i < possiblePoints.size() - 1) {
					thisDelta = (int) possiblePoints.get(i + 1)
							.distance(actual);
				} else {
					thisDelta = 999999999;
				}
				if (thisDelta > maxDist || i == possiblePoints.size()) {
					// System.out.println("I - INDEX: " + (i - index));
					if (i - index >= minSize) {
						Point p = new Point(minX + (maxX - minX) / 2, minY
								+ (maxY - minY) / 2);
						points.add(p);
						minX = 999999999;
						minY = 999999999;
						maxX = -1;
						maxY = -1;
						break;
					}
				}

			}
		}
		return points;

	}

	static public Rectangle getRectangleOfColor(Rectangle searchArea,
			Color col, int filterSize, int minSize, boolean simpleDelta) {
		ArrayList<Point> possiblePoints;
		possiblePoints = PixelHandler.filterGetAreaColorPoints(searchArea, col,
				filterSize, simpleDelta);

		if (possiblePoints.isEmpty() || possiblePoints.size() < minSize) {
			System.out.println("N�o foi achado pontos suficientes");
			return null;
		}
		int minX = 999999999;
		int minY = 999999999;
		int maxX = -1;
		int maxY = -1;
		int index = 1;
		int count = 1;

		for (int i = index; i < possiblePoints.size(); i++) {
			count++;
			Point actual = possiblePoints.get(i - 1);
			if (actual.x < minX)
				minX = actual.x;
			if (actual.x > maxX)
				maxX = actual.x;
			if (actual.y < minY)
				minY = actual.y;
			if (actual.y > maxY)
				maxY = actual.y;

			if (i == possiblePoints.size() - 1) {
				actual = possiblePoints.get(i);
				if (actual.x < minX)
					minX = actual.x;
				if (actual.x > maxX)
					maxX = actual.x;
				if (actual.y < minY)
					minY = actual.y;
				if (actual.y > maxY)
					maxY = actual.y;

			}

		}

		return new Rectangle(minX, minY, maxX - minX, maxY - minY);
	}

	/**
	 * Este m�todo procura por duas linhas verticais paralelas de uma ou mais
	 * cores.
	 * 
	 * @param areaToSearch
	 *            - a �rea para procurar.
	 * @param lineColor
	 *            - a cor da linha a ser procurada.
	 * @param fiterSizer
	 *            - o tamanho do filtro, mais cores semelhantes ser�o
	 *            consideradas. Se 0, s� a cor eespecificada ser� considerada.
	 * @param simpleDelta
	 *            - se o m�todo de compara��o deve ser simples(true) ou
	 *            vetorial.
	 * @param minSize
	 *            - o tamanho minimo que as linhas devem ter.
	 * @param maxSize
	 *            - o tamanho m�ximo que as linhas devem ter.
	 * @param maxDistBtw
	 *            - a distancia m�xima ente as duas linhas.
	 * @param minDistBtw
	 *            - a distancia minima entre as duas linhas.
	 * 
	 * 
	 * @return Retorna um retangulo que espressa onde a altura � a altura das
	 *         linhas, o y � onde elas come�am, o x � a posi��o da linha da
	 *         esquerda e a largura a distancia entre elas. Ou nulo se as
	 *         epecifica��es n�o atenderem.
	 */
	public static ArrayList<Rectangle> getParLines(Rectangle areaToSearch,
			Color lineColor, int filterSize, boolean simpleDelta, int minSize,
			int maxSize, int maxDistBtw, int minDistBtw) {
		ArrayList<Rectangle> parLines = new ArrayList<Rectangle>();
		ArrayList<Point[]> p = PixelHandler.getVerticalLine(areaToSearch,
				lineColor, filterSize, simpleDelta, minSize, maxSize);
		if (p == null) {
			RuneMethods.log("N�o foi encontrada linha!");
			return null;
		}
		if (p.size() == 1) {
			RuneMethods.log("S� foi encontrado 1 linha!");
			return null;
		}
		RuneMethods.log("Foram encontradas: " + p.size());
		//M�todo ruim, ele n�o compara todas as linhas com todas as linhas...P�ssimo.
		for (int i = 0; i < p.size() -1; i++) {
			RuneMethods.log("Verificando linhas");
			Point tpLeft = p.get(i)[0];
			Point tpRight = p.get(i + 1)[0];
			Point botLeft = p.get(i)[1];
			Point botRight = p.get(i + 1)[1];
			if (tpLeft.y == tpRight.y && botLeft.y == botLeft.y
					&& tpLeft.x == botLeft.x && tpRight.x == botRight.x) {
				RuneMethods.log("S�o iguais, verificando outros quit�rios...");
				Rectangle temp = new Rectangle(tpLeft.x, tpLeft.y, tpRight.x
						- tpLeft.x, botLeft.y - tpLeft.y);
				RuneMethods.log("Widht: " + temp.width + " Height: " + temp.height);
				if (Math.abs(temp.width) <= maxDistBtw && Math.abs(temp.width) >= minDistBtw) {
					parLines.add(temp);
					RuneMethods.log("Linhas verificadas e add");
				}
				else{
					RuneMethods.log("N�o atenderam ao quit�rio...");
				}
			}
		}
		if (parLines.isEmpty())
			return null;
		return parLines;

	}
}