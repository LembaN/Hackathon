package application;
import javax.swing.*;
import java.awt.*;
public class YogaPanel extends JPanel{
	public YogaPanel(BrainifyController controller) {
       setLayout(new BorderLayout());
       setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       JLabel title = new JLabel("Yoga Exercises", SwingConstants.CENTER);
       title.setFont(new Font("Arial", Font.BOLD, 24));
       add(title, BorderLayout.NORTH);
       JTextArea description = new JTextArea("Yoga helps with impulse control and emotional regulation.\n\nChoose a yoga exercise to practice for 5 minutes:");
       description.setFont(new Font("Arial", Font.PLAIN, 16));
       description.setWrapStyleWord(true);
       description.setLineWrap(true);
       description.setEditable(false);
       description.setBackground(getBackground());
       add(description, BorderLayout.CENTER);
       JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
       String[] exercises = {"Mountain Pose", "Tree Pose", "Child's Pose", "Return to Menu"};
       for (String ex : exercises) {
           JButton button = new JButton(ex);
           button.setFont(new Font("Arial", Font.PLAIN, 16));
           button.addActionListener(e -> {
               if (!ex.equals("Return to Menu")) {
                   controller.points += 30;
                   controller.updatePointsLabel();
                   JOptionPane.showMessageDialog(controller.mainFrame, "Great job completing " + ex + "! +30 points");
               }
               controller.showPanel("menu");
           });
           panel.add(button);
       }
       add(panel, BorderLayout.SOUTH);
   }
}
