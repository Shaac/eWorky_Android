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

    public Storage(Activity activity) {
        this.activity = activity;
    }

    public String getToken() {
        if (token == null)
            token = read("token");
        return token;
    }

    public String getName() {
        if (name == null)
            name = read("name");
        return name;
    }

    public String getFirstName() {
        if (firstName == null)
            firstName = read("firstName");
        return firstName;
    }

    public String getEmail() {
        if (email == null)
            email = read("email");
        return email;
    }

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
