import java.awt.Point;
import java.util.ArrayList;

abstract class CombatScript extends Script {

	protected boolean someoneDied = false;
	protected boolean targetting = false;
	protected boolean justTargetted = false;
	private ArrayList<InterestPoint> intPoints;
	protected CheckCombateEvent checkCombats = new CheckCombateEvent();
	private long targettingTime;
	private int dDelay;
	private int i = 0;
	private boolean buryBones = false;
	private String[] whatToKeep;
	private String[] lootName;

	/**
	 * Método básico para criação de novas Scripts.
	 */
	CombatScript() {
		super();
		intPoints = setMobInteresPoints();
		dDelay = (int) 1000 / deathCheckFreq();
		buryBones = toBuryBones();
		whatToKeep = whatToKeep();
		lootName = lootName();
		
	}

	private Point getNpcNearestToMouse() {
		for (int i = 0; i < intPoints.size(); i++) {
			InterestPoint tempIntP = intPoints.get(i);
			Point p = PointGetter.getNearestToPoint(MouseHandler.getMousePos(),
					ScreenGetter.getPlayScrArea(), tempIntP.getPointColor(),
					tempIntP.getMaxDistBetweenPoints(), tempIntP
							.getFilterSize(), tempIntP.getMinisize(), tempIntP
							.getSimpleDelta());
			if (p != null)
				return p;
		}
		return null;
	}

	/**
	 * Método a ser reescrito que retorna a frequencia com que a script deve
	 * chegar por mortes enquanto em combate para verificar se o npc atacado
	 * está morto.
	 */
	abstract int deathCheckFreq();

	@Override
	int loop() {
		i++;
		System.out.println("(" + i + ") Minha situação é targetting: "
				+ targetting);
		System.out.println("(" + i + ") Minha situação é justTargetted: "
				+ justTargetted);
		if (targetting) {
			// someoneDied = deathOcourred.lookForDeath();
			// if (justTargetted) {
			if (justTargetted) {
				System.out.println("(" + i + ") cheguei ao justtarget 2");
				for (int i = 0; i < 100; i++) {
					ArrayList<Point> p = checkCombats.getCombatEventBars();
					if (p != null && !p.isEmpty()) {
						for (int j = 0; j < p.size(); j++) {
							System.out.println("Combat in (" + j
									+ ") Position: " + p.get(j).x + ","
									+ p.get(j).y);
							targettingTime = System.currentTimeMillis()
									+ maxTimeOutCombat();
							justTargetted = false;
						}
						return 0;
					}
					wait(50);
				}
				System.out
						.println("("
								+ i
								+ ") cheguei ao justtarget mas não cheguei a conclusão 3");
				justTargetted = false;
				targetting = false;
				return Meth.intRandom(100, 200);

			}
			System.out.println("procurando por mortes 4");
			ArrayList<Point> deathPoints = checkCombats.lookForDeath();
			if (deathPoints != null && !(deathPoints.size() == 0)) {
				System.out.println("Morte");
				System.out.println("(" + i + ") achei " + "( "
						+ deathPoints.size() + ")" + " mortes 5");
				targetting = false;
				wait(Meth.intRandom(2500, 3000)); // TODO tentar modificar isso
				// por um método que remove
				// dos pontos possiveis os
				// pontos onde ocorreram
				// mortes.
				if (deathPoints.size() > 1) {
					System.out
							.println("Foi encontrado mais de uma morte simultanea, não será recolhido o loot por motivos técnicos");
					return Meth.intRandom(200, 360);
				}
				int deathX = deathPoints.get(0).x;
				int deathY = deathPoints.get(0).y + 30;
				System.out
						.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Morte em: "
								+ deathX + " " + deathY);
				ArrayList<Slot> inventory = InventoryHandler.getInventory();
				boolean looted = loot(new Point(deathX, deathY),lootName);
				RuneMethods.wait(Meth.intRandom(200, 360));
				System.out.println("Inventory size is: " + inventory.size()
						+ " Looted? " + looted);
				if (inventory.size() == 28
						|| ((inventory.size() == 27 && looted))) {
					if (buryBones) {
						buryBones(inventory);
					}
					inventoryFullDrop(inventory, whatToKeep); // TODO fazer
					// método
				}
				// para quando o
				// inventorio
				// estiver cheio ir
				// pro bando etc
				// usando o boolean.
				return Meth.intRandom(200, 360);
			} else {
				System.out.println("Não foram achadas mortes!");
				return dDelay;
			}
		}
		if (!targetting) {
			System.out.println("(" + i + ") pronto para atacar 6");
			Point p = getNpcNearestToMouse();
			if (p == null)
				return Meth.intRandom(400, 800);
			double dist = p.distance(MouseHandler.getMousePos());
			if (dist < 4) {
				MouseHandler.dragMouse(p, 0, false, 0);
				wait(Meth.intRandom(10, 30));
				targetting = MouseHandler.MouseLeftClick(true);
				if (targetting) {
					justTargetted = true;
					System.out.println("Targetting: " + p);
					return Meth.intRandom(300, 800);

				}
				return Meth.intRandom(400, 800);
			}
			// Point pm = MouseHandler.getMousePos();
			// int dx = (int) ((p.x - pm.x) * Meth.doubleRandom(0.95, 1));
			// int dy = (int) ((p.y - pm.y) * Meth.doubleRandom(0.95, 1));
			// MouseHandler.dragMouse(new Point(pm.x + dx, pm.y + dy), 0, true,
			// 0);
			if (dist > 50) {
				Point mouseP = MouseHandler.getMousePos();
				int dx = (int) ((p.x - mouseP.x) * Meth.doubleRandom(0.3, 0.5));
				int dy = (int) ((p.y - mouseP.y) * Meth.doubleRandom(0.3, 0.5));
				MouseHandler.dragMouse(new Point(mouseP.x + dx, mouseP.y + dy),
						5, true, 0);
			} else {
				MouseHandler.dragMouse(new Point(p.x, p.y), 0, true, 0);
			}
			return 0;

		}
		return 100;
	}

	/**
	 * Método a ser reescrito para as Scripts de combate. Deve retornar true
	 * caso se deseje enterrar os ossos, ou false caso contrário.
	 * 
	 */
	abstract boolean toBuryBones();

	private boolean buryBones(ArrayList<Slot> inv) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < inv.size(); i++) {
			if (inv.get(i).name.toLowerCase().contains("bone")) {
				index.add(i);
			}
		}
		if (index.isEmpty())
			return false;
		for (int i = 0; i < index.size(); i++) {
			inv.get(index.get(i)).click();
			RuneMethods.wait(Meth.intRandom(200, 300));
		}
		return true;

	}

	protected boolean inventoryFullDrop(ArrayList<Slot> inventory,
			String[] whatToKeep) {
		if (whatToKeep == null)
			return false;
		boolean dropedSome = false;
		for (int i = 0; i < inventory.size(); i++) {
			String name = (inventory.get(i).name);
			boolean toDrop = true;
			for (int j = 0; j < whatToKeep.length; j++) {
				if (name.contains(whatToKeep[j])) {
					toDrop = false;
					break;
				}
			}
			if (toDrop) {
				dropedSome = true;
				inventory.get(i).doAction("Drop");
				RuneMethods.wait(Meth.intRandom(200, 300));
			}
		}
		return dropedSome;
	}

	/**
	 * Este método só deve ser reescrito se o jogador deve jogar eve dropar
	 * coisas no chão quando o inventório estiver cheio.
	 * 
	 * @return este return alimentará o main, deixe null caso voce não queira
	 *         dropar nada, ou substitua por um vetor contendo os itens que você
	 *         quer que ele mantenha(Não esqueça da comida caso voce esteja
	 *         usando).
	 */
	abstract String[] whatToKeep();

	/**
	 * Deve ser substituido pelas classes que a implementam, a lista deve conter
	 * os "InterestingPoints" contendo os mobs. A ordem é importante, será usada
	 * no método para achar mosntros, ele procurará primeiro pelo 1 tipo de
	 * monstro e assim por diante.
	 * 
	 * @return
	 */
	abstract ArrayList<InterestPoint> setMobInteresPoints();

	abstract long maxTimeOutCombat();

	/**
	 * Método a ser rescrito caso haja o desejo de pegar itens do monstro.
	 * 
	 * @param dPoint
	 *            para ser usado no método: é o local aproximado onde o monstro
	 *            morreu.
	 * @param - o método deve avisar se o loot foi recolhido ou não.
	 */
	boolean loot(Point dPoint, String[] loot) {
		if (loot == null)
			return false;
		int amount = OptionTableManager.getOptionOnPointContaningOneOrOther(
				dPoint, loot, true);
		System.out.println("Amount of loots found: " + amount);
		if (amount > 1) {
			loot(dPoint, loot);
		}
		if (amount == 1)
			return true;
		return false;
	}

	/**
	 * Método a ser reescrito pelas Scripts de combate. Se a Script não deve
	 * pegar loot, então ela deve retornar nulo.Caso contrário, deve retornar os
	 * nomes interessantes.
	 */
	abstract protected String[] lootName();
	
}

