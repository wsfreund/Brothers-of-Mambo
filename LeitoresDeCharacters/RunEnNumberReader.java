
public class RunEnNumberReader extends NumbersReader {

	RunEnNumberReader() {
		super();
	}

	@Override
	protected int LineWidht() {
		return ScreenGetter.getRunNumbersLine().width;
	}

	@Override
	protected int lineBeginX() {
		return ScreenGetter.getRunNumbersLine().x;
	}

	@Override
	protected int lineBeginY() {
		return ScreenGetter.getRunNumbersLine().y;
	}

}
