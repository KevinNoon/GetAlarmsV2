package View;

import javax.swing.SwingUtilities;

public class MainGUI {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}
