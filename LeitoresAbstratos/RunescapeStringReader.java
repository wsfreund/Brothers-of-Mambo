import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class RunescapeStringReader {

	private int maxDelta;
	private int lineHeight;
	private int lineWidht;;
	private int lineBeginX;
	private int lineBeginY;
	private int maxSpace;
	private Color[] colors;

	/**
	 * Classe abstrata esqueleto das classes que l�em informa��es dada pelo
	 * jogo. � respons�vel por transformar linhas de imagens em "escrita"
	 * (String), para servir de informa��o em algum outro m�todo.
	 */
	RunescapeStringReader() {

		colors = getColors();
		lineWidht = LineWidht();
		lineHeight = lineHeight();
		lineBeginX = lineBeginX();
		lineBeginY = lineBeginY();
		maxSpace = maxSpace();
		maxDelta = maxDelta();
	}

	/**
	 * Este m�todo l� a imagem e retorna uma "frase" (String) contendo o que foi
	 * lido nela.
	 * 
	 * @return String - A frase lida.
	 */
	public String getMensage() {
		ArrayList<Long> ids = getValues(colors);
		return MensageBoxLineDecodec(ids);
	}

	/**
	 * M�todo a ser reescrito pelas classes que herdam de StringReader. A fun��o
	 * deve retornar a largura da linha.
	 * 
	 */
	protected abstract int LineWidht();

	/**
	 * M�todo a ser reescrito pelas classes que herdam de StringReader. A fun��o
	 * deve retornar a altura da linha.
	 * 
	 */
	protected abstract int lineHeight();

	/**
	 * M�todo a ser reescrito pelas classes que herdam de StringReader. A fun��o
	 * deve retornar a cordenada X onde a linha come�a (o canto mais a esquerda
	 * dela).
	 * 
	 */
	protected abstract int lineBeginX();

	/**
	 * M�todo a ser reescrito pelas classes que herdam de StringReader. A fun��o
	 * deve retornar a cordenada Y onde a linha come�a (o canto mais ao norte
	 * dela).
	 * 
	 */
	protected abstract int lineBeginY();

	/**
	 * M�todo a ser reescrito pelas classes que herdam de StringReader. A fun��o
	 * deve retornar a "dist�ncia" m�xima entre as cores. O valor usado indica o
	 * qu�o pr�ximo uma cor deve ser da(s) informada(s) para que seja considera
	 * um ponto de interesse. Se 0, s� ser� considerada de interesse se tiver
	 * exatamente a mesma cor.
	 * 
	 */
	abstract protected int maxDelta();

	/**
	 * M�todo a ser reescrito pelas classes que herdam de StringReader. A fun��o
	 * deve retornar um vetor contendo as cores que servir�o de base para a
	 * codifica��o (as cores devem ser as cores das letras a serem lidas...).
	 */
	abstract protected Color[] getColors();

	/**
	 * M�todo a ser reescrito pelas classes que herdam de StringReader. A fun��o
	 * deve retornar o espa�o maximo entre as letras a fim de separar os
	 * characters. Ex: se for 2, quando a classe estiver lendo uma linha, ao
	 * encontrar 2 pixels de separa��o entender� aquilo como um novo character.
	 * 
	 */
	abstract protected int maxSpace();

	/**
	 * M�todo a ser reescrito pelas classes que herdam de StringReader. Retorna
	 * se o m�todo de compara��o entre as cores � simples ou n�o. Para maiores
	 * informa��es sobre o filtro, veja a classe PixelHandler.
	 * 
	 */
	abstract protected boolean simpleDelta();

	/**
	 * L� uma linha (um pequeno retangulo contendo characters a serem
	 * codificados) e os codifica para serem mais tarde decoficados.
	 * 
	 * @param colors
	 *            - As cores dos pontos que ser�o levados em conta na codifi��o
	 *            (mais as cores similares se o tamanho do filtro for maior que
	 *            zero).
	 * @return uma lista contendo os valores codificados.
	 */
	protected ArrayList<Long> getValues(Color[] colors) {
		ArrayList<Long> values = new ArrayList<Long>();
		BufferedImage im = PixelHandler.printScreen(new Rectangle(lineBeginX,
				lineBeginY, lineWidht, lineHeight));
		long id = 0;
		long score = 0;
		long multX = 1;
		int addSpace = 0;
		for (int x = 0; x < lineWidht; x++) {
			for (int y = 0; y < lineHeight; y++) {
				for (int i = 0; i < colors.length; i++) {
					int delta = Meth.getDelta(colors[i], new Color(im.getRGB(x,
							y)), simpleDelta());
					// System.out.println ("X: " + x + " Y : " + y + " DELTA: "
					// + delta); 
					if (delta <= maxDelta) {
						score = (long) (score + Math.pow(2, y));
						break;

					}
				}
			}
			if (score > 0) {
				id = id + score * multX;
				score = 0;
				multX = multX * 10;
				addSpace = 0;
			} else {
				if (addSpace == 0) {
					if (id != 0) {
						values.add(id);
					}
					id = 0;
					score = 0;
					multX = 1;
					++addSpace;
				}
				if (addSpace < maxSpace) {
					++addSpace;
				} else {
					if (addSpace == maxSpace) {
						long zero = 0;
						values.add(zero);
						++addSpace;
					}

				}
			}
		}
		return values;
	}

	/**
	 * M�todo usado para obter a "frase" (String) de uma ArrayList<Long>
	 * contendo valores codificados pelo m�todo </code>getValues().</>
	 * 
	 * @param values
	 *            a ArrayList<Long> contendo os valores.
	 * @return a frase decodificada.
	 */
	protected String MensageBoxLineDecodec(ArrayList<Long> values) {
		String mensage = "";
		for (int i = 0; i < values.size(); i++) {
			mensage = mensage + lineValueDecodec(values.get(i));
		}
		if(mensage.replace(" ","").isEmpty()) return "";
		return mensage;
	}

	/**
	 * M�todo a ser reescrito pelas classes que herdam de </code>
	 * RunescapeStringReader </> respons�vel por decodificar os valores que
	 * foram codificados pelo m�todo </code>getValues().</>.
	 * 
	 * @param value
	 *            - o valor de entrada a ser decodificado.
	 * @return - o chararacter (mais especificamente o String) de sa�da.
	 */
	protected abstract String lineValueDecodec(Long value);

	public void setLineWidht(int lineWidht) {
		this.lineWidht = lineWidht;
	}

	public void setLineBeginX(int lineBeginX) {
		this.lineBeginX = lineBeginX;
	}

	public void setLineBeginY(int lineBeginY) {
		this.lineBeginY = lineBeginY;
	}
}
