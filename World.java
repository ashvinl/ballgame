package y2020_p1_team_7_breakoutdemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class World extends Pane{

	private AnimationTimer timer;
	private HashSet<KeyCode> keysPressed;

	public World() {
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				act();
				try {
					for (Node a : getChildren()) {
						if(a instanceof Actor) ((Actor)a).act(now);
					}
				} catch (Exception e) {

				}

			}

		};
		keysPressed = new HashSet<>();
	}

	public abstract void act();

	public void add(Score s) { 
		getChildren().add(s);
	}

	public void add(Actor actor) {
		getChildren().add(actor);
	}

	public void remove(Actor actor) {
		if (actor instanceof Brick) {
			FadeTransition ft = new FadeTransition();
			ft.setDuration(Duration.seconds(1));
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setNode(actor);
			ft.play();
			AnimationTimer removeTimer = new AnimationTimer() {
				long prev = 0;
				@Override
				public void handle(long now) {
					if (prev == 0) {
						prev = now;
					}else if (now - prev > 1000000000) {
						getChildren().remove(actor);
						this.stop();
					}

				}

			};
			removeTimer.start();
		} else {
			getChildren().remove(actor);
		}
		
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		List<A> result = new ArrayList<A>();
		for (Node a : getChildren()) {
			if (cls.isInstance(a)) result.add((A)a);
		}
		return result;
	}

	public void addKey(KeyCode key) {
		keysPressed.add(key);
	}

	public void removeKey(KeyCode key) {
		keysPressed.remove(key);
	}

	public boolean isKeyDown(KeyCode key) {
		return keysPressed.contains(key);
	}

}
