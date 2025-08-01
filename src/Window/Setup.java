package Window;

import javax.swing.*;
import java.awt.*;

public class Setup {
	
		private final ConfigLoader config;
		private JFrame window;
		
		public Setup(ConfigLoader config) {
	        this.config = config;
		}
		
		 public void run() {
		        window = new JFrame("First-Time Setup");
		        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        window.setSize(400, 250);
		        window.setLocationRelativeTo(null);

		        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
		        JTextField userField = new JTextField();
		        JPasswordField passField = new JPasswordField();
		        JPasswordField confirmField = new JPasswordField();
		        JButton submit = new JButton("Confirm");

		        panel.add(new JLabel("Username:"));
		        panel.add(userField);
		        panel.add(new JLabel("Password:"));
		        panel.add(passField);
		        panel.add(new JLabel("Confirm Password:"));
		        panel.add(confirmField);
		        panel.add(new JLabel());
		        panel.add(submit);

		        window.add(panel);
		        window.setVisible(true);

		        submit.addActionListener(e -> {
		            String username = userField.getText().trim();
		            String pass = new String(passField.getPassword());
		            String confirm = new String(confirmField.getPassword());

		            if (username.isEmpty() || pass.isEmpty()) {
		                JOptionPane.showMessageDialog(window, "Fields cannot be empty.");
		                return;
		            }

		            if (!pass.equals(confirm)) {
		                JOptionPane.showMessageDialog(window, "Passwords do not match.");
		                return;
		            }

		            config.setUsername(username);
		            config.setPasswordHash(Hash.hashPassword(pass));
		            window.dispose();
		            new Login(config).show(); // proceed to login
		        });
		 }
}
