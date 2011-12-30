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
	AffineTransform identity = new AffineTransform();
	Random rand = new Random();

	Player player = new Player();
	
	AudioClip pickupAudio;
	
	Flower[] flowerList = new Flower[14];
	Vector<FallingItem> fallingItems = new Vector<FallingItem>();
	
	/* applet init event. Should initialize the applet. */
	public void init() {
		backbuffer = new BufferedImage(512,512, BufferedImage.TYPE_INT_ARGB);
		g2d = backbuffer.createGraphics();
		
		Image iconSheet = getImage(this.getClass().getResource("icons2.png")); 
		player.image = iconSheet; //iconSheet.getSubimage(32, 32, 32, 32);
		//pickupAudio = getAudioClip(this.getClass().getResource("../../../data/Pickup.wav"));
		
		for(int i=0; i<14; i++) {
			flowerList[i] = new Flower();
		}
		
		addKeyListener(this);
	}

	/* applet update event to redraw the screen */
	public void update(Graphics g) {
		g2d.setTransform(identity);
		
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0,0,getSize().width,getSize().height);
				
		drawScene();
		drawObjects();
		drawPlayer();
		
		/* Repaint the applet window */
		paint(g);
		
	}
	
	public void drawScene() {
		g2d.setPaint(Color.BLUE);
		g2d.fillRect(50, 50, 100, 100);

	}
	public void drawObjects() {
		for(int i=0; i<14; i++) {
			//g2d.drawImage(flowerList[i].getImage());
		}
	}
	public void drawPlayer() {
		//AffineTransform myTransform = new AffineTransform();
		//myTransform.scale(2,2);
		//g2d.setTransform(myTransform);
		//g2d.translate(player.xPos, player.yPos);
		//g2d.drawImage(player.image, identity, this);
	}
	
	/* applet paint event. Called by update and whenever applet needs to e be updated. */
	public void paint(Graphics g) {
/*		g.drawString("Sandy's Sunflowers",220,30);		
		g.drawString("Arrow keys to move",220,50);
		g.drawString("Space to water", 220, 70); 
		g.drawString("Return to plant", 220, 90);
*/
		g.drawImage(backbuffer, 0, 0, this);
		g.drawImage(player.image, (int)player.xPos, (int)player.yPos, this);
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