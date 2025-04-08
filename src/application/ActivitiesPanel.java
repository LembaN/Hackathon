package application;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.util.*;

public class ActivitiesPanel extends JPanel {
    private static final String[] WORDS = {"focus", "brain", "memory", "routine", "reward", "relax", "breathe"};

    public ActivitiesPanel(BrainifyController controller) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Cognitive Activities", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JTextArea description = new JTextArea("These activities help improve executive functioning skills:\n");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setEditable(false);
        description.setBackground(getBackground());
        add(description, BorderLayout.CENTER);

        JPanel grid = new JPanel(new GridLayout(3, 2, 20, 20));
        String[] activities = {"Puzzles", "Basketball", "Board Games", "Drawing"};

        for (String activity : activities) {
            JButton button = new JButton(activity);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.addActionListener(e -> {
                controller.points += 40;
                controller.updatePointsLabel();
                JOptionPane.showMessageDialog(controller.mainFrame, "Great job completing " + activity + "! +40 points");
            });
            grid.add(button);
        }

        // Chess Game (2-Player)
        JButton chessButton = new JButton("2-Player Chess Game");
        chessButton.setFont(new Font("Arial", Font.PLAIN, 16));
        chessButton.addActionListener(e -> launchChessGame());
        grid.add(chessButton);

        // Scramble the Word Game
        JButton scrambleButton = new JButton("Scramble the Word");
        scrambleButton.setFont(new Font("Arial", Font.PLAIN, 16));
        scrambleButton.addActionListener(e -> launchScrambleGame(controller));
        grid.add(scrambleButton);

        add(grid, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back to Menu");
        backBtn.addActionListener(e -> controller.showPanel("menu"));
        add(backBtn, BorderLayout.SOUTH);
    }

    private void launchChessGame() {
        JFrame chessFrame = new JFrame("2-Player Chess Game");
        chessFrame.setSize(600, 600);
        chessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chessFrame.setLayout(new GridLayout(8, 8));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();
                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                chessFrame.add(square);
            }
        }

        chessFrame.setLocationRelativeTo(null);
        chessFrame.setVisible(true);
    }

    private void launchScrambleGame(BrainifyController controller) {
        JFrame gameFrame = new JFrame("Scramble the Word");
        gameFrame.setSize(400, 200);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        String word = WORDS[new Random().nextInt(WORDS.length)];
        String scrambled = shuffleWord(word);

        JLabel wordLabel = new JLabel("Unscramble this: " + scrambled, SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField inputField = new JTextField();
        JButton submitBtn = new JButton("Submit");

        submitBtn.addActionListener(e -> {
            String guess = inputField.getText().trim().toLowerCase();
            if (guess.equals(word)) {
                controller.points += 35;
                controller.updatePointsLabel();
                JOptionPane.showMessageDialog(gameFrame, "Correct! +35 points");
                gameFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(gameFrame, "Oops! Try again.");
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitBtn, BorderLayout.EAST);

        gameFrame.add(wordLabel, BorderLayout.NORTH);
        gameFrame.add(inputPanel, BorderLayout.SOUTH);
        gameFrame.setVisible(true);
    }

    private String shuffleWord(String word) {
        List<Character> chars = new ArrayList<>();
        for (char c : word.toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder sb = new StringBuilder();
        for (char c : chars) sb.append(c);
        return sb.toString();
    }
}
