public class Pixel {
	private int a;
	private int r;
	private int g;
	private int b;

	public Pixel() {
		a = 0;
		r = 0;
		g = 0;
		b = 0;
	}

	public Pixel(int aNew, int rNew, int gNew, int bNew) {
		a = aNew;
		r = rNew;
		g = gNew;
		b = bNew;
	}

	public void setA(int aNew) {
		a = aNew;
	}

	public void setR(int rNew) {
		r = rNew;
	}

	public void setG(int gNew) {
		g = gNew;
	}

	public void setB(int bNew) {
		b = bNew;
	}

	public int getA() {
		return a;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	public void toGreyscale() {
		int avg = (r+g+b)/3;
		r = avg;
		b = avg;
		g = avg;
	}
}