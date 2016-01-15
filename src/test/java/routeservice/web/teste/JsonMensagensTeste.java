package routeservice.web.teste;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.BeforeClass;
import org.junit.Test;

public class JsonMensagensTeste {

	private static Server server;

	@BeforeClass
	public static void beforeClass() throws Exception {
		System.setProperty("ambiente", "teste");
		server = new Server(8083);

		WebAppContext context = new WebAppContext();
		context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		context.setResourceBase("src/main/webapp");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);

		server.setHandler(context);

		server.start();
	}

	@Test
	public void buscaMelhorRota() throws IOException {
		final String mensagemUrl  = "http://localhost:8083/buscaMelhorCaminho";
		System.out.println("MENSAGEM:"+mensagemUrl);
		URL requestUrl = new URL(mensagemUrl);
		HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.addRequestProperty("Content-type", "application/json");
		final String request = "{\"mapa\":\"TESTE\",\"origem\":\"A\",\"destino\":\"D\",\"autonomia\":10,\"custoPorLitro\":2.50}";
		System.out.println("REQUEST:"+request);
		conn.getOutputStream().write(request.getBytes());
		System.out.println("RESPONSE:"+getResponseMessage(conn));
		System.out.println("");
	}
	
	@Test
	public void buscaMelhorRotaErro() throws IOException {
		final String mensagemUrl  = "http://localhost:8083/buscaMelhorCaminho";
		System.out.println("MENSAGEM:"+mensagemUrl);
		URL requestUrl = new URL(mensagemUrl);
		HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.addRequestProperty("Content-type", "application/json");
		final String request = "{\"mapa\":\"TESTE\",\"origem\":\"A\",\"destino\":\"H\",\"autonomia\":10,\"custoPorLitro\":2.50}";
		System.out.println("REQUEST:"+request);
		conn.getOutputStream().write(request.getBytes());
		System.out.println("RESPONSE:"+getResponseMessage(conn));
		System.out.println("");
	}

	@Test
	public void mensagemInsereMapa() throws IOException {
		final String mensagemUrl  = "http://localhost:8083/inserirMapa";
		System.out.println("MENSAGEM:"+mensagemUrl);
		URL requestUrl = new URL(mensagemUrl);
		HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.addRequestProperty("Content-type", "application/json");
		final String request = "{\"mapa\":\"TESTE\",\"rotas\":[" //
				+ "\n{\"origem\":\"A\",\"destino\":\"B\",\"distancia\":10}" //
				+ "\n,{\"origem\":\"B\",\"destino\":\"D\",\"distancia\":15}" //
				+ "\n,{\"origem\":\"A\",\"destino\":\"C\",\"distancia\":20}" //
				+ "\n,{\"origem\":\"C\",\"destino\":\"D\",\"distancia\":30}" //
				+ "\n,{\"origem\":\"B\",\"destino\":\"E\",\"distancia\":50}" //
				+ "\n,{\"origem\":\"D\",\"destino\":\"E\",\"distancia\":30}" //
				+ "\n,{\"origem\":\"E\",\"destino\":\"F\",\"distancia\":12}" //
				+ "\n]}"; //
		System.out.println("REQUEST:"+request);
		conn.getOutputStream().write(request.getBytes());
		System.out.println("RESPONSE:"+getResponseMessage(conn));
		System.out.println("");
	}

	
  
	
	
	private static String getResponseMessage(HttpURLConnection conn) throws IOException {
		InputStream file = null;
		try {
			StringBuilder build = new StringBuilder();
		    System.out.println("RESPONSE STATUS:"+conn.getResponseCode());
			if (conn.getResponseCode() == 200) {
				file = conn.getInputStream();
			} else {
				file = conn.getErrorStream();
			}
			final byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = file.read(buffer)) > 0) {
				build.append(new String(Arrays.copyOfRange(buffer, 0, len)));
			}

			return build.toString();
		} finally {
			if (file != null) {
				file.close();
			}
		}

	}

}
