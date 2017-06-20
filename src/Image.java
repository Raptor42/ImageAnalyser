import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class Image {
	private Pixel[][] pixel;
	private String filepath;
	private int width;
	private int height;

	public Image(String fn) {
		filepath = fn;

		try {
			File file = new File(fn);
			BufferedImage image = ImageIO.read(file);

	    	width = image.getWidth();
	    	height = image.getHeight();
	    	
	    	pixel = new Pixel[width][height];

			for(int y = 0; y < height; y++) {
	      		for(int x = 0; x < width; x++) {
	        		int a = getA(image, x, y);
	        		int r = getR(image, x, y);
	        		int g = getG(image, x, y);
	        		int b = getB(image, x, y);

	        		pixel[x][y] = new Pixel(a, r, g, b);
	      		}
	   		}

		} catch(IOException ioE) {
      		System.out.println(ioE);
    	}
	}

	public Image(BufferedImage image) {
		width = image.getWidth();
    	height = image.getHeight();
    	filepath = null;

    	for(int y = 0; y < height; y++) {
      		for(int x = 0; x < width; x++) {
        		int a = getA(image, x, y);
        		int r = getR(image, x, y);
        		int g = getG(image, x, y);
        		int b = getB(image, x, y);

        		pixel[x][y] = new Pixel(a, r, g, b);
      		}
   		}
	}

	public Image(BufferedImage image, String fn) {
		width = image.getWidth();
    	height = image.getHeight();
    	filepath = fn;

    	for(int y = 0; y < height; y++) {
      		for(int x = 0; x < width; x++) {
        		int a = getA(image, x, y);
        		int r = getR(image, x, y);
        		int g = getG(image, x, y);
        		int b = getB(image, x, y);

        		pixel[x][y] = new Pixel(a, r, g, b);
      		}
   		}
	}

	public Pixel getPixel(int x, int y) {
		return pixel[x][y];
	}

	public String getFilepath() {
		return filepath;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void write() {
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			for (int x=0; x<width; x++) {
				for (int y=0; y<height; y++) {
					int a = pixel[x][y].getA();
					int r = pixel[x][y].getR();
					int g = pixel[x][y].getG();
					int b = pixel[x][y].getB();

					int rgb = (a<<24) | (r<<16) | (g<<8) | b;

					image.setRGB(x, y, rgb);
				}
			}

			File file = new File(filepath);
			ImageIO.write(image, "jpg", file);
		} catch(IOException ioE) {
      		System.out.println(ioE);
    	}
	}

	public void write(String fn) {
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

			for (int x=0; x<width; x++) {
				for (int y=0; y<height; y++) {
					int a = pixel[x][y].getA();
					int r = pixel[x][y].getR();
					int g = pixel[x][y].getG();
					int b = pixel[x][y].getB();

					int rgb = (a<<24) | (r<<16) | (g<<8) | b;

					image.setRGB(x, y, rgb);
				}
			}

			File file = new File(fn);
			ImageIO.write(image, "jpg", file);
		} catch(IOException ioE) {
      		System.out.println(ioE);
    	}
	}

	public void toGreyscale() {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				pixel[x][y].toGreyscale();
			}
		}
	}

	private static int getA(BufferedImage image, int x, int y) {
		int rgb = image.getRGB(x,y);
		int a = (rgb>>24) & 0xff;
		return a;
	}

	private static int getR(BufferedImage image, int x, int y) {
		int rgb = image.getRGB(x,y);
		int r = (rgb>>16) & 0xff;
		return r;
	}

	private static int getG(BufferedImage image, int x, int y) {
		int rgb = image.getRGB(x,y);
		int g = (rgb>>8) & 0xff;
		return g;
	}

	private static int getB(BufferedImage image, int x, int y) {
		int rgb = image.getRGB(x,y);
		int b  = rgb & 0xff;
		return b;
	}
}