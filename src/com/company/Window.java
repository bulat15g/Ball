package com.company;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by mctf on 06.05.17.
 */
public class Window extends JFrame{
    public static int BoundX=500,BoundY=600;
    public static Double updateTime[];

    JPanel rightInfoPanel;
    JPanel centerPanel;
    JTextComponent infoTextField;
    static JTextComponent infoRunTimeField;
    static JTextComponent infoAllTimeField;

    static MainContent mainContent=new MainContent();
    static Timer infoUpdateTimer;



    /**
     * init GIU()
     */
    Window(){
        setBounds(100,100,BoundX,BoundY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initiateGui();
        Timeinitiate();
        infoUpdateTimer.start();

        setVisible(true);
        setLayout(new BorderLayout());
        addKeyListener(new KeyListener());
        addMouseListener(new MouseListener());

    }

    /**
     * Инициализация пользовательского интерфейса
     */
    void initiateGui(){
        rightInfoPanel =new JPanel();
        centerPanel =new JPanel();centerPanel.setLayout(new BorderLayout());

        infoTextField =new JTextField("hello,its me");
        infoTextField.setEditable(false);
        infoTextField.setFocusable(false);

        infoRunTimeField =new JTextField("                                ");
        infoRunTimeField.setEditable(false);
        infoRunTimeField.setFocusable(false);
        infoAllTimeField =new JTextField("                                ");
        infoAllTimeField.setEditable(false);
        infoAllTimeField.setFocusable(false);


        rightInfoPanel.setLayout(new FlowLayout());
        rightInfoPanel.add(infoTextField);
        rightInfoPanel.add(centerPanel);
        rightInfoPanel.add(infoRunTimeField);

        centerPanel.add(infoAllTimeField);

        rightInfoPanel.setBackground(new Color(168, 220, 200));

        add(mainContent,BorderLayout.CENTER);
        add(rightInfoPanel,BorderLayout.BEFORE_FIRST_LINE);
    }

    //Инициализация Таймера работы программы и статического
    // массива времени для отображения времени работыпрограммы
    void Timeinitiate(){
        updateTime=new Double[2];
        updateTime[0]=updateTime[1]=0.0;

        infoUpdateTimer=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoRunTimeField.setText("Время работы:"+updateTime[1].toString());
                infoAllTimeField.setText("Время работы:"+updateTime[0].toString());

                updateTime[0]+=0.5;
                if(mainContent.moveTimer.isRunning()){
                    updateTime[1]+=0.5;
                }
            }
        });
    }


    /**
     * my Custom key listener
     */
    private class KeyListener implements java.awt.event.KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getExtendedKeyCode());
            if(e.getExtendedKeyCode()==27)
                System.exit(0);
            if(e.getExtendedKeyCode()==32){
                if (mainContent.moveTimer.isRunning()){
                    infoTextField.setText("Paused");
                    mainContent.moveTimer.stop();
                }
                else {
                    infoTextField.setText("Runned");
                    mainContent.moveTimer.start();
                }

            }
        }
    }

    private class MouseListener implements java.awt.event.MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX()+"        "+e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
