package application;

import javax.swing.*;
import java.awt.*;

public class PointManager {
	private int points = 0;
    private JLabel pointsLabel;
    
    public PointManager() {
        pointsLabel = new JLabel("Points: " + points, SwingConstants.RIGHT);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsLabel.setForeground(Color.WHITE);
    }
    
    public void addPoints(int amount) {
        points += amount;
        updatePointsLabel();
    }
    
    public int getPoints() {
        return points;
    }
    
    public JLabel getPointsLabel() {
        return pointsLabel;
    }
    
    private void updatePointsLabel() {
        pointsLabel.setText("Points: " + points);
    }

}
