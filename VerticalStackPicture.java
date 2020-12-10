package pixelspicturesclasses;

public class VerticalStackPicture implements Picture{

	private int width;
	private int height;
	private Pixel[][]picture;
	

	public VerticalStackPicture(Picture top, Picture bottom) {
		if(top == null || bottom == null || top.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("top and/or bottom inputs are invalid/null");
		}else {
		
		this.width = top.getWidth();
		this.height = top.getHeight()+bottom.getHeight();
		picture = new Pixel[width][height];
		
		for (int x = 0; x < top.getWidth(); x++) {
            for (int y = 0; y < top.getHeight(); y++) {
                picture[x][y] = top.getPixel(x, y);
            }
        }
		for (int x = 0; x < bottom.getWidth(); x++) {
            for (int y = 0; y < bottom.getHeight(); y++) {
                picture[x][top.getHeight()+y] = bottom.getPixel(x, y);
            }
        }
	}
	}

	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}

	public Pixel getPixel(int x, int y) {
		if(x<0 || y<0 || x >= width || y >= height) {
			throw new IllegalArgumentException("x/y are not valid");
		}else {
			return this.picture[x][y];
		}
	}
	public void paint(int x, int y, Pixel p){
		if(x<0 || y<0 || x >= width || y >= height|| p == null || x>=width || y>=width) {
			throw new IllegalArgumentException("x/y,p are not valid");
		}else {
			this.picture[x][y]=p;
		}
	}
	public void paint(int x, int y, Pixel p, double factor) {
		if(x < 0 || y < 0 || factor < 0 || factor >1 || x >= width || y >= height || p == null) {
			throw new IllegalArgumentException("x,y,factors are not valid");
		}else {
			this.picture[x][y] = this.picture[x][y].blend(p, factor);
		}
	}
	public void paint(int ax, int ay, int bx, int by, Pixel p) {
		if(ax < 0 || ay <0 || bx <0 || by < 0 || p == null) {
			throw new IllegalArgumentException("ax,ay,bx,by, p null or invalid");
		}
		if(ax > this.width || bx > this.width || ay > this.height || by > this.height ) {
			throw new IllegalArgumentException("values are not acceptable");
		}
		
	}
	public void paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if(ax < 0 || ay <0 || bx <0 || by < 0 || p == null|| factor<0 || factor>1) {
			throw new IllegalArgumentException("ax,ay,bx,by, p,factor null or invalid");
		}
		if(ax > this.width || bx > this.width || ay > this.height || by > this.height ) {
			throw new IllegalArgumentException("values are not acceptable");
		}
		
	}
	public void paint(int cx, int cy, double radius, Pixel p) {
		if(radius<0 || p == null) {
			throw new IllegalArgumentException("radius, p are not valid");
		}
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				double distance = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
				if(distance <= radius) {
					this.picture[x][y] = p;
				}
			}
		}
	}
	public void paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(radius<0 || p == null|| factor>1 || factor<0) {
			throw new IllegalArgumentException("radius,p,factor are not valid");
		}
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				double distance = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
				if(distance <= radius) {
					this.picture[x][y] = this.picture[x][y].blend(p, factor);
				}
			}
		}
	}
}
