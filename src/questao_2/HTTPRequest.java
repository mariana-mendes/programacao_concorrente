package questao_2;

public class HTTPRequest implements HTTPRequestI{
	
	private String data;

	public HTTPRequest() {
		this.data = "Conte�do da requisi��o";
	}

	@Override
	public String request(String serverName) {
		return serverName + " retornou: " + data;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String server) {
		this.data = server;
	}

}
