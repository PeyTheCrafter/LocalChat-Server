package controller;

import view.View;

public class Controller {

	private static Controller instance;
	private View view;
	private Logic logic;
	
	private Controller() {
		this.view = new View();
		this.logic = new Logic();
	}
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		
		return instance;
	}

	public void startServer() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				logic.startServer(Integer.parseInt(view.getPort()));
			}
		});
		t.start();
	}
	
	public void addLog(String log) {
		this.view.addLog(log);
	}

}
