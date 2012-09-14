import java.awt.*;

import javax.swing.*;

public abstract class coreDisplay {

	private static DisplayMode modes[] = {
		
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
	
	private     boolean          running;
	protected   screenManager    screen;
	
	//stop method
	public void stop(){
		running = false;
	}
	
	//call init and gameloop
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			screen.restoreScreen();
		}
	}
	
	//initializes full screen mode
	public void init(){
		
		DisplayMode     dm         = screen.findFirstCompatibleMode(modes);
		Window          window     = screen.getFullScreenWindow();
		                screen     = new screenManager();
		
		screen.setFullScreen(dm);
		window.setFont(new Font("Calibri", Font.PLAIN, 20));
		window.setBackground(Color.GREEN);
		window.setForeground(Color.WHITE);
		running = true;
	}
	
	//main gameloop
	public void gameLoop(){
		
		long     startTime     = System.currentTimeMillis();
		long     cummlTime     = startTime;
		
		while(running){
			long timePassed = System.currentTimeMillis() - cummlTime;
			cummlTime += timePassed;
			
			update(timePassed);
			Graphics2D g = screen.getGraphics();
			draw(g);
			g.dispose();
			screen.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){
				//nothing
			}
		}
	}
	
}



