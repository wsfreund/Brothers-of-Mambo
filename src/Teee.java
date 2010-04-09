import java.awt.Color;
import java.awt.Window;
import java.awt.peer.ComponentPeer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


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
	

}
