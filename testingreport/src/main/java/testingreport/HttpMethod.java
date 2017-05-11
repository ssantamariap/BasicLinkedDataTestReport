package testingreport;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpMethod {
	
	public static final String GET = "method: GET";
	
	private Header [] headers;
	
	public HttpResponse httpGet(String uri) throws ClientProtocolException, IOException{
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(uri);
		
		HttpResponse response = httpClient.execute(request);
		
		/*
		int statusCode = response.getStatusLine().getStatusCode();
		
		if(statusCode == HttpStatus.SC_OK){
			
			headers = response.getAllHeaders();
			
			for (int i=0; i<headers.length; i++){
				
				System.out.println(headers[i]);
			}
			
		}
		
		System.out.println(response.toString());
		*/
		
		return response;
		
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	
}
