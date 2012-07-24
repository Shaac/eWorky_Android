package omnicentre.eworky.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import omnicentre.eworky.API.AuthJson;

import android.app.Activity;
import android.content.Context;

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

    /**
     * Create a tool to manage storage within the activity.
     * @param activity the current activity.
     * @param auth the initialization data.
     * @throws IOException in case of an error in file manipulation.
     */
    public Storage(Activity activity, AuthJson auth) throws IOException {
        this.activity = activity;
        setToken(auth.getToken());
        setName(auth.getName());
        setFirstName(auth.getFirstName());
        setEmail(auth.getEmail());
    }

    public boolean isConnected() {
        return getToken().length() > 0;
    }
    
    public static boolean isConnected(Activity activity) {
        Storage s = new Storage(activity);
        return s.getToken().length() > 0;
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
     * Write the token in internal storage.
     * @param token the token.
     * @throws IOException in case of an error in file manipulation.
     */
    public void setToken(String token) throws IOException {
        write("token", token);
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
     * Write the name in internal storage.
     * @param name the name.
     * @throws IOException in case of an error in file manipulation.
     */
    public void setName(String name) throws IOException {
        write("name", name);
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
     * Write the first name in internal storage.
     * @param firstName the first name.
     * @throws IOException in case of an error in file manipulation.
     */
    public void setFirstName(String firstName) throws IOException {
        write("firstName", firstName);
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
     * Write the email in internal storage.
     * @param email the email.
     * @throws IOException in case of an error in file manipulation.
     */
    public void setEmail(String email) throws IOException {
        write("email", email);
    }

    /**
     * Read a file in internal storage.
     * @param filename the file's name.
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

    /**
     * Write a file in internal storage
     * @param filename the file's name.
     * @param content its content.
     * @throws IOException in case of an error in the file manipulation.
     */
    private void write(String filename, String content) throws IOException {
        FileOutputStream fos = activity.getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
        fos.write(content.getBytes());
        fos.close();
    }

    /**
     * Remove all files in order to create a log out.
     * @param activity the current activity.
     * @throws IOException in case of an error in the file manipulation.
     */
    public static void purge(Activity activity) throws IOException {
        Storage storage = new Storage(activity);
        storage.setToken("");
        storage.setName("");
        storage.setFirstName("");
        storage.setEmail("");
    }

}
