package controller.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import controller.Controller;

public class ServerThread implements Runnable, ListDataListener {

	private DefaultListModel<Object> conversation;
	private Socket socket;
	private ObjectInputStream objectInput;
	private ObjectOutputStream objectOutput;

	public ServerThread(DefaultListModel<Object> conversation, Socket socket) {
		this.conversation = conversation;
		this.socket = socket;
		try {
			this.objectOutput = new ObjectOutputStream(socket.getOutputStream());
			this.objectOutput.flush();
			this.objectInput = new ObjectInputStream(socket.getInputStream());

			this.conversation.addListDataListener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				Object object = objectInput.readObject();
				synchronized (conversation) {
					this.sendMessage(object);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			this.disconnect();
		}
	}

	private void disconnect() {
		Controller.getInstance().addLog("User disconnected.");
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread.currentThread().interrupt();
	}

	private void sendMessage(Object message) {
		this.conversation.addElement(message);
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		Object object = this.conversation.getElementAt(e.getIndex0());
		try {
			objectOutput.writeObject(object);
		} catch (Exception e1) {
		}
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
	}

}
