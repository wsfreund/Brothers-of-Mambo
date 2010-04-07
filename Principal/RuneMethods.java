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
	private static Calendar calendar = new GregorianCalendar();
	private static String playerName = "";
	private static String playerPass = "";

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
	static public void setLogToFile(String location) throws IOException {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		String fileName = location + playerName + " " + day + " " + month
				+ " " + year + " " + hour + "h " + min + "min" + ".txt";
		File logFile = new File(fileName);
		logFile.createNewFile();
		log = new PrintWriter(new FileWriter(fileName));
		log.println("Log Starts - " + playerName);
	}
	

	/**
	 * -------- <strong> ATENÇÃO </strong> -------- </nl> Só use este método se
	 * voce já tiver usado o setLogFile() antes!. Adiciona uma informação ao
	 * arquivo com contendo as informações do acontecimento;
	 * 
	 * @param m
	 */
	static public void log(String m, LogFrame logFrame) {
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		log.println(hour + ":" + min + ":" + sec + " - " + m);
	}

	static public void logErrorToFile(Exception e) {
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		log.println(hour + ":" + min + ":" + sec + " - ERROR : ");
		log.println("Local: " + e.getLocalizedMessage() + ". Cause: "
				+ e.getCause());
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
