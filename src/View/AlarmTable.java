package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class AlarmTable extends JPanel {

	public JTable table;
	public DefaultTableModel model;
	public XTableColumnModel columnModel = new XTableColumnModel();
	private static final long serialVersionUID = 1L;

	AlarmTable() {

		model = new DefaultTableModel(new Object[] { "Comms No", "Comms Name", "Field No", "Field Name", "Block No",
				"Dig Point No", "Dig Point Name", "Ang Point No", "Ang Point Name", "Number", "Active", "InActive",
				"Priority", "Name" }, 0);
		table = new JTable(model);
		table.setColumnModel(columnModel);
		table.createDefaultColumnsFromModel();
		table.setRowSorter(new TableRowSorter<DefaultTableModel>(model));
		JScrollPane tableScrollPane = new JScrollPane(table);

		JPanel Fields = new JPanel();
		Fields.setBorder(new TitledBorder(new EtchedBorder(), "Fields"));
		GridBagLayout fieldsLayout = new GridBagLayout();
		GridBagConstraints fgbc = new GridBagConstraints();
		Fields.setLayout(fieldsLayout);
		{
			JCheckBox fieldCommsNo = new JCheckBox("Comms No");
			fieldCommsNo.setName("0");
			fieldCommsNo.setSelected(false);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldCommsNo.getName())));
			columnModel.setColumnVisible(column, (fieldCommsNo.isSelected()));
			fieldCommsNo.addActionListener(checkedActionListener);
			fgbc.gridx = 0;
			fgbc.gridy = 0;
			fgbc.weightx = 100;
			fgbc.anchor = GridBagConstraints.WEST;
			fieldsLayout.setConstraints(fieldCommsNo, fgbc);
			Fields.add(fieldCommsNo);
		}
		{
			JCheckBox fieldCommsName = new JCheckBox("Comms Name");
			fieldCommsName.setName("1");
			fieldCommsName.setSelected(true);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldCommsName.getName())));
			columnModel.setColumnVisible(column, (fieldCommsName.isSelected()));
			fieldCommsName.addActionListener(checkedActionListener);
			fgbc.gridx = 1;
			fieldsLayout.setConstraints(fieldCommsName, fgbc);
			Fields.add(fieldCommsName);
		}
		{
			JCheckBox fieldFieldNo = new JCheckBox("Field No");
			fieldFieldNo.addActionListener(checkedActionListener);
			fieldFieldNo.setName("2");
			fieldFieldNo.setSelected(false);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldFieldNo.getName())));
			columnModel.setColumnVisible(column, (fieldFieldNo.isSelected()));
			fgbc.gridx = 2;
			fieldsLayout.setConstraints(fieldFieldNo, fgbc);
			Fields.add(fieldFieldNo);
		}
		{
			JCheckBox fieldFieldName = new JCheckBox("Field Name");
			fieldFieldName.addActionListener(checkedActionListener);
			fieldFieldName.setName("3");
			fieldFieldName.setSelected(true);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldFieldName.getName())));
			columnModel.setColumnVisible(column, (fieldFieldName.isSelected()));
			fgbc.gridx = 3;
			fieldsLayout.setConstraints(fieldFieldName, fgbc);
			Fields.add(fieldFieldName);
		}
		{
			JCheckBox fieldBlockNo = new JCheckBox("Block No");
			fieldBlockNo.addActionListener(checkedActionListener);
			fieldBlockNo.setName("4");
			fieldBlockNo.setSelected(false);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldBlockNo.getName())));
			columnModel.setColumnVisible(column, (fieldBlockNo.isSelected()));
			fgbc.gridx = 4;
			fieldsLayout.setConstraints(fieldBlockNo, fgbc);
			Fields.add(fieldBlockNo);
		}
		{
			JCheckBox fieldDigPointNo = new JCheckBox("Dig Point No");
			fieldDigPointNo.addActionListener(checkedActionListener);
			fieldDigPointNo.setName("5");
			fieldDigPointNo.setSelected(false);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldDigPointNo.getName())));
			columnModel.setColumnVisible(column, (fieldDigPointNo.isSelected()));
			fgbc.gridx = 5;
			fieldsLayout.setConstraints(fieldDigPointNo, fgbc);
			Fields.add(fieldDigPointNo);
		}
		{
			JCheckBox fieldDigPointName = new JCheckBox("Dig Point Name");
			fieldDigPointName.addActionListener(checkedActionListener);
			fieldDigPointName.setName("6");
			fieldDigPointName.setSelected(true);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldDigPointName.getName())));
			columnModel.setColumnVisible(column, (fieldDigPointName.isSelected()));
			fgbc.gridx = 6;
			fieldsLayout.setConstraints(fieldDigPointName, fgbc);
			Fields.add(fieldDigPointName);
		}
		{
			JCheckBox fieldAngPointNo = new JCheckBox("Ang Point No");
			fieldAngPointNo.addActionListener(checkedActionListener);
			fieldAngPointNo.setName("7");
			fieldAngPointNo.setSelected(false);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldAngPointNo.getName())));
			columnModel.setColumnVisible(column, (fieldAngPointNo.isSelected()));
			fgbc.gridx = 0;
			fgbc.gridy = 1;
			fieldsLayout.setConstraints(fieldAngPointNo, fgbc);
			Fields.add(fieldAngPointNo);
		}
		{
			JCheckBox fieldAngPointName = new JCheckBox("Ang Point Name");
			fieldAngPointName.addActionListener(checkedActionListener);
			fieldAngPointName.setName("8");
			fieldAngPointName.setSelected(true);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldAngPointName.getName())));
			columnModel.setColumnVisible(column, (fieldAngPointName.isSelected()));
			fgbc.gridx = 1;
			fieldsLayout.setConstraints(fieldAngPointName, fgbc);
			Fields.add(fieldAngPointName);
		}
		{
			JCheckBox fieldNumberNo = new JCheckBox("Alarm Number");
			fieldNumberNo.addActionListener(checkedActionListener);
			fieldNumberNo.setName("9");
			fieldNumberNo.setSelected(false);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldNumberNo.getName())));
			columnModel.setColumnVisible(column, (fieldNumberNo.isSelected()));
			fgbc.gridx = 2;
			fieldsLayout.setConstraints(fieldNumberNo, fgbc);
			Fields.add(fieldNumberNo);
		}
		{
			JCheckBox fieldActiveString = new JCheckBox("Active String");
			fieldActiveString.addActionListener(checkedActionListener);
			fieldActiveString.setName("10");
			fieldActiveString.setSelected(true);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldActiveString.getName())));
			columnModel.setColumnVisible(column, (fieldActiveString.isSelected()));
			fgbc.gridx = 3;
			fieldsLayout.setConstraints(fieldActiveString, fgbc);
			Fields.add(fieldActiveString);
		}
		{
			JCheckBox fieldInActiveString = new JCheckBox("InActive String");
			fieldInActiveString.addActionListener(checkedActionListener);
			fieldInActiveString.setName("11");
			fieldInActiveString.setSelected(true);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldInActiveString.getName())));
			columnModel.setColumnVisible(column, (fieldInActiveString.isSelected()));
			fgbc.gridx = 4;
			fieldsLayout.setConstraints(fieldInActiveString, fgbc);
			Fields.add(fieldInActiveString);
		}
		{
			JCheckBox fieldPirority = new JCheckBox("Priority");
			fieldPirority.addActionListener(checkedActionListener);
			fieldPirority.setName("12");
			fieldPirority.setSelected(true);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldPirority.getName())));
			columnModel.setColumnVisible(column, (fieldPirority.isSelected()));
			fgbc.gridx = 5;
			fieldsLayout.setConstraints(fieldPirority, fgbc);
			Fields.add(fieldPirority);
		}
		{
			JCheckBox fieldName = new JCheckBox("Name");
			fieldName.addActionListener(checkedActionListener);
			fieldName.setName("13");
			fieldName.setSelected(true);
			TableColumn column = columnModel.getColumnByModelIndex(Integer.parseInt((fieldName.getName())));
			columnModel.setColumnVisible(column, (fieldName.isSelected()));
			fgbc.gridx = 6;
			fieldsLayout.setConstraints(fieldName, fgbc);
			Fields.add(fieldName);
		}

		GridBagLayout mainLayout = new GridBagLayout();
		GridBagConstraints mgbc = new GridBagConstraints();
		this.setLayout(mainLayout);
		mgbc.gridx = 0;
		mgbc.gridy = 0;
		mgbc.weightx = 100;
		mgbc.fill = 1;
		mgbc.weighty = 10;
		mainLayout.setConstraints(Fields, mgbc);
		this.add(Fields);

		mgbc.gridy = 1;
		mgbc.weighty = 100;
		mainLayout.setConstraints(tableScrollPane, mgbc);
		this.add(tableScrollPane);
	}

	ActionListener checkedActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			TableColumn column = columnModel
					.getColumnByModelIndex(Integer.parseInt(((JCheckBox) (arg0.getSource())).getName()));
			columnModel.setColumnVisible(column, ((JCheckBox) (arg0.getSource())).isSelected());
		}
	};
}
