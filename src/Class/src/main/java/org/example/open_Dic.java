package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class open_Dic extends JFrame {
    Dictionary dictionary = new Dictionary();
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    DictionaryCommandLine dictionaryCommandLine=new DictionaryCommandLine();



    public open_Dic() {
        String current_word=null;

        setTitle("English Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);


        //nap danh sach tu
        dictionaryManagement.insertFromFile(dictionary, "src/dictionaries.txt");


        // Panel chứa các nút bấm
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;
        JPanel explain_Panel = new JPanel(new GridBagLayout());


        JPanel grid1 = new JPanel(new BorderLayout());
        JLabel Text1 = new JLabel("Nghĩa của từ");
        Font font = Text1.getFont();
        Text1.setFont(new Font(font.getName(), font.getStyle(), 20));
        grid1.add(Text1, BorderLayout.NORTH);
        GridBagConstraints R_gbc = new GridBagConstraints();
        R_gbc.gridx = 0;
        R_gbc.gridy = 0;
        R_gbc.weightx = 1;
        R_gbc.weighty = 0.02; // 10%
        R_gbc.fill = GridBagConstraints.BOTH;
        explain_Panel.add(grid1, R_gbc);

        // Lưới 2 chứa biểu tượng loa
        JPanel grid2 = new JPanel(new BorderLayout());
        JButton speakerButton = new JButton();
        int iconSize = 20; // Kích thước mới của biểu tượng loa
        Image scaledIcon = new ImageIcon("src/Speaker_Icon.svg.png").getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        speakerButton.setIcon(new ImageIcon(scaledIcon));
        grid2.add(speakerButton, BorderLayout.WEST);

        //add_btn,edit_btn,delete_btn
        JPanel Word_Control_button = new JPanel(new GridLayout(1, 3));

        JButton add_btn = new JButton("Add");
        add_btn.addActionListener(new ActionListener() {
            String action="Add Word";
            @Override
            public void actionPerformed(ActionEvent e) {
               open_edit_scene(dictionaryManagement,action);

            }
        });
        Word_Control_button.add(add_btn);





        JButton edit_btn = new JButton("Edit");
        edit_btn.addActionListener(new ActionListener() {
            String action="Edit Word";
            @Override
            public void actionPerformed(ActionEvent e) {
                open_edit_scene(dictionaryManagement,action);
                System.out.println(current_word);

            }
        });
        Word_Control_button.add(edit_btn);


        JButton deleta_btn = new JButton("Delete");
        Word_Control_button.add(deleta_btn);


        grid2.add(Word_Control_button, BorderLayout.EAST);


        R_gbc = new GridBagConstraints();
        R_gbc.gridx = 0;
        R_gbc.gridy = 1;
        R_gbc.weightx = 1;
        R_gbc.weighty = 0.02;
        R_gbc.fill = GridBagConstraints.BOTH;
        explain_Panel.add(grid2, R_gbc);


        JPanel grid3 = new JPanel();
        R_gbc = new GridBagConstraints();
        JLabel text2 = new JLabel("");
        grid3.add(text2);
        R_gbc.gridx = 0;
        R_gbc.gridy = 2;
        R_gbc.weightx = 1;
        R_gbc.weighty = 0.96;
        R_gbc.fill = GridBagConstraints.BOTH;
        explain_Panel.add(grid3, R_gbc);

        Map<String, JButton> buttonMap = new HashMap<>();


        // Tạo nút bấm cho mỗi từ vựng và thêm vào panel
        Word[] allword= dictionaryCommandLine.showAllWords(dictionary).toArray(new Word[0]);
        for (int i = 0; i < allword.length; i++) {
            String buttonId = Integer.toString(i);
            JButton button = new JButton(allword[i].getWord_target());
            String button_meaning = allword[i].getWord_explain();
            buttonMap.put(buttonId, button);
            button.setPreferredSize(new Dimension(200, 40)); // Đặt kích thước ưa thích
            button.addActionListener(new ButtonClickListener(buttonId, buttonMap, button_meaning, text2,current_word));
            buttonPanel.add(button, gbc);

        }

        // Tạo JScrollPane và thêm buttonPanel vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Tạo thanh tìm kiếm, nút tìm kiếm và nút return
        JPanel searchPanel = new JPanel();
        JButton returnButton = new JButton("Back");
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");


        // Thêm thanh tìm kiếm, nút tìm kiếm và nút return vào searchPanel

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Tạo panel chứa các nút Add, Edit, Delete
        JPanel buttonControlPanel = new JPanel();
        buttonControlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));


        // Thêm các nút vào buttonControlPanel
        buttonControlPanel.add(returnButton);


        // Tạo leftPanel chứa searchPanel, scrollPane và buttonControlPanel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(searchPanel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(buttonControlPanel, BorderLayout.SOUTH);





        // Tạo panel chính và thiết lập GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();

        // Thêm leftPanel vào mainPanel
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 0.25; // Chiếm 1/4 chiều rộng của mainPanel
        mainGbc.weighty = 1.0;
        mainGbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(leftPanel, mainGbc);

        // Thêm rightpanel vào mainPanel
        mainGbc.gridx = 1;
        mainGbc.weightx = 0.75; // Chiếm 3/4 chiều rộng của mainPanel
        mainPanel.add(explain_Panel, mainGbc);

        // Thêm mainPanel vào JFrame
        add(mainPanel);

        // Hiển thị JFrame
        setVisible(true);
    }

    static class ButtonClickListener implements ActionListener {
        private String buttonId;
        private Map<String, JButton> buttonMap;
        private String button_meaning;
        private JLabel text;
        private String current_word;

        public ButtonClickListener(String buttonId, Map<String, JButton> buttonMap, String button_meaning, JLabel text,String current_word) {

            this.current_word=current_word;
            this.buttonId = buttonId;
            this.buttonMap = buttonMap;
            this.button_meaning = button_meaning;
            this.text = text;

        }

        public void actionPerformed(ActionEvent e) {
            JButton button = buttonMap.get(buttonId); // Lấy ra button được click dựa trên ID
            if (button != null) {
                current_word=button_meaning;
                text.setText(button_meaning); // Thay đổi văn bản của button thành "Clicked"
            }
        }
    }

    public void open_edit_scene(DictionaryManagement dictionaryManagement,String action) {
            JFrame edit_frame=new JFrame(action);
            edit_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            edit_frame.setSize(600, 400);


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
            formPanel.add(new JScrollPane(textArea2), gbc);
            textArea1.setLineWrap(true);
            textArea1.setWrapStyleWord(true);
            textArea2.setLineWrap(true);
            textArea2.setWrapStyleWord(true);


        // Create the submit button
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                                               @Override
                                               public void actionPerformed(ActionEvent e) {
                                                   // Get text from textArea1 and textArea2
                                                   String word_target = textArea1.getText();
                                                    String word_explain = textArea2.getText();
                                                    dictionaryManagement.addWord(dictionary,word_target,word_explain);
                                                    dictionaryManagement.dictionaryExportToFile(dictionary,"src/dictionaries.txt");
                                               }
                                           });

            // Add the submit button to the panel
            panel.add(formPanel, BorderLayout.CENTER);
            panel.add(submitButton, BorderLayout.SOUTH);

            // Add the panel to the frame's content pane
            edit_frame.add(panel);

            // Center the frame on the screen
            edit_frame.setLocationRelativeTo(null);
            edit_frame.setVisible(true);


        }


}
