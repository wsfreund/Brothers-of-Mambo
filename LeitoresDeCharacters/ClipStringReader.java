import java.awt.Color;


public class ClipStringReader extends RunescapeStringReader {

	public ClipStringReader() {
		super();
	}

	@Override
	protected Color[] getColors() {
		return new Color[] { Color.BLACK, Color.BLUE };
	}

	@Override
	protected int LineWidht() {
		return ScreenGetter.getMensageBoxLine().width;
	}

	@Override
	protected int lineBeginX() {
		return ScreenGetter.getMensageBoxLine().x;
	}

	@Override
	protected int lineBeginY() {
		return ScreenGetter.getMensageBoxLine().y;
	}

	@Override
	protected int lineHeight() {
		return ScreenGetter.getMensageBoxLine().height;
	}

	@Override
	protected int maxDelta() {
		return 0;
	}

	@Override
	protected int maxSpace() {
		return 3;
	}

	protected String lineValueDecodec(Long value) {
		//System.out.println("value: " + value); 
		if (value == 0)
			return " ";
		if (value == 10577536)
			return "a";
		if (value == 5083103)
			return "b";
		if (value == 586560)
			return "c";
		if (value == 10816688)
			return "d";
		if (value == 6808640)
			return "e";
		if (value == 135840)
			return "f";
		if (value == 91283776)
			return "g";
		if (value == 9938943)
			return "h";
		if (value == 1000)
			return "i";
		if (value == 9075264)
			return "j";
		if (value == 5409663)
			return "k";
		if (value == 1023)
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
		if (value == 2928)
			return "r";
		if (value == 4657664)
			return "s";
		if (value == 58590)
			return "t";
		if (value == 5528816)
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
		if (value == 102199900)
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
		if (value == 1023497863)
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
		return ""; // default unknow
	}

	@Override
	protected boolean simpleDelta() {
		return true;
	}

}