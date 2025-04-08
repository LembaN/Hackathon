package application1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BrainifyAppOneClass {
    private static int points = 0;
    private static ArrayList<String> schedule = new ArrayList<>();
    private static ArrayList<String> reflections = new ArrayList<>();
    private static JFrame mainFrame;
    private static JLabel pointsLabel;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;

    public static void main(String[] args) {
        initializeSampleData();
        createAndShowGUI();
    }

    private static void initializeSampleData() {
        // Initialize with some sample schedule items
        schedule.add("Monday: Math Homework - 4:00 PM");
        schedule.add("Tuesday: Science Project - 5:30 PM");
        schedule.add("Wednesday: Reading - 3:45 PM");
       
        // Initialize with some sample reflections
        reflections.add(LocalDate.now() + ": Had trouble focusing during math homework");
        reflections.add(LocalDate.now() + ": Did better with time management today");
    }

    private static void createAndShowGUI() {
        // Create and set up the main window
        mainFrame = new JFrame("Brainify - Executive Functioning Helper");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        
        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("BRAINIFY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        pointsLabel = new JLabel("Points: " + points, SwingConstants.RIGHT);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsLabel.setForeground(Color.WHITE);
        headerPanel.add(pointsLabel, BorderLayout.EAST);
        
        // Create card layout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Create all screens
        createMenuPanel();
        createSchedulePanel();
        createYogaPanel();
        createActivitiesPanel();
        createReflectionsPanel();
        createRewardsPanel();
        
        // Add panels to frame
        mainFrame.add(headerPanel, BorderLayout.NORTH);
        mainFrame.add(cardPanel, BorderLayout.CENTER);
        
        // Display the window
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private static void createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create menu buttons
        JButton scheduleBtn = createMenuButton("Schedule Buddy");
        JButton yogaBtn = createMenuButton("Yoga Exercises");
        JButton activitiesBtn = createMenuButton("Cognitive Activities");
        JButton reflectionsBtn = createMenuButton("Reflections");
        JButton rewardsBtn = createMenuButton("Points & Rewards");
        
        // Add action listeners
        scheduleBtn.addActionListener(e -> cardLayout.show(cardPanel, "schedule"));
        yogaBtn.addActionListener(e -> cardLayout.show(cardPanel, "yoga"));
        activitiesBtn.addActionListener(e -> cardLayout.show(cardPanel, "activities"));
        reflectionsBtn.addActionListener(e -> cardLayout.show(cardPanel, "reflections"));
        rewardsBtn.addActionListener(e -> cardLayout.show(cardPanel, "rewards"));
        
        // Add buttons to panel
        menuPanel.add(scheduleBtn);
        menuPanel.add(yogaBtn);
        menuPanel.add(activitiesBtn);
        menuPanel.add(reflectionsBtn);
        menuPanel.add(rewardsBtn);
        
        cardPanel.add(menuPanel, "menu");
    }

    private static void createSchedulePanel() {
        JPanel schedulePanel = new JPanel(new BorderLayout());
        schedulePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create title
        JLabel titleLabel = new JLabel("Schedule Buddy (Checklist)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        schedulePanel.add(titleLabel, BorderLayout.NORTH);

        // Create checklist panel
        JPanel checklistPanel = new JPanel();
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(checklistPanel);
        schedulePanel.add(scrollPane, BorderLayout.CENTER);

        // Store checkboxes to prevent double scoring
        ArrayList<JCheckBox> checkBoxList = new ArrayList<>();

        // Populate with initial schedule
        for (String item : schedule) {
            JCheckBox checkBox = new JCheckBox(item);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 16));
            checklistPanel.add(checkBox);
            checkBoxList.add(checkBox);

            checkBox.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    points += 15;
                    updatePoints();
                    JOptionPane.showMessageDialog(mainFrame, "Task completed! +15 points");
                }
            });
        }

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add New Item");
        JButton backButton = new JButton("Back to Menu");

        addButton.addActionListener(e -> {
            String newItem = JOptionPane.showInputDialog(mainFrame,
                "Enter new task (e.g., 'Thursday: History Report - 4:30 PM'):");
            if (newItem != null && !newItem.trim().isEmpty()) {
                schedule.add(newItem);
                JCheckBox newCheckBox = new JCheckBox(newItem);
                newCheckBox.setFont(new Font("Arial", Font.PLAIN, 16));
                checklistPanel.add(newCheckBox);
                checkBoxList.add(newCheckBox);

                newCheckBox.addItemListener(ev -> {
                    if (ev.getStateChange() == ItemEvent.SELECTED) {
                        points += 15;
                        updatePoints();
                        JOptionPane.showMessageDialog(mainFrame, "Task completed! +15 points");
                    }
                });

                checklistPanel.revalidate();
                checklistPanel.repaint();

                points += 50;
                updatePoints();
                JOptionPane.showMessageDialog(mainFrame, "Schedule item added! +50 points");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        schedulePanel.add(buttonPanel, BorderLayout.SOUTH);

        cardPanel.add(schedulePanel, "schedule");
    }


    private static void createYogaPanel() {
        JPanel yogaPanel = new JPanel(new BorderLayout());
        yogaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create title
        JLabel titleLabel = new JLabel("Yoga Exercises", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        yogaPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create description
        JTextArea description = new JTextArea(
            "Yoga helps with impulse control and emotional regulation.\n\n" +
            "Choose a yoga exercise to practice for 5 minutes:");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setEditable(false);
        description.setBackground(yogaPanel.getBackground());
        yogaPanel.add(description, BorderLayout.NORTH);
        
        // Create exercise buttons
        JPanel exercisesPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        exercisesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        String[] exercises = {
            "Mountain Pose",
            "Tree Pose",
            "Child's Pose",
            "Return to Menu"
        };
        
        for (String exercise : exercises) {
            JButton exerciseBtn = new JButton(exercise);
            exerciseBtn.setFont(new Font("Arial", Font.PLAIN, 16));
            exerciseBtn.addActionListener(e -> {
                if (!exercise.equals("Return to Menu")) {
                    points += 30;
                    updatePoints();
                    JOptionPane.showMessageDialog(mainFrame,
                        "Great job completing " + exercise + "! +30 points");
                }
                cardLayout.show(cardPanel, "menu");
            });
            exercisesPanel.add(exerciseBtn);
        }
        
        yogaPanel.add(exercisesPanel, BorderLayout.CENTER);
        cardPanel.add(yogaPanel, "yoga");
    }

    private static void createActivitiesPanel() {
        JPanel activitiesPanel = new JPanel(new BorderLayout());
        activitiesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create title
        JLabel titleLabel = new JLabel("Cognitive Activities", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        activitiesPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create description
        JTextArea description = new JTextArea(
            "These activities help improve executive functioning skills:\n");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setEditable(false);
        description.setBackground(activitiesPanel.getBackground());
        activitiesPanel.add(description, BorderLayout.NORTH);
        
        // Create activity buttons
        JPanel activitiesGrid = new JPanel(new GridLayout(2, 2, 20, 20));
        activitiesGrid.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        String[] activities = {
            "Puzzles",
            "Basketball",
            "Board Games",
            "Drawing"
        };
        
        for (String activity : activities) {
            JButton activityBtn = new JButton(activity);
            activityBtn.setFont(new Font("Arial", Font.PLAIN, 16));
            activityBtn.addActionListener(e -> {
                points += 40;
                updatePoints();
                JOptionPane.showMessageDialog(mainFrame,
                    "Great job completing " + activity + "! +40 points");
            });
            activitiesGrid.add(activityBtn);
        }
        
        activitiesPanel.add(activitiesGrid, BorderLayout.CENTER);
        
        // Add back button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        activitiesPanel.add(backButton, BorderLayout.SOUTH);
        
        cardPanel.add(activitiesPanel, "activities");
    }

    private static void createReflectionsPanel() {
        JPanel reflectionsPanel = new JPanel(new BorderLayout());
        reflectionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create title
        JLabel titleLabel = new JLabel("Reflections Journal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        reflectionsPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create reflections list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String reflection : reflections) {
            listModel.addElement(reflection);
        }
        
        JList<String> reflectionsList = new JList<>(listModel);
        reflectionsList.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(reflectionsList);
        reflectionsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add New Reflection");
        JButton backButton = new JButton("Back to Menu");
        
        addButton.addActionListener(e -> {
            String newReflection = JOptionPane.showInputDialog(mainFrame,
                "Enter your reflection (e.g., 'Had trouble focusing today'):");
            if (newReflection != null && !newReflection.trim().isEmpty()) {
                String datedReflection = LocalDate.now() + ": " + newReflection;
                reflections.add(datedReflection);
                listModel.addElement(datedReflection);
                points += 20;
                updatePoints();
                JOptionPane.showMessageDialog(mainFrame, "Reflection added! +20 points");
            }
        });
        
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        reflectionsPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        cardPanel.add(reflectionsPanel, "reflections");
    }

    private static void createRewardsPanel() {
        JPanel rewardsPanel = new JPanel(new BorderLayout());
        rewardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create title
        JLabel titleLabel = new JLabel("Points & Rewards", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        rewardsPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create points display
        JPanel pointsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        pointsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        JLabel currentPoints = new JLabel("Current Points: " + points, SwingConstants.CENTER);
        currentPoints.setFont(new Font("Arial", Font.BOLD, 20));
        pointsPanel.add(currentPoints);
        
        int pointsNeeded = Math.max(0, 1000 - points);
        JLabel nextReward = new JLabel("Points needed for next reward: " + pointsNeeded,
            SwingConstants.CENTER);
        nextReward.setFont(new Font("Arial", Font.PLAIN, 16));
        pointsPanel.add(nextReward);
        
        if (points >= 1000) {
            JLabel congrats = new JLabel("CONGRATULATIONS! You've earned a reward!",
                SwingConstants.CENTER);
            congrats.setFont(new Font("Arial", Font.BOLD, 18));
            congrats.setForeground(new Color(0, 100, 0)); // Dark green
            pointsPanel.add(congrats);
            
            JLabel claim = new JLabel("Talk to your parent/guardian to claim your treat!",
                SwingConstants.CENTER);
            claim.setFont(new Font("Arial", Font.PLAIN, 16));
            pointsPanel.add(claim);
        }
        
        rewardsPanel.add(pointsPanel, BorderLayout.CENTER);
        
        // Add back button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        rewardsPanel.add(backButton, BorderLayout.SOUTH);
        
        cardPanel.add(rewardsPanel, "rewards");
    }

    private static JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return button;
    }

    private static void updatePoints() {
        pointsLabel.setText("Points: " + points);
        // Update rewards panel if it's visible
        if (cardLayout != null) {
            createRewardsPanel(); // Recreate rewards panel to update points display
        }
    }
}

