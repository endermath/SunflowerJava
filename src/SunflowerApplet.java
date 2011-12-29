import java.awt.*;
import java.applet.*;

public class SunflowerApplet extends Applet {
	public void paint(Graphics g) {
		g.drawString("Sandy's Sunflowers",20,30);
		g.drawString("Arrow keys to move",30,50);
		g.drawString("Space to water", 20, 70); 
		g.drawString("Space to water and Return to plant", 20, 90);
		
	}
}