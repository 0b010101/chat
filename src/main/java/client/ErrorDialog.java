package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ErrorDialog extends Dialog {
    Label labelConnection, labelAccount, labelRegister;
    Button close;

    ErrorDialog(DialogConnection d) {
        super(d);

        setLayout(new BorderLayout());
        Panel panel = new Panel();

        labelConnection = new Label("Ошибка подключения");
        labelAccount = new Label("Неправильный логин/пароль");
        labelRegister = new Label("Пользователь существует");

        close = new Button("Close");
        //TODO Доделать вывод ошибок при неправильных данных
        panel.add(labelConnection);
        panel.add(close);

        add("Center", panel);
        setSize(200, 100);
        setVisible(true);
        setResizable(false);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

}
