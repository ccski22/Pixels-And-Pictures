package pixelspicturesclasses;

public class MutablePixelArrayPicture implements Picture{
	
	private int width;
	private int height;
	private Pixel [][] picture;
	
	// creates new object using values provided by pixel array, matching in size
	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
		
		if(pixel_array == null || pixel_array.length == 0 || pixel_array[0].length == 0) {
			throw new IllegalArgumentException("invalid input");
		}else {
			for(int i = 0; i<pixel_array.length;i++) {// check each element for exceptions
				if(pixel_array[i] == null || pixel_array[i].length != pixel_array[0].length ) {
					throw new IllegalArgumentException("invalid input");
				}
				else {
					for(int j = 0; j < pixel_array[0].length; j++) {
						if(pixel_array[i][j] == null) {
						throw new IllegalArgumentException("invalid input");
						}
					}
				}
			}
		}
			
			this.width = pixel_array.length;
			
			this.height = pixel_array[0].length;
			
			this.picture = new Pixel[this.width][this.height];
			
			for(int i=0;i<pixel_array.length;i++) { // for each element in pixel array:
				
			    for(int j=0;j<pixel_array[0].length;j++) {
			    	
				picture[i][j]=pixel_array[i][j]; //build the picture array of the pixels
				
			    }
			}
	}
	
	
	// constructor 2: create new object by providing geometry of picture/initial value for all pixels
	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		
		if(width <= 0 || height <= 0 || initial_value == null) {
			throw new IllegalArgumentException("invalid input");
			
		}else { //construct new picture array
			this.width = width;
			this.height = height;
			
			picture = new Pixel[width][height];
			
	        for (int j = 0; j < width; j++) {
	            for (int i = 0; i < height; i++) {
	                picture[j][i] = initial_value; 
	            }
	        }
		}
	}
	
	// constructor 3: create new object by providing geometry of picture
	// initial value of all pixels should be medium gray (grayscale pixel w intensity = 0.5)
	public MutablePixelArrayPicture(int width, int height) {
		
		if(width <= 0 || height <= 0) {
			throw new IllegalArgumentException("invalid input");
			
		}else {
			this.width = width;
			this.height = height;
			
			picture = new Pixel[width][height];
			
	        for (int j = 0; j < width; j++) {
	            for (int i = 0; i < height; i++) {
	                picture[j][i] = new GrayPixel(0.5);
	            }
	        }
		}
	}
	

	/** Getters for the dimensions of a picture
	Width refers to the number of columns and height is the number of rows**/
		@Override
		public int getWidth() {
			return this.width;
		}
		
		@Override
		public int getHeight() {
			return this.height;
		}
		
		/**Retrieves pixel at position (x,y) in picture. (0,0) is the upper left
		 * corner of the picture, (getWidth()-1,getHeight()-1) is lower right.
		 * Throw IAE if x or y are not in range**/
		@Override
		public Pixel getPixel(int x, int y) {
			if(x<0 || y<0 || x>=width || y>=height) {
				throw new IllegalArgumentException ("X and or Y are not in range");
			}else {
				return this.picture[x][y];
		}
		}

		/**Paint() method forms return new Picture obj with certain pixel positions
		 * painted with a new value**/
		
		// paint(int x, int y, Pixel p) paints the pixel at
		// position (x,y) with the pixel value p. The second 
		// form includes a factor parameter that specifies a
		// blending factor. A factor of 0.0 leaves the specified
		// pixel unchanged. A factor of 1.0 results in replacing
		// the value with the specified pixel value. Values between
		// 0.0 and 1.0 blend proportionally.
		
		@Override
		public void paint(int x, int y, Pixel p) {
			
			if(x<0 || y<0 || p == null|| x>= width || y>=height) {
				throw new IllegalArgumentException("x and or y are out of range");
			}else {
				this.picture[x][y] = p;
			}
		}
		@Override
		public void paint(int x, int y, Pixel p, double factor) {
			if(x < 0 || y < 0 || factor < 0 || factor > 1 || x>= width || y>=height || p == null) {
				throw new IllegalArgumentException("x and or y are out of range");
			}else {
				this.picture[x][y].blend(p, factor);
			}
		}
		
		// paints the rectangular region defined by the positions (ax, ay) and
		// (bx, by) with the specified pixel value.
		@Override
		public void paint(int ax, int ay, int bx, int by, Pixel p) {
			//catch exceptions for out of range x and y
			if(ax >= this.width || bx >= this.width || ay >= this.height || by >= this.height || p == null) {
				throw new IllegalArgumentException("x and or y are out of range");
			}
			if(ax < 0 || ay <0 || bx < 0 || by < 0) {
				throw new IllegalArgumentException(" one or more dimensions<0");
			}
			
		}
		// this form should blend with the specified factor as previously described.
		@Override
		public void paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
			//catch exceptions for out of range x and y
			if(ax > this.width || bx > this.width || ay > this.height || by > this.height || p == null
					|| factor < 0 || factor > 1) {
				throw new IllegalArgumentException("x and or y are out of range");
			}
			
		}

		// sets all pixels in the picture that are within radius distance of the
		// coordinate (cx, cy) to the Pixel value p. Only positive values of radius 
		// should be allowed. Any value of cx and cy should be allowed 
		// (even if negative or otherwise outside of the boundaries of the frame). 
		
		// Calculate the distance of a particular (x,y) position to (cx,cy) with
		// the expression: Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy))	

		// The second form with factor, blends as previously described.
		@Override
		public void paint(int cx, int cy, double radius, Pixel p) {
			if(p == null || radius < 0) {
				throw new IllegalArgumentException("p is null or radius is negative");
			}
			for(int x = 0 ; x < width ; x++) {
				for(int y = 0 ; y < height ; y++) {
					double dist = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
					if(dist <= radius) {
						this.picture[x][y] = p;
					}
				}
			}
			
		}
		// The second form with factor, blends as previously described.
		@Override
		public void paint(int cx, int cy, double radius, Pixel p, double factor) {
			if(p == null || radius < 0 || factor < 0 || factor > 1) {
				throw new IllegalArgumentException("p is null or radius is negative");
			}
			for(int x = 0 ; x < width ; x++) {
				for(int y = 0 ; y < height ; y++) {
					double dist = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
					if(dist <= radius) {
						this.picture[x][y] = this.picture[x][y].blend(p, factor);;
					}
				}
			}
		}
	
}
