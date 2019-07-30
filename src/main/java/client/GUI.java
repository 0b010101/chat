package client;

import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame {
    TextArea chatMsg;
    TextField inputMsg;
    DialogConnection dc;
    ClientController clientController;

    GUI() {
        initGUI();
    }

    private void initGUI() {
        this.setSize(700, 600);
        this.setVisible(true);
        //this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        chatMsg = new TextArea("",34, 85, TextArea.SCROLLBARS_VERTICAL_ONLY);
        chatMsg.setEditable(false);
        add(chatMsg);
        inputMsg = new TextField(85);
        add(inputMsg);
        repaint();
        clientController = new ClientController(this);
        dc = new DialogConnection(this, clientController);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        inputMsg.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) clientController.sendMsg(inputMsg.getText());
                inputMsg.setText(null);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void displayMsg(String msg) {
        //TODO
        chatMsg.append(msg);
        chatMsg.append("\n");
    }


}
