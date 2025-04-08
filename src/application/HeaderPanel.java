package application;
import javax.swing.*;
import java.awt.*;
public class HeaderPanel extends JPanel{
	public HeaderPanel(String title, BrainifyController controller) {
       setLayout(new BorderLayout());
       setBackground(new Color(70, 130, 180));
       setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
       titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
       titleLabel.setForeground(Color.WHITE);
       add(titleLabel, BorderLayout.CENTER);
       controller.pointsLabel = new JLabel("Points: " + controller.points, SwingConstants.RIGHT);
       controller.pointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
       controller.pointsLabel.setForeground(Color.WHITE);
       add(controller.pointsLabel, BorderLayout.EAST);
   }
}
