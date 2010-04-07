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
	 * devolve sua identifica��o.
	 * 
	 * @param binCode
	 *            - o c�digo em bin�rio gerado pelo m�todo invent�rio.
	 * @return - o "nome" do  respectivo c�dico ou -1 se n�o foi achado.
	 */
	public String deCodec(String binCode) {
		//System.out.println("Content : " + content);
		int index = content.indexOf(binCode, 0);
		if (index == -1)
			return "-1";
		int start = content.indexOf("*", index);
		//System.out.println(start);
		int end = content.indexOf("*", start+1);
		System.out.println(end);
		String name = content.substring(start+1, end);
		//System.out.println("Achei o nome em deCodec: " + name);
		return name;
	}
	
	public void addCodec(String binCode, String name) {
		content = content + " " + binCode + "*" + name + "*";
		try {
			ReadWriteTextFile.setContents(baseFile, content);
		} catch (FileNotFoundException e) {
			System.out.println("Problema na execu��o: O file n�o foi achado, causa" + e.getCause());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problema na execu��o:IOExecpiot, causa" + e.getCause());
			e.printStackTrace();
		}
	}
	
}
