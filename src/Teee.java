
public class Teee {

	/**
	 * @param args
	 */
	
	
	/**
	 * setUndecorated(true); 
    setFocusableWindowState(false); 
    setFocusable(false); 
    enableInputMethods(false); 
	 */
	public static void main(String[] args) {
		try{
		SystemTools.set();
		//SystemTools.getRobot().delay(8000);
		RoboTPanel panel = new RoboTPanel();
		//ScreenGetter.set();
		//InventoryHandler.generateNamesForUnkowns();
		//LootManager.getLootRectangle();
		//MuggerSlayer mug = new MuggerSlayer();
		//mug.run();
		//KeyBoardManager.type("GAY AYAYAY");
		}
		catch (Exception e) {
			RuneMethods.closeLog();
			System.exit(-1);
		}
	}

}
