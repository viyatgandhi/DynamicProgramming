/**
 * @author viyat
 * find minimum cost of matrix chain multiplication using dynamic programming
 */

public class matrixChainMultiplication {

	int arraySize = 0;
	int parenthesis[][];

	public matrixChainMultiplication(int length) {
		arraySize = length;
	}

	public int findCost(int arr[]) {
		
		int memo[][] = new int[arr.length][arr.length];
		parenthesis = new int[arr.length][arr.length];
		int q = 0;
		for (int l = 2; l < arr.length; l++) {
			for (int i = 0; i < arr.length - l; i++) {
				int j = i + l;
				memo[i][j] = Integer.MAX_VALUE;
				for (int k = i + 1; k < j; k++) {
					q = memo[i][k] + memo[k][j] + arr[i] * arr[k] * arr[j];
					if (q < memo[i][j]) {
						memo[i][j] = q;
						parenthesis[i + 1][j] = k;
					}
				}
			}
		}
		return memo[0][arr.length - 1];
	}

	private String printparenthesis(int i, int j) {
		if (i == j)
			return "A[" + i + "]";
		else
			return "(" + printparenthesis(i, parenthesis[i][j]) + printparenthesis(parenthesis[i][j] + 1, j) + ")";
	}

	public static void main(String args[]) {

		// input order of matrices such that ith matrix A[i] is of dimension arr[i-1] x arr[i]
		 int arr[] = { 4, 2, 3, 5, 3 };
		//int arr[] = { 2, 4, 5, 2, 1 };

		matrixChainMultiplication mmc = new matrixChainMultiplication(arr.length);

		System.out.println("Matrices are of order: ");
		for (int i = 1; i < arr.length; i++) {
			System.out.println("A" + i + "--> " + arr[i - 1] + "x" + arr[i]);
		}

		int cost = mmc.findCost(arr);
		System.out.println("\nAnd minimum cost is: ");
		System.out.println(cost);
		System.out.println("\nMatrix Multiplication order with parenthesis is: ");
		System.out.println(mmc.printparenthesis(1, arr.length - 1));

	}
}