package questao_2;

public class HTTPRequest implements HTTPRequestI{
	
	private String server;

	public HTTPRequest() {
		this.server = "None";
	}

	@Override
	public String request(String serverName) {
		this.setServer(serverName);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reliableRequest() {
		return this.getServer();
	}
	
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

}
