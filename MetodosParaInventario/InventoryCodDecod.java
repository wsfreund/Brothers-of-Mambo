import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InventoryCodDecod {
	/**
	 * Classe est�tica respons�vel por codificar e decodificar os bin�rios do
	 * invent�rio. N�o s� decodificar� como tamb�m cria um novo c�digo para
	 * itens novos possibilitanto uma futura identifica��o.
	 */
	File baseFile;

	public InventoryCodDecod(File fileBase) {
		content = ReadWriteTextFile.getContents(fileBase);
		this.baseFile = fileBase;
	}

	private String content;

	/**
	 * Entra com um c�digo bin�rio gerado na identifica��o de um item do inv e
	 * devolve sua identifica��o. Este m�todo l� o "c�digo de barras".
	 * 
	 * @param binCode
	 *            - o c�digo em bin�rio gerado pelo m�todo invent�rio.
	 * @return - o "nome" do respectivo c�dico ou -1 se n�o foi achado.
	 */
	public String deCodec(String binCode) {
		// System.out.println("Content : " + content);
		int index = content.indexOf(binCode, 0);
		if (index == -1)
			return "-1";
		int start = content.indexOf("*", index);
		//RuneMethods.logWindow("Index start "+start);
		int end = content.indexOf("*", start + 1);
		//RuneMethods.logWindow("Index end "+end);
		String name = content.substring(start + 1, end);
		// System.out.println("Achei o nome em deCodec: " + name);
		//RuneMethods.logWindow(name);
		return name;
	}

	/**
	 * Adiciona o bin�rio e seu respectiovo nome ao arquivo indicado no
	 * contrutor e atualiza-o em tempo real. N�o � necess�rio recome�ar o
	 * programa para checar que funcione.
	 * 
	 * @param binCode - o c�digo bin�rio do item.
	 * @param name - seu nome.
	 */
	public void addCodec(String binCode, String name) {
		String newline = System.getProperty("line.separator");
		content = content + " " + binCode + "*" + name + "* " + newline;
		try {
			ReadWriteTextFile.setContents(baseFile, content);
		} catch (FileNotFoundException e) {
			RuneMethods.log(e);
		} catch (IOException e) {
			RuneMethods.log(e);
		}
	}

}
