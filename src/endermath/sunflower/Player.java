package endermath.sunflower;

public class Player extends BasicSprite {
	private double waterLeftInWaterCan;
	private int seedsCarrying;
	
	private int score;
	private byte scoreMultiplier;
	
	private boolean isFacingRight;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	
	public boolean isOutside;
	public boolean isWatering;
	public boolean isRefilling;
	
	public Player() {
		isOutside = false;
	}
	
	
	
	public void addScore(int points) {
		this.score += points * this.scoreMultiplier;
	}
	public void stopMovingLeft() {
		this.isMovingLeft = false;
		if (!this.isMovingRight) xSpeed = 0;
	}
	public void stopMovingRight() {
		this.isMovingRight = false;
		if (!this.isMovingLeft) xSpeed = 0;
	}
	public void moveLeft() {
		this.isMovingRight = false;
		this.isMovingLeft = true;
		this.isRefilling = false;
		xSpeed = -10 * 32 / 60;
	}
	
	public void moveRight() {
		this.isMovingLeft = false;
		this.isMovingRight = true;
		this.isRefilling = false;
		xSpeed = 10.0 * 32 / 60;
	}
	
	public void plantSeed() {
		
	}
	public void water() {
		
	}
	public void tick() {
		xPos += xSpeed;
		yPos += ySpeed;
	}
	
}
