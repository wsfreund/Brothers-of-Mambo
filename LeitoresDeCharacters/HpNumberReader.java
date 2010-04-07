
public class HpNumberReader extends NumbersReader {

	HpNumberReader() {
		super();
	}

	@Override
	protected int LineWidht() {
		return ScreenGetter.getHpLine().width;
	}

	@Override
	protected int lineBeginX() {
		return ScreenGetter.getHpLine().x;
	}

	@Override
	protected int lineBeginY() {
		return ScreenGetter.getHpLine().y;
	}

	
}
