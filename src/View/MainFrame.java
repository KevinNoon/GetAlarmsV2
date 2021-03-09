package View;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumn;

import Model.AlarmFullList;

public class  MainFrame extends JFrame implements ActionListener, PropertyChangeListener {
	String SitePath = "";
	String SavePath = "";
	private JButton applyButton;
	private JButton saveAllButton;
	private JButton saveSelectButton;
	private Paths pathsPanel;
	private AlarmTable alarmPanel;
	private JProgressBar PBar;
	private JLabel NoOfAlarms;
	public int progress;
	private static final long serialVersionUID = 1L;
	static ArrayList<AlarmFullList> fal;
	private Task task;

	class Task extends SwingWorker<Void, Void> {
		public Void doInBackground() {
			{
				progress = 0;
				setProgress(0);
				fal = Model.GetAlarmFullList.GetAlarmFull(pathsPanel.cylonDirInput.getText());
				while (alarmPanel.model.getRowCount() > 0)
					alarmPanel.model.removeRow(0);

				for (int n = 0; n < fal.size(); n++) {
					progress = (n * 100 / fal.size());
					setProgress(Math.min(progress, 100));
					try {
						Thread.sleep(1);
					} catch (InterruptedException ignore) {
					} 
					String CommsNo = new String();
					CommsNo = fal.get(n).getCommCtrlNo();
					CommsNo = "00" + CommsNo;
					CommsNo = CommsNo.substring(CommsNo.length()-3);
					
					
					String FieldNo = new String();
					FieldNo=fal.get(n).getFieldCtrlNo();
					FieldNo = "00" + FieldNo;
					FieldNo = FieldNo.substring(FieldNo.length()-3);
						
					String Piroirty = new String();
					Piroirty = fal.get(n).getPiroirty();
					Piroirty = "00" + FieldNo;
					Piroirty = Piroirty.substring(Piroirty.length()-3);		
					
					alarmPanel.model.insertRow(0,
							new Object[] { CommsNo, fal.get(n).getCommCtrlName(),
									FieldNo, fal.get(n).getFieldCtrlName(), fal.get(n).getBlockNo(),
									fal.get(n).getInPointDNo(), fal.get(n).getInPointDName(),
									fal.get(n).getInPointANo(), fal.get(n).getInPointAName(), fal.get(n).getAlarmNo(),
									fal.get(n).getActiveText(), fal.get(n).getInActiveText(), Piroirty,
									fal.get(n).getName() });
				}
			}
			return null;
		}

		public void done() {
			Toolkit.getDefaultToolkit().beep();
			setCursor(null);
			applyButton.setEnabled(true);
			saveAllButton.setEnabled(true);
			saveSelectButton.setEnabled(true);
			PBar.setValue(100);
			NoOfAlarms.setText("Total Alarms = " + fal.size());
			pathsPanel.setPreference(true);
		}
	}

	public void actionPerformed(ActionEvent evt) {
		applyButton.setEnabled(false);
		saveAllButton.setEnabled(false);
		saveSelectButton.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// Instances of javax.swing.SwingWorker are not reusuable, so
		// we create new instances as needed.
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			PBar.setValue(progress);
		}
	}	
		
	MainFrame() {
		super("Alarm List");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("AlarmViewer.png")));
		this.setSize(800, 600);
		MainMenu menuBar = new MainMenu();
		this.setJMenuBar(menuBar);
		Container cp = getContentPane();
		// Create the panel for the paths and add the items
		GridBagLayout mainLayout = new GridBagLayout();
		GridBagConstraints mgbc = new GridBagConstraints();
		this.setLayout(mainLayout);
		
		mgbc.gridx = 0;
		mgbc.gridy = 0;
		mgbc.weightx = 100;
		mgbc.fill = 1;
		mgbc.weighty = 1;
		pathsPanel = new Paths();
		mainLayout.setConstraints(pathsPanel, mgbc);
		
		cp.add(pathsPanel);
		mgbc.gridx = 0;
		mgbc.gridy = 1;
		mgbc.weighty = 10;
		alarmPanel = new AlarmTable();
		mainLayout.setConstraints(alarmPanel, mgbc);
		cp.add(alarmPanel);

		mgbc.gridx = 0;
		mgbc.gridy = 3;
		mgbc.weighty = 1;
		JPanel ControlButtons = new JPanel();
		
		Dimension bd = new Dimension();
		bd.setSize(120, 26);
		applyButton = new JButton("Run");
		applyButton.setPreferredSize(bd);
		ControlButtons.add(applyButton);
		saveAllButton = new JButton("Save All");
		saveAllButton.setPreferredSize(bd);
		saveAllButton.setEnabled(false);
		ControlButtons.add(saveAllButton);
		saveSelectButton = new JButton("Save Selected");
		saveSelectButton.setPreferredSize(bd);
		saveSelectButton.setEnabled(false);
		ControlButtons.add(saveSelectButton);
		PBar = new JProgressBar();
		ControlButtons.add(PBar);
		NoOfAlarms = new JLabel();
		NoOfAlarms.setText("Total Alarms = 0");
		ControlButtons.add(NoOfAlarms);

		applyButton.addActionListener(this);

		saveAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enumeration<?> etc;
				etc = alarmPanel.columnModel.getColumns(false);
				WriteToFile(pathsPanel.saveDirInput.getText(), fal, etc);
			}
		});

		saveSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enumeration<?> etc;
				etc = alarmPanel.columnModel.getColumns(true);
				WriteToFile(pathsPanel.saveDirInput.getText(), fal, etc);
			}
		});

		// ControlButtons controlButtons = new ControlButtons();
		mainLayout.setConstraints(ControlButtons, mgbc);
		cp.add(ControlButtons);
		// ensure view is up to date!
		pack();
		setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void WriteToFile(String SavePath, ArrayList<AlarmFullList> fal, Enumeration<?> etc) {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(SavePath + "\\Alarms.csv"));
			String AlarmPrintString = "";
			while (etc.hasMoreElements()) {
				TableColumn tc = new TableColumn();
				tc = (TableColumn) etc.nextElement();
				AlarmPrintString = AlarmPrintString + tc.getHeaderValue();
				if (etc.hasMoreElements())
					AlarmPrintString = AlarmPrintString + ",";
			}
			out.write(AlarmPrintString);
			out.newLine();

			String APS = AlarmPrintString;
			for (int n = 0; n < fal.size(); n++) {
				AlarmPrintString = "";

				if (APS.contains("Comms No")) {
					AlarmPrintString = fal.get(n).getCommCtrlNo() + ",";
				}
				if (APS.contains("Comms Name")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getCommCtrlName() + ",";
				}
				if (APS.contains("Field No")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getFieldCtrlNo() + ",";
				}
				if (APS.contains("Field Name")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getFieldCtrlName() + ",";
				}
				if (APS.contains("Block No")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getBlockNo() + ",";
				}
				if (APS.contains("Dig Point No")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getInPointDNo() + ",";
				}
				if (APS.contains("Dig Point Name")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getInPointDName() + ",";
				}
				if (APS.contains("Ang Point No")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getInPointANo() + ",";
				}
				if (APS.contains("Ang Point Name")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getInPointAName() + ",";
				}
				if (APS.contains("Number")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getAlarmNo() + ",";
				}
				if (APS.contains("Active")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getActiveText() + ",";
				}
				if (APS.contains("InActive")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getInActiveText() + ",";
				}
				if (APS.contains("Priority")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getPiroirty() + ",";
				}
				if (APS.contains("Name")) {
					AlarmPrintString = AlarmPrintString + fal.get(n).getName() + ",";
				}
				out.write(AlarmPrintString);
				out.newLine();
			}
			JOptionPane.showMessageDialog(null, "File Saved OK", "File Save", JOptionPane.INFORMATION_MESSAGE);
			out.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Please enter a vaild path!", "Invaild Path MF",
					JOptionPane.WARNING_MESSAGE);
		}
		pathsPanel.setPreference(true);
	}
}
