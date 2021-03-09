package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Paths extends JPanel {


	 private Preferences prefs;

	
	public JTextField cylonDirInput;
	public JTextField saveDirInput;
	private static final long serialVersionUID = 1L;

	public Paths() {
		JLabel cylonDirectory = new JLabel();
		cylonDirectory.setText("Site Directory:-");
	    cylonDirInput = new JTextField();

		JLabel saveDirectory = new JLabel();
		saveDirectory.setText("Save Directory:-");
	    saveDirInput = new JTextField();

		Dimension dir = new Dimension();
		dir.width = 300;
		dir.height = cylonDirInput.getPreferredSize().height;
		Dimension btn = new Dimension();
		btn.width = 40;
		btn.height = cylonDirInput.getPreferredSize().height;
		cylonDirInput.setPreferredSize(dir);
		saveDirInput.setPreferredSize(dir);
		
		setPreference(false);

		// Create a file chooser for the buttons to select directories
		JFileChooser fileSelect = new JFileChooser();
		fileSelect.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileSelect.setCurrentDirectory(new File("C:\\"));
		// Create buttons to select directories for paths
		JButton cylonDirButton = new JButton("...");
		cylonDirButton.setPreferredSize(btn);
		cylonDirButton.addActionListener(e -> {
			fileSelect.setCurrentDirectory(new File(cylonDirInput.getText()));
			int openChoose = fileSelect.showOpenDialog(null);
			if (openChoose == JFileChooser.APPROVE_OPTION) {
				cylonDirInput.setText(fileSelect.getSelectedFile().toString());
			}
			;
		});
		JButton saveDirButton = new JButton("...");
		saveDirButton.setPreferredSize(btn);
		saveDirButton.addActionListener(e -> {
			fileSelect.setCurrentDirectory(new File(saveDirInput.getText()));
			int openChoose = fileSelect.showOpenDialog(null);
			if (openChoose == JFileChooser.APPROVE_OPTION) {
				saveDirInput.setText(fileSelect.getSelectedFile().toString());
			}
			;
		});

		this.setBorder(new TitledBorder(new EtchedBorder(), "Paths"));
		GridBagLayout pathLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(pathLayout);

		gbc.insets = new Insets(10, 0, 10, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 0.5;
		gbc.weighty = 10;
		pathLayout.setConstraints(cylonDirectory, gbc);
		this.add(cylonDirectory);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 10.0;
		pathLayout.setConstraints(cylonDirInput, gbc);
		this.add(cylonDirInput);

		gbc.gridx = 2;
		gbc.weightx = 100;
		pathLayout.setConstraints(cylonDirButton, gbc);
		this.add(cylonDirButton);

		// Add the objects for the Save directory
		gbc.insets = new Insets(0, 0, 10, 5);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 0.5;
		pathLayout.setConstraints(saveDirectory, gbc);
		this.add(saveDirectory);

		gbc.gridx = 1;
		gbc.weightx = 10;
		gbc.anchor = GridBagConstraints.WEST;
		pathLayout.setConstraints(saveDirInput, gbc);
		this.add(saveDirInput);

		gbc.gridx = 2;
		gbc.weightx = 100;
		pathLayout.setConstraints(saveDirButton, gbc);
		this.add(saveDirButton);
	}
	


	  public void setPreference(Boolean set) {
	    prefs = Preferences.userRoot().node(this.getClass().getName());
	    String SiteDir = "SiteDir"; String SaveDir = "SaveDir";
	    if (!set){
	    cylonDirInput.setText(prefs.get(SiteDir, ""));
	    saveDirInput.setText(prefs.get(SaveDir, ""));
	    } else{
	    prefs.put(SiteDir, cylonDirInput.getText());
	    prefs.put(SaveDir, saveDirInput.getText());
	    }
	  }
}
