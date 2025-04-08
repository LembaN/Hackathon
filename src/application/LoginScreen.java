package application;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class LoginScreen {
	private static final String ACCOUNT_FILE = "accounts.txt";

    public void showLogin() {
        JFrame loginFrame = new JFrame("Brainify Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Brainify", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        loginFrame.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        loginFrame.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton loginBtn = new JButton("Login");
        JButton createBtn = new JButton("Create Account");

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (isValidLogin(username, password)) {
                JOptionPane.showMessageDialog(loginFrame, "Login successful!");
                loginFrame.dispose();
                BrainifyController controller = new BrainifyController();
                controller.initializeSampleData();
                controller.createAndShowGUI();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        createBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, "Username and password cannot be empty.");
                return;
            }
            if (createAccount(username, password)) {
                JOptionPane.showMessageDialog(loginFrame, "Account created successfully! You can now log in.");
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(loginBtn);
        buttonPanel.add(createBtn);
        loginFrame.add(buttonPanel, BorderLayout.SOUTH);

        loginFrame.setVisible(true);
    }

    private boolean isValidLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean createAccount(String username, String password) {
        if (accountExists(username)) return false;
        try (FileWriter writer = new FileWriter(ACCOUNT_FILE, true)) {
            writer.write(username + "," + password + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean accountExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File may not exist on first run, and that's okay.
        }
        return false;
    }
}
