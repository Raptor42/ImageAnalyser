import java.lang.Math;

public class Matrix {
	private double[][] matrix;
	private int width;
	private int length;
	private static boolean log;
	/*
	private boolean square;
	private boolean zero;
	private boolean identity;
	private boolean vector;
	private boolean upperTriang;
	private boolean lowerTriang;
	private boolean triangular;
	private boolean diagonal;
	private double trace;
	private Matrix trans;
	private double det2x2;
	 */


	public Matrix(int x, int y) {
		matrix = new double[y][x];
		width = x;
		length = y;

		// this.removeNull();
	}

	public Matrix(double[][] data){
		int x = data[0].length;
		int y = data.length;

		matrix = new double[y][x];
		width = x;
		length = y;

		this.fill(data);

		// this.removeNull();
	}

	public Matrix(Matrix data) {
		this.width = data.getWidth();
		this.length = data.getLength();
		this.fill(data.getAll());

		// this.removeNull();
	}

	public Matrix(int x, int y, boolean v) {
		matrix = new double[y][x];
		width = x;
		length = y;
		log = v;

		// this.removeNull();
	}

	public Matrix(double[][] data, boolean v){
		int x = data[0].length;
		int y = data.length;

		matrix = new double[y][x];
		width = x;
		length = y;

		this.fill(data);

		log = v;

		// this.removeNull();
	}

	public Matrix(Matrix data, boolean v) {
		this.width = data.getWidth();
		this.length = data.getLength();
		this.fill(data.getAll());

		log = v;

		// this.removeNull();
	}

	/*
	public void removeNull() {
		for (int x=0; x<this.width; x++) {
			for (int y=0; y<this.length; y++) {
				double value = this.getEntry(x, y);
				if (value==null) {
					this.setEntry(x, y, 0);
				}
			}
		}
	}
	 */
	
	public void setEntry(int x, int y, double value) {
		matrix[y][x] = value;
	}

	public void setColumn(int x, double[] data) {
		int dataLength = data.length;
		if (dataLength>length) {
			System.err.println("Input data too large: " + dataLength + " compared to " + length);
			System.exit(0);
		}

		for (int y=0; y<dataLength; y++) {
			this.setEntry(x, y, data[y]);
		}
	}

	public void setRow(int y, double[] data) {
		int dataLength = data.length;
		if (dataLength>width) {
			System.err.println("Input data too large: " + dataLength + " compared to " + width);
			System.exit(0);
		}

		for (int x=0; x<dataLength; x++) {
			this.setEntry(x, y, data[x]);
		}
	}

	public void fill(int x, int y, double[][] data) {
		int dataLength = data.length;
		int dataWidth = data[0].length;

		if (((dataWidth+x) > width) || ((dataLength+y) > length)) {
			System.err.println("Input data too large: ("+dataWidth+","+dataLength+") compared to ("+width+","+length+")");
			System.exit(0);
		}

		for (int i=x; i<dataWidth; i++) {
			for (int j=y; j<dataLength; j++) {
				this.setEntry(i, j, data[j][i]);
			}
		}
	} 

	public void fill(double[][] data) {
		this.fill(0, 0, data);
	}

	public void fill(Matrix data) {
		double[][] values = data.getAll();
		this.fill(0, 0, values);
	}

	public double getEntry(int x, int y) {
		return matrix[y][x];
	}

	public double[] getRow(int x) {
		return matrix[x];
	}

	public double[] getColumn(int y) {
		Matrix trans = this.getTrans();
		return trans.getRow(y);
	}

	public double[][] getAll() {
		return this.matrix;
	}

	public int getWidth() {
		return width;
	}

	public int getLength() {
		return length;
	}

	public Matrix plus(Matrix data) {
		int dataWidth = data.getWidth();
		int dataLength = data.getLength();

		if ((dataWidth != this.width) || (dataLength != this.length)) {
			System.err.println("Matricies different sizes: ("+dataWidth+","+dataLength+") compared to ("+width+","+length+")");
			System.exit(0);
		}

		Matrix result = new Matrix(this.width, this.length);

		for (int x=0; x<this.width; x++) {
			for (int y=0; y<this.length; y++) {
				double a = this.getEntry(x, y);
				double b = this.getEntry(x, y);

				result.setEntry(x, y, (a+b));
			}
		}

		return result;
	}

	public Matrix subtract(Matrix data) {
		data.multiply(-1.0);
		return this.plus(data);
	}

	public Matrix multiply(double scalar) {
		Matrix result = new Matrix(this.width, this.length);

		for (int x=0; x<width; x++) {
			for (int y=0; y<length; y++) {
				double input = this.getEntry(x, y);
				result.setEntry(x, y, (scalar*input));
			}
		}

		return result;
	}

	public Matrix multiply(Matrix data) {
		if (this.width != data.length) {
			System.err.println("Invalid sizes");
			System.exit(0);
		}

		int n = this.width;

		Matrix result = new Matrix(data.getWidth(), this.getLength());

		for (int i=0; i<result.getWidth(); i++) {
			for (int j=0; j<result.getLength(); j++) {
				double value = 0;

				for (int k=0; k<n; k++) {
					double a = this.getEntry(i, k);
					double b = data.getEntry(k, j);

					value += (a*b);
				}

				result.setEntry(i, j, value);
			}
		}

		return result;
	}

	public boolean isSquare() {
		/*
		if (square!=null) {
			return square;
		}
		 */
		
		boolean square;

		if(width==length) {
			square = true;
		} else {
			square = false;
		}

		return square;
	}

	public boolean isZero() {
		/*
		if (zero!=null) {
			return zero;
		}
		 */
		
		boolean zero;

		for (int i=0; i<width; i++) {
			for (int j=0; j<length; j++) {
				double value = this.getEntry(i, j);
				if (value!=0) {
					zero = false;
					return zero;
				}
			}
		}

		zero = true;
		return zero;
	}

	public boolean isIdentity() {
		/*
		if (identity!=null) {
			return identity;
		}
		 */
		
		boolean identity;

		if (this.isSquare()) {
			identity = false;
			return identity;
		}

		for (int i=0; i<width; i++) {
			for (int j=0; j<length; j++) {
				double value = this.getEntry(i, j);
				if (i==j) {
					if (value!=1) {
						identity = false;
						return identity;
					}
				} else {
					if (value!=0) {
						identity = false;
						return identity;
					}
				}
			}
		}

		// diagonal = true;
		identity = true;
		return true;
	}

	public boolean isVector() {
		/*
		if (vector!=null) {
			return vector;
		}
		 */
		
		boolean vector;

		if (this.getWidth() == 1) {
			vector = true;
		} else {
			vector = false;
		}

		return vector;
	}

	public boolean isUpperTriang() {
		/*
		if (upperTriang!=null) {
			return upperTriang;
		}
		 */
		
		boolean upperTriang;

		if (this.isSquare()) {
			upperTriang=false;
			//triangular=false;
			return upperTriang;
		}

		/*
		if (triangular==false) {
			upperTriang=false;
			//lowerTriang=false;
			return upperTriang;
		}
		 */

		for (int i=0; i<width; i++) {
			for (int j=i; j<length; j++) {
				double value = this.getEntry(i, j);
				
				if (value!=0) {
					upperTriang = false;
					return upperTriang;
				}
				
			}
		}

		upperTriang=true;
		//triangular=true;
		return upperTriang;
	}

	public boolean isLowerTriang() {
		/*
		if (lowerTriang!=null) {
			return lowerTriang;
		}
		 */
		
		boolean lowerTriang;

		if (this.isSquare()) {
			lowerTriang=false;
			// triangular=false;
			return lowerTriang;
		}

		/*
		if (triangular==false) {
			upperTriang=false;
			lowerTriang=false;
			return lowerTriang;
		}
		 */

		for (int i=0; i<width; i++) {
			for (int j=0; j<i; j++) {
				double value = this.getEntry(i, j);
				
				if (value!=0) {
					lowerTriang = false;
					return lowerTriang;
				}
				
			}
		}

		lowerTriang=true;
		// triangular=true;
		return lowerTriang;
	}

	public boolean isTriangular() {
		/*
		if (triangular!=null) {
			return triangular;
		}


		if (diagonal==true) {
			triangular = true;
			upperTriang = true;
			lowerTriang = true;
			return triangular;
		}
		 */
		
		boolean triangular;

		if (this.isUpperTriang()) {
			triangular = true;
			return triangular;
		}

		if (this.isLowerTriang()) {
			triangular = true;
			return triangular;
		}

		triangular = false;
		return triangular;
	}

	public boolean isDiagonal() {
		/*
		if (diagonal!=null) {
			return diagonal;
		}


		if ((upperTriang==true)&&(lowerTriang==true)) {
			diagonal = true;
			return diagonal;
		}
		 */
		
		boolean diagonal;

		for (int i=0; i<width; i++) {
			for (int j=0; j<length; j++) {
				double value = this.getEntry(i, j);
				if ((i!=j) && (value!=0)) {
					diagonal = false;
					return diagonal;
				}
			}
		}

		diagonal = true;
		//triangular = true;
		//upperTriang = true;
		//lowerTriang = true;
		return diagonal;
	}

	public double getTrace() {
		/*
		if (trace!=null) {
			return trace;
		}
		 */

		if (this.isSquare()==false) {
			System.err.println("Matrix not square");
			System.exit(0);
		}

		double trace = 0;

		for (int i=0; i<this.getWidth(); i++) {
			double value = this.getEntry(i, i);
			trace += value;
		}

		return trace;
	}

	public Matrix commute(Matrix other) {
		// AB - BA
		// (r)  (s)
		
		if ((!(this.isSquare()))||(!(other.isSquare()))||( (this.getWidth())!=(other.getWidth()) )||( (this.getLength())!=(other.getLength()) )) {
			System.err.println("Invalid Input");
			System.exit(0);
		}
	
		Matrix r = this.multiply(other);
		Matrix s = other.multiply(this);

		Matrix result = r.subtract(s);

		return result;	
	}

	public boolean commutesWith(Matrix other) {
		if ((!(this.isSquare()))||(!(other.isSquare()))||( (this.getWidth())!=(other.getWidth()) )||( (this.getLength())!=(other.getLength()) )) {
			System.err.println("Invalid Input");
			System.exit(0);
		}

		Matrix commutator = this.commute(other);

		return commutator.isZero();
	}

	public Matrix getTrans() {
		/*
		if (trans!=null) {
			return trans;
		}
		 */

		Matrix trans = new Matrix(this.getLength(), this.getWidth());

		for (int i=0; i<trans.getWidth(); i++) {
			for (int j=0; j<trans.getLength(); j++) {
				double value = this.getEntry(j, i);
				trans.setEntry(i, j, value);
			}
		}

		return trans;
	}

	public double getDet2x2() {
		/*
		if (det2x2!=null) {
			return det2x2;
		}
		 */

		if((!(this.isSquare()))||(width!=2)) {
			System.err.println("Invalid Input");
			System.exit(0);
		}

		double det2x2 = this.getEntry(0,0)*this.getEntry(1,1) - this.getEntry(1,0)*this.getEntry(0,1);
		return det2x2;
	}

	public Matrix getMinor(int r, int s) {
		//remove row r an collumn s
		//r and s start at 0
		
		log("(getMinor) Input Size: " + this.getWidth() + "x" + this.getLength());
		Matrix minor = new Matrix ( ((this.getWidth())-1) , ((this.getLength())-1));
		log("(getMinor) Minor size: " + minor.getWidth() + "x" + minor.getLength());

		int j=0;

		for (int y=0; (y-1)<minor.getLength(); y++) {
			log("y:" + y);
			
			log("j:" + j);

			if (y==r) {
				//y++;
			} else {
				int i=0;

				for (int x=0; (x-1)<minor.getWidth(); x++) {
					log("x:" + x);
					log("i:" + i);
					if (x==s) {
						//x++;
					} else {
						double value = this.getEntry(x, y);
						minor.setEntry(i, j, value);
						log("minor("+i+","+j+") set to this("+x+","+y+")" );
						i++;
						
					}
				}
				
				j++;
			}			
		}

		return minor;
	}

	public double getCof(int s, int r) {
		log("(getCof) Finding sign...");
		double sign = Math.pow(-1, (r+s));
		log("(getCof) Sign found: " + sign);
		log("(getCof) Finding minor (r="+r+", s="+s+")...");
		Matrix minor = this.getMinor(r, s);
		//minor.print();
		int minorSize = minor.getWidth();
		log("(getCof) minor n="+minorSize);
		log("(getCof) Finding determinant...");
		double det = minor.getDet();
		log("(getCof) Det found: "+det);
		return sign*det;
	}

	public double getDet() {
		log("(getDet) Checking input is square...");

		if(!(this.isSquare())) {
			System.err.println("Invalid Input");
			System.exit(0);
		}
		log("(getDet) Input is square.");

		log("(getDet) Checking if n=2..");
		if (this.getWidth() == 2) {
			log("(getDet) n=2. Using getDet2x2...");
			return this.getDet2x2();			
		}
		log("n!=2. Using recursive method...");
		double det = 0;


		for (int k=0; k<this.getLength(); k++) {
			log("(getDet) FInding Cofactor (k="+k+")...");
			det+= this.getEntry(k,0)*this.getCof(k,0);
		}

		return det;
	}

	public Matrix getCofMat() {
		Matrix result = new Matrix(this.getWidth(), this.getLength());

		for (int y=0; y<result.getLength(); y++) {
			for (int x=0; x<result.getWidth(); x++) {
				double value = this.getCof(y, x);
				result.setEntry(x, y, value);
			}
		}

		return result;
	}

	public Matrix getInv() {
		double det = 1/this.getDet();
		Matrix mat = (this.getCofMat()).getTrans();
		return mat.multiply(det);
	}

	private static void log(String s) {
		if (log) {
			System.out.println("Log: " + s);
		}
	}

	public void print() {
		for (int y=0; y<this.getLength(); y++) {
			String line = "";
			for (int x=0; x<this.getWidth(); x++) {
				line += (this.getEntry(x,y) + "\t");
			}
			System.out.println(line);
		}
	}


}