import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class InventoryCodec {
	/**
	 * Classe contendo m�todos est�ticos para um c�digo bin�rio ao slot do inv.
	 */
	private InventoryCodec() {
	}
	

	public static String getBinCode(BufferedImage img) {
		String code = "";
		for (int x = 0; x < img.getWidth(); x++) { 
			for (int y = 9; y < img.getHeight(); y++) {
				Color pixel = new Color(img.getRGB(x, y));
				// come�a no 9, pois em cima h� o n�mero!
				if (Meth.getDelta(Color.BLACK, new Color(img.getRGB(x, y)),
						true) <= 7) {
					code = code + "1"; // 1 se achou um ponto preto - de
										// contorno.
				} else {
					code = code + "0";
				}
			}
		}
		return code;
	}
}