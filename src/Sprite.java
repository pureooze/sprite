import java.awt.Image;

public class Sprite {
	
	private     Animation     animated;	
	private     float         x;
	private     float         y;
	private     float         velX;
	private     float         velY;
	
	//CONSTRUCTOR
	public Sprite(Animation animated){
		
		this.animated = animated;
	}

	//change position of sprite
	public void update(long timePassed){
		
		x += velX * timePassed;
		y += velY * timePassed;
		animated.update(timePassed);
	}
	
	//get x position
	public float getX(){
		
		return x;
	}
	
	//get y position
	public float getY(){	
		
		return y;
	}
	
	//set sprite x position
	public void setX(float x){
		
		this.x = x;
	}
	
	//set sprite y position
	public void setY(float y){
			
		this.y = y;
	}
	
	//get sprite width
	public int getWidth(){
		
		return animated.getImage().getWidth(null);
	}
	
	//get sprite height
	public int getHeight(){
			
		return animated.getImage().getHeight(null);
	}
	
	//get horizontal velocity
	public float getVelocityX(){
		
		return velX;
	}
	
	//get vertical velocity
	public float getVelocityY(){
			
		return velY;
	}
	
	//set horizontal velocity
	public void setVelocityX(float velX){
		
		this.velX = velX;
	}
	
	//set vertical velocity
	public void setVelocityY(float velY){
			
		this.velY = velY;
	}
	
	//get sprite
	public Image getImage(){
		
		return animated.getImage();
	}
	
	
}



