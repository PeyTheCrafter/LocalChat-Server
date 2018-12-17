package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;

import controller.thread.ServerThread;

public class Server {

	private DefaultListModel<Object> conversation = new DefaultListModel<>();
	private ServerSocket serverSocket;
	private ServerThread serverThread;
	private Thread threadServer;
	private Socket socket;

	public void start(int port) {
		this.logInfo("Starting server...");
		this.logInfo("Attempting to start server on port " + port);

		try {
			this.serverSocket = new ServerSocket(port);
			this.logInfo("Server running on " + InetAddress.getLocalHost() + ":" + port);

			while (true) {
				socket = this.serverSocket.accept();
				this.logInfo("Connection from " + socket.getInetAddress());
				serverThread = new ServerThread(this.conversation, socket);
				this.threadServer = new Thread(serverThread);
				this.threadServer.start();
			}
		} catch (IOException e) {
			logInfo("Server closed");
		}
	}

	private void logInfo(String log) {
		System.out.println("[INFO] " + log);
		Controller.getInstance().addLog("[INFO] " + log);
	}
}
