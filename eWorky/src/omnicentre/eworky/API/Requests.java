package omnicentre.eworky.API;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import omnicentre.eworky.tools.SearchCriteria;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

/**
 * This class handles API actions.
 *
 */
public class Requests {

    /**
     * The API's URL.
     */
    private static final String host =
            //"http://10.0.2.2:15157/api/localisation/";
            //"http://taff.coworky.com/api/localisation/";
            "http://www.eworky.com/api/localisation/";

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
            Log.w("request", request);
            Log.w("out", out);

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

        return Requests.call("comment", postArguments, getArguments);
    }

    /**
     * Ask for a connection to the server.
     * @param login the login.
     * @param password the password.
     * @return the token.
     * @throws NoSuccessException in case of an invalid request.
     */
    public static AuthJson connect(String login, String password)
            throws NoSuccessException {

        // We construct the request:
        HashMap<String, String> postArguments = new HashMap<String, String>();
        postArguments.put("Login", login);
        postArguments.put("Password", password);
        HashMap<String, String> getArguments = new HashMap<String, String>();

        // We make it:
        String json = Requests.call("connect", postArguments, getArguments);

        // We parse it:
        Gson gson = new Gson();
        Type type = new TypeToken<ObjectResult<AuthJson>>() {}.getType();
        ObjectResult<AuthJson> o = gson.fromJson(json, type);

        return o.getResponse();
    }

    public static String register(String firstName, String lastName, 
            String email, String phone) {

        HashMap<String, String> postArguments = new HashMap<String, String>();
        postArguments.put("FirstName", firstName);
        postArguments.put("LastName", lastName);
        postArguments.put("Email", email);
        postArguments.put("PhoneNumber", phone);

        HashMap<String, String> getArguments = new HashMap<String, String>();

        return Requests.call("register", postArguments, getArguments);
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

    public static List<LocalisationJson> search(SearchCriteria criteria)
            throws NoSuccessException {

        // TODO internet deconnexion

        // We construct the request:
        HashMap<String, String> postArguments = new HashMap<String, String>();
        HashMap<String, String> getArguments = criteria.getParams();

        // We make it:
        String json = Requests.call("search", postArguments, getArguments);

        // We parse it:
        Gson gson = new Gson();
        Type type = new TypeToken<ObjectResult<List<LocalisationJson>>>() {}.getType();
        ObjectResult<List<LocalisationJson>> o = gson.fromJson(json, type);

        return o.getResponse();
    }


    public static LocalisationJson details(int id) throws NoSuccessException {

        // We construct the request:
        HashMap<String, String> postArguments = new HashMap<String, String>();
        HashMap<String, String> getArguments = new HashMap<String, String>();
        getArguments.put("id", String.valueOf(id));

        // We make it:
        String json = Requests.call("details", postArguments, getArguments);
        
        Gson gson = new Gson();
        Type type = new TypeToken<ObjectResult<LocalisationJson>>() {}.getType();
        ObjectResult<LocalisationJson> o = gson.fromJson(json, type);

        return o.getResponse();
    }
}
