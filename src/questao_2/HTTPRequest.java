package questao_2;

public class HTTPRequest implements HTTPRequestI{
	
	private String data;
	private static final String STATIC_CONTENT_REQUEST = "Request content";
	
	public HTTPRequest() {
		this.data = STATIC_CONTENT_REQUEST;
	}

	@Override
	public String request(String serverName) {
		return serverName + " returns: " + data;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String server) {
		this.data = server;
	}
}
