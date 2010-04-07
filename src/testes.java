import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class testes {

	/**
	 * @param args
	 * @throws AWTException
	 */
	public static void main(String[] args) throws AWTException {

		SystemTools.set();
		Robot rob = SystemTools.getRobot();
		rob.delay(4000);
		System.out.println(rob.getPixelColor(400, 300));
		BufferedImage im = PixelHandler.printScreen();
		ScreenGetter.set();
		ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
		recs.add(ScreenGetter.getMensageBoxLine());
		recs.add(ScreenGetter.getPlayScrArea());
		recs.addAll(ScreenGetter.getSlotList());
		recs.add(ScreenGetter.getTopScrActionsLine());
		recs.add(ScreenGetter.getHpLine());
		recs.add(ScreenGetter.getRunNumbersLine());
		recs.add(ScreenGetter.getPrayerNumbersLine());
		recs.add(ScreenGetter.getNorthPlayerArea());
		recs.add(ScreenGetter.getSouthlayerArea());
		recs.add(ScreenGetter.getRightlayerArea());
		recs.add(ScreenGetter.getLeftlayerArea());
		recs.add(ScreenGetter.getCenterlayerArea());
		recs.add(ScreenGetter.getNearPlayer());
		recs.add(ScreenGetter.getJavaAppArea());
		recs.add(OptionTableManager.getLootRectangle());
		recs.addAll(OptionTableManager.getRectanglesLootLine());
		
		
		OptionTableManager.getListOfOptions();

		ClipStringReader clip = new ClipStringReader();
		HpNumberReader hp = new HpNumberReader();
		PrayerNumberReader pra = new PrayerNumberReader();
		RunEnNumberReader run = new RunEnNumberReader();
		TopLeftScreenInfo tls = new TopLeftScreenInfo();
	
	
		System.out.println("Clip mensage= " + clip.getMensage());
		//System.out.println("Action: " + tls.getMensage());
		//System.out.println("Hp = " + hp.getValue());
		//System.out.println("Prayer = " + pra.getValue());
		//System.out.println("Run = " + run.getValue());
		
		im = PixelHandler.PrintRectangle(recs, im);

		// pix.testPrintPoints(new File("C:\\a.bmp"),
		// pix.filterGetAreaColorPoints(areaToCheck, Color.BLACK, 1, true), new
		// File("C:\\abc.bmp"));
		PixelHandler.renderBufImg(im, "C://", "OPF", "bmp");
	}

}
