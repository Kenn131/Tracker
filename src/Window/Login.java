package Window;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Login {
	
		private JFrame window;
		private JPanel cards;
		private static final String LOGIN = "login";
		private final ConfigLoader config;
		
		public Login(ConfigLoader config) {
			this.config = config;
			
			window = new JFrame();
			window.setTitle("Tracker");
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			window.setSize(450, 200);
			window.setLocationRelativeTo(null);
			
			cards = new JPanel(new CardLayout()); //cards hold panel variables
			cards.add(makeLoginPanel(), LOGIN);
			window.setContentPane(cards);
			
		}
		
		public void show() {
			
			window.setVisible(true);
			
		}
		
		 public void showScreen(String name) {
		        CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, name);
		    }
		
		private JPanel makeLoginPanel() {
	        
			JPanel p = new JPanel(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();
	        c.insets = new Insets(5,5,5,5);

	        // Create GUI elements
	        JLabel userLab = new JLabel("Username:");
	        JTextField user    = new JTextField(15);
	        JLabel passLab = new JLabel("Password:");
	        JPasswordField pswd = new JPasswordField(15);
	        JLabel dateLab = new JLabel("Date:");
	        JSpinner date = new JSpinner(new SpinnerDateModel());
	        JButton loginBtn = new JButton("Log In");
	        
	        // Font
	        Font labelFont = new Font("Cambria", Font.PLAIN, 14);
	        userLab.setFont(labelFont);
	        passLab.setFont(labelFont);
	        dateLab.setFont(labelFont);
	        loginBtn.setFont(labelFont);
	        
	        // Date Box
	        JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "yyyy-MM-dd");
	        date.setEditor(editor);
	        editor.getTextField().setEditable(false);

	        // layout
	        
	        c.gridx = 0; c.gridy = 0; p.add(userLab, c);
	        c.gridx = 1;          p.add(user,    c);
	        
	        c.gridy = 1; c.gridx = 0; p.add(passLab, c);
	        c.gridx = 1;          p.add(pswd,    c);
	        
	        c.gridy = 2; c.gridx = 0; p.add(dateLab, c);
	        c.gridx = 1;          p.add(date,    c);
	        
	        c.gridy = 3; c.gridx = 1; p.add(loginBtn, c);

	        // when “Log In” is clicked, validate then switch:
	        loginBtn.addActionListener(e -> {
	        	
	            char[] pw = pswd.getPassword();
	            
	            if (isValid(user.getText(), pw)) {
	                window.dispose();
	                //action function to start main application
	            }
	            
	            else {
	                JOptionPane.showMessageDialog(window,
	                    "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
	                pswd.setText(""); //clear the text box
	            }
	       });

	      return p;
	    }
		
		public boolean isValid(String inputUser, char[] inputPw) {
			String expectedUser = config.getUsername();
			String expectedHash = config.getPasswordHash();
			
			String enteredHash = Hash.hashPassword(new String(inputPw));
			Arrays.fill(inputPw, '0');
			
			return expectedUser.equals(inputUser) && expectedHash.equals(enteredHash);
		}
		
		
}

