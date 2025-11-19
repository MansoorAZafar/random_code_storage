package fpg;
import java.awt.*;
import java.awt.event.*;

/*
 * Creates a box which acts as either a player or enemy or object
 * 
 * @mansoor zafar
 * @2022-06-12
 */
public class Box extends Rectangle{
	//Instance Variables
	Color color;
	public boolean left, right, up, down;
	int speed = 20; // sets default speed to 20
	
	/*
	 * Input: integer x, y, width, height, Color color
	 * Inside: sets all the variables to the inputs
	 * Output: the variables are set the respective value
	 */
	
	public Box(){
		
	}
	
	Box(int x, int y, int width, int height, Color color){
		this.x=x; // sets x to x
		this.y=y; // sets the y to y
		this.width=width; // sets the width to width
		this.height=height; // sets the height to the height
		this.color=color; // sets the color to the color
	}
	
	/*
	 * Input: KeyEvent e
	 * Inside: detect which key was pressed 
	 * Output: the y or x value respective to the key pressed
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
			this.y -= speed; // decrease the y value
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			this.y+= speed; // increase the y value
			down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			this.x-= speed; // decrease the x value
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			this.x+= speed; // increase the x value
			right = true;
		}
	}
	
	/*
	 * Input: Graphics g
	 * Inside: sets the color of the graphics & fills the oval shape with the variables
	 * Output: the box 
	 */
	public void draw(Graphics g) {
		g.setColor(this.color); // sets the colour
		g.fillOval(this.x, this.y, this.width, this.height); // fills the oval with the respective variables 
	}
	
}



