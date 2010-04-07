import org.neuroph.core.NeuralNetwork;
import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.omg.IOP.ComponentIdHelper;



import edu.uci.ics.jung.visualization.transform.shape.Graphics2DWrapper;

import java.util.HashMap;
import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.AWTEventListenerProxy;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.event.MouseInputAdapter;

public class MainRecignising {

	public static void main(String[] args) throws AWTException,
			InterruptedException, IOException {

		SystemTools.set();
		SystemTools.getRobot().delay(2000);
		BufferedImage im;
		im = PixelHandler.printScreen();
		int[] colors = { 700000 };
		// int[] exclude = {8576238, 6576238}; bom

		// int[] exclude = {8576238, 6076238}; ainda melhor

		// Só os contornos
		// int[] colors = {0};
		// int[] exclude = {800000};

		// int[] colors = {0};
		int[] exclude = { 8576238, 6076238, 5082795, 0 };
		// int[] exclude = {8576238, 6076238, 5082795};
		// im = pix.makeNewImgUsingClrsAndExclude(im, colors, exclude, true);
		// Color[] c = {new Color(38,38,29), new Color(44,44,33)};
		// new Color(147,141,140), new Color(75,48,32) new Color(119,104,66),
		// new Color(203,154,113),

		// Color[] c = {new Color(231,174,128)};
		long time = System.currentTimeMillis();

		// Point p = d.getProbPoint(25,ddf,3,5,false);
		long time2 = System.currentTimeMillis();
		// Rectangle r = new Rectangle(50,50);

		System.out.println(rob.getPixelColor(400, 300));
		// Color ddf = new Color(75, 48, 32);
		// PointGetter d = new PointGetter(g.getScreenArea(), ddf, 20, 1, 0,
		// true); para druidas
		PointGetter d = new PointGetter(g.getScreenArea(), new Color(124, 67,
				55), 20, 15, 1, true);
		MouseHandler m = new MouseHandler();
		// Point p = d.getProbPoint(20,ddf,1,0,true);
		// m.dragMouse(p, 0, true,0.1);
		// im = pix.filterColors(im, c,d,8); esse padrão mostra a parede
		// PixelHandler.testPrintPoints(im,pix.filterGetAreaColorPoints(g.getScreenArea(),
		// new Color(124,67,55), 5,
		// true));

	}

	// 38,38,29 carvão
	// 41,41,31
	// 44,44,33

	// 82,71,45
	// 119,104,66 pedra claro
}

// Color[] d = {new Color(75,48,32)};
// visualisador perfeito de druidas!
// mudar para 5 pois vou usar maior ou igual...