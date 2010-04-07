
public class TopLeftScreenInfo extends ActionStringReader{
	TopLeftScreenInfo (){
		super();
	}

	@Override
	protected int LineWidht() {
		return ScreenGetter.getTopScrActionsLine().width;
	}

	@Override
	protected int lineBeginX() {
		return ScreenGetter.getTopScrActionsLine().x;
	}

	@Override
	protected int lineBeginY() {
		return  ScreenGetter.getTopScrActionsLine().y;
	}

	@Override
	protected int lineHeight() {
		return  ScreenGetter.getTopScrActionsLine().height;
	}

}
