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

    DictionaryApiClient dictionaryApiClient=new DictionaryApiClient();

    static String current_word=null;

    public open_Dic() {


        setTitle("English Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);


        //nap danh sach tu
        dictionaryManagement.insertFromFile(dictionary, "src/dictionaries.txt");


        JPanel Target_Panel=new JPanel(new GridBagLayout());
        // Panel chứa các nút bấm
        JPanel buttonPanel = new JPanel(new GridBagLayout());

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
        grid2.add(Word_Control_button, BorderLayout.EAST);


        R_gbc = new GridBagConstraints();
        R_gbc.gridx = 0;
        R_gbc.gridy = 1;
        R_gbc.weightx = 1;
        R_gbc.weighty = 0.02;
        R_gbc.fill = GridBagConstraints.BOTH;
        explain_Panel.add(grid2, R_gbc);


        JPanel grid3 = new JPanel(new GridBagLayout());
        R_gbc = new GridBagConstraints();



        JLabel vietnamese_text = new JLabel("");
        Font font1 = new Font("Arial", Font.BOLD, 16);
        vietnamese_text.setFont(font1);



        // Tạo một JTextArea với số cột được tính toán
        JTextArea english_text = new JTextArea();
        english_text.setLineWrap(true); // Cho phép tự động xuống dòng
        english_text.setWrapStyleWord(true); // Xuống dòng dựa trên từ
        Font font2 = new Font("SansSerif", Font.ITALIC, 20);
        english_text.setFont(font2);



        GridBagConstraints vietnameseConstraints = new GridBagConstraints();
        vietnameseConstraints.gridx = 0; // Cột 0
        vietnameseConstraints.gridy = 0; // Hàng 0
        vietnameseConstraints.anchor = GridBagConstraints.NORTHWEST; // Căn trái
        vietnameseConstraints.weightx = 0.6; // 80% chiều rộng của grid3
        vietnameseConstraints.weighty = 0.05; // Không chiếm nhiều chiều cao
        vietnameseConstraints.fill = GridBagConstraints.HORIZONTAL; // Độ dài linh hoạt

// Thêm vietnamese_text vào grid3 với các ràng buộc GridBagConstraints tương ứng
        grid3.add(vietnamese_text, vietnameseConstraints);

// Tạo đối tượng GridBagConstraints cho english_text
        GridBagConstraints englishConstraints = new GridBagConstraints();
        englishConstraints.gridx = 0; // Cột 0
        englishConstraints.gridy = 1; // Hàng 1
        englishConstraints.anchor = GridBagConstraints.NORTHWEST;
        englishConstraints.weightx = 0.7; // 80% chiều rộng của grid3
        englishConstraints.weighty = 0.95; // 96% chiều cao của grid3
        englishConstraints.fill = GridBagConstraints.BOTH; // Độ dài và độ cao linh hoạt

// Thêm english_text vào grid3 với các ràng buộc GridBagConstraints tương ứng
        grid3.add(english_text, englishConstraints);




        R_gbc.gridx = 0;
        R_gbc.gridy = 2;
        R_gbc.weightx = 0.8;
        R_gbc.weighty = 0.90;
        R_gbc.fill=GridBagConstraints.BOTH;

        explain_Panel.add(grid3, R_gbc);

        Map<String, JButton> buttonMap = new HashMap<>();


        // Tạo nút bấm cho mỗi từ vựng và thêm vào panel
        Create_Vocab_list(buttonMap,vietnamese_text,english_text,buttonPanel) ;


        // Tạo JScrollPane và thêm buttonPanel vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Tạo thanh tìm kiếm, nút tìm kiếm và nút return
        JPanel searchPanel = new JPanel();
        JButton returnButton = new JButton("Back");
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);



        // Thêm thanh tìm kiếm, nút tìm kiếm và nút return vào searchPanel

        searchPanel.add(searchField);


        // Tạo panel chứa các nút Add, Edit, Delete
        JPanel buttonControlPanel = new JPanel();
        buttonControlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));


        // Thêm các nút vào buttonControlPanel
        buttonControlPanel.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DictionaryApplication dictionaryApplication=new DictionaryApplication();
                dispose();
            }
        });


        JButton add_btn = new JButton("Add");
        add_btn.addActionListener(new ActionListener() {
            String action="Add Word";
            @Override
            public void actionPerformed(ActionEvent e) {
                open_edit_scene(dictionaryManagement,action,buttonMap,vietnamese_text,english_text,buttonPanel);

            }
        });
        Word_Control_button.add(add_btn);





        JButton edit_btn = new JButton("Edit");
        edit_btn.addActionListener(new ActionListener() {
            String action="Edit Word";
            @Override
            public void actionPerformed(ActionEvent e) {
                open_edit_scene(dictionaryManagement,action,buttonMap,vietnamese_text,english_text,buttonPanel);

            }
        });
        Word_Control_button.add(edit_btn);


        JButton deleta_btn = new JButton("Delete");
        deleta_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(current_word);
                dictionaryManagement.deleteWord(dictionary,current_word);
                dictionaryManagement.dictionaryExportToFile(dictionary,"src/dictionaries.txt");
            }
        });
        Word_Control_button.add(deleta_btn);


        // Tạo leftPanel chứa searchPanel, scrollPane và buttonControlPanel
        JPanel leftPanel = new JPanel(new GridBagLayout());

        GridBagConstraints searchConstraints = new GridBagConstraints();
        searchConstraints.gridx = 0;
        searchConstraints.gridy = 0;
        searchConstraints.weightx = 1.0;
        searchConstraints.weighty = 0.05; // 10% chiều cao của leftPanel
        searchConstraints.fill = GridBagConstraints.BOTH;
        leftPanel.add(searchPanel, searchConstraints);

        GridBagConstraints scrollConstraints = new GridBagConstraints();
        scrollConstraints.gridx = 0;
        scrollConstraints.gridy = 1;
        scrollConstraints.weightx = 1.0;
        scrollConstraints.weighty = 0.9; // 80% chiều cao của leftPanel
        scrollConstraints.fill = GridBagConstraints.BOTH;


        // Thêm scrollPane vào leftPanel với các ràng buộc GridBagConstraints tương ứng
        leftPanel.add(scrollPane, scrollConstraints);



        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 2;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 0.05; // 10% chiều cao của leftPanel
        buttonConstraints.fill = GridBagConstraints.BOTH;


        // Thêm buttonControlPanel vào leftPanel với các ràng buộc GridBagConstraints tương ứng
        leftPanel.add(buttonControlPanel, buttonConstraints);






        // Tạo panel chính và thiết lập GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();

        // Thêm leftPanel vào mainPanel
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 0.25; // Chiếm 1/4 chiều rộng của mainPanel
        mainGbc.weighty = 0.8;
        mainGbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(leftPanel, mainGbc);

        // Thêm rightpanel vào mainPanel
        mainGbc.gridx = 1;
        mainGbc.weightx = 0.75; // Chiếm 3/4 chiều rộng của mainPanel
        mainGbc.weighty = 0.8;
        mainPanel.add(explain_Panel, mainGbc);

        // Thêm mainPanel vào JFrame
        add(mainPanel);

        // Hiển thị JFrame
        setVisible(true);
    }

    static class ButtonClickListener implements ActionListener {
        private  JTextArea text2;
        private String buttonId;
        private Map<String, JButton> buttonMap;
        private String button_meaning;
        private JLabel text;
        private String button_word;


        public ButtonClickListener(String buttonId, Map<String, JButton> buttonMap, String button_word,String button_meaning, JLabel text,JTextArea text2) {

            this.button_word=button_word;
            this.buttonId = buttonId;
            this.buttonMap = buttonMap;
            this.button_meaning = button_meaning;
            this.text = text;
            this.text2=text2;


        }

        public void actionPerformed(ActionEvent e) {

            DictionaryResult dictionaryResult=new DictionaryResult();
            DictionaryApiClient dictionaryApiClient=new DictionaryApiClient();
            String word= button_word.replaceAll("\\s+", "%20");
            DictionaryResult result = dictionaryApiClient.fetchDefinitionAndAudioLink(word);
            JButton button = buttonMap.get(buttonId); // Lấy ra button được click dựa trên ID
            current_word=button_word;
            if (button != null) {

                text.setText(button_meaning); // Thay đổi văn bản của button thành "Clicked"
                text2.setText(result.getDefinition());

            }
        }
    }

    public void open_edit_scene(DictionaryManagement dictionaryManagement,String action,Map<String, JButton> buttonMap, JLabel vietnamese_text,JTextArea english_text,JPanel buttonPanel) {
            JFrame edit_frame=new JFrame(action);
            edit_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            edit_frame.setSize(600, 400);


            // Create labels and text fields

            JLabel label1 = new JLabel("Word:");
            JTextArea textArea1 = new JTextArea(3, 40); // 5 hàng, 20 cột
            if(action=="Edit Word"){
                textArea1.setText(current_word);
                textArea1.setEditable(false);
            }
            JLabel label2 = new JLabel("Explain:");
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
            if(action=="Add Word") {
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get text from textArea1 and textArea2
                        String word_target = textArea1.getText();
                        String word_explain = textArea2.getText();
                        dictionaryManagement.addWord(dictionary, word_target, word_explain);
                        dictionaryManagement.dictionaryExportToFile(dictionary, "src/dictionaries.txt");
                        Create_Vocab_list( buttonMap, vietnamese_text, english_text, buttonPanel);
                        edit_frame.dispose();
                    }
                });
            }else{
                submitButton.addActionListener(new ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent e) {
                                                       // Get text from textArea1 and textArea2
                                                       String word_target = textArea1.getText();
                                                       String word_explain = textArea2.getText();
                                                       dictionaryManagement.editWord(dictionary, word_target, word_explain);
                                                       dictionaryManagement.dictionaryExportToFile(dictionary, "src/dictionaries.txt");
                                                       Create_Vocab_list( buttonMap, vietnamese_text, english_text, buttonPanel);
                                                       edit_frame.dispose();
                                                   }
                                               });}

            // Add the submit button to the panel
            panel.add(formPanel, BorderLayout.CENTER);
            panel.add(submitButton, BorderLayout.SOUTH);

            // Add the panel to the frame's content pane
            edit_frame.add(panel);

            // Center the frame on the screen
            edit_frame.setLocationRelativeTo(null);
            edit_frame.setVisible(true);


        }
    public void Create_Vocab_list( Map<String, JButton> buttonMap, JLabel vietnamese_text,JTextArea english_text,JPanel buttonPanel) {
        Word[] allword = dictionaryCommandLine.showAllWords(dictionary).toArray(new Word[0]);

        for (int i = 0; i < allword.length; i++) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = GridBagConstraints.RELATIVE;
            gbc.weightx = 1.0;
            String buttonId = Integer.toString(i);
            JButton button = new JButton(allword[i].getWord_target());
            String button_word = allword[i].getWord_target();
            String button_meaning = allword[i].getWord_explain();
            buttonMap.put(buttonId, button);
            button.setSize(new Dimension(200, 40));
            button.addActionListener(new ButtonClickListener(buttonId, buttonMap, button_word, button_meaning, vietnamese_text, english_text));
            buttonPanel.add(button,gbc);

        }
    }


}
