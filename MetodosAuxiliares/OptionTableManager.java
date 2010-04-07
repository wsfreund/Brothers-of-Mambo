import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class OptionTableManager {
	// [r=43,g=39,b=28] r=198,g=184,b=149
	private static Color yLineColor = new Color(82, 74, 61);

	private OptionTableManager() {
	}

	/**
	 * Usando o m�todo point getter, uma lista contendo possiveis retangulos, se
	 * for achado somente um retangulo devolve ele, caso contr�rio devolve nulo.
	 * 
	 * @return um retangulo, ou nulo caso insucedido.
	 */
	public static Rectangle getLootRectangle() {
		ArrayList<Point[]> p = PixelHandler.getVerticalLine(ScreenGetter
				.getJavaAppArea(), yLineColor, 1, true, 6, 600);
		if (p == null)
			return null;
		if (p.size() > 4) {
			System.out.println("Menor do q 4");

			return null;
		}
		Point tpLeft = p.get(0)[0];
		Point tpRight = p.get(1)[0];
		Point botLeft = p.get(0)[1];
		Point botRight = p.get(1)[1];
		if (tpLeft.y == tpRight.y && botLeft.y == botLeft.y
				&& tpLeft.x == botLeft.x && tpRight.x == botRight.x) {
			System.out.println("Cheguei aqui");
			int deX = 0;
			int deY = 3;
			int dWi = 0;
			int dHe = -16;
			return new Rectangle(tpLeft.x + deX, tpLeft.y + deY, tpRight.x
					- tpLeft.x + dWi, botLeft.y - tpLeft.y + dHe);
		}
		return null;
	}

	// Altura do retangulo = 16;
	public static ArrayList<Rectangle> getRectanglesLootLine() {
		ArrayList<Rectangle> listOfLines = new ArrayList<Rectangle>();
		Rectangle box = getLootRectangle();

		if (box == null)
			return null;
		for (int i = 0; i < box.height; i = i + 16) {
			int y = box.y + i;
			listOfLines.add(new Rectangle(box.x, y, box.width, 15));
		}
		return listOfLines;
	}

	public static ArrayList<OptionTableLine> getListOfOptions() {
		ArrayList<OptionTableLine> listOfOptions = new ArrayList<OptionTableLine>();
		OptionTableCharReader reader = new OptionTableCharReader();
		ArrayList<Rectangle> listOfLines = getRectanglesLootLine();
		if (listOfLines == null)
			return null;
		for (int i = 0; i < listOfLines.size(); i++) {
			System.out.println("Elemento " + i + " valores : ");
			Rectangle actualRec = listOfLines.get(i);
			reader.setLineBeginX(actualRec.x);
			reader.setLineBeginY(actualRec.y);
			reader.setLineWidht(actualRec.width);
			String option = reader.getMensage();
			System.out.println("Option: " + option);
			listOfOptions.add(new OptionTableLine(actualRec, option));

		}
		return listOfOptions;

	}

	public static ArrayList<OptionTableLine> getListOfNames() {
		ArrayList<OptionTableLine> listOfOptions = new ArrayList<OptionTableLine>();
		OptionTableCharReader reader = new OptionTableCharReader();
		ArrayList<Rectangle> listOfLines = getRectanglesLootLine();
		if (listOfLines == null)
			return null;
		for (int i = 0; i < listOfLines.size(); i++) {
			System.out.println("Elemento " + i + " valores : ");
			Rectangle actualRec = listOfLines.get(i);
			reader.setLineBeginX(actualRec.x);
			reader.setLineBeginY(actualRec.y);
			reader.setLineWidht(actualRec.width);
			String option = reader.getMensageItemName();
			if (option.isEmpty() || option == null
					|| option.replace(" ", "").isEmpty())
				continue;
			System.out.println("Option: " + option);
			listOfOptions.add(new OptionTableLine(actualRec, option));

		}
		return listOfOptions;

	}

	/**
	 * M�todo que pega um item no ch�o da seguinte maneira: primeiro ele clica
	 * com o direito no ponto no ch�o, depois verifica se na tabela de op��es
	 * cont�m as op��es com a(s) palavra(s) chave(s) informada(s). E clica nela.
	 * 
	 * @param position
	 *            - a posi��o do loot.
	 * 
	 * @param contaningWords
	 *            - a(s) palavra(s) que a op��o deve conter.
	 * @param clickCancel
	 *            - caso TRUE - se n�o for achada a op��o indicada, o m�todo
	 *            clicar� em cancel e retornar� "-1". caso False - s� retornar�
	 *            "-1".
	 * @return - um inteiro que representa a quantidade de itens encontrados, ou
	 *         -1 caso nenhuma seja encontrada. Este m�todo possibilita que o
	 *         principal pegue os 2 itens caso encontrados.
	 * 
	 */
	public static int getOptionOnPoint(Point position, String[] contaningWords,
			boolean clickCancel) { // TODO fazer m�todo para String, "OU". E
									// fazer m�todo para pegar que procura por
									// mais de um loot sem ter que abrir 2
									// vezes.
		Point p = position;
		MouseHandler.dragMouse(p, 0, true, 0);
		RuneMethods.wait(Meth.intRandom(30, 50));
		MouseHandler.MouseRightClick();
		RuneMethods.wait(Meth.intRandom(200, 300));
		ArrayList<OptionTableLine> listOfOptions = getListOfOptions();
		if (listOfOptions == null)
			return -1;
		if (listOfOptions.size() == 0)
			return -1;
		ArrayList<Integer> index = new ArrayList<Integer>();
		int cancelIndex = -1;
		for (int i = 0; i < listOfOptions.size(); i++) {
			String option = listOfOptions.get(i).option;
			boolean add = true;
			for (int temp = 0; temp < contaningWords.length; temp++) {
				if (!(option.contains(contaningWords[temp]))) {
					add = false;
					break;
				}
			}
			if (add)
				index.add(i);
			else {
				if (option.contains("Cancel")) {
					cancelIndex = i;
				}
			}
		}
		int size = index.size();
		if (size == 0) {
			if (clickCancel) {
				if (cancelIndex == -1)
					return -1;
				Rectangle lineOption = listOfOptions.get(cancelIndex).line;
				MouseHandler.dragMouse(new Point(lineOption.x
						+ Meth.intRandom(3, lineOption.width - 3), lineOption.y
						+ Meth.intRandom(3, lineOption.height - 3)), 0, true,
						0.05);
				RuneMethods.wait(Meth.intRandom(30, 90));
				MouseHandler.MouseLeftClick(false);
			}
			return -1;
		}
		System.out.println(0);
		Rectangle lineOption = listOfOptions.get(index.get(0)).line;
		MouseHandler.dragMouse(new Point(lineOption.x
				+ Meth.intRandom(3, lineOption.width - 3), lineOption.y
				+ Meth.intRandom(3, lineOption.height - 3)), 0, true, 0.05);
		RuneMethods.wait(Meth.intRandom(30, 90));
		MouseHandler.MouseLeftClick(false);
		RuneMethods.wait(Meth.intRandom(30, 90));
		return size;
	}

	/**
	 * M�todo que pega um item no ch�o da seguinte maneira: primeiro ele clica
	 * com o direito no ponto no ch�o, depois verifica se na tabela de op��es
	 * cont�m as op��es com a(s) palavra(s) chave(s) informada(s). E clica nela.
	 * 
	 * @param position
	 *            - a posi��o do loot.
	 * 
	 * @param contaningWords
	 *            - a(s) palavra(s) que se encontradas ser�o contadas. Se achar
	 *            uma OU outra ser� adicionada.
	 * @param clickCancel
	 *            - caso TRUE - se n�o for achada a op��o indicada, o m�todo
	 *            clicar� em cancel e retornar� "-1". caso False - s� retornar�
	 *            "-1".
	 * @return - um inteiro que representa a quantidade de itens encontrados, ou
	 *         -1 caso nenhuma seja encontrada. Este m�todo possibilita que o
	 *         principal pegue os 2 itens caso encontrados.
	 * 
	 */
	public static int getOptionOnPointContaningOneOrOther(Point position,
			String[] contaningWords, boolean clickCancel) { // TODO fazer m�todo
															// para String,
															// "OU". E fazer
															// m�todo para pegar
															// que procura por
															// mais de um loot
															// sem ter que abrir
															// 2 vezes.
		Point p = position;
		MouseHandler.dragMouse(p, 0, true, 0);
		RuneMethods.wait(Meth.intRandom(30, 50));
		MouseHandler.MouseRightClick();
		RuneMethods.wait(Meth.intRandom(200, 300));
		ArrayList<OptionTableLine> listOfOptions = getListOfOptions();
		if (listOfOptions == null)
			return -1;
		if (listOfOptions.size() == 0)
			return -1;
		ArrayList<Integer> index = new ArrayList<Integer>();
		int cancelIndex = -1;
		for (int i = 0; i < listOfOptions.size(); i++) {
			String option = listOfOptions.get(i).option;
			boolean add = false;
			for (int temp = 0; temp < contaningWords.length; temp++) {
				if ((option.contains(contaningWords[temp]))) {
					add = true;
					break;
				}
			}
			if (add)
				index.add(i);
			else {
				if (option.contains("Cancel")) {
					cancelIndex = i;
				}
			}
		}
		int size = index.size();
		if (size == 0) {
			if (clickCancel) {
				if (cancelIndex == -1)
					return -1;
				Rectangle lineOption = listOfOptions.get(cancelIndex).line;
				MouseHandler.dragMouse(new Point(lineOption.x
						+ Meth.intRandom(3, lineOption.width - 3), lineOption.y
						+ Meth.intRandom(3, lineOption.height - 3)), 0, true,
						0.05);
				RuneMethods.wait(Meth.intRandom(30, 90));
				MouseHandler.MouseLeftClick(false);
			}
			return -1;
		}
		System.out.println(0);
		Rectangle lineOption = listOfOptions.get(index.get(0)).line;
		MouseHandler.dragMouse(new Point(lineOption.x
				+ Meth.intRandom(3, lineOption.width - 3), lineOption.y
				+ Meth.intRandom(3, lineOption.height - 3)), 0, true, 0.05);
		RuneMethods.wait(Meth.intRandom(30, 90));
		MouseHandler.MouseLeftClick(false);
		RuneMethods.wait(Meth.intRandom(30, 90));
		size = index.size();
		return size;
	}

	/**
	 * Procura na tabela de op��es por um unico nome. Se s� houver um nome
	 * (ex:get BONE, bury BONE, tudo BONE) ele devolve o nome (BONE). Caso
	 * contr�rio (se tiver BONE e BANANA por exemplo) ele devolve "-1". A fun��o
	 * deste m�todo � identificar itens no inv.
	 * 
	 * @param open
	 *            - true se o m�todo deve abrir a lista (isto � clicar com o
	 *            direito no ponto), false caso contr�rio.
	 * @param close
	 *            - true se depois de abrir o m�todo de fechar a tabela (isto �
	 *            clicar em cancel).
	 * 
	 * @return
	 */
	public static String getName(Point position) {
		System.out.println("Estou aqui");
		Point p = position;
		MouseHandler.dragMouse(p, 0, true, 0);
		RuneMethods.wait(Meth.intRandom(30, 50));
		MouseHandler.MouseRightClick();
		RuneMethods.wait(Meth.intRandom(200, 300));
		ArrayList<OptionTableLine> listOfOptions = getListOfNames();
		if (listOfOptions == null || listOfOptions.size() == 0) {
			System.out.println("A lista est� vazia");
			clickOnOption(new String[] { "Cancel" }, true);
			return "-1";
		}
		if (listOfOptions.size() == 1) {
			System.out.println("!!!!!!!!!!!!!!!!!!!! nome final: "
					+ cleanName(listOfOptions.get(0).option));
			clickOnOption(new String[] { "Cancel" }, true);
			return cleanName(listOfOptions.get(0).option);
		}
		for (int i = 1; i < listOfOptions.size() - 1; i++) {
			System.out.println((cleanName(listOfOptions.get(i - 1).option)
					+ " is equal/not equal to: " + (cleanName(listOfOptions
					.get(i).option))));
			if (!(cleanName(listOfOptions.get(i - 1).option)
					.equals(cleanName(listOfOptions.get(i).option)))) {
				System.out.println((cleanName(listOfOptions.get(i - 1).option)
						+ " is not equal to: " + (cleanName(listOfOptions
						.get(i).option))));
				clickOnOption(new String[] { "Cancel" }, true);
				return "-1";
			}
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!! nome final: "
				+ cleanName(listOfOptions.get(0).option));
		clickOnOption(new String[] { "Cancel" }, true);
		return cleanName(listOfOptions.get(0).option);

	}

	private static String cleanName(String name) {
		int begin = 0;
		int end = 0;

		for (int i = 0; i < name.length(); i++) {
			if (!(Character.isSpaceChar(name.charAt(i)))) {
				begin = i;
				break;
			}
		}
		for (int i = name.length() - 1; i > 0; i--) {
			if (!Character.isSpaceChar(name.charAt(i))) {
				end = i + 1;
				break;
			}
		}
		System.out.println("begin: " + begin + " End: " + end);
		return name.substring(begin, end);
	}

	/**
	 * Clica numa determinada op��o, caso n�o haja, ele tentar� clicar em cancel
	 * e devolver� "-1".
	 * 
	 * @param contaningWords
	 *            - a(s) palavra(s) que a op��o deve conter.
	 * @param clickCancel
	 *            - caso TRUE - se n�o for achada a op��o indicada, o m�todo
	 *            clicar� em cancel e retornar� "-1". caso False - s� retornar�
	 *            "-1".
	 * @return - um inteiro que representa a quantidade de itens encontrados, ou
	 *         -1 caso nenhuma seja encontrada. Este m�todo possibilita que o
	 *         principal pegue os 2 itens caso encontrados.
	 */
	public static int clickOnOption(String[] contaningWords, boolean clickCancel) {
		ArrayList<OptionTableLine> listOfOptions = getListOfOptions();
		if (listOfOptions == null)
			return -1;
		if (listOfOptions.size() == 0)
			return -1;
		ArrayList<Integer> index = new ArrayList<Integer>();
		int cancelIndex = -1;
		for (int i = 0; i < listOfOptions.size(); i++) {
			String option = listOfOptions.get(i).option;
			boolean add = true;
			for (int temp = 0; temp < contaningWords.length; temp++) {
				if (!(option.contains(contaningWords[temp]))) {
					add = false;
					break;
				}
			}
			if (add)
				index.add(i);
			else {
				if (option.contains("Cancel")) {
					cancelIndex = i;
				}
			}
		}
		int size = index.size();
		if (size == 0) {
			if (clickCancel) {
				if (cancelIndex == -1)
					return -1;
				Rectangle lineOption = listOfOptions.get(cancelIndex).line;
				MouseHandler.dragMouse(new Point(lineOption.x
						+ Meth.intRandom(3, lineOption.width - 3), lineOption.y
						+ Meth.intRandom(3, lineOption.height - 3)), 0, true,
						0.05);
				RuneMethods.wait(Meth.intRandom(30, 90));
				MouseHandler.MouseLeftClick(false);
				RuneMethods.wait(Meth.intRandom(30, 90));
			}
			return -1;
		}
		System.out.println(0);
		Rectangle lineOption = listOfOptions.get(index.get(0)).line;
		MouseHandler.dragMouse(new Point(lineOption.x
				+ Meth.intRandom(3, lineOption.width - 3), lineOption.y
				+ Meth.intRandom(3, lineOption.height - 3)), 0, true, 0.05);
		RuneMethods.wait(Meth.intRandom(30, 90));
		MouseHandler.MouseLeftClick(false);
		RuneMethods.wait(Meth.intRandom(30, 90));
		return size;
	}
}
