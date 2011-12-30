package endermath.sunflower;

public class Skull extends FallingSprite implements playerInteraction {
	public void touchedByPlayer(Player p) {
		p.waterLeftInWaterCan /= 2;
	}
	
}
