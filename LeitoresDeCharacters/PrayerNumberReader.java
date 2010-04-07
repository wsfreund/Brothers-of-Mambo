
public class PrayerNumberReader extends NumbersReader {

	PrayerNumberReader() {
		super();
	}

	@Override
	protected int LineWidht() {
		return ScreenGetter.getPrayerNumbersLine().width;
	}

	@Override
	protected int lineBeginX() {
		return ScreenGetter.getPrayerNumbersLine().x;
	}

	@Override
	protected int lineBeginY() {
		return ScreenGetter.getPrayerNumbersLine().y;
	}


}
