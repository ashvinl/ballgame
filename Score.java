package y2020_p1_team_7_breakoutdemo;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text{

    private int score; 

    public Score() { 
        this.score = 0; 
        this.setFont(new Font(60.0));
        this.fillProperty(); 
        this.updateDisplay();
        DropShadow dropShadow = new DropShadow(5, 5, 5, Color.GRAY);
        this.setEffect(dropShadow);
    }

    public void updateDisplay() { 

        String s = "" + score; 
        this.setText(s);

    }

    public int getScore() { 
        return score; 
    }

    public void setScore(int score) {
        this.score = score; 
        updateDisplay(); 
    }





}