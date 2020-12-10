package pixelspicturesclasses;

public class GradientPicture implements Picture{
	
private int width;
private int height;
private Pixel upper_left;
private Pixel upper_right;
private Pixel lower_left;
private Pixel lower_right;
private Pixel[][] pixel_array;

public GradientPicture (int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {
	
	if( width <= 0 || height <=0 || upper_left == null || upper_right == null || lower_left == null || lower_right == null) {
		throw new IllegalArgumentException ("one or more inputs are not valid");
	}
	
	this.width=width;
	this.height=height;
	this.upper_left=upper_left;
	this.upper_right=upper_right;
	this.lower_left=lower_left;
	this.lower_right=lower_right;
	
	pixel_array = new Pixel[this.width][this.height];
	
	double factor_width = 1.0 / (this.width-1);
	double factor_height = 1.0 / (this.height-1);
	
	for (int col = 0 ; col < this.width ; col ++) {
		for(int row = 0 ; row < this.height ; row++) {
			pixel_array[col][row] = upper_left.blend(lower_left, row*factor_height)
					.blend(upper_right.blend(lower_right,row*factor_height),col*factor_width);
			
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
	if( x < 0 || y < 0 || x>= width || y>=height) {
		throw new IllegalArgumentException("x/y less than 0");
	}
	return this.pixel_array[x][y];
	// calculate value of pixel at beginning of desired row
		// blend of upper left and lower left corners
	
	// calculate value of pixel at end of desired row
		// at (getWidth()-1, y )
	
	// calculate value of (x, y) as blend of the beginning and end of row
}
public void paint(int x, int y, Pixel p) {
	throw new UnsupportedOperationException("picture is immutable");
}
public void paint(int x, int y, Pixel p, double factor){
	throw new UnsupportedOperationException("picture is immutable");
}
public void paint(int ax, int ay, int bx, int by, Pixel p){
	throw new UnsupportedOperationException("picture is immutable");
}
public void paint(int ax, int ay, int bx, int by, Pixel p, double factor){
	throw new UnsupportedOperationException("picture is immutable");
}
public void paint(int cx, int cy, double radius, Pixel p){
	throw new UnsupportedOperationException("picture is immutable");
}
public void paint(int cx, int cy, double radius, Pixel p, double factor){
	throw new UnsupportedOperationException("picture is immutable");
}

}
