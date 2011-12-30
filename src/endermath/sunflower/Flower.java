package endermath.sunflower;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Flower extends Image {
	private int height;
	private static double maxWater = 3000;
	private double water;
	
	public int xPosition;
	public boolean isFinished;
	public boolean didGrowToMaximumHeight;
	
	private int growCounter;
	
	public void grow() {
		if (!this.isFinished) {
			this.water -= this.height / 3;
			if (this.water<0) {
				this.isFinished = true;
				this.didGrowToMaximumHeight = false;
			} else {
				this.growCounter += (int)this.water/2500;
				if (this.growCounter>200) {
					this.growCounter = 0;
					this.height += 1;
					if (this.height > 11) {
						this.isFinished = true;
						this.didGrowToMaximumHeight = true;
					}
				}
			}
			
		}
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}
	
			
}
