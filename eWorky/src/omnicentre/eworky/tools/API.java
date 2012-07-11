package omnicentre.eworky.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import android.util.Log;

/**
 * This class handles API actions.
 *
 */
public class API {

    /**
     * The API's URL.
     */
    private static final String host =
            "http://10.0.2.2:15157/api/localisation/";
    //"http://www.eworky.com/api/localisation/";

    /**
     * Calls a function of the API.
     * @param function the function we want to call.
     * @param postArguments the POST arguments.
     * @param getArguments the GET arguments.
     * @return the content of the page.
     */
    public static String call(String function, HashMap<String,
            String> postArguments, HashMap<String, String> getArguments) {

        String urlParameters = joinArguments(postArguments);
        String request = host + function + "?" + joinArguments(getArguments) +
                "json=1/mobile";

        try {
            URL url = new URL(request);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setUseCaches (false);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
            wr.writeBytes(urlParameters);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    connection.getInputStream())); 
            String line;
            String out = "";
            while ((line = rd.readLine()) != null) { 
                out += line;
            }
            wr.close();
            rd.close();
            Log.w("EWORKY", out);

            connection.disconnect();
            return out;
        } catch (IOException e) {
            Log.e("EWORKY", e.toString());
            return null;
        }
    }

    public static String comment(String login, String password, String post, int
            postLanguage, int rating, int id) {
        
        HashMap<String, String> postArguments = new HashMap<String, String>();
        postArguments.put("Login", login);
        postArguments.put("Password", password);
        postArguments.put("Post", post);
        postArguments.put("PostLanguage", "" + postLanguage);
        postArguments.put("Rating", "" + rating);
        
        HashMap<String, String> getArguments = new HashMap<String, String>();
        getArguments.put("id", "" + id);
        
        return API.call("comment", postArguments, getArguments);
    }
    
    public static String getToken(String login, String password) {
        
        HashMap<String, String> postArguments = new HashMap<String, String>();
        postArguments.put("Login", login);
        postArguments.put("Password", password);
        
        HashMap<String, String> getArguments = new HashMap<String, String>();
        
        return API.call("getToken", postArguments, getArguments);
    }
    
    private static String joinArguments(HashMap<String, String> arguments) {
        StringBuilder s = new StringBuilder();
        if (arguments != null) {
            for (HashMap.Entry<String, String> e : arguments.entrySet()) {
                s.append(e.getKey());
                s.append("=");
                try {
                    s.append(URLEncoder.encode(e.getValue(), "UTF-8"));
                } catch (UnsupportedEncodingException e1) {}
                s.append("&");
            }
        }
        return s.toString();
    }
    
    

}