package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import coffeeshop.Cup;
import coffeeshop.EspressoMachine;
import coffeeshop.Grinder;
import coffeeshop.LatteArt;
import coffeeshop.Pitcher;
import coffeeshop.Portafilter;
import coffeeshop.Mug;
import coffeeshop.SteamEffect;

public class CoffeePanel extends JPanel implements ActionListener {
	// eco points: custom-made image for start screen
	public static final int W_WIDTH = 1050;
	public static final int W_HEIGHT = 750;
	public static final int COUNTER_Y = 480;

	private double mouseX, mouseY;

	// state machine:
	// 0  = welcome screen
	// 1  = drag portafilter to grinder
	// 2  = grinding coffee for 3 seconds
	// 3  = drag portafilter to espresso machine
	// 4  = choose number of shots and press button
	// 5  = pulling espresso shot (5 seconds)
	// 6  = shot in cup, drag pitcher to steam wand
	// 7  = steaming milk for 3 seconds
	// 8  = drag pitcher back to table
	// 9  = drag cup to mug to pour espresso
	// 10 = drag pitcher to mug to pour steamed milk
	// 11 = latte complete, end screen
	private int state = 0;

	private Grinder grinder;
	private EspressoMachine espressoMachine;
	private Portafilter portafilter;
	private Cup cup;
	private Pitcher pitcher;
	private SteamEffect steamEffect;
	private Mug mug;

	private LatteArt latteArt = new LatteArt();
	private BufferedImage startBg;

	private JFrame frame;

	private Timer timer;
	private Timer steamCountdown;
	private boolean steamDone = false;

	CoffeePanel(JFrame frame) {
		this.frame = frame;
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
		setBackground(Color.WHITE);

		grinder = new Grinder(500, 380);
		espressoMachine = new EspressoMachine(790, 365);
		portafilter = new Portafilter(190, 590);
		cup = new Cup(790, 461);
		pitcher = new Pitcher(300, COUNTER_Y - 30);
		mug = new Mug(W_WIDTH / 2, COUNTER_Y + 90);

		try {
			startBg = ImageIO.read(getClass().getResourceAsStream("/assets/Start-screen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseMotionListener());

		timer = new Timer(30, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (state == 0) {
			drawWelcomeScreen(g2);
		} else if (state == 11) {
			drawEndScreen(g2);
		} else {
			drawMainScene(g2);
		}
	}

	private void drawWelcomeScreen(Graphics2D g2) {
		if (startBg != null) {
			g2.drawImage(startBg, 0, 0, W_WIDTH, W_HEIGHT, null);
		} else {
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, W_WIDTH, W_HEIGHT);
		}

		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Georgia", Font.BOLD, 54));
		drawCentered(g2, "Diana's Coffee Shop", W_WIDTH / 2, 270);

		g2.setFont(new Font("Georgia", Font.ITALIC, 18));
		drawCentered(g2, "Grind, brew, steam — follow the steps to craft your own latte.", W_WIDTH / 2, 320);

		int bw = 180, bh = 52;
		int bx = W_WIDTH / 2 - bw / 2, by = 420;
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(bx, by, bw, bh);
		g2.setStroke(new BasicStroke(1));
		g2.setFont(new Font("Georgia", Font.BOLD, 24));
		drawCentered(g2, "Start", W_WIDTH / 2, by + 36);

		latteArt.drawLatteArt(g2, 870, 510, 110);
	}

	private void drawEndScreen(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, W_WIDTH, W_HEIGHT);

		// completed mug with steamed milk and latte art
		int mugCx = W_WIDTH / 2;
		int mugCy = 165;
		int mugR = 65;
		g2.setColor(Color.WHITE);
		g2.fillOval(mugCx - mugR, mugCy - mugR, mugR * 2, mugR * 2);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(mugCx - mugR, mugCy - mugR, mugR * 2, mugR * 2);
		g2.setStroke(new BasicStroke(1));
		latteArt.drawLatteArt(g2, mugCx, mugCy - 20, 43);

		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Georgia", Font.BOLD, 54));
		drawCentered(g2, "Your latte is ready!", W_WIDTH / 2, 310);

		int bw = 180, bh = 52;
		int bx = W_WIDTH / 2 - bw / 2, by = 420;
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(bx, by, bw, bh);
		g2.setStroke(new BasicStroke(1));
		g2.setFont(new Font("Georgia", Font.BOLD, 24));
		drawCentered(g2, "Restart", W_WIDTH / 2, by + 36);
	}

	private void drawMainScene(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, W_WIDTH, W_HEIGHT);

		// counter line
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(0, COUNTER_Y, W_WIDTH, COUNTER_Y);
		g2.setStroke(new BasicStroke(1));

		grinder.draw(g2);
		espressoMachine.draw(g2);
		if (state < 10) {
			cup.draw(g2);
		}
		portafilter.draw(g2);

		if (state >= 6) {
			pitcher.draw(g2);
		}
		if (state == 7 && steamEffect != null) {
			steamEffect.draw(g2);
		}
		if (state == 9 || state == 10) {
			mug.draw(g2);
		}

		drawInstructionBox(g2, getInstructionText(state));
	}

	private String getInstructionText(int s) {
		switch (s) {
			case 1: return "Drag the portafilter to the grinder";
			case 2: return "Grinding coffee...";
			case 3: return "Drag the portafilter to the espresso machine";
			case 4: return "Choose the number of shots and press the button";
			case 5: return "Brewing...";
			case 6: return "Drag the pitcher to the steam wand on the espresso machine";
			case 7: return "Steaming milk...";
			case 8: return "Place the pitcher on the table";
			case 9: return "Pour the espresso into the mug";
			case 10: return "Pour the steamed milk into the mug";
			default: return "";
		}
	}

	private void drawInstructionBox(Graphics2D g2, String text) {
		if (text.isEmpty()) return;

		g2.setFont(new Font("Georgia", Font.PLAIN, 18));
		FontMetrics fm = g2.getFontMetrics();

		int padding = 14;
		int boxX = 24;
		int boxY = 24;
		int boxW = fm.stringWidth(text) + padding * 2;
		int boxH = fm.getAscent() + fm.getDescent() + padding * 2;

		g2.setColor(Color.WHITE);
		g2.fillRect(boxX, boxY, boxW, boxH);

		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(boxX, boxY, boxW, boxH);
		g2.setStroke(new BasicStroke(1));

		g2.drawString(text, boxX + padding, boxY + padding + fm.getAscent());
	}

	private void drawCentered(Graphics2D g2, String s, int cx, int cy) {
		FontMetrics fm = g2.getFontMetrics();
		g2.drawString(s, cx - fm.stringWidth(s) / 2, cy);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// grinds appear in portafilter
		if (state == 2 && grinder.isGrindDone()) {
			portafilter.setHasGrinds(true);
			state = 3;
		}

		// shot timer finished
		if (state == 5 && espressoMachine.isShotReady()) {
			cup.addEspresso();
			state = 6;
		}

		// steam animation
		if (state == 7 && steamEffect != null) {
			steamEffect.update();
		}

		// steam countdown
		if (state == 7 && steamDone) {
			state = 8;
		}

		repaint();
	}

	private class MyMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			// portafilter is draggable to grinder and to machine
			if ((state == 1 || state == 3) && portafilter.clicked(e.getX(), e.getY())) {
				portafilter.setDragging(true);
			}
			// cup is draggable to mug in state 9
			if (state == 9 && cup.clicked(e.getX(), e.getY())) {
				cup.setDragging(true);
			}
			// pitcher is draggable to wand, back to table, and to mug
			if ((state == 6 || state == 8 || state == 10) && pitcher.clicked(e.getX(), e.getY())) {
				pitcher.setDragging(true);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (state == 1 && portafilter.isDragging() && portafilter.hitGrinder(grinder)) {
				portafilter.setXPos(grinder.getDockX());
				portafilter.setYPos(grinder.getDockY());
				grinder.startGrinding();
				state = 2;
			}

			if (state == 3 && portafilter.isDragging() && portafilter.hit(espressoMachine)) {
				portafilter.setXPos(espressoMachine.getDockX());
				portafilter.setYPos(espressoMachine.getDockY());
				espressoMachine.setButtonsActive(true);
				state = 4;
			}

			portafilter.setDragging(false);

			// cup poured into mug
			if (state == 9 && cup.isDragging() && cup.hitMug(mug)) {
				mug.addEspresso();
				state = 10;
			}
			cup.setDragging(false);

			// pitcher docks at steam wand
			if (state == 6 && pitcher.isDragging() && pitcher.hitSteamWand(espressoMachine)) {
				pitcher.setXPos(espressoMachine.getSteamWandX());
				pitcher.setYPos(espressoMachine.getSteamWandY() + 25);
				steamEffect = new SteamEffect(
					(float)(pitcher.getXPos() - 12),
					(float)(pitcher.getYPos() - 58)
				);
				steamDone = false;
				steamCountdown = new Timer(3000, new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						steamDone = true;
					}
				});
				steamCountdown.setRepeats(false);
				steamCountdown.start();
				state = 7;
			}

			// pitcher dropped back on the table
			if (state == 8 && pitcher.isDragging() && pitcher.hitTable(COUNTER_Y)) {
				state = 9;
			}

			// pitcher poured into mug
			if (state == 10 && pitcher.isDragging() && pitcher.hitMug(mug)) {
				mug.addLatte();
				state = 11;
			}

			pitcher.setDragging(false);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			double x = e.getX(), y = e.getY();

			if (state == 0 && isStartClicked(x, y)) {
				state = 1;
			}

			if (state == 11 && isRestartClicked(x, y)) {
				timer.stop();
				frame.dispose();
				new CoffeeApp("Diana's Coffee Shop");
			}

			if (state == 4) {
				int shot = espressoMachine.shotButtonClicked(x, y);
				if (shot > 0) {
					espressoMachine.setShotCount(shot);
					espressoMachine.startPulling();
					state = 5;
				}
			}
		}
	}

	private class MyMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			if (portafilter.isDragging()) {
				portafilter.setXPos(mouseX);
				portafilter.setYPos(mouseY);
			}
			if (cup.isDragging()) {
				cup.setXPos(mouseX);
				cup.setYPos(mouseY);
			}
			if (pitcher.isDragging()) {
				pitcher.setXPos(mouseX);
				pitcher.setYPos(mouseY);
			}
			repaint();
		}
	}

	private boolean isStartClicked(double x, double y) {
		int bw = 180, bh = 52;
		int bx = W_WIDTH / 2 - bw / 2, by = 420;
		return x >= bx && x <= bx + bw && y >= by && y <= by + bh;
	}

	private boolean isRestartClicked(double x, double y) {
		int bw = 180, bh = 52;
		int bx = W_WIDTH / 2 - bw / 2, by = 420;
		return x >= bx && x <= bx + bw && y >= by && y <= by + bh;
	}
}
