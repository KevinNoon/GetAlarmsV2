package View;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	MainMenu(){
		JMenu fileMenu = new JMenu("File");
	    this.add(fileMenu);		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		// Add menu items and a separator to the menu
		exitMenuItem.addActionListener(e -> System.exit(0));

		// Set E as mnemonic for Exit menu and Ctrl + E as its accelerator
		exitMenuItem.setMnemonic(KeyEvent.VK_E);
		
		KeyStroke cntrlEKey = KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK);
		exitMenuItem.setAccelerator(cntrlEKey);
		
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);	
	}
	

}
