package client;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends Frame {
    TextArea chatMsg;
    TextField inputMsg;
    DialogConnection dc;

    GUI() {
        initGUI();
    }

    private void initGUI() {
        this.setSize(700, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        chatMsg = new TextArea("",34, 85, TextArea.SCROLLBARS_VERTICAL_ONLY);
        chatMsg.setEditable(false);
        add(chatMsg);
        inputMsg = new TextField(85);
        add(inputMsg);
        repaint();
        dc = new DialogConnection(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
