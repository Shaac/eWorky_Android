package omnicentre.eworky.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;

/**
 * This class handles internal storage in the phone.
 *
 */
public class Storage {
    
    private String token;
    private String name;
    private String firstName;
    private String email;
    private Activity activity;

    /**
     * Create a tool to manage storage within the activity.
     * @param activity the current activity.
     */
    public Storage(Activity activity) {
        this.activity = activity;
    }
    
    public boolean isConnected() {
        return getToken().length() > 0;
    }

    /**
     * Get the stored token.
     * @return the token.
     */
    public String getToken() {
        if (token == null)
            token = read("token");
        return token;
    }

    /**
     * Get the stores last name.
     * @return the last name.
     */
    public String getName() {
        if (name == null)
            name = read("name");
        return name;
    }

    /**
     * Get the stored first name.
     * @return the first name.
     */
    public String getFirstName() {
        if (firstName == null)
            firstName = read("firstName");
        return firstName;
    }

    /** 
     * Get the stored email.
     * @return the email.
     */
    public String getEmail() {
        if (email == null)
            email = read("email");
        return email;
    }

    /**
     * Read a file in internal storage.
     * @param filename the files name.
     * @return its content.
     */
    private String read(String filename) {
        FileInputStream in;
        try {
            in = activity.openFileInput(filename);
            StringBuffer s = new StringBuffer("");
            byte[] buffer = new byte[1024];
            while ((in.read(buffer)) != -1) {
                s.append(new String(buffer));
            }
            in.close();
            return s.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }

}
