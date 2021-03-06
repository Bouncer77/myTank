import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow extends JFrame implements ActionListener, TCPConnectionListener {

    private static final String IP_ADDR = "109.173.20.192";
    private static final int PORT = 8189;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;


    public static void main(String[] args) {
        // Выполнять в потоке edt (ограничение свинга на многопоточное использование GUI)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });
    }

    private final JTextArea log = new JTextArea();
    private final JTextField fieldNickName = new JTextField("Tom");
    private final JTextField fieldInput = new JTextField("Привет =)");

    private TCPConnection connection;

    private ClientWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // окно по середине

        log.setEditable(false);
        log.setLineWrap(true); // Перенос строк

        fieldInput.addActionListener(this);

        add(log, BorderLayout.CENTER);
        add(fieldInput, BorderLayout.SOUTH);
        add(fieldNickName, BorderLayout.NORTH);

        setVisible(true);

        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
            printMsg("Connection exception: " + e);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if (msg.equals("")) return;

        fieldInput.setText(null); // стереть строчку
        connection.sendString(fieldNickName.getText() + ": " + msg);
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMsg("Connection ready");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
    }

    @Override
    public void onDisconect(TCPConnection tcpConnection) {
        printMsg("Connection close");
    }

    @Override
    public void onExceptioon(TCPConnection tcpConnection, Exception e) {
        printMsg("Connection exception: " + e);
    }

    private synchronized void printMsg(String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength()); // Прокрутка
            }
        });
    }
}
