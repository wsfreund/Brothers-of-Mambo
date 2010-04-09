import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;


public class InventoryHandler {
	/**
	 * Classe estática, responsavel por "pegar" o inventorio, de modo a poder
	 * trabalhar com ele.
	 */
	private static InventoryCodDecod codDecod = new InventoryCodDecod(new File(
	"Codes.txt"));
	
	private InventoryHandler() {
		 
	}

	

	/**
	 * Retorna uma lista contendo os Slots do inventorio, os slots terão o seu
	 * nome original ou Unknow para os desconhecidos.
	 */
	public static ArrayList<Slot> getInventory() {
		ArrayList<Rectangle> slotRectangle = ScreenGetter.getSlotList();
		ArrayList<Slot> slotList = new ArrayList<Slot>();
		for (int i = 0; i < slotRectangle.size(); i++) {
			Rectangle actual = slotRectangle.get(i);
			String codec = BinaryItemGenerator.getBinCode(PixelHandler
					.printScreen(actual));
			String name = codDecod.deCodec(codec);
			if (name.equals("-1")) {
				slotList.add(new Slot(actual, codec));
			} else {
				if(!name.equals("Empty"))
				slotList.add(new Slot(actual, codec, name));
			}
		}
		return slotList;

	}

	/**
	 * Retorna o elemento de determinado index
	 * 
	 * @param index
	 *            - o indice.
	 * @return - o elemento. Ou nulo se for maior que 27.
	 */
	public static Slot getInventory(int index) {
		if (index > 27)
			return null;
		Rectangle actual = ScreenGetter.getSlotList().get(index);
		String codec = BinaryItemGenerator.getBinCode(PixelHandler
				.printScreen(actual));
		String name;
		name = codDecod.deCodec(codec);
		if (name == "-1") {
			RuneMethods.logWindow(name);
			return new Slot(actual, codec);
		} else {
			return new Slot(actual, codec, name);
		}
	}

	/**
	 * Retorna o inv de posição x,y
	 * 
	 * @param x
	 *            - a posição x, contando da esquerda para a direita e começando
	 *            em 0.
	 * @param y
	 *            - a posição y, contando de cima para baixo e começando em 0.
	 */
	public static Slot getInvPos(int x, int y) {
		int index = y * 4 + x;
		return getInventory(index);

	}

	// TODO fazer este método.
	public static void generateNamesForUnkowns() {
		RuneMethods.log("Gerando nomes para os desconhecidos! ----- >>");
		for (int i = 0; i < 28; i++) {
			OptionTableManager.closeOptionTableIfOpen();
			//System.out.println ("Inventário (" + i + ")" + " : ");
			Slot temp = getInventory(i);
			//System.out.println ("Binário (" + i + ")" + temp.binCode);
			//System.out.println ("Nome (" + i + ")" + temp.name);
			//System.out.println("O nome é desconehcido? " + (temp.name == "Unkwon"));
			if (!(temp.name == "Unkwon"))
				continue;
			Rectangle slotPos = temp.squareSlotRec;
			Point p = new Point(slotPos.x + Meth.intRandom(5, slotPos.width),slotPos.y + Meth.intRandom(5, slotPos.height -5));
			String name = OptionTableManager.getName(p);
			if(name!="-1") {
			RuneMethods.log ("Gerando codec para: " + name);
			codDecod.addCodec(temp.binCode, name);
			}
			
		}
	}

	


}
