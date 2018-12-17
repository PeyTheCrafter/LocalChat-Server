package view.visualElements;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;

import controller.Controller;


public class SwitchButton extends JComponent implements MouseListener {

	private static final long serialVersionUID = -8057868901427123664L;
	private boolean state = false;
	private final int MARGIN = 5;
	private final int BORDER = 4;
	private Color backgroundColor;
	private Color buttonColor;
	private final Color DISABLED_COMPONENT_COLOR = new Color(131, 131, 131);
	
	public SwitchButton() {
		super();
		SwitchButton.this.setSize(new Dimension(60, 40));
		SwitchButton.this.setPreferredSize(new Dimension(60, 40));
		SwitchButton.this.setMinimumSize(new Dimension(60, 40));
		SwitchButton.this.setVisible(true);
		SwitchButton.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		SwitchButton.this.addMouseListener(SwitchButton.this);
		SwitchButton.this.setBackgroundColor(new Color(75, 216, 101));
		SwitchButton.this.setButtonColor(new Color(255, 255, 255));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (isOpaque()) {// Pinta el fondo del componente
			g2.setColor(getBackground());
			g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
		}

		if (isEnabled()) {// componente habilitado
			g2.setColor(((state) ? getBackgroundColor() : new Color(216, 217, 219)));
			g2.fill(new RoundRectangle2D.Double((float) MARGIN, (float) MARGIN, (float) getWidth() - MARGIN * 2,
					(float) getHeight() - MARGIN * 2, getHeight() - MARGIN * 2, getHeight() - MARGIN * 2));
		} else {// componente desabilitado
			g2.setColor(DISABLED_COMPONENT_COLOR);
			g2.draw(new RoundRectangle2D.Double((float) MARGIN, (float) MARGIN, (float) getWidth() - MARGIN * 2,
					(float) getHeight() - MARGIN * 2, getHeight() - MARGIN * 2, getHeight() - MARGIN * 2));
		}

		g2.setColor((isEnabled()) ? getButtonColor() : DISABLED_COMPONENT_COLOR);
		// boton circular
		if (state) {// ON a la izquierda
			g2.fillOval(MARGIN + BORDER / 2, MARGIN + BORDER / 2, getHeight() - MARGIN * 2 - BORDER,
					getHeight() - MARGIN * 2 - BORDER);
		} else {// OFF a la derecha
			g2.fillOval(getWidth() - getHeight() + MARGIN + BORDER / 2, MARGIN + BORDER / 2,
					getHeight() - MARGIN * 2 - BORDER, getHeight() - MARGIN * 2 - BORDER);
		}
	}

	public boolean state() {
		return state;
	}

	public void setOnOff(boolean OnOff) {
		this.state = OnOff;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(Color buttonColor) {
		this.buttonColor = buttonColor;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isEnabled()) {
			state = !state;
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/* ... */ }

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		/* ... */ }

	@Override
	public void mouseExited(MouseEvent e) {
		/* ... */ }

}
