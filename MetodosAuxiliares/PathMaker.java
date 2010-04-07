import java.awt.Point;
import java.util.ArrayList;

public final class PathMaker {

	/**
	 * The class <code>PathMake</code> contains methods for making Path between
	 * two or more points, using integer values. This class is a static class.
	 */

	// May not initiate this class
	private PathMaker() {
	}

	// TODO obs: nao precisa ordenar ao percorrer o eixo x, apenas o eixo y.
	// Também nao precisa percorre o primeiro ultimos pontos, apenas
	// adicioná-los.
	/**
	 * Makes a line Path (of integer) Points, between two points.
	 * 
	 * @param begin
	 *            The firts Point of the line.
	 * 
	 * @param end
	 *            The final Point of the line.
	 * 
	 * @return a List of Points, containing the integer Path between the two
	 *         Points informed.
	 */
	public static ArrayList<Point> makeLinePath(Point begin, Point last) {
		return null;
	}

	/**
	 * This method is used to get the angle (from axis Y) between two points.
	 * 
	 * @param first
	 *            The first Point.
	 * 
	 * @param second
	 *            The second Point.
	 * 
	 * @return the angular coefficient
	 */
	@SuppressWarnings("unused")
	private static double getYAng(Point first, Point second) {
		return (second.x - first.x) / (second.y - first.y);
	}

	/**
	 * This method is used to get the angle (from axis Y) between two points.
	 * 
	 * @param first
	 *            The first Point.
	 * 
	 * @param second
	 *            The second Point.
	 * 
	 * @return the angular coefficient
	 */
	@SuppressWarnings("unused")
	private static double getXAng(Point first, Point second) {
		return (second.y - first.y) / (second.x - first.x);
	}

	/**
	 * This method is used to get the intersection between the X axis and a line
	 * that passes between two Points.
	 * 
	 * @param first
	 *            The first Point.
	 * 
	 * @param second
	 *            The second Point.
	 * 
	 * @return the angular coefficient
	 */
	@SuppressWarnings("unused")
	private static double getYLinCoef(Point first, Point second) {
		return (first.x * second.y - first.y * second.x) / (first.x - second.x);
	}

	/**
	 * This method is used to get the intersection between the Y axis and a line
	 * that passes between two Points.
	 * 
	 * @param first
	 *            The first Point.
	 * 
	 * @param second
	 *            The second Point.
	 * 
	 * @return the angular coefficient
	 */
	@SuppressWarnings("unused")
	private static double getXLinCoef(Point first, Point second) {
		return (first.y * second.x - first.x * second.y) / (first.y - second.y);
	}

	/**
	 * This method is used to generate a random path between two points.
	 * 
	 * @param first
	 *            The first Point.
	 * 
	 * @param second
	 *            The second Point.
	 * 
	 * @return An arrayList of points contaning all points of the path.
	 */
	public static ArrayList<Point> generateRandomPath(Point inicialP,
			Point finalP) {
		ArrayList<Point> points = new ArrayList<Point>();
		int px = inicialP.x;
		int py = inicialP.y;
		int dx = (finalP.x - inicialP.x);
		int dy = (finalP.y - inicialP.y);

		points.add(inicialP);
		while (!(dx == 0) || !(dy == 0)) {
			int mX = 0;
			int mY = 0;
			// -- //
			// System.out.println("dx: " + dx + " dy: " + dy);
			float modDx = Math.abs(dx);
			float modDy = Math.abs(dy);
			float total = modDx + modDy;
			boolean tempX = (Math.random() < Math.abs((modDx / total)));
			boolean tempY = (Math.random() < Math.abs((modDy / total)));

			mX = (tempX ? dx > 0 ? 1 : -1 : 0);
			mY = (tempY ? dy > 0 ? 1 : -1 : 0);

			if (mX != 0 || mY != 0) {
				dx = (dx - mX);
				dy = (dy - mY);
				px += mX;
				py += mY;
				points.add(new Point(px, py));
			}

		}
		return points;
	}

	/**
	 * This method is used to generate a random path between two points.
	 * 
	 * @param points
	 *            : an array of points containing points where the path must
	 *            move by.
	 * 
	 * @param random
	 *            : will add randomness to all points execept to the first and
	 *            last point
	 * 
	 * @return An arrayList of points containing all points of the path.
	 */
	public static ArrayList<Point> makeRandomPathByArrayPoints(
			ArrayList<Point> points, int randomness) {
		if (points.size() < 2)
			return null;
		if (points.size() == 2)
			return generateRandomPath(points.get(0), points.get(1));

		ArrayList<Point> path = new ArrayList<Point>();
		for (int i = 1; i < points.size(); i++) {
			if (i == 1) {
				Point lRand = new Point(points.get(i).x
						+ Meth.intRandom(-randomness, randomness), points
						.get(i).y
						+ Meth.intRandom(-randomness, randomness));
				path.addAll(generateRandomPath(points.get(i - 1), lRand));
			}
			if (i == points.size()) {
				Point fRand = new Point(points.get(i - 1).x
						+ Meth.intRandom(-randomness, randomness), points
						.get(i).y
						+ Meth.intRandom(-randomness, randomness));
				path.addAll(generateRandomPath(fRand, points.get(i)));
			}
			Point lRand = new Point(points.get(i).x
					+ Meth.intRandom(-randomness, randomness), points.get(i).y
					+ Meth.intRandom(-randomness, randomness));
			path.addAll(generateRandomPath(points.get(i - 1), lRand));
			Point fRand = new Point(points.get(i - 1).x
					+ Meth.intRandom(-randomness, randomness), points.get(i).y
					+ Meth.intRandom(-randomness, randomness));
			path.addAll(generateRandomPath(fRand, points.get(i)));
			path.addAll(generateRandomPath(fRand, lRand));
		}
		return path;
	}

	/**
	 * This method is used to generate a random path between two points.
	 * 
	 * @param Point
	 *            init: inicial point
	 * 
	 * @param Point
	 *            last: last point
	 * 
	 * @param int factor: the amount of distance between the random points
	 *        added.
	 * 
	 * @return An arrayList of points containing all points of the path.
	 */
	public static ArrayList<Point> makeRandomPathByArrayPoints(Point init,
			Point last, int factor) {

		int dx = last.x - init.x;
		int dy = last.y - init.y;
		int amount = (Math.abs(dx) + Math.abs(dy)) / factor;
		if (amount < 3)
			return generateRandomPath(init, last);
		ArrayList<Point> randomPoints = new ArrayList<Point>();
		randomPoints.add(init);
		for (int i = 1; i == amount - 1; i++) {
			int tempx = (int) (last.x / (amount - i)) + Meth.intRandom(-4, 4);
			int tempy = (int) (last.y / (amount - i)) + Meth.intRandom(-4, 4);
			randomPoints.add(new Point(tempx, tempy));

		}
		randomPoints.add(last);
		return makeRandomPathByArrayPoints(randomPoints, 0);
	}

}