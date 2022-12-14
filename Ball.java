package y2020_p1_team_7_breakoutdemo;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Ball extends Actor {
	
	private double dx;
	private double dy;
	private boolean flag;
	
	public Ball () {
		String path = getClass().getClassLoader().getResource("resources/ballImage.png").toString();
		Image img = new Image(path);
		this.setImage(img);
		dx = 2;
		dy = 5;
		
	}

	@Override
	public void act(long now) {
		this.move(dx, dy);
		if ((this.getX() <= 0 && dx < 0) || (dx > 0 && (getX() + getWidth()) >= getWorld().getWidth())) dx *= -1;
		if ((this.getY() <= 0 && dy < 0) || (dy > 0 && (getY() + getHeight()) >= getWorld().getHeight())) dy *= -1;
		if(this.getY() + this.getHeight() >= getWorld().getHeight()) ((BallWorld)getWorld()).getScore().setScore(((BallWorld)getWorld()).getScore().getScore() - 1000);
		if (!flag && this.getIntersectingObjects(Paddle.class).size() > 0) {
			Paddle paddle = getOneIntersectingObject(Paddle.class);
			if (!(paddle.isMovedLeft() || getWorld().isKeyDown(KeyCode.LEFT)) && !(paddle.isMovedRight() || getWorld().isKeyDown(KeyCode.RIGHT)))dy *= -1;
			else if ((paddle.isMovedLeft() || getWorld().isKeyDown(KeyCode.LEFT)) && this.getX() >= (paddle.getX() + paddle.getFitWidth() / 3) && (this.getX() + this.getFitWidth()) <= (paddle.getX() + 2 * paddle.getFitWidth() / 3)) dy *= -1;
			else if ((paddle.isMovedRight() || getWorld().isKeyDown(KeyCode.RIGHT)) && this.getX() >= (paddle.getX()) && (this.getX() + this.getFitWidth()) <= (paddle.getX() + paddle.getFitWidth() / 3)) {
				dy *= -1;
				if (dx > 0) dx *= -1;
			} else if (this.getX() >= (paddle.getX() + 2*paddle.getFitWidth() / 3) && (this.getX() + this.getFitWidth()) <= (paddle.getX() + paddle.getFitWidth())) {
				dy *= -1;
				if (dx < 0) dx *= -1;
			} else if (this.getX() < paddle.getX()) {
				dy = 2;
				dx = -5;
			} else if (this.getX() > (paddle.getX() + 2 * paddle.getFitWidth() / 3)) {
				dy = 2;
				dx = 5;
			} else {
				dy *= -1;
			}
			flag = true;
		} else if (!(getIntersectingObjects(Paddle.class).size() > 0)){
			flag = false;
		}
		
		if(this.getIntersectingObjects(Brick.class).size() > 0) {
			((BallWorld)getWorld()).getScore().setScore(((BallWorld)getWorld()).getScore().getScore() + 100);
			Brick b = getOneIntersectingObject(Brick.class);
			if(this.getX() < (b.getX() + b.getFitWidth()) && this.getX() > b.getX()){
				dy *= -1;
			}
			else if(this.getY() > b.getY() && this.getY() < b.getY() + b.getFitHeight()) {
				dx *= -1;
			}
			else {
				dx *= -1;
				dy *= -1;
			}
			this.getWorld().remove(b);
		}
		
	}
	
}

