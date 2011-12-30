package endermath.sunflower;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.applet.*;
import java.util.Random;
import java.util.Vector;

public class SunflowerApplet extends Applet implements Runnable, KeyListener {
	Thread gameLoop;
	BufferedImage backbuffer;
	Graphics2D g2d;
	AffineTransform scale2X = AffineTransform.getScaleInstance(2,2);
	Random rand = new Random();

	Player player = new Player();
	
	Image iconSheet;
	
	Image playerImage;
	Image wateringCanImage;
	Image wateringSplashImage;
	Image flowerStalkImage;
	Image flowerImage;
	Image seedImage;
	Image skullImage;
	Image clockImage;
	Image tapImage;
	Image tapSplashImage;
	Image brickImage;
	Image dirtImage;
	
	AudioClip pickupAudio;
	
	
	Flower[] flowerList = new Flower[14];
	Vector<FallingSprite> fallingItems = new Vector<FallingSprite>();
	
	public Image getImageFromIconSheet(int x, int y) {
		return createImage(new FilteredImageSource(iconSheet.getSource(), new CropImageFilter(x*16,y*16,16,16)));
	}
	/* applet init event. Should initialize the applet. */
	public void init() {		 
		backbuffer = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
		g2d = backbuffer.createGraphics();
		
		iconSheet = getImage(this.getClass().getResource("data/icons2.png")); 
		
		wateringCanImage = getImageFromIconSheet(0,0);
		wateringSplashImage = getImageFromIconSheet(1,0);
		flowerImage = getImageFromIconSheet(2,0);
		seedImage = getImageFromIconSheet(3,0);
		skullImage = getImageFromIconSheet(4,0);
		tapImage = getImageFromIconSheet(5,0);
		playerImage = getImageFromIconSheet(0,1);
		flowerStalkImage = getImageFromIconSheet(2,1);
		clockImage = getImageFromIconSheet(4,1);
		tapSplashImage = getImageFromIconSheet(5,1);
		dirtImage = getImageFromIconSheet(0,2);
		brickImage = getImageFromIconSheet(0,3);
		
		player.image = playerImage;
		
				
		pickupAudio = getAudioClip(this.getClass().getResource("data/Pickup.wav"));
		
		for(int i=0; i<14; i++) {
			flowerList[i] = new Flower();
			flowerList[i].height = rand.nextInt(14);
		}
		
		addKeyListener(this);
	}

	/* applet update event to redraw the screen */
	public void update(Graphics g) {
		g2d.setTransform(scale2X);
		
		g2d.setPaint(new Color(68,124,209));
		g2d.fillRect(0,0,getSize().width,getSize().height);
				
		drawScene();
		drawObjects();
		drawPlayer();
		
		/* Repaint the applet window */
		paint(g);
		
	}
	
	public void drawScene() {
		for(int y=0; y<16; y++) {
			if (y<=12 || y==15 || !player.isOutside) {
				g2d.drawImage(brickImage, 0, 16*y, this);	
			}
			if (y<=12 || y==15 || player.isOutside) {
				g2d.drawImage(brickImage, 240, 16*y, this);
			}
			if (player.isOutside) {
				g2d.drawImage(brickImage, 16*y, 240, this);
			} else {
				g2d.drawImage(dirtImage, 16*y, 240, this);
			}
		}
	}
	public void drawObjects() {
		for(int i=0; i<14; i++) {
			int h=flowerList[i].height;
			if (h==0) {
				continue;
			}
			g2d.drawImage(flowerImage, 16+i*16, (14-h)*16, this);
			for(int y=14; y>14-h; y--) {
				g2d.drawImage(flowerStalkImage, 16+i*16, y*16, this);
			}
		}
	}
	public void drawPlayer() {
		//AffineTransform myTransform = new AffineTransform();
		//myTransform.scale(2,2);
		//g2d.setTransform(myTransform);
		//g2d.translate(player.xPos, player.yPos);
		//g2d.drawImage(player.image, identity, this);
		g2d.drawImage(player.image, (int)player.xPos, (int)player.yPos, this);
	}
	
	/* applet paint event. Called by update and whenever applet needs to e be updated. */
	public void paint(Graphics g) {
/*		g.drawString("Sandy's Sunflowers",220,30);		
		g.drawString("Arrow keys to move",220,50);
		g.drawString("Space to water", 220, 70); 
		g.drawString("Return to plant", 220, 90);
*/
		g.drawImage(backbuffer, 0, 0, this);

	}

	
	/* Implementing required KeyListener methods */
	public void keyReleased(KeyEvent k) { }
	public void keyTyped(KeyEvent k) { }
	public void keyPressed(KeyEvent k) {
		int keycode = k.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_LEFT:
			player.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			player.moveRight();
		case KeyEvent.VK_SPACE:
			player.water();
			break;
		case KeyEvent.VK_ENTER:
			player.plantSeed();
			pickupAudio.play();
			break;
		
		}
	}
	
	
	/* Implementing required Runnable methods */
	public void start() {
		gameLoop = new Thread(this);
		gameLoop.start();
	}
	public void run() {
		Thread t = Thread.currentThread();
		
		while (t==gameLoop) {
			try {
				updateObjects();
				Thread.sleep(20);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	public void stop() {
		gameLoop = null;
	}
	
	public void updateObjects() {
		player.tick();
	}
}