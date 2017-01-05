package pl.stepniewski.java_chat_client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.caucho.hessian.client.HessianProxyFactory;

import pl.stepniewski.java_chat_contracts.CommunicationService;

public class HessianClient {

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/communication-service";
        HessianProxyFactory factory = new HessianProxyFactory();
        final CommunicationService basic = (CommunicationService) factory.create(CommunicationService.class, url);

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame("Hessian Hello World");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                final JTextField field = new JTextField("Peter", 100);
                frame.add(field, BorderLayout.NORTH);
                field.setToolTipText("Press Enter to submit text to server");
                
                final JLabel resultLabel = new JLabel("Server Response");
                frame.add(resultLabel, BorderLayout.SOUTH);

                field.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        resultLabel.setText("Server said : " + basic.communicate(field.getText()));
                    }
                });

                frame.setSize(600, 400);
                frame.setVisible(true);
            }
        });
    }
}