package Window;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	 private Properties props;
	 
	 
	 // Sets props object to read defined boundaries.
	 public ConfigLoader() {
	        props = new Properties();
	        try (FileInputStream in = new FileInputStream("resources/config.properties")) {
	            props.load(in);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public boolean isFirstTimeUse() {
		    String user = getUsername();
		    String hash = getPasswordHash(); // or getPasswordHash()
		    return user.isEmpty() || hash.isEmpty();
		}
	 
	 public String getUsername() {
	        return props.getProperty("username", "");
	    }

	 public String getPassword() {
	        return props.getProperty("password", "");
	    }
	 
	 public String getPasswordHash() {
	        return props.getProperty("passwordHash", "");
	    }

	 public String getDataPath() {
	        return props.getProperty("dataPath", "./data/");
	    }

	 public String getStartDate() {
	        return props.getProperty("startDate", "2025-01-01");
	    }
	 
	 public void setUsername(String newUser) {
		    props.setProperty("username", newUser);
		    save();
		}
	 
	 public void setPasswordHash(String newHash) {
		    props.setProperty("passwordHash", newHash);
		    save();
		}
	 
	 private void save() {
		    try (FileOutputStream out = new FileOutputStream("resources/config.properties")) {
		        props.store(out, "Updated config");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
}
