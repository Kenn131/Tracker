package Window;

import javax.swing.*;

public class Launcher {
	
	public static void main(String [] args) {
		SwingUtilities.invokeLater(() -> {
            ConfigLoader config = new ConfigLoader();

            if (config.isFirstTimeUse()) {
                new Setup(config).run();
            } else {
                new Login(config).show();
			}
		});
	}
}

	