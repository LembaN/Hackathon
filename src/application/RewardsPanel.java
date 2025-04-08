package application;
import javax.swing.*;
import java.awt.*;
public class RewardsPanel extends JPanel{
	public RewardsPanel(BrainifyController controller) {
       setLayout(new BorderLayout());
       setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
       JLabel title = new JLabel("Points & Rewards", SwingConstants.CENTER);
       title.setFont(new Font("Arial", Font.BOLD, 24));
       add(title, BorderLayout.NORTH);
       JPanel info = new JPanel(new GridLayout(3, 1, 10, 10));
       info.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
       JLabel current = new JLabel("Current Points: " + controller.points, SwingConstants.CENTER);
       current.setFont(new Font("Arial", Font.BOLD, 20));
       info.add(current);
       int needed = Math.max(0, 100 - controller.points);
       JLabel neededLabel = new JLabel("Points needed for next reward: " + needed, SwingConstants.CENTER);
       neededLabel.setFont(new Font("Arial", Font.PLAIN, 16));
       info.add(neededLabel);
       if (controller.points >= 1000) {
           JLabel congrats = new JLabel("CONGRATULATIONS! You've earned a reward!", SwingConstants.CENTER);
           congrats.setFont(new Font("Arial", Font.BOLD, 18));
           congrats.setForeground(new Color(0, 100, 0));
           info.add(congrats);
           JLabel claim = new JLabel("Talk to your parent/guardian to claim your treat!", SwingConstants.CENTER);
           claim.setFont(new Font("Arial", Font.PLAIN, 16));
           info.add(claim);
       }
       add(info, BorderLayout.CENTER);
       JButton backBtn = new JButton("Back to Menu");
       backBtn.addActionListener(e -> controller.showPanel("menu"));
       add(backBtn, BorderLayout.SOUTH);
   }
}
