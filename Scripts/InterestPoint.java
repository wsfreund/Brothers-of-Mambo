import java.awt.Color;

public class InterestPoint {
	
	/**
	 * Objeto que representa identifica um ponto de interesse, seja ele uma pedra um monstro, enfim.
	 */
	private Color pointColor;
	private int maxDistBetweenPoints;
	private int filterSize;
	private int minisize;
	private boolean simpleDelta;

	public Color getPointColor() {
		return pointColor;
	}

	public int getMaxDistBetweenPoints() {
		return maxDistBetweenPoints;
	}

	public int getFilterSize() {
		return filterSize;
	}

	public int getMinisize() {
		return minisize;
	}

	public boolean getSimpleDelta() {
		return simpleDelta;
	}

	InterestPoint(Color pointColorX, int maxDistBetweenPointsX, int filterSizeX,
			int minisizeX, boolean simpleDeltaX) {
		this.pointColor = pointColorX;
		this.maxDistBetweenPoints = maxDistBetweenPointsX;
		this.filterSize = filterSizeX;
		this.minisize = minisizeX;
		this.simpleDelta = simpleDeltaX;
	}
}
