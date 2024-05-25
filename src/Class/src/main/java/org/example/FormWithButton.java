package org.example;

import javax.swing.*;
import java.awt.*;

public class FormWithButton extends JFrame {
    public FormWithButton() {
        // Set up the frame
        setTitle("Form with Button");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Create labels and text fields
        JLabel label1 = new JLabel("Label 1:");
        JTextArea textArea1 = new JTextArea(3, 40); // 5 hàng, 20 cột

        JLabel label2 = new JLabel("Label 2:");
        JTextArea textArea2 = new JTextArea(5, 40); // 5 hàng, 20 cột

        // Enable line wrap và wrap style word cho text area để cho phép xuống dòng tự động
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);

        // Create a panel to hold components
        JPanel panel = new JPanel(new BorderLayout());

        // Create a sub-panel for labels and text fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.05;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        formPanel.add(label1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0.05;
        formPanel.add(label2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        formPanel.add(new JScrollPane(textArea1), gbc); // Sử dụng JScrollPane để hỗ trợ cuộn nếu cần

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.6; // Set weighty for textArea2
        formPanel.add(new JScrollPane(textArea2), gbc); // Sử dụng JScrollPane để hỗ trợ cuộn nếu cần

        // Create the submit button
        JButton submitButton = new JButton("Submit");

        // Add the submit button to the panel
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);

        // Add the panel to the frame's content pane
        getContentPane().add(panel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormWithButton frame = new FormWithButton();
            frame.setVisible(true);
        });
    }
}
