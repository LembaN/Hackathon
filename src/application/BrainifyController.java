package application;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class BrainifyController {
	int points = 0;
   ArrayList<String> schedule = new ArrayList<>();
   ArrayList<String> reflections = new ArrayList<>();
   JFrame mainFrame;
   JLabel pointsLabel;
   CardLayout cardLayout;
   JPanel cardPanel;
   public void initializeSampleData() {
       schedule.add("Eg: Monday: Math Homework - 4:00 PM");
       reflections.add(LocalDate.now() + ": Had trouble focusing during math homework");
       reflections.add(LocalDate.now() + ": Did better with time management today");
   }
   public void createAndShowGUI() {
       mainFrame = new JFrame("Brainify - Executive Functioning Helper");
       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       mainFrame.setSize(800, 600);
       JPanel header = new HeaderPanel("BRAINIFY", this);
       mainFrame.add(header, BorderLayout.NORTH);
       cardLayout = new CardLayout();
       cardPanel = new JPanel(cardLayout);
       cardPanel.add(new MenuPanel(this), "menu");
       cardPanel.add(new SchedulePanel(this), "schedule");
       cardPanel.add(new YogaPanel(this), "yoga");
       cardPanel.add(new ActivitiesPanel(this), "activities");
       cardPanel.add(new ReflectionsPanel(this), "reflections");
       cardPanel.add(new RewardsPanel(this), "rewards");
       mainFrame.add(cardPanel, BorderLayout.CENTER);
       mainFrame.setLocationRelativeTo(null);
       mainFrame.setVisible(true);
   }
   public void updatePointsLabel() {
       pointsLabel.setText("Points: " + points);
       cardPanel.remove(5);
       cardPanel.add(new RewardsPanel(this), "rewards");
   }
   public void showPanel(String name) {
       cardLayout.show(cardPanel, name);
   }
}
