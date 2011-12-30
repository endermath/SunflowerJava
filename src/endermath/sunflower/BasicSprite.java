package endermath.sunflower;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class BasicSprite {
	Image image;
	double xPos;
	double yPos;
	double xSpeed;
	double ySpeed;
	
	public BasicSprite() {
		xPos = 128;
		yPos = 256-2*16;
		xSpeed = 0;
		ySpeed = 0;
	}	
	
	public void tick() {
		xPos += xSpeed;
		yPos += ySpeed;
		if (xPos<32 || xPos>512-32) {
			xSpeed = -xSpeed;
		}
		if (yPos>512-32) {
			ySpeed = -ySpeed;
		}
	}
}
