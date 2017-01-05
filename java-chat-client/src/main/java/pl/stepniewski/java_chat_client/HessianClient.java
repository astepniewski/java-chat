package pl.stepniewski.java_chat_client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.prompt.PromptSupport;
import com.caucho.hessian.client.HessianProxyFactory;
import pl.stepniewski.java_chat_contracts.CommunicationService;

public class HessianClient {

	private static Integer userId = 0;
	private static String userName = null;

	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8080/communication-service";
		HessianProxyFactory factory = new HessianProxyFactory();
		final CommunicationService basic = (CommunicationService) factory.create(CommunicationService.class, url);

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				JFrame frame = new JFrame("Hessian Hello World");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				final JTextField field = new JTextField(100);
				PromptSupport.setPrompt("Podaj Login", field);
				frame.add(field, BorderLayout.SOUTH);
				field.setToolTipText("Press Enter to submit text to server");

				final JTextArea resultArea = new JTextArea();
				JScrollPane scroll = new JScrollPane(resultArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				frame.add(scroll, BorderLayout.CENTER);

				field.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (userName == null) {
							String newUserName = field.getText();
							Integer newUserId = basic.Login(newUserName);
							if (newUserId == -1) {
								resultArea.append(
										"Użytkownik o loginie " + newUserName + " już istnieje. Wybierz inny login \n");
							} else {
								userId = newUserId;
								userName = newUserName;
								PromptSupport.setPrompt("Wiadomość", field);
								resultArea.append(
										"Zalogowałeś się jako " + userName + " o numerze Id "+ userId + "\n");
								field.setText("");
							}
						} else {
							resultArea.append("Server said : " + basic.communicate(field.getText()) + "\n");
						}
					}
				});

				frame.setSize(600, 400);
				frame.setVisible(true);
			}
		});
	}
}