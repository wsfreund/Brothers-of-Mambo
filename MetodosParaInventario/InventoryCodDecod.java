import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InventoryCodDecod {
	/**
	 * Classe estática responsável por codificar e decodificar os binários do
	 * inventório. Não só decodificará como também cria um novo código para
	 * itens novos possibilitanto uma futura identificação.
	 */
	File baseFile;

	public InventoryCodDecod(File fileBase) {
		content = ReadWriteTextFile.getContents(fileBase);
		this.baseFile = fileBase;
	}

	private String content;

	/**
	 * Entra com um código binário gerado na identificação de um item do inv e
	 * devolve sua identificação. Este método lê o "código de barras".
	 * 
	 * @param binCode
	 *            - o código em binário gerado pelo método inventório.
	 * @return - o "nome" do respectivo códico ou -1 se não foi achado.
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
	 * Adiciona o binário e seu respectiovo nome ao arquivo indicado no
	 * contrutor e atualiza-o em tempo real. Não é necessário recomeçar o
	 * programa para checar que funcione.
	 * 
	 * @param binCode - o código binário do item.
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
