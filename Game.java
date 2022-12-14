package y2020_p1_team_7_breakoutdemo;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
	
	Scene level1;
	Scene level2;
	Scene level3;
	Scene gameOver;
	Stage stage;
	BallWorld bw1;
	BallWorld bw2;
	BallWorld bw3;
	AnimationTimer level1Timer;
	AnimationTimer level2Timer;
	AnimationTimer level3Timer;
	
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("BallWorld");
		stage.setResizable(false);
		stage.sizeToScene();
		
		BorderPane titleBP = new BorderPane();
		
		Scene title = new Scene(titleBP, 600, 600);
		
		BallWorld titleBW = new BallWorld();
		titleBW.setPrefSize(600, 600);
		
		titleBP.setCenter(titleBW);
		
		String path = getClass().getClassLoader().getResource("resources/titleImage.png").toString();
		Image img = new Image(path);
		ImageView imgView = new ImageView(img);
		imgView.setX(0);
		imgView.setY(0);
		titleBW.getChildren().add(imgView);
		
		Button playButton = new Button("Play");
		MouseEventHandlerPlay val = new MouseEventHandlerPlay();
		playButton.setPrefSize(75, 50);
		playButton.setOnMouseClicked(val);
		playButton.setLayoutX(300 - playButton.getPrefWidth()/2);
		playButton.setLayoutY(450);
		titleBW.getChildren().add(playButton);
		
		BorderPane goBP = new BorderPane();
		
		gameOver = new Scene(goBP, 600, 600);
		
		BallWorld goBW = new BallWorld();
		goBW.setPrefSize(600, 600);
		
		goBP.setCenter(goBW);
		
		path = getClass().getClassLoader().getResource("resources/gameOverScreen.png").toString();
		img = new Image(path);
		ImageView iv = new ImageView(img);
		iv.setX(0);
		iv.setY(0);
		goBW.getChildren().add(iv);
		
		Button replayButton = new Button("Return to Title");
		MouseEventHandlerReplay value = new MouseEventHandlerReplay();
		replayButton.setPrefSize(125, 50);
		replayButton.setOnMouseClicked(value);
		replayButton.setLayoutX(300 - replayButton.getPrefWidth()/2);
		replayButton.setLayoutY(450);
		goBW.getChildren().add(replayButton);
		
		
		BorderPane bp1 = new BorderPane();
		
		
		bw1 = new BallWorld();
		bw1.setPrefSize(600, 600);
		
		
		bp1.setCenter(bw1);
		
		level1 = new Scene(bp1, 600, 600);
		stage.setScene(title);
		
		Ball b1 = new Ball();
		b1.setX(300);
		b1.setY(300);
		b1.setFitWidth(30);
		b1.setFitHeight(30);
		bw1.add(b1);
		
		Paddle p1 = new Paddle();
		p1.setX(150);
		p1.setY(550);
		p1.setFitWidth(100);
		p1.setFitHeight(25);
		bw1.add(p1);
		
		Brick brick1 = new Brick(); 
		brick1.setX(240);
		brick1.setY(300);
		bw1.add(brick1);
		
		bw1.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getX() > p1.getX()) {
					p1.setMovedRight(true);
					p1.setMovedLeft(false);
				} else {
					p1.setMovedLeft(true);
					p1.setMovedRight(false);
				}
				p1.setX(event.getX());
			}});
		
		
		//IMPORTANT:
		bw1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				bw1.addKey(event.getCode());
			}});
		
		bw1.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				bw1.removeKey(event.getCode());
			}
		});
		//ENDIMP
		
		BorderPane bp2 = new BorderPane();
		
		
		bw2 = new BallWorld();
		bw2.setPrefSize(600, 600);
		
		
		bp2.setCenter(bw2);
		
		level2 = new Scene(bp2, 600, 600);
		
		Ball b2 = new Ball();
		b2.setX(300);
		b2.setY(300);
		b2.setFitWidth(30);
		b2.setFitHeight(30);
		bw2.add(b2);
		
		Paddle p2 = new Paddle();
		p2.setX(150);
		p2.setY(550);
		p2.setFitWidth(100);
		p2.setFitHeight(25);
		bw2.add(p2);
		
		Brick brick2 = new Brick(); 
		brick2.setX(200);
		brick2.setY(200);
		bw2.add(brick2);
		
		Brick brick3 = new Brick(); 
		brick3.setX(368);
		brick3.setY(200);
		bw2.add(brick3);
		
		bw2.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getX() > p2.getX()) {
					p2.setMovedRight(true);
					p2.setMovedLeft(false);
				} else {
					p2.setMovedLeft(true);
					p2.setMovedRight(false);
				}
				p2.setX(event.getX());
			}});
		
		bw2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				bw2.addKey(event.getCode());
			}});
		
		bw2.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				bw2.removeKey(event.getCode());
			}
		});
		
		BorderPane bp3 = new BorderPane();
		
		
		bw3 = new BallWorld();
		bw3.setPrefSize(600, 600);
		
		
		bp3.setCenter(bw3);
		
		level3 = new Scene(bp3, 600, 600);
		
		Ball b3 = new Ball();
		b3.setX(300);
		b3.setY(300);
		b3.setFitWidth(30);
		b3.setFitHeight(30);
		bw3.add(b3);
		
		Paddle p3 = new Paddle();
		p3.setX(150);
		p3.setY(550);
		p3.setFitWidth(100);
		p3.setFitHeight(25);
		bw3.add(p3);
		
		Brick brick4 = new Brick(); 
		brick4.setX(200);
		brick4.setY(200);
		bw3.add(brick4);
		
		Brick brick5 = new Brick(); 
		brick5.setX(284);
		brick5.setY(200);
		bw3.add(brick5);
		
		Brick brick6 = new Brick(); 
		brick6.setX(368);
		brick6.setY(200);
		bw3.add(brick6);
		
		bw3.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getX() > p3.getX()) {
					p3.setMovedRight(true);
					p3.setMovedLeft(false);
				} else {
					p3.setMovedLeft(true);
					p3.setMovedRight(false);
				}
				p3.setX(event.getX());
			}});
		
		bw3.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				bw3.addKey(event.getCode());
			}});
		
		bw3.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				bw3.removeKey(event.getCode());
			}
		});
		
		level1Timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (bw1.getObjects(Brick.class).isEmpty()) {
					stage.setScene(level2);
					bw2.setScore(bw1.getScore().getScore());
					bw1.stop();
					level1Timer.stop();
					bw2.start();
					level2Timer.start();
				}

			}

		};
		
		level2Timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (bw2.getObjects(Brick.class).isEmpty()) {
					stage.setScene(level3);
					bw3.setScore(bw2.getScore().getScore());
					bw2.stop();
					level2Timer.stop();
					bw3.start();
					level3Timer.start();
				}

			}

		};
		
		level3Timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (bw3.getObjects(Brick.class).isEmpty()) {
					stage.setScene(gameOver);
					bw3.stop();
					level3Timer.stop();
				}

			}

		};
		
		stage.show();
		
		bw1.requestFocus();
	}
	
	private class MouseEventHandlerPlay implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			stage.setScene(level1);
			bw1.start();
			level1Timer.start();
		}

	}
	
	private class MouseEventHandlerReplay implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			try {
				start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

