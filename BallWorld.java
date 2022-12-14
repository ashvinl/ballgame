package y2020_p1_team_7_breakoutdemo;

public class BallWorld extends World{

	private Score score; 
	
	public BallWorld() { 
		score = new Score(); 
		score.setX(20);
		score.setY(60);
		getChildren().add(score); 
	}
	
	@Override
	public void act() {
		
	}
	
	public Score getScore()  {
		return score; 
	}
	
	public void setScore(int score) {
		this.score.setScore(score);
	}

}

