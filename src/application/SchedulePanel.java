package application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class SchedulePanel extends JPanel{
	public SchedulePanel(BrainifyController controller) {
       setLayout(new BorderLayout());
       setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       JLabel title = new JLabel("Schedule Buddy (Checklist)", SwingConstants.CENTER);
       title.setFont(new Font("Arial", Font.BOLD, 24));
       add(title, BorderLayout.NORTH);
       JPanel checklist = new JPanel();
       checklist.setLayout(new BoxLayout(checklist, BoxLayout.Y_AXIS));
       JScrollPane scroll = new JScrollPane(checklist);
       add(scroll, BorderLayout.CENTER);
       for (String item : controller.schedule) {
           JCheckBox box = new JCheckBox(item);
           box.setFont(new Font("Arial", Font.PLAIN, 16));
           box.addItemListener(e -> {
               if (e.getStateChange() == ItemEvent.SELECTED) {
                   controller.points += 15;
                   controller.updatePointsLabel();
                   JOptionPane.showMessageDialog(controller.mainFrame, "Task completed! +15 points");
               }
           });
           checklist.add(box);
       }
       JButton addBtn = new JButton("Add New Item");
       addBtn.addActionListener(e -> {
           String task = JOptionPane.showInputDialog("Enter new task:");
           if (task != null && !task.trim().isEmpty()) {
               controller.schedule.add(task);
               JCheckBox newBox = new JCheckBox(task);
               newBox.setFont(new Font("Arial", Font.PLAIN, 16));
               newBox.addItemListener(ev -> {
                   if (ev.getStateChange() == ItemEvent.SELECTED) {
                       controller.points += 15;
                       controller.updatePointsLabel();
                       JOptionPane.showMessageDialog(controller.mainFrame, "Task completed! +15 points");
                   }
               });
               checklist.add(newBox);
               checklist.revalidate();
               checklist.repaint();
               controller.points += 50;
               controller.updatePointsLabel();
               JOptionPane.showMessageDialog(controller.mainFrame, "Schedule item added! +50 points");
           }
       });
       JButton backBtn = new JButton("Back to Menu");
       backBtn.addActionListener(e -> controller.showPanel("menu"));
       JPanel buttons = new JPanel();
       buttons.add(addBtn);
       buttons.add(backBtn);
       add(buttons, BorderLayout.SOUTH);
   }
}
