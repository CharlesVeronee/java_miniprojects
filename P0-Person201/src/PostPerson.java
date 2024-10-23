import java.net.*;
import java.io.*;
import java.net.http.*;
import com.google.gson.*;

/**
 * Create a post request to a 201 REST server
 * to record json information for compsci 201,
 * fall 2021.
 * @author Owen Astrachan, ola@duke.edu
 */

public class PostPerson {

    /**
     * Create a post request to the specified URL and
     * send a json string corresponding to the specified Person201
     * object.
     * @param url is the URL of a rest server accepting json
     * @param p is the Person201 object sent to server
     */
    public void postJson(String url, Person201 p) {
        Gson gs = new Gson();
        String str = gs.toJson(p);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(str))
                .build();
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("printing response from POST request");
            System.out.println(response.body());
            System.out.printf("code = %d\n",response.statusCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("post request likely failed");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String url = "https://apt.cs.duke.edu/upload201.php";
        PostPerson pp = new PostPerson();
        Person201 p = new Person201("Charlie",35.2271, 80.8431,"What's a mob to a king?\n" +
                "What's a king to a god?\n" +
                "What's a god to a non-believer\n" +
                "Who don't believe in anything?");
        pp.postJson(url,p);
    }
}
