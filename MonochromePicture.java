package pixelspicturesclasses;

public class MonochromePicture implements Picture{
	private int width;
	private int height;
	private Pixel value;
	
	public MonochromePicture (int width, int height, Pixel value) {
		if(width <= 0 || height <= 0 || value == null) {
			throw new IllegalArgumentException ("width, height, or value are negative or null");
		}else {
		this.width=width;
		this.height=height;
		this.value=value;
	}
	} 
	
	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
	@Override
	public Pixel getPixel(int x, int y) {
		if(x<0 || y<0 || x >= width || y>=height) {
			throw new IllegalArgumentException("invalid width and/or height");
		}else {
			return this.value;
		}
	}
	
	@Override
	public void paint(int x, int y, Pixel p) {
		throw new UnsupportedOperationException("picture is immutable");
	}
	@Override
	public void paint(int x, int y, Pixel p, double factor) {
		throw new UnsupportedOperationException("picture is immutable");
	}
	@Override
	public void paint(int ax, int ay, int bx, int by, Pixel p) {
		throw new UnsupportedOperationException("picture is immutable");
		
	}
	@Override
	public void paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		throw new UnsupportedOperationException("picture is immutable");
		
	}
	@Override
	public void paint(int cx, int cy, double radius, Pixel p) {
		throw new UnsupportedOperationException("picture is immutable");
		
	}
	@Override
	public void paint(int cx, int cy, double radius, Pixel p, double factor) {
		throw new UnsupportedOperationException("picture is immutable");
		
	}
	
}
