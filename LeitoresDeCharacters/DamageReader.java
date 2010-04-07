import java.awt.Color;

public class DamageReader extends NumbersReader {

	@Override
	protected int LineWidht() {
		return 0;
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
	protected Color[] getColors() {
		return new Color[] { Color.WHITE };
	}

}
