package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import org.itr_rescue.dataGuard.ui.VerticalFlowLayout;

import controller.Controller;
import controller.ValidatorsController;
import view.visualElements.MyScrollBarUI;
import view.visualElements.TextPrompt;

import javax.swing.border.EmptyBorder;
import java.awt.Font;

import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class View extends JFrame {

	private static final long serialVersionUID = -4323420922028284192L;
	private JPanel contentPane;
	private JTextField port;
	private JList<String> logger;
	private JScrollPane scrollPane;
	private JPanel panelPort;
	private JButton connectButton;
	private JButton button;
	private JPanel panelTrush;

	public View() {
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		setTitle("LocalChat Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(14, 22, 33));
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelPort = new JPanel();
		panelPort.setBorder(new EmptyBorder(50, 50, 50, 50));
		panelPort.setOpaque(false);
		contentPane.add(panelPort, BorderLayout.CENTER);

		port = new JTextField();
		port.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				validatePort();
			}
		});
		port.addActionListener(e -> connect());
		VerticalFlowLayout vfl_panelPort = new VerticalFlowLayout();
		vfl_panelPort.setVgap(20);
		vfl_panelPort.setVerticalAlign(0.3f);
		vfl_panelPort.setHorizontalFill(VerticalFlowLayout.MATCH_COMPONENTS);
		panelPort.setLayout(vfl_panelPort);
		port.setForeground(Color.WHITE);
		port.setFont(new Font("Arial", Font.PLAIN, 25));
		port.setOpaque(false);
		port.setCaretColor(Color.WHITE);
		port.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)),
				new EmptyBorder(5, 10, 5, 10)));
		panelPort.add(port);
		port.setColumns(10);

		TextPrompt placeholderPort = new TextPrompt("Port", port);

		connectButton = new JButton("CONNECT");
		connectButton.setEnabled(false);
		connectButton.addActionListener(e -> connect());
		connectButton.setForeground(Color.WHITE);
		connectButton.setFont(new Font("Arial", Font.BOLD, 20));
		connectButton.setBackground(new Color(46, 139, 87));
		connectButton.setBorder(new EmptyBorder(5, 20, 5, 20));
		panelPort.add(connectButton);
		
		panelTrush = new JPanel();
		FlowLayout fl_panelTrush = (FlowLayout) panelTrush.getLayout();
		fl_panelTrush.setAlignment(FlowLayout.RIGHT);
		panelTrush.setOpaque(false);
		panelTrush.setBorder(new EmptyBorder(10, 10, 10, 15));
		
		button = new JButton();
		button.addActionListener(e -> deleteAllLog());
		button.setBorder(null);
		panelTrush.add(button);
		button.setIcon(new ImageIcon(View.class.getResource("/assets/trash.png")));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		placeholderPort.setBorder(null);
		placeholderPort.setFont(new Font("Arial", Font.PLAIN, 25));
		placeholderPort.setForeground(Color.LIGHT_GRAY);

		logger = new JList<>();
		logger.setModel(new DefaultListModel<>());
		logger.setForeground(Color.WHITE);
		logger.setFont(new Font("Monospaced", Font.PLAIN, 13));
		logger.setBorder(new EmptyBorder(10, 10, 10, 10));
		logger.setBackground(new Color(23, 33, 43));
		
		scrollPane = new JScrollPane(logger);
		scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
		scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setLayout(new ScrollPaneLayout());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		setVisible(true);
	}

	public void addLog(String log) {
		((DefaultListModel<String>) this.logger.getModel()).addElement(log);
		revalidate();
	}
	
	public void deleteAllLog() {
		((DefaultListModel<String>) this.logger.getModel()).clear();
		this.contentPane.updateUI();
	}

	private void validatePort() {
		this.connectButton.setEnabled(ValidatorsController.getInstance().validatePort(this.port.getText()));
	}
	
	private void connect() {
		this.contentPane.remove(this.panelPort);
		this.contentPane.add(this.scrollPane, BorderLayout.CENTER);
		this.contentPane.add(this.panelTrush, BorderLayout.SOUTH);
		Controller.getInstance().startServer();
	}

	public String getPort() {
		return this.port.getText();
	}

}
