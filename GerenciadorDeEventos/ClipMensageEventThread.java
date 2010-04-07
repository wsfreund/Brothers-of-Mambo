
public class ClipMensageEventThread extends Thread{

	private ClipMensageListener hisListener;
	private int freq;
	private String lastMsg;
	private ClipStringReader cpRead = new ClipStringReader();
	
	public ClipMensageEventThread(ClipMensageListener yourListener, int freq) {
		hisListener = yourListener;
		this.freq = freq;
	}
	
	public void run(){
		long time = (long) (1000 / freq);
		lastMsg = cpRead.getMensage();
		inform(hisListener, lastMsg);
		for(;;) {
			checkNewMensage();
			try {
				sleep(time);
			} catch (InterruptedException e) {
				//TODO log!
			}
		}
		
	}
	
	
	private boolean checkNewMensage(){
		String actualMsg = cpRead.getMensage();
		if (lastMsg!=actualMsg) {
			lastMsg = actualMsg;
			inform(hisListener, actualMsg);
			return true;
			
		}
		return false;
	}
	
	private void inform(ClipMensageListener hisListener, String mensage) {
		hisListener.mensageEventRecived(mensage);
	}
}
