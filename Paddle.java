package y2020_p1_team_7_breakoutdemo;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;

public class Paddle extends Actor {
	
	private boolean movedLeft;
	private boolean movedRight;
	private AnimationTimer resetMoved; // Resets moved variables periodically
	private long prevTime = 0;
	
	public Paddle() {
		String path = getClass().getClassLoader().getResource("resources/paddle.png").toString();
		Image img = new Image(path);
		this.setImage(img);
		resetMoved = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if ((now - prevTime) > 75) {
					movedLeft = false;
					movedRight = false;
				}
				prevTime = now;
			}
			
		};
	}

	@Override
	public void act(long now) {
		if (getWorld().isKeyDown(KeyCode.LEFT)) setX(getX() - 5);
		if (getWorld().isKeyDown(KeyCode.RIGHT)) setX(getX() + 5);
	}
	
	public boolean isMovedLeft() {
		return movedLeft;
	}

	public void setMovedLeft(boolean movedLeft) {
		this.movedLeft = movedLeft;
	}

	public boolean isMovedRight() {
		return movedRight;
	}

	public void setMovedRight(boolean movedRight) {
		this.movedRight = movedRight;
	}
	
}
