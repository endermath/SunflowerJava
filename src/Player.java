
public class Player {
	private double waterLeftInWaterCan;
	private int seedsCarrying;
	
	private int score;
	private byte scoreMultiplier;
	
	private boolean isFacingRight;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private double xspeed;
	private double yspeed;
	
	public boolean isOutside;
	public boolean isWatering;
	public boolean isRefilling;
	
	public void addScore(int points) {
		this.score += points * this.scoreMultiplier;
	}
	public void stopMovingLeft() {
		this.isMovingLeft = false;
		if (!this.isMovingRight) this.xspeed = 0;
	}
	public void stopMovingRight() {
		this.isMovingRight = false;
		if (!this.isMovingLeft) this.xspeed = 0;
	}
	public void moveLeft() {
		this.isMovingRight = false;
		this.isMovingLeft = true;
		this.isRefilling = false;
		this.xspeed = -10 * 32 / 60;
	}
	
	public void moveRight() {
		this.isMovingLeft = false;
		this.isMovingRight = true;
		this.isRefilling = false;
		this.xspeed = 10.0 * 32 / 60;
	}
	
	public void tick() {
		
	}
	
}
