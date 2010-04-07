import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


public class MuggerSlayer extends CombatScript{

	@Override
	int deathCheckFreq() {
		return 8;
	}

	@Override
	ArrayList<InterestPoint> setMobInteresPoints() {
		ArrayList<InterestPoint> temp = new ArrayList<InterestPoint>();
		InterestPoint mob = new InterestPoint(new Color(124, 67,
				55), 25, 20, 0, true);
		temp.add(mob);
		return temp;
	}

	@Override
	void checkMensageEvent(String mensage) {
	}

	@Override
	int msgListFreq() {
		return 1;
	}

	@Override
	void onFinish() {
	}

	@Override
	void onStart() {
	}

	@Override
	boolean toLogMsg() {
		return true;
	}

	
	
	@Override
	long maxTimeOutCombat() {
		// TODO Auto-generated method stub
		return 15000;
	}

	@Override
	boolean toBuryBones() {
		return true;
	}

	@Override
	String[] whatToKeep() {
		return new String[] {"Body Rune", "Bones", "Coins", "Trout"};
	}

	@Override
	protected String[] lootName() {
		return new String[] {"Body Runes","Bones"};
	}
	
	
	

}
