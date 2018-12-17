package controller;

public class Logic {
	
	private Server server;
	
	public Logic() {
		this.server = new Server();
	}

	public void startServer(int port) {
		this.server.start(port);
	}

}
