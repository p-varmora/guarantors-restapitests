import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class TheGuarantorRestClient {
    // POST Method:
    public CloseableHttpResponse post(String url, String body,String contentType) throws  IOException{

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost postRequest = new HttpPost(url); //http post request

        StringEntity payload = new StringEntity(body);//for payload

        //for header

        payload.setContentType(contentType);
        postRequest.setEntity(payload);

        CloseableHttpResponse closebaleHttpResponse = httpClient.execute(postRequest);
        return closebaleHttpResponse;
    }
}
