package application;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
public class ReflectionsPanel extends JPanel{
	public ReflectionsPanel(BrainifyController controller) {
       setLayout(new BorderLayout());
       setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       JLabel title = new JLabel("Reflections Journal", SwingConstants.CENTER);
       title.setFont(new Font("Arial", Font.BOLD, 24));
       add(title, BorderLayout.NORTH);
       DefaultListModel<String> model = new DefaultListModel<>();
       for (String reflection : controller.reflections) {
           model.addElement(reflection);
       }
       JList<String> list = new JList<>(model);
       list.setFont(new Font("Arial", Font.PLAIN, 16));
       JScrollPane scroll = new JScrollPane(list);
       add(scroll, BorderLayout.CENTER);
       JButton addBtn = new JButton("Add New Reflection");
       addBtn.addActionListener(e -> {
           String input = JOptionPane.showInputDialog("Enter your reflection:");
           if (input != null && !input.trim().isEmpty()) {
               String reflection = LocalDate.now() + ": " + input;
               controller.reflections.add(reflection);
               model.addElement(reflection);
               controller.points += 20;
               controller.updatePointsLabel();
               JOptionPane.showMessageDialog(controller.mainFrame, "Reflection added! +20 points");
           }
       });
       JButton backBtn = new JButton("Back to Menu");
       backBtn.addActionListener(e -> controller.showPanel("menu"));
       JPanel panel = new JPanel();
       panel.add(addBtn);
       panel.add(backBtn);
       add(panel, BorderLayout.SOUTH);
   }
}
