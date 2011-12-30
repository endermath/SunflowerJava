package endermath.sunflower;

public class FallingSprite extends BasicSprite {
	public void tick() {
		super.ySpeed += 1;
		super.tick();
		
	}
}
