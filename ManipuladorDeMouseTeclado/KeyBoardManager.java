

import java.awt.event.KeyEvent;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardHomeHandler;

public class KeyBoardManager {
	/**
	 * Classe estática contendo métodos para utiização do teclado.
	 */
	private KeyBoardManager() {
	}

	@SuppressWarnings("deprecation")
	/**
	 * Preciona uma tecla, atualmente não tem maiusculos, nem os pontos.Só as letras.
	 */
	static private boolean digit(String str) {
		if (str == " ") {
			pushAndRelease(KeyEvent.VK_SPACE);
			return true;
		}
		if (str == "a") {
			pushAndRelease(KeyEvent.VK_A);
			return true;
		}
		if (str == "b") {
			pushAndRelease(KeyEvent.VK_B);
			return true;
		}
		if (str == "c") {
			pushAndRelease(KeyEvent.VK_C);
			return true;
		}
		if (str == "d") {
			pushAndRelease(KeyEvent.VK_D);
			return true;
		}
		if (str == "e") {
			pushAndRelease(KeyEvent.VK_E);
			return true;
		}
		if (str == "f") {
			pushAndRelease(KeyEvent.VK_F);
			return true;
		}
		if (str == "g") {
			pushAndRelease(KeyEvent.VK_G);
			return true;
		}
		if (str == "h") {
			pushAndRelease(KeyEvent.VK_H);
			return true;
		}
		if (str == "i") {
			pushAndRelease(KeyEvent.VK_I);
			return true;
		}
		if (str == "j") {
			pushAndRelease(KeyEvent.VK_J);
			return true;
		}
		if (str == "k") {
			pushAndRelease(KeyEvent.VK_K);
			return true;
		}
		if (str == "l") {
			pushAndRelease(KeyEvent.VK_L);
			return true;
		}
		if (str == "m") {
			pushAndRelease(KeyEvent.VK_M);
			return true;
		}
		if (str == "n") {
			pushAndRelease(KeyEvent.VK_N);
			return true;
		}
		if (str == "o") {
			pushAndRelease(KeyEvent.VK_O);
			return true;
		}
		if (str == "p") {
			pushAndRelease(KeyEvent.VK_P);
			return true;
		}
		if (str == "q") {
			pushAndRelease(KeyEvent.VK_Q);
			return true;
		}
		if (str == "r") {
			pushAndRelease(KeyEvent.VK_R);
			return true;
		}
		if (str == "s") {
			pushAndRelease(KeyEvent.VK_S);
			return true;
		}
		if (str == "t") {
			pushAndRelease(KeyEvent.VK_T);
			return true;
		}
		if (str == "u") {
			pushAndRelease(KeyEvent.VK_U);
			return true;
		}
		if (str == "v") {
			pushAndRelease(KeyEvent.VK_V);
			return true;
		}
		if (str == "w") {
			pushAndRelease(KeyEvent.VK_W);
			return true;
		}
		if (str == "x") {
			pushAndRelease(KeyEvent.VK_X);
			return true;
		}
		if (str == "y") {
			pushAndRelease(KeyEvent.VK_Y);
			return true;
		}
		if (str == "z") {
			pushAndRelease(KeyEvent.VK_Z);
			return true;
		}
		if (str == "0") {
			pushAndRelease(KeyEvent.VK_0);
			return true;
		}
		if (str == "1") {
			pushAndRelease(KeyEvent.VK_1);
			return true;
		}
		if (str == "2") {
			pushAndRelease(KeyEvent.VK_2);
			return true;
		}
		if (str == "3") {
			pushAndRelease(KeyEvent.VK_3);
			return true;
		}
		if (str == "4") {
			pushAndRelease(KeyEvent.VK_4);
			return true;
		}
		if (str == "5") {
			pushAndRelease(KeyEvent.VK_5);
			return true;
		}
		if (str == "6") {
			pushAndRelease(KeyEvent.VK_6);
			return true;
		}
		if (str == "7") {
			pushAndRelease(KeyEvent.VK_7);
			return true;
		}
		if (str == "8") {
			pushAndRelease(KeyEvent.VK_8);
			return true;
		}
		if (str == "9") {
			pushAndRelease(KeyEvent.VK_9);
			return true;
		}
		if (str == "!") {
			pushAndRelease(KeyEvent.VK_EXCLAMATION_MARK);
			return true;
		}
		return false;

	}

	/**
	 * Digita a mensagem desejada e aperta, e aperta enter se desejado.
	 * 
	 * @param whatToType
	 */
	static public boolean type(String whatToType) {
		String temp = whatToType;
		boolean allTyped = true;
		for (int i = 0; i < temp.length(); i++) {
			String thischar = "" + temp.charAt(i);
			System.out.println(thischar);
			if (digit(thischar)) {
				SystemTools.getRobot().delay(Meth.intRandom(60, 130));
			} else {
				allTyped = false;
			}

		}
		return allTyped;
	}

	static private void pushAndRelease(int keyCode) {
		SystemTools.getRobot().keyPress(keyCode);
		//SystemTools.getRobot().delay(Meth.intRandom(10, 30));
		SystemTools.getRobot().keyRelease(keyCode);
	}
}
