package RestartJava;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

public class myCanvas {
	int xCanvasSize = 512;				// constants for relevant sizes
	int yCanvasSize = 512;
    GraphicsContext gc; 

    public myCanvas(GraphicsContext g, int xcs, int ycs) {
    	gc = g;
    	xCanvasSize = xcs;
    	yCanvasSize = ycs;
    }
    public int getXCanvasSize() {return xCanvasSize;}
    public int getYCanvasSize() {return yCanvasSize;}
    public void clearCanvas() {	gc.clearRect(0,  0,  xCanvasSize,  yCanvasSize);}		// clear canvas#
    
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
