package omnicentre.eworky.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.drawable.Drawable;

/**
 * This class gets stuff online through HTTP protocol.
 *
 */
public class Http {

    /**
     * Get the content of a online page.
     * @param url the page's URL.
     * @return the content of the page.
     * @throws ClientProtocolException in case of protocol errors.
     * @throws IOException in case of connection issues.
     */
	public static String getUrl(String url)
	        throws ClientProtocolException, IOException {

		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			BufferedReader reader =
			        new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} else {
			throw new ClientProtocolException("Server error");
		}
		return builder.toString();
	}
	
	/**
	 * Get the online image as a {@link Drawable}.
	 * @param url the image's URL.
	 * @return the image as a {@link Drawable}, null if an error occurred.
	 */
	public static Drawable getImage(String url) {
	    Drawable d = null;
	    if (url.length() > 0) {
            try {
                URL u = new URL(url);
                InputStream content = (InputStream)u.getContent();
                d = Drawable.createFromStream(content , "src"); 
            } catch (Exception e) {
                d = null;
            }
	    }
	    return d;
	}
}
