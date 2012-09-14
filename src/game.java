import java.awt.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.*;

public class game extends JFrame {
	
	private    Sprite           sprite;
	private    Animation        animated;
	private    screenManager    screen;
	private    Image            bg;
	
	public static void main(String[] args){
		game g = new game();
		g.run();
		
	}
	
	//display modes array
	private static final DisplayMode modes1[] = {
		
		new DisplayMode(1920, 1080, 32, 0),
		new DisplayMode(1920, 1080, 24, 0),
		new DisplayMode(1920, 1080, 16, 0),
		
		new DisplayMode(800, 600, 32, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 16, 0),
		
		new DisplayMode(640, 480, 32, 0),
		new DisplayMode(640, 480, 24, 0),
		new DisplayMode(640, 480, 16, 0),
	};
	
	//load images and add scenes
	public void loadImages(){
		
		Image open      = new ImageIcon("C:\\Users\\User\\newboston\\sprites\\Images\\eyes_open.png").getImage();
		Image close     = new ImageIcon("C:\\Users\\User\\newboston\\sprites\\Images\\eyes_closed.png").getImage();
		      bg        = new ImageIcon("C:\\Users\\User\\newboston\\sprites\\Images\\bg.jpg").getImage();
		      animated  = new Animation();
		      sprite    = new Sprite(animated);
		
		animated.addScene(open, 250);
		animated.addScene(close, 250);
		
		sprite.setVelocityX(0.1f);
		sprite.setVelocityY(0.1f);
	}
	
	//main method called from main
	public void run(){
		
		screen = new screenManager();
		
		try{
			DisplayMode dm = screen.findFirstCompatibleMode(modes1);
			screen.setFullScreen(dm);
			loadImages();
			movieLoop();
		}finally{
			screen.restoreScreen();
		}
	}
	
	//play movie
	public void movieLoop(){
		
		long     startingTime     = System.currentTimeMillis();
		long     cummlTime        = startingTime;
		
		while(cummlTime - startingTime < 7000){
			long timePassed = System.currentTimeMillis() - cummlTime;
			cummlTime += timePassed;
			update(timePassed);
			
			//draw and update screen
			Graphics2D g = screen.getGraphics();
			draw(g);
			g.dispose();
			screen.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){
				
			}
		}
		
	}
	
	//draw graphics
	public void draw(Graphics g){
		g.drawImage(bg, 0, 0, null);
		g.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
	}
	
	//update sprite
	public void update(long timePassed){
		
		if(sprite.getX() < 0){
			sprite.setVelocityX(Math.abs(sprite.getVelocityX()));
		}
		else if(sprite.getX() + sprite.getWidth() >= screen.getWidth()){
			sprite.setVelocityX(-Math.abs(sprite.getVelocityX()));
		}
		
		if(sprite.getY() < 0){
			sprite.setVelocityY(Math.abs(sprite.getVelocityY()));
		}
		else if(sprite.getY() + sprite.getHeight() >= screen.getHeight()){
			sprite.setVelocityY(-Math.abs(sprite.getVelocityY()));
		}
		
		sprite.update(timePassed);
	}
	
}


	
