package app;

import javax.swing.JFrame;

public class CoffeeApp extends JFrame {
	private static final long serialVersionUID = 1L;

	public CoffeeApp(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CoffeePanel panel = new CoffeePanel(this);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CoffeeApp("Diana's Coffee Shop");
	}
}
