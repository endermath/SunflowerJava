package endermath.sunflower;

public class ClockSprite extends FallingSprite implements playerInteraction {
	public void touchedByPlayer(Player p) {
		p.timeLeft += 10;
	}
}
