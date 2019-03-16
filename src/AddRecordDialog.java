
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class AddRecordDialog extends JDialog implements ActionListener {
	JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;
	JButton save, cancel;
	EmployeeDetails parent;

	public AddRecordDialog(EmployeeDetails parent) {
		setTitle("Add Record");
		setModal(true);
		this.parent = parent;
		this.parent.setEnabled(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(dialogPane());
		setContentPane(scrollPane);
		
		getRootPane().setDefaultButton(save);
		
		setSize(500, 370);
		setLocation(350, 250);
		setVisible(true);
	}

	
	public Container dialogPane() {
		JPanel empDetails, buttonPanel;
		empDetails = new JPanel(new MigLayout());
		buttonPanel = new JPanel();
		JTextField field;

		empDetails.setBorder(BorderFactory.createTitledBorder("Employee Details"));

		empDetails.add(new JLabel("ID:"), ManageLayout.migLayout1);
		empDetails.add(idField = new JTextField(20), ManageLayout.migLayout2);
		idField.setEditable(false);
		

		empDetails.add(new JLabel("PPS Number:"), ManageLayout.migLayout1);
		empDetails.add(ppsField = new JTextField(20), ManageLayout.migLayout2);

		empDetails.add(new JLabel("Surname:"), ManageLayout.migLayout1);
		empDetails.add(surnameField = new JTextField(20), ManageLayout.migLayout2);

		empDetails.add(new JLabel("First Name:"), ManageLayout.migLayout1);
		empDetails.add(firstNameField = new JTextField(20), ManageLayout.migLayout2);

		empDetails.add(new JLabel("Gender:"), ManageLayout.migLayout1);
		empDetails.add(genderCombo = new JComboBox<String>(this.parent.gender), ManageLayout.migLayout2);

		empDetails.add(new JLabel("Department:"), ManageLayout.migLayout1);
		empDetails.add(departmentCombo = new JComboBox<String>(this.parent.department), ManageLayout.migLayout2);

		empDetails.add(new JLabel("Salary:"), ManageLayout.migLayout1);
		empDetails.add(salaryField = new JTextField(20), ManageLayout.migLayout2);

		empDetails.add(new JLabel("Full Time:"), ManageLayout.migLayout1);
		empDetails.add(fullTimeCombo = new JComboBox<String>(this.parent.fullTime), ManageLayout.migLayout2);

		buttonPanel.add(save = new JButton("Save"));
		save.addActionListener(this);
		save.requestFocus();
		buttonPanel.add(cancel = new JButton("Cancel"));
		cancel.addActionListener(this);

		empDetails.add(buttonPanel, ManageLayout.migLayout3);
		for (int i = 0; i < empDetails.getComponentCount(); i++) {
			empDetails.getComponent(i).setFont(this.parent.font1);
			if (empDetails.getComponent(i) instanceof JComboBox) {
				empDetails.getComponent(i).setBackground(Color.WHITE);
			}
			else if(empDetails.getComponent(i) instanceof JTextField){
				field = (JTextField) empDetails.getComponent(i);
				if(field == ppsField)
					field.setDocument(new JTextFieldLimit(9));
				else
				field.setDocument(new JTextFieldLimit(20));
			}
		}
		idField.setText(Integer.toString(this.parent.getNextFreeId()));
		return empDetails;
	}

	public void addRecord() {
		boolean fullTime = false;
		Employee theEmployee;

		if (((String) fullTimeCombo.getSelectedItem()).equalsIgnoreCase("Yes"))
			fullTime = true;
		theEmployee = new Employee(Integer.parseInt(idField.getText()), ppsField.getText().toUpperCase(), surnameField.getText().toUpperCase(),
				firstNameField.getText().toUpperCase(), genderCombo.getSelectedItem().toString().charAt(0),
				departmentCombo.getSelectedItem().toString(), Double.parseDouble(salaryField.getText()), fullTime);
		this.parent.currentEmployee = theEmployee;
		this.parent.addRecord(theEmployee);
		this.parent.displayRecords(theEmployee);
	}

	public boolean checkInput() {
		boolean valid = true;
		if (ppsField.getText().equals("")) {
			ppsField.setBackground(Colors.red);
			valid = false;
		}
		if (this.parent.correctPps(this.ppsField.getText().trim(), -1)) {
			ppsField.setBackground(Colors.red);
			valid = false;
		}
		if (surnameField.getText().isEmpty()) {
			surnameField.setBackground(Colors.red);
			valid = false;
		}
		if (firstNameField.getText().isEmpty()) {
			firstNameField.setBackground(Colors.red);
			valid = false;
		}
		if (genderCombo.getSelectedIndex() == 0) {
			genderCombo.setBackground(Colors.red);
			valid = false;
		}
		if (departmentCombo.getSelectedIndex() == 0) {
			departmentCombo.setBackground(Colors.red);
			valid = false;
		}
		try {
			Double.parseDouble(salaryField.getText());
			if (Double.parseDouble(salaryField.getText()) < 0) {
				salaryField.setBackground(Colors.red);
				valid = false;
			}
		}
		catch (NumberFormatException num) {
			salaryField.setBackground(Colors.red);
			valid = false;
		}
		if (fullTimeCombo.getSelectedIndex() == 0) {
			fullTimeCombo.setBackground(Colors.red);
			valid = false;
		}
		return valid;
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			if (checkInput()) {
				addRecord();
				dispose();
				this.parent.changesMade = true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Wrong values or format! Please check!");
			}
		}
		else if (e.getSource() == cancel)
			dispose();
	}
}