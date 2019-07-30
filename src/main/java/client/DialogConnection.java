package client;

import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogConnection extends Dialog {
    Label labelIP, labelPort, labelNic, labelPass;
    Button enter, register;
    TextField fieldIP, fieldPort, fieldNic, fieldPass;

    public DialogConnection(Frame parent) {
        super(parent, false);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        Panel panel = new Panel();

        labelIP = new Label("IP");
        labelPort = new Label("Port");
        labelNic = new Label("NicName");
        labelPass = new Label("Password");

        fieldIP = new TextField(20);
        fieldPort = new TextField(20);
        fieldNic = new TextField(20);
        fieldPass = new TextField(20);

        enter = new Button("Вход");
        register = new Button("Регистрация");

        panel.add(labelIP);
        panel.add(fieldIP);
        panel.add(labelPort);
        panel.add(fieldPort);
        panel.add(labelNic);
        panel.add(fieldNic);
        panel.add(labelPass);
        panel.add(fieldPass);

        panel.add(enter);
        panel.add(register);

        add("Center", panel);
        setSize(180,320);
        setVisible(true);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog err = new ErrorDialog(DialogConnection.this);
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        repaint();
    }

}
