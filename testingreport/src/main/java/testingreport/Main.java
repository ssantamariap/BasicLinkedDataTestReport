package testingreport;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class Main {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		String uri = "http://dbpedia.org/page/Bilbao";
		
		HttpMethod method = new HttpMethod();
		
		method.httpGet(uri);
		
	}

}
