package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class DictionaryApplication extends JFrame {


    private JButton Dic_Btn,Game_btn,Exit_btn;
    public DictionaryApplication(){

        setTitle("Dictionary Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);


        JPanel mainpanel=new JPanel();
        mainpanel.setBackground(new Color(173, 216, 230));


        Dic_Btn = new JButton("Dictionary");
        Game_btn = new JButton("Mini Game!");
        Exit_btn = new JButton("Exit!");

        Dic_Btn.setBounds(150, 300, 100, 30);
        Game_btn.setBounds(150, 300, 100, 30);
        Exit_btn.setBounds(150, 300, 100, 30);

        Dic_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             open_Dic()  ;
            }
        });
        Game_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Exit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exit_app();
            }
        });



        mainpanel.add(Dic_Btn);
        mainpanel.add(Game_btn);
        mainpanel.add(Exit_btn);

        add(mainpanel);




        setVisible(true);

    }



    private void Exit_app(){

    }
    private void open_Game(){

    }

    private void open_Dic(){
        open_Dic openDic=new open_Dic();
        dispose();
    }








    public static void main(String[] args) {
        DictionaryApplication dictionaryApplication=new DictionaryApplication();
    }
}



