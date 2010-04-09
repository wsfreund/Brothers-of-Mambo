import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RuneMethods {

	private RuneMethods() {
	};

	private static PrintWriter log;
	private static Calendar calendar;
	private static String playerName = "";
	private static String playerPass = "";
	static LogWindow logWindow;

	public static String getPlayerName() {
		return playerName;
	}

	public static String getPlayerPass() {
		return playerPass;
	}

	/**
	 * Prepara um arquivo e um "escritor" para usar como logger.
	 * 
	 * @param location
	 *            - o local onde o arquivo será gravado.
	 * @throws IOException
	 *             - erro que pode acontecer na criação do logger.
	 */
	static public void setLogToFile() throws IOException {
		calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		String fileName = playerName + " " + day + " " + month + " "
				+ year + " " + hour + "h " + min + "min" + ".txt";
		File logFile = new File(fileName);
		logFile.createNewFile();
		log = new PrintWriter(new FileWriter(fileName));
		log.println("Log Starts - " + playerName);
	}

	/**
	 * Começa a tela de display.
	 */
	static public void logToWindowStartup() {
		logWindow = new LogWindow();
		logWindow.setVisible(true);
	}

	/**
	 * Retorna a hora min e segundo.
	 */
	static private String time() {
		calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String def = hour + ":" + min + ":" + sec + " - ";
		return def;
	}
	
	/**
	 * -------- <strong> ATENÇÃO </strong> -------- </nl> Só use este método se
	 * voce já tiver usado o setLogFile() antes!. Adiciona uma informação ao
	 * arquivo com contendo as informações do acontecimento;
	 * 
	 * @param m - a mensagem.
	 */
	static public void logFile(String m) {
		if (log==null)
			try {
				setLogToFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		log.println(time() + m);
	}
	
	/**
	 * Este método imprime no LogBox a mensagem desejada.
	 * @param m - a mensagem.
	 */
	static public void logWindow(String m) { 
		if (logWindow == null)
			logToWindowStartup();
		logWindow.addLog(time() + m);
	}
	
	/**
	 * Loga um erro na tela.
	 * @param e o erro a ser logado.
	 */
	static public void logWindow(Exception e) {
		if (logWindow == null)
			logToWindowStartup();
		logWindow.addLog("ERROR!!");
		logWindow.addLog(time() + "Local: " + e.getLocalizedMessage() + ". Cause: "
				+ e.getCause());
	}
	

	/**
	 * -------- <strong> ATENÇÃO </strong> -------- </nl> Só use este método se
	 * voce já tiver usado o setLogFile() antes!. Adiciona um erro ao arquivo
	 * com contendo as informações do acontecimento;
	 * 
	 * @param e - o erro a ser relatado.
	 */
	static public void logFile(Exception e) {
		log.println("ERROR!");
		log.println(time() + "Local: " + e.getLocalizedMessage() + ". Cause: "
				+ e.getCause());
	}
	
	/**
	 * Loga a mensagem tanto no FILE quanto na janela.
	 * @param m - a mensagem.
	 */
	static public void log(String m) {
		logFile(m);
		logWindow(m);
	}
	
	/**
	 * Loga o erro tanto no FILE quanto na janela.
	 * @param m - a mensagem.
	 */
	static public void log(Exception e) {
		logFile(e);
		logWindow(e);
	}
	

	/**
	 * Usado para fechar o arquivo e escritor do log.
	 */
	static public void closeLog() {
		log.close();
	}

	/**
	 * Método usado para setar o nome do jogador
	 * 
	 * @param name
	 */
	static public void setPlayerName(String name) {
		playerName = name;
	}

	static public void setPlayerPass(String pass) {
		playerPass = pass;
	}

	/**
	 * Método para a script esperar um determinado tempo.
	 * 
	 * @param ms
	 */
	static public void wait(int ms) {
		SystemTools.getRobot().delay(ms);
	}

}
