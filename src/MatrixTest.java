public class MatrixTest {
	public static void main(String[] args) {
		System.out.println("(MatrixTest) Making arrays...");
		double[][] matrix1 = new double[][]{{4,3},
											{3,2}}; //-2

		double[][] matrix2 = new double[][]{{2,3,4},
											{9,2,3},
											{4,7,9}}; //7

		double[][] matrix3 = new double[][]{{2,3,4,3},
											{9,2,3,5},
											{4,7,9,7},
											{2,6,3,5}}; //-31

		System.out.println("(MatrixTest) Making matricies...");
		Matrix n_2 = new Matrix(matrix1, false);
		//System.out.println("(MatrixTest) Matrix 1:");
		//n_2.print();
		Matrix n_3 = new Matrix(matrix2, false);
		//System.out.println("(MatrixTest) Matrix 2:");
		//n_3.print();
		Matrix n_4 = new Matrix(matrix3, false);
		//System.out.println("(MatrixTest) Matrix 3:");
		//n_4.print();

		System.out.println("(MatrixTest) Finding Dets...");
		double det2 = n_2.getDet();
		System.out.println("(MatrixTest) n=2: " + det2);
		double det3 = n_3.getDet();
		System.out.println("(MatrixTest) n=3: " + det3);
		double det4 = n_4.getDet();
		System.out.println("(MatrixTest) n=4: " + det4);

		System.out.println("(MatrixTest) Finding Invs...");
		Matrix res_1 = n_2.getInv();
		res_1.print();
	}
}