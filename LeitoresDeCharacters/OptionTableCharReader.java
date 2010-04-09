import java.awt.Color;

public class OptionTableCharReader extends RunescapeStringReader {

	@Override
	protected int LineWidht() {
		return 0;
	}

	@Override
	protected Color[] getColors() {
		return new Color[] { new Color(198, 184, 149), new Color(255, 144, 64),
				Color.yellow, Color.green, Color.red };
	}

	@Override
	protected int lineBeginX() {
		return 0;
	}

	@Override
	protected int lineBeginY() {
		return 0;
	}

	@Override
	protected int lineHeight() {
		return 15;
	}

	@Override
	protected String lineValueDecodec(Long value) {
		System.out.println("Value: " + value);
		// System.out.println("value: " + value);
		if (value == 0)
			return " ";
		if (value == 219883392)// feito
			return "a";
		if (value == 117344106) // feito
			return "b";
		if (value == 6942720) // feito
			return "c";
		if (value == 226242720) // feitro
			return "d";
		if (value == 163704320)// feito
			return "e";
		if (value == 2966400) // feito
			return "f";
		if (value == 1978819072) // feito
			return "g";
		if (value == 218617706) // feito
			return "h";
		if (value == 22000) // feito
			return "i";
		if (value == 9075264)
			return "j";
		if (value == 119011306)// feito
			return "k";
		if (value == 22506) // feito
			return "l";
		if (value / 100 == 218810813)
			return "m";
		if (value == 218617376) // feito
			return "n";
		if (value == 117342720) // feito
			return "o";
		if (value == 117501440) // feito
			return "p";
		if (value == 573703872)
			return "q";
		if (value == 380576) // feito
			return "r";
		if (value == 100835968)// feito
			return "s";
		if (value == 11510632)// feito
			return "t";
		if (value == 222907552) // feito
			return "u";
		if (value == 16516896) // feito
			return "v";
		if (value == 125555936) // feito
			return "x";
		if (value == 1201761952) // feito
			return "w";
		if (value == 989231776) // feito
			return "y";
		if (value == 118211808) // feito
			return "z";
		if (value == 2379256)
			return "(";
		if (value == 1253230)
			return ")";
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
		if (value == 2992) // feito
			return ":";
		if (value == 12)
			return "'";
		if (value == 5739956)
			return "1";
		if (value == 117252024) // feito
			return "2";
		if (value == 5527448)
			return "3";
		if (value == 15102335)
			return "4";
		if (value == 5396581)
			return "5";
		if (value == 117346448) // feito
			return "6";
		if (value == 50599)
			return "7";
		if (value == 26207980)
			return "8";
		if (value == 17318484)
			return "9";
		if (value == 16468320)
			return "0";
		if (value / 10 == 224484028) // feito
			return "A";
		if (value == 120446306) // feito
			return "B";
		if (value == 68170080)// feito
			return "C";
		if (value == 123611106) // feito
			return "D";
		if (value == 114046306) // feito
			return "E";
		if (value == 279906) // feito
			return "F";
		if (value == 54073132)
			return "G";
		if (value == 10248783)
			return "H";
		if (value == 1252086) // feito
			return "I";
		if (value == 677815)
			return "J";
		if (value == 53908223)
			return "K";
		if (value == 113788906) // feito
			return "L";
		if (value / 1000 == 225094801) // feito
			return "M";
		if (value == 106254243)
			return "N";
		if (value == 1236010080) // feito
			return "O";
		if (value == 7355106) // feito
			return "P";
		if (value == 79681532)
			return "Q";
		if (value == 218555106) // feito
			return "R";
		if (value == 2287380)
			return "S";
		if (value == 2470622)// feito
			return "T";
		if (value == 123807882) // feito
			return "U";
		if (value == 613071)
			return "V";
		if (value == 9101579)
			return "X";
		if (value / 1000 == 51239399) // feito
			return "W";
		if (value == 178887)
			return "Y";
		if (value == 5734887)
			return "Z";
		return "~"; // default unknow
	}

	@Override
	protected int maxDelta() {
		return 0;
	}

	@Override
	protected int maxSpace() {
		return 4;
	}

	@Override
	protected boolean simpleDelta() {
		return true;
	}

	/**
	 * Método a ser chamado quando deseja-se apenas o nome do item.
	 */
	protected String getMensageItemName() {
		return MensageBoxLineDecodec(getValues(new Color[] { new Color(255, 144, 64) }));
	}

}
