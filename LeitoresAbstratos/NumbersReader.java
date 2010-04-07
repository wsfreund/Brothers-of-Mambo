
import java.awt.Color;


public abstract class NumbersReader extends RunescapeStringReader {

	NumbersReader() {
		super();
	}

	@Override
	protected Color[] getColors() {
		return new Color[] {Color.GREEN, Color.RED, Color.ORANGE };
	}

	@Override
	protected int maxDelta() {
		return 0;
	}
	
	protected int maxSpace() {
		return 1;
	}
	
	@Override
	final protected int lineHeight() {
		return 9;
	}
	
	@Override
	protected boolean simpleDelta() {
		return true;
	}
	

	/**
	 * @return o valor do campo, ou -1 se não foi bem sucedido;
	 */
	public int getValue() {
		String n = getMensage();
		if (n.isEmpty() || n.contains("_")) return -1;
		return Integer.parseInt(n);
	}

	protected String lineValueDecodec(Long value) {
		//System.out.println("value: " + value); 
		if (value == 0) 
			return "";
		if (value == 30960)
			return "1";
		if (value == 2986608)
			return "2";
		if (value == 266272)
			return "3";
		if (value == 114366)
			return "4";
		if (value == 256298)
			return "5";
		if (value == 2584568)
			return "6";
		if (value == 9966)
			return "7";
		if (value == 2664376)
			return "8";
		if (value == 5117592)
			return "9";
		if (value == 1359240)
			return "0";
		return "_";
	}

}
