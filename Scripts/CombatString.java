import java.awt.Point;
import java.util.ArrayList;

abstract class CombatScript extends Script {

	protected boolean someoneDied = false;
	protected boolean targetting = false;
	protected boolean justTargetted = false;
	private ArrayList<InterestPoint> intPoints;
	protected CheckCombateEvent checkCombats = new CheckCombateEvent();
	private long targettingTime = 0;
	private int dDelay;
	private int i = 0;
	private boolean buryBones = false;
	private String[] whatToKeep;
	private String[] lootName;

	/**
	 * M�todo b�sico para cria��o de novas Scripts.
	 */
	CombatScript() {
		super();
		intPoints = setMobInteresPoints();
		dDelay = (int) 1000 / deathCheckFreq();
		buryBones = toBuryBones();
		whatToKeep = whatToKeep();
		if (buryBones) {
			String[] loot = lootName();
			lootName = new String[loot.length + 1];
			for (int i = 0; i < loot.length; i++) {
				lootName[i] = loot[i];
			}
			lootName[loot.length] = "Ossos";
		} else {
			lootName = lootName();
		}

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
	 * M�todo a ser reescrito que retorna a frequencia com que a script deve
	 * chegar por mortes enquanto em combate para verificar se o npc atacado
	 * est� morto.
	 */
	abstract int deathCheckFreq();

	@Override
	int loop() {
		//
		// RuneMethods.logWindow("(" + i + ") Minha situa��o � targetting: "
		// + targetting);
		// RuneMethods.logWindow("(" + i + ") Minha situa��o � justTargetted: "
		// + justTargetted);
		if (targetting) {
			if (justTargetted) {
				// RuneMethods.logWindow("Acabei de clicar num monstro, verificando se em batalha.");
				for (int i = 0; i < 130; i++) { // Espera no m�ximo 7 segundos.
					ArrayList<Point> p = checkCombats.getCombatEventBars();
					if (p != null && !p.isEmpty()) {
						for (int j = 0; j < p.size(); j++) {
							RuneMethods
									.logWindow("H� combate(s) na proximidade do jogador, interpretando como combate.");
							targettingTime = System.currentTimeMillis()
									+ maxTimeOutCombat();
							justTargetted = false;
						}
						return 0;
					}
					wait(50);
				}
				RuneMethods
						.logWindow("N�o foi achado combate na proximidade do jogador, interpretando como fora de combate.");
				justTargetted = false;
				targetting = false;
				return Meth.intRandom(100, 200);

			}
			// RuneMethods.logWindow("Em combate, esperando por morte...");
			Point deathPos = checkForDeathAndPosition();
			if (deathPos != null) {
				if (deathPos.x != -1 && deathPos.y != -1) {
					targetting = false;
					String strBones = buryBones ? "Pegando ossos" : "";
					String decom = "O Npc entrou em decomposi��o.";
					if (lootName == null) {
						RuneMethods.logWindow(decom + strBones);
					} else {
						RuneMethods.logWindow(decom + strBones
								+ "Coletando loot");
					}
					wait(Meth.intRandom(250, 300)); // TODO tentar modificar
													// isso
					// por um m�todo que remove
					// dos pontos possiveis os
					// pontos onde ocorreram
					// mortes.
					
					// if (lootName != null) {
					boolean looted = loot(deathPos, lootName);
					wait(Meth.intRandom(200, 300));
					ArrayList<Slot> inventory = InventoryHandler.getInventory();
					if (inventory.size() == 28
							|| ((inventory.size() == 27 && looted))) {
						RuneMethods.wait(Meth.intRandom(300, 1300));
						RuneMethods.logWindow("Inventorio cheio!");
						if (buryBones) {
							RuneMethods.logWindow("Enterrando ossos");
							buryBones(inventory);
						}

						inventoryFullDropAllBut(inventory, whatToKeep); // TODO
						// fazer
						// m�todo
					}
				}
				// }
				// para quando o
				// inventorio
				// estiver cheio ir
				// pro bando etc
				// usando o boolean.
				return Meth.intRandom(200, 360);
			} else {
				boolean toLongWaiting = ((System.currentTimeMillis() - targettingTime) > 20000);
				if (toLongWaiting) {
					targetting = false;
					RuneMethods
							.log("Esperei tempo demais por uma morte, acho que j� morreu...");
				}
				return dDelay;
			}
		}
		if (!targetting) {

			Point p = getNpcNearestToMouse();
			if (p == null) {
				RuneMethods.logWindow("Procurando por Npc...");
				return Meth.intRandom(400, 800);
			}
			// RuneMethods.logWindow("Movendo Mouse rumo NPC...");
			double dist = p.distance(MouseHandler.getMousePos());
			if (dist < 4) {
				MouseHandler.dragMouse(p, 0, false, 0);
				wait(Meth.intRandom(10, 30));
				targetting = MouseHandler.MouseLeftClick(true);
				if (targetting) {
					justTargetted = true;
					targettingTime = System.currentTimeMillis();
					return 0;

				} else {
					RuneMethods.logWindow("Oops, cliquei errado...");
				}
				return Meth.intRandom(400, 2200);
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
	 * M�todo a ser reescrito para as Scripts de combate. Deve retornar true
	 * caso se deseje enterrar os ossos, ou false caso contr�rio.
	 * 
	 */
	abstract boolean toBuryBones();

	private boolean buryBones(ArrayList<Slot> inv) {

		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < inv.size(); i++) {
			if (inv.get(i).name.toLowerCase().contains("ossos")) {
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

	/**
	 * Dropa tudo no ch�o execeto o informado.
	 * 
	 * @param inventory
	 *            - O invent�rio do jogador, pode ser obtido pelo m�todo
	 *            InventoryHandler.getInventory().
	 * @param whatToKeep
	 *            - O que deve ser mantido do inventorio.
	 * @return
	 */
	protected boolean inventoryFullDropAllBut(ArrayList<Slot> inventory,
			String[] whatToKeep) {
		if (whatToKeep == null)
			return false;
		OptionTableManager.closeOptionTableIfOpen();
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
				inventory.get(i).doAction("Largar");
				RuneMethods.wait(Meth.intRandom(200, 300));
			}
		}
		return dropedSome;
	}

	/**
	 * Este m�todo s� deve ser reescrito se o jogador deve jogar eve dropar
	 * coisas no ch�o quando o invent�rio estiver cheio.
	 * 
	 * @return este return alimentar� o main, deixe null caso voce n�o queira
	 *         dropar nada, ou substitua por um vetor contendo os itens que voc�
	 *         quer que ele mantenha(N�o esque�a da comida caso voce esteja
	 *         usando).
	 */
	abstract String[] whatToKeep();

	/**
	 * Deve ser substituido pelas classes que a implementam, a lista deve conter
	 * os "InterestingPoints" contendo os mobs. A ordem � importante, ser� usada
	 * no m�todo para achar mosntros, ele procurar� primeiro pelo 1 tipo de
	 * monstro e assim por diante.
	 * 
	 * @return
	 */
	abstract ArrayList<InterestPoint> setMobInteresPoints();

	abstract long maxTimeOutCombat();

	/**
	 * M�todo a ser rescrito caso haja o desejo de pegar itens do monstro.
	 * 
	 * @param dPoint
	 *            para ser usado no m�todo: � o local aproximado onde o monstro
	 *            morreu.
	 * @param - o m�todo deve avisar se o loot foi recolhido ou n�o.
	 */
	boolean loot(Point dPoint, String[] loot) {
		if (loot == null)
			return false;
		String[] takeLoot = new String[loot.length];
		for (int i = 0; i < loot.length; i++) {
			takeLoot[i] = "Pegar " + loot[i]; // S� funciona para portugues.
		}
		int amount = OptionTableManager.getOptionOnPointContaningOneOrOther(
				dPoint, takeLoot, true);
		if (amount - 1 > 0) {
			wait(Meth.intRandom(500, 1200));
			loot(dPoint, loot);
			RuneMethods.logWindow("Oba achei " + amount
					+ " itens para serem coletados");
		}
		if (amount - 1 == 0) {
			RuneMethods.logWindow("Terminei de pegar o loot");
			return true;
		}
		RuneMethods.logWindow("N�o h� loot para ser pego");
		return false;
	}

	/**
	 * Procura por uma morte.
	 * 
	 * @return se n�o houve morte retorna NULL. Se houver mais de uma morte
	 *         simult�nea, devolve um Ponto conteno(-1,-1).Caso contr�rio
	 *         retorna um ponto contendo o lugar mais provavel da morte.
	 */
	private Point checkForDeathAndPosition() {
		ArrayList<Point> deathPoints = checkCombats.lookForDeath();
		if (deathPoints != null && !(deathPoints.size() == 0)) { // continua se
																	// n�o for
																	// vazio.
			RuneMethods
					.logWindow("Um NPC foi morto nas proximidades! Esperando entrar em decomposi��o...");
			ArrayList<Point> tempDeathPoints = checkCombats.lookForDeath();
			if (tempDeathPoints.size() > 1)
				return new Point(-1, -1);
			Point initP = tempDeathPoints.get(0);
			Point finalP = tempDeathPoints.get(0);
			int x = initP.x;
			int y = initP.y;
			int i = 1;
			while (tempDeathPoints != null && !(tempDeathPoints.size() == 0)) {
				if (tempDeathPoints.size() > 1)
					return new Point(-1, -1);
				finalP = tempDeathPoints.get(0);
				x+= finalP.x;
				y+= finalP.y;
				i++;
				tempDeathPoints = checkCombats.lookForDeath();
				wait(100);

			}
			initP.x = x/i;
			initP.y = y/i;
			tempDeathPoints = null;
			int dx = finalP.x - initP.x;
			//dx = dx > 3 ? 20 : dx == 0 ? 0 : dx < -3 ? - 20 : 0;
			dx = (int) Math.round(dx * 1.5);
			int dy = finalP.y - initP.y;
			//dy = dy > 3 ? 20 : dy == 0 ? 0 : dy < -3 ? - 20 : 0;
			dy = (int) Math.round(dy * 1.5);
			return new Point(finalP.x + dx, finalP.y + dy);

		}
		return null;
	}

	/**
	 * M�todo a ser reescrito pelas Scripts de combate. Se a Script n�o deve
	 * pegar loot, ent�o ela deve retornar nulo.Caso contr�rio, deve retornar os
	 * nomes interessantes.
	 */
	abstract protected String[] lootName();

}
