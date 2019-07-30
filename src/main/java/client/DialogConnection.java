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
    ClientController clientController;

    public DialogConnection(Frame parent, ClientController clientController) {
        super(parent, false);
        this.clientController = clientController;
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        Panel panel = new Panel();

        labelIP = new Label("IP");
        labelPort = new Label("Port");
        labelNic = new Label("NicName");
        labelPass = new Label("Password");

        fieldIP = new TextField(20);
        fieldPort = new TextField("9000", 20);
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
                String ip = fieldIP.getText();
                String sPort = fieldPort.getText();
                String nic = fieldNic.getText();
                int port = 0;

                try {
                    port = Integer.parseInt(sPort);
                    if (port == 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    new ErrorDialog(DialogConnection.this, "Неправильно введен порт");
                    return;
                }
                if (DialogConnection.this.clientController.connect(ip, port, nic)) {
                    dispose();
                } else {
                    new ErrorDialog(DialogConnection.this, "Connection Error");
                }

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
