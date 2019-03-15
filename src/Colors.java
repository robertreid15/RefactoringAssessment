import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Colors {
	
	protected JTextField ppsField;
	protected JTextField surnameField;
	protected JTextField firstNameField;
	protected JTextField salaryField;
	protected JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;
	
	public Colors(JTextField ppsField, JTextField surnameField, JTextField firstNameField, JTextField salaryField,
			JComboBox<String> genderCombo, JComboBox<String> departmentCombo, JComboBox<String> fullTimeCombo) {
		this.ppsField = ppsField;
		this.surnameField = surnameField;
		this.firstNameField = firstNameField;
		this.salaryField = salaryField;
		this.genderCombo = genderCombo;
		this.departmentCombo = departmentCombo;
		this.fullTimeCombo = fullTimeCombo;
		
	}

	static Color red = new Color(255, 150, 150);
	static Color white = Color.WHITE;
	static Color foreground = new Color(65, 65, 65);
	
	public void setToWhite() {
		ppsField.setBackground(white);
		surnameField.setBackground(white);
		firstNameField.setBackground(white);
		salaryField.setBackground(white);
		genderCombo.setBackground(white);
		departmentCombo.setBackground(white);
		fullTimeCombo.setBackground(white);
	}
	
	public void setToWhite2() {
		ppsField.setBackground(UIManager.getColor("TextField.background"));
		surnameField.setBackground(UIManager.getColor("TextField.background"));
		firstNameField.setBackground(UIManager.getColor("TextField.background"));
		salaryField.setBackground(UIManager.getColor("TextField.background"));
		genderCombo.setBackground(UIManager.getColor("TextField.background"));
		departmentCombo.setBackground(UIManager.getColor("TextField.background"));
		fullTimeCombo.setBackground(UIManager.getColor("TextField.background"));
	}
}