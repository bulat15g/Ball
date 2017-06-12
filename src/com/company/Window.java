package com.company;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by mctf on 06.05.17.
 */
public class Window extends JFrame {
    public static int BoundX = 500, BoundY = 600;
    public static Double updateTime[];
    public static long clashnumber;

    JPanel upInfoPanel;
    JPanel centerPanel;
    JTextComponent infoTextField;
    static JTextComponent infoRunTimeField;
    static JTextComponent infoAllTimeField;
    static JTextComponent clashCount;

    static MainContent mainContent = new MainContent();
    static Timer infoUpdateTimer;


    /**
     * init GIU()
     */
    Window() {
        setBounds(100, 100, BoundX, BoundY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initiateGui();
        Timeinitiate();
        infoUpdateTimer.start();

        setVisible(true);
        setLayout(new BorderLayout());
        addKeyListener(new KeyListener());
        addMouseListener(new MouseListener());
        addComponentListener(new ComponentListener());
    }

    /**
     * Инициализация пользовательского интерфейса
     */
    void initiateGui() {
        upInfoPanel = new JPanel();
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        infoTextField = new JTextField("hello,its me");
        infoTextField.setEditable(false);
        infoTextField.setFocusable(false);

        infoRunTimeField = new JTextField("                                ");
        infoRunTimeField.setEditable(false);
        infoRunTimeField.setFocusable(false);
        infoAllTimeField = new JTextField("                                ");
        infoAllTimeField.setEditable(false);
        infoAllTimeField.setFocusable(false);
        clashCount = new JTextField("                           ");
        clashCount.setEditable(false);
        clashCount.setFocusable(false);


        upInfoPanel.setLayout(new FlowLayout());
        upInfoPanel.add(infoTextField);
        upInfoPanel.add(centerPanel);
        upInfoPanel.add(infoRunTimeField);
        upInfoPanel.add(clashCount);

        centerPanel.add(infoAllTimeField);

        upInfoPanel.setBackground(new Color(168, 220, 200));

        add(mainContent, BorderLayout.CENTER);
        add(upInfoPanel, BorderLayout.BEFORE_FIRST_LINE);
    }

    //Инициализация Таймера работы программы и статического
    // массива времени для отображения времени работыпрограммы
    void Timeinitiate() {
        updateTime = new Double[2];
        updateTime[0] = updateTime[1] = 0.0;

        infoUpdateTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoRunTimeField.setText("Время работы:" + updateTime[1].toString());
                infoAllTimeField.setText("Время работы:" + updateTime[0].toString());
                clashCount.setText("Ударов:"+clashnumber);

                updateTime[0] += 0.5;
                if (mainContent.moveTimer.isRunning()) {
                    updateTime[1] += 0.5;
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
            if (e.getExtendedKeyCode() == 27)
                System.exit(0);
            if (e.getExtendedKeyCode() == 32) {
                if (mainContent.moveTimer.isRunning()) {
                    infoTextField.setText("Paused");
                    mainContent.moveTimer.stop();
                } else {
                    infoTextField.setText("Runned");
                    mainContent.moveTimer.start();
                }

            }
            if (e.getExtendedKeyCode() == 32||e.getExtendedKeyCode() == 77){
                for (int i = 0; i < mainContent.moveObjects.ballArrayList.size(); i++) {
                    mainContent.moveObjects.ballArrayList.get(i).decreaseSpeed(false,1.5);
                }
            }
            if (e.getExtendedKeyCode() == 37)mainContent.moveObjects.cue.move(-1);
            if (e.getExtendedKeyCode() == 39)mainContent.moveObjects.cue.move(1);
            if (e.getExtendedKeyCode() == 38)mainContent.moveObjects.cue.move(0);

        }
    }

    private class MouseListener implements java.awt.event.MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX() + "        " + e.getY());
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

    private class ComponentListener implements java.awt.event.ComponentListener{
        @Override
        public void componentResized(ComponentEvent e) {
            Rectangle rectangle=new Rectangle();
            rectangle=e.getComponent().getBounds();


        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    }

}