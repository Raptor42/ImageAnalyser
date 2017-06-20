public class ImageAnalyser {
	public static void main(String[] args) {
		String inFN = args[0];
		int lastSlash = inFN.lastIndexOf("/");
		String fn = inFN.substring(lastSlash);
		String outFN = "../output/" + fn;
		Image image = new Image(inFN);
		image.toGreyscale();
		image.write(outFN);
	}
}