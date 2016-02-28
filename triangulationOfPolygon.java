/**
 * @author viyat
 * find minimum cost of convex polygon triangulation using dynamic programming
 */
public class triangulationOfPolygon {

	private double findCost(int[][] points) {
		
		int size = points.length;
		double memo[][] = new double[size][size];
		for (int gap = 0; gap < size; gap++) {
			for (int i = 0, j = gap; j < size; i++, j++) {
				if (j < i + 2)
					memo[i][j] = 0.0;
				else {
					memo[i][j] = Double.MAX_VALUE;
					for (int k = i + 1; k < j; k++) {
						double val = memo[i][k] + memo[k][j] + cost(points, i, j, k);
						if (memo[i][j] > val)
							memo[i][j] = val;
					}
				}
			}
		}

		return memo[0][size - 1];
	}

	private double cost(int[][] points, int i, int j, int k) {

		int p1x = points[i][0];
		int p1y = points[i][1];

		int p2x = points[j][0];
		int p2y = points[j][1];

		int p3x = points[k][0];
		int p3y = points[k][1];

		return dist(p1x, p1y, p2x, p2y) + dist(p2x, p2y, p3x, p3y) + dist(p3x, p3y, p1x, p1y);

	}

	private double dist(int p1x, int p1y, int p2x, int p2y) {
		
		return Math.sqrt((p1x - p2x) * (p1x - p2x) + (p1y - p2y) * (p1y - p2y));

	}

	public static void main(String[] args) {

		// points in x-y Cartesian plane
		int points[][] = { { 0, 0 }, { 1, 0 }, { 2, 1 }, { 1, 2 }, { 0, 2 } };
		int n = points.length;

		if (n < 4) {
			System.out.println("Must provide more than 3 points in polygon");
			System.out.println("So exiting...");
			System.exit(1);
		}

		System.out.println("X-Y co-ordinate of polygon are: ");
		for (int i = 0; i < n; i++) {
			System.out.println("(x,y)--> (" + points[i][0] + "," + points[i][1] + ")");
		}

		triangulationOfPolygon top = new triangulationOfPolygon();

		double cost = top.findCost(points);
		System.out.println("\nAnd minimum cost is: ");
		System.out.println(cost);

	}

}
