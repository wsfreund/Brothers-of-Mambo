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
	 * devolve sua identificação.
	 * 
	 * @param binCode
	 *            - o código em binário gerado pelo método inventório.
	 * @return - o "nome" do  respectivo códico ou -1 se não foi achado.
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
			System.out.println("Problema na execução: O file não foi achado, causa" + e.getCause());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problema na execução:IOExecpiot, causa" + e.getCause());
			e.printStackTrace();
		}
	}
	
}
