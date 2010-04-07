import java.awt.Point;
import java.awt.Rectangle;

public class Slot {
	public Rectangle squareSlotRec;
	public String binCode;
	public String name = "Unkwon";
	//TODO adicionar método USE, BURY, DROP

	Slot(Rectangle squareSlotRec, String binCode, String name) {
		this.squareSlotRec = squareSlotRec;
		this.binCode = binCode;
		this.name = name;
	}

	Slot(Rectangle squareSlotRec, String binCode) {
		this.squareSlotRec = squareSlotRec;
		this.binCode = binCode;
	}
	Slot(Rectangle squareSlotRec) {
		this.squareSlotRec = squareSlotRec;
	}
	public void doAction(String action) {
		Point p = new Point(squareSlotRec.x + Meth.intRandom(5, squareSlotRec.width),squareSlotRec.y + Meth.intRandom(5, squareSlotRec.height -5));
		OptionTableManager.getOptionOnPoint(p, new String[] {action}, true);
	}
	public void click() {
		Point p = new Point(squareSlotRec.x + Meth.intRandom(5, squareSlotRec.width),squareSlotRec.y + Meth.intRandom(5, squareSlotRec.height -5));
		MouseHandler.dragMouse(p, 0, true, Meth.doubleRandom(0, 0.05));
		MouseHandler.MouseLeftClick(false);
	}
}
