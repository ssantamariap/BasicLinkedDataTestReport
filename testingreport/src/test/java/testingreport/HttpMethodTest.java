package testingreport;

import static org.junit.Assert.*;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class HttpMethodTest {

	static File reporter;
	static BufferedWriter writer;
	String uri = "http://dbpedia.org/page/Bilbao";
	HttpResponse response;
	Header[] headers;

	@BeforeClass
	public static void setUp() throws IOException {
 
		String report = System.getProperty("user.dir") + "\\report.html";
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		
		reporter = new File(report);
		writer = new BufferedWriter(new FileWriter(reporter, true));
		writer.write("<html><body>");
		writer.write("<h1>Resultados Test - " + dateFormat.format(date)+ "</h1>");
 
	}
 
	@AfterClass
	public static void tearDown() throws IOException {
 
		writer.write("</body></html>");
		writer.close();
		Desktop.getDesktop().browse(reporter.toURI());
 
	}
 
	@Rule
	public TestRule watchman = new TestWatcher() {
 
		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
		}
 
		@Override
		protected void succeeded(Description description) {
			try {
				writer.write("<p>Método: " + HttpMethod.GET + "</p>");
				writer.write("<p>Recurso: " + uri + "</p>");
				writer.write("<p>Código HTTP: " + response.getStatusLine().getStatusCode());
				writer.write("<p>Respuesta: " + response.toString() + "</p>");
				writer.write("<p>Resultado: Test SUPERADO</p>");
				for (Header header : headers){
					
					writer.write(header + "<br>");
				}
				
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
 
		@Override
		protected void failed(Throwable e, Description description) {
			try {
				writer.write(description.getDisplayName() + " Test NO SUPERADO");
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	};

	@Test
	public void test() throws ClientProtocolException, IOException {
		
		HttpMethod testing = new HttpMethod();
		response = testing.httpGet(uri);
		headers = response.getAllHeaders();
	}

}
