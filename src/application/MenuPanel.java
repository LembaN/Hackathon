package application;
import javax.swing.*;
import java.awt.*;
public class MenuPanel extends JPanel{
	public MenuPanel(BrainifyController controller) {
       setLayout(new GridLayout(5, 1, 10, 10));
       setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
       String[] labels = {"Schedule Buddy", "Yoga Exercises", "Cognitive Activities", "Reflections", "Points & Rewards"};
       String[] actions = {"schedule", "yoga", "activities", "reflections", "rewards"};
       for (int i = 0; i < labels.length; i++) {
           JButton button = new JButton(labels[i]);
           button.setFont(new Font("Arial", Font.BOLD, 18));
           button.setBackground(new Color(100, 149, 237));
           button.setForeground(Color.WHITE);
           int finalI = i;
           button.addActionListener(e -> controller.showPanel(actions[finalI]));
           add(button);
       }
   }
}
