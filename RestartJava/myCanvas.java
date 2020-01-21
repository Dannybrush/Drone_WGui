package RestartJava;


import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

public class myCanvas extends Canvas {
	int xCanvasSize = 512;				// constants for relevant sizes
	int yCanvasSize = 512;
    GraphicsContext gc; 
    
    
    @Override
    public double minHeight(double width)
    {
        return 64;
    }

    @Override
    public double maxHeight(double width)
    {
        return 1000;
    }

    @Override
    public double prefHeight(double width)
    {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height)
    {
        return 0;
    }

    @Override
    public double maxWidth(double height)
    {
        return 10000;
    }
    @Override
    public boolean isResizable()
    {
        return true;
    }
    @Override
    public void resize(double width, double height)
    {
    	
    	//System.out.println("killme");
    	this.xCanvasSize = (int) width;				// constants for relevant sizes
    	this.yCanvasSize = (int) height;
        super.setWidth(width);
        super.setHeight(height);
       
    }
    public myCanvas(GraphicsContext g, int xcs, int ycs) {
    	gc = g;
    	xCanvasSize = xcs;
    	yCanvasSize = ycs;
    }
    public int getXCanvasSize() {return xCanvasSize;}
    public int getYCanvasSize() {return yCanvasSize;}
    public void clearCanvas() {	
    	//gc.clearRect(0,  0,  xCanvasSize,  yCanvasSize);}		// clear canvas#
    	gc.setFill(Color.BLACK);
    //	System.out.println(xCanvasSize+" "+ yCanvasSize);
		gc.fillRect(0,  0,  xCanvasSize,  yCanvasSize);}		// clear canvas#
    public void drawIt (Image i, double x, double y, double sz) {
		// to draw centred at x,y, give top left position and x,y size
		// sizes/position in range 0..1, so scale to canvassize 
	gc.drawImage(i, xCanvasSize * (x - sz/2), yCanvasSize*(y - sz/2), xCanvasSize*sz, yCanvasSize*sz);
}
    
    Color colFromChar (char c){
		Color ans = Color.BLACK;
		switch (c) {
		case 'y' :	ans = Color.YELLOW;
					break;
		case 'w' :	ans = Color.WHITE;
					break;
		case 'r' :	ans = Color.RED;
					break;
		case 'g' :	ans = Color.GREEN;
					break;
		case 'b' :	ans = Color.BLUE;
					break;
		case 'o' :	ans = Color.ORANGE;
					break;
		case 'B' :  ans = Color.BLACK;
					break;
		case 'T' :  ans = Color.TRANSPARENT;
					break;
		case 'p' :  ans = Color.PINK;
					break;
		case 'i'  : ans = Color.INDIGO;
					break; 
		}
		return ans;
	}
    public void setFillColour (Color c) {
		gc.setFill(c);
	}
    public void showText (double x, double y, String s) {
		gc.setTextAlign(TextAlignment.CENTER);							// set horizontal alignment
		gc.setTextBaseline(VPos.CENTER);								// vertical
		gc.setFill(Color.WHITE);										// colour in white
		gc.fillText(s, x, y);											// print score as text
	}
    public void showInt (double x, double y, int i) {
		showText (x, y, Integer.toString(i));
	}
	public void showCircle(double x, double y, double rad, char col) {
		// TODO Auto-generated method stub
	 	setFillColour(colFromChar(col));									// set the fill colour
			gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.ROUND);	// fill circle
			
	}	
}
