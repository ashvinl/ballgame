package y2020_p1_team_7_breakoutdemo;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView{
	
	public abstract void act(long now);
	
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	public World getWorld() {
		return (World)this.getParent();
	}
	
	public double getWidth() {
		return this.getBoundsInLocal().getWidth();
	}
	
	public double getHeight() {
		return this.getBoundsInLocal().getHeight();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		List<A> result = new ArrayList<A>();
		for (Actor a : getWorld().getObjects(cls)) {
			if (a != this && a.intersects(this.getBoundsInLocal())) result.add((A)a);
		}
		return result;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		for (Actor a : getWorld().getObjects(cls)) {
			if (a != this && a.intersects(this.getBoundsInLocal())) return (A)a;
		}
		return null;
	}
}
