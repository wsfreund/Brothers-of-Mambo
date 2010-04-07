import java.awt.Color;
import java.util.ArrayList;

abstract class ActionStringReader extends RunescapeStringReader {

	ActionStringReader() {
		super();
	}

	private final Color actions = new Color(200, 200, 200);
	//private final Color level1 = new Color(30, 217, 16);
	//private final Color level2 = new Color(7, 198, 4);
	private final Color level = new Color(18, 207, 10);
	private final Color npcName = new Color(201, 197, 2);
	private final Color itemName = Color.ORANGE;

	@Override
	protected Color[] getColors() {
		Color[] colors = { actions, level, npcName, itemName };
		return colors;
	}

	@Override
	protected int lineHeight() {
		return 17; // O codificador só funcionará para esta altura!
	}

	@Override
	protected int maxDelta() {
		return 30;
	}

	@Override
	protected boolean simpleDelta() {
		return false;
	}

	@Override
	protected int maxSpace() {
		return 4;
	}

	/**
	 * Retorna uma frase ("String") contendo apenas as informações desejadas.
	 * 
	 * @param actions
	 *            - true se a frase deve conter as ações.
	 * @param level
	 *            - true se a frase deve conter a informação sobre o level.
	 * @param npcName
	 *            - true se a frase deve conter o nome do npc.
	 * @param itemName
	 *            - true se a frase deve conter o nome do item.
	 * @return
	 */
	public String getMensages(boolean actions, boolean level, boolean npcName,
			boolean itemName) {
		ArrayList<Color> col = new ArrayList<Color>();
		// if (actions) col.add(this.actions);
		if (level) {
			col.add(this.level);
			//col.add(this.level2);
		}
		if (npcName)
			col.add(this.npcName);
		if (itemName)
			col.add(this.itemName);
		if (actions)
			col.add(this.actions);
		Color[] customCol = new Color[col.size()];
		col.toArray(customCol);
		ArrayList<Long> ids = super.getValues(customCol);
		return super.MensageBoxLineDecodec(ids);
	}

	@Override
	protected String lineValueDecodec(Long value) {
		System.out.println("Value: " + value);
		long temp;
		if (value == 0)
			return " ";
		if (value == 439766784) // feito
			return "a";
		if (value == 5083103)
			return "b";
		if (value == 13885440) // feito
			return "c";
		if (value == 10816688)
			return "d";
		if (value == 327408640) // feito
			return "e";
		if (value == 135840)
			return "f";
		temp = value / 1000;
		if (temp == 3957638)
			return "g";
		if (value == 9938943)
			return "h";
		if (value == 1000)
			return "i";
		if (value == 9075264)
			return "j";
		if (value == 238022612) // feito
			return "k";
		if (value == 4132) // feito
			return "l";
		if (value == 994754752)
			return "m";
		if (value == 9938928)
			return "n";
		if (value == 5386560)
			return "o";
		if (value == 5394240)
			return "p";
		if (value == 573703872)
			return "q";
		if (value == 5248) // feito
			return "r";
		if (value == 4657664)
			return "s";
		if (value == 23021264) // Feito
			return "t";
		if (value == 445815104) // Feito
			return "u";
		if (value == 750768)
			return "v";
		if (value == 5707088)
			return "x";
		if (value == 5573616)
			return "w";
		if (value == 46085616)
			return "y";
		if (value == 5906544)
			return "z";
		if (value == 7472)
			return "(";
		if (value == 10112)
			return ",";
		if (value == 512)
			return ".";
		if (value == 1)
			return "";
		if (value == 8696)
			return "?";
		if (value == 895)
			return "!";
		if (value == 7817364)
			return "*";
		if (value == 528)
			return ":";
		if (value == 12)
			return "'";
		if (value == 5739956)
			return "1";
		if (value == 58309880)
			return "2";
		if (value == 5527448)
			return "3";
		if (value == 15102335)
			return "4";
		if (value == 5396581)
			return "5";
		if (value == 22685052)
			return "6";
		if (value == 50599)
			return "7";
		if (value == 26207980)
			return "8";
		if (value == 17318484)
			return "9";
		if (value == 16468320)
			return "0";
		temp = value / 1000;
		if (temp == 4489680) // Feito
			return "A";
		if (value == 5528213)
			return "B";
		if (value == 3147132)
			return "C";
		if (value == 5651453)
			return "D";
		if (value == 5702213)
			return "E";
		if (value == 13893)
			return "F";
		if (value == 54073132)
			return "G";
		if (value == 10248783)
			return "H";
		if (value == 62043)
			return "I";
		if (value == 677815)
			return "J";
		if (value == 53908223)
			return "K";
		if (value == 5689343)
			return "L";
		temp = value / 1000;
		if (temp == 450189602) // feito
			return "M";
		if (value == 106254243)
			return "N";
		if (value == 28347132)
			return "O";
		if (value == 142653)
			return "P";
		if (value == 79681532)
			return "Q";
		if (value == 7976053)
			return "R";
		if (value == 2287380)
			return "S";
		if (value == 113311)
			return "T";
		if (value == 105426015)
			return "U";
		if (value == 613071)
			return "V";
		if (value == 9101579)
			return "X";
		if (value == 5573616)
			return "W";
		if (value == 178887)
			return "Y";
		if (value == 5734887)
			return "Z";
		return "*"; // default unknow
	}
}
