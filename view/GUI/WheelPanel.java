package view.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.interfaces.Slot;

@SuppressWarnings("serial")
public class WheelPanel extends JPanel {
	private Image wheel;
	private int slot;

	public WheelPanel() {
		wheel = new ImageIcon("img/Basic_roulette_wheel_1024x1024.png").getImage();
	}

	public void paintComponent(Graphics g) {
		// draw Wheel
		super.paintComponent(g);
		int size = Math.min(getWidth(), getHeight());
		int x = (getWidth() - size) / 2;
		int y = (getHeight() - size) / 2;
		g.drawImage(wheel, x, y, size, size, null);

		// define ball
		Graphics2D ball = (Graphics2D) g.create();
		ball.setColor(Color.YELLOW);
		double degree = (slot * 360.0 / Slot.WHEEL_SIZE);
		int ballSize = size / 45;
		double radians = Math.toRadians(degree - 90);
		double radius = (size / 2) - (ballSize * 1.6);
		// get coordinate
		int xBall = Math.round((float) ((size / 2) + Math.cos(radians) * radius));
		int yBall = Math.round((float) ((size / 2) + Math.sin(radians) * radius));
		// draw ball
		ball.fillOval((x + xBall - ballSize / 2), (y + yBall - ballSize / 2), ballSize, ballSize);
		ball.dispose();
	}

	public void setSlot(int slot) {
		this.slot = slot;
		repaint();
	}
}