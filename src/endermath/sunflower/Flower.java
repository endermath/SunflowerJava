package endermath.sunflower;

public class Flower {
	int height;
	static double maxWater = 3000;
	double water;
	
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
}
