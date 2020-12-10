package pixelspicturesclasses;

public class HorizontalStackPicture implements Picture{

private Picture left;
private Picture right;
private int width;
private int height;
private Pixel[][] picture;

public HorizontalStackPicture(Picture left, Picture right) {
	if(left == null || right == null || left.getHeight() != right.getHeight()) {
		throw new IllegalArgumentException("left and/or right inputs are invalid/null");
	}
	
	this.left=left;
	this.right=right;
	this.width = left.getWidth() + right.getWidth();
	this.height = left.getHeight();
	
	picture = new Pixel[width][height];
	
	for (int x = 0; x < left.getWidth(); x++) {
        for (int y = 0; y < left.getHeight(); y++) {
            picture[x][y] = left.getPixel(x, y);
        }
    }
	
	for (int x = 0; x < right.getWidth(); x++) {
        for (int y = 0; y < right.getHeight(); y++) {
            picture[left.getWidth()+x][y] = right.getPixel(x, y);
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
		throw new IllegalArgumentException("x and y are not valid");
	}else {
		return this.picture[x][y];
	}
}
public void paint(int x, int y, Pixel p){
	if(x<0 || y<0 || x >= width || y >= height || p == null) {
		throw new IllegalArgumentException("x,y,p are not valid");
	}else {
		this.picture [x][y]=p;
	}
}
public void paint(int x, int y, Pixel p, double factor) {
	if(x<0 || y<0 || x >= width || y >= height || p == null || factor >1 
			|| factor <0) {
		throw new IllegalArgumentException("x,y,p,factor are not valid");
	}else {
		this.picture[x][y] = this.picture[x][y].blend(p, factor);
	}
}
public void paint(int ax, int ay, int bx, int by, Pixel p) {
	if(ax < 0 || ay < 0 || bx < 0 || by  <0 || p == null) {
		throw new IllegalArgumentException("ax,ay,bx,by,p are not valid or null");
	}
}
public void paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
	if(ax < 0 || ay < 0 || bx < 0 || by  <0 || p == null|| factor<0 || factor>1) {
		throw new IllegalArgumentException("ax,ay,bx,by,p,factor are not valid or null");
	}
}
public void paint(int cx, int cy, double radius, Pixel p) {
	if(radius <0 || p ==null) {
		throw new IllegalArgumentException("radius, and/or p are not valid or null");
	}
	for(int i = 0 ;i < width ; i++) {
		for(int j = 0; j<height ; j++) {
			double dist = Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy));
			if(dist<=radius) {
				this.picture[i][j]=p;
			}
		}
	}
}
public void paint(int cx, int cy, double radius, Pixel p, double factor) {
	if(radius<0 || p==null || factor<0 || factor>1) {
		throw new IllegalArgumentException("radius,p,factor are not valid or null");
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
