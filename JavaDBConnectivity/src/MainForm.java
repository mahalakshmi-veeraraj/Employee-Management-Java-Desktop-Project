import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainForm {
	private JFrame mainFrame;
	private JTabbedPane mainTabbedPane;
	private JPanel entryPanel;
	private JPanel viewPanel;
	private JPanel entrySubPanel;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblGender;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblDesgination;
	private JLabel lblSalary;
	private JTextField txtId;
	private JTextField txtName;
	private JComboBox<String> cmbGender;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtDesgination;
	private JTextField txtSalary;
	private JButton butSave;
	private JButton butView;
	private JButton butClear;

	private JPanel viewSubPanel;
	private JTable jTable;
	private DefaultTableModel defaultTableModel;
	private JScrollPane jScrollPane;

	private LogicForm logicForm = new LogicForm();

	private List<EmployeeEntity> listViewEmployees;
	private boolean blnEdit = false;

	public MainForm() {
		mainFrame = new JFrame();
		mainFrame.setSize(700, 500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setTitle("Employee Management System");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainTabbedPane = new JTabbedPane();
		mainTabbedPane.setEnabled(false);

		entryPanel = new JPanel(new BorderLayout());
		entryPanel.add(getEntrySubPanel(), BorderLayout.CENTER);

		viewPanel = new JPanel();
		viewPanel.add(getViewPanel());

		mainTabbedPane.addTab("ENTRY", entryPanel);
		mainTabbedPane.addTab("VIEW", viewPanel);

		entryPanel.setVisible(true);
		viewPanel.setVisible(true);
		mainTabbedPane.setVisible(true);

		mainFrame.add(mainTabbedPane);
		addActionListeners();
		addKeyListener();
		mainFrame.setVisible(true);
	}

	private JPanel getEntrySubPanel() {
		int x = 30;
		int y = 10;
		int width = 200;
		int height = 25;
		int xAxixGap = 10;
		int yAxisGap = 25;

		entrySubPanel = new JPanel();
		entrySubPanel.setLayout(null);

		lblId = new JLabel("Id");
		lblId.setBounds(x, y, width, height);
		lblId.setVisible(false);

		txtId = new JTextField();
		txtId.setBounds(lblId.getX() + lblId.getWidth() + xAxixGap, y, width, height);
		txtId.setEditable(false);
		txtId.setVisible(false);

		y += height + yAxisGap;
		lblName = new JLabel("Name");
		lblName.setBounds(x, y, width, height);

		txtName = new JTextField();
		txtName.setBounds(lblName.getX() + lblName.getWidth() + xAxixGap, y, width, height);

		y += height + yAxisGap;
		lblGender = new JLabel("Gender");
		lblGender.setBounds(x, y, width, height);

		cmbGender = new JComboBox<String>();
		cmbGender.setBounds(lblGender.getX() + lblGender.getWidth() + xAxixGap, y, width, height);
		cmbGender.addItem("Female");
		cmbGender.addItem("Male");
		cmbGender.addItem("Others");

		y += height + yAxisGap;
		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(x, y, width, height);

		txtPhone = new JTextField();
		txtPhone.setBounds(lblPhone.getX() + lblPhone.getWidth() + xAxixGap, y, width, height);

		y += height + yAxisGap;
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(x, y, width, height);

		txtEmail = new JTextField();
		txtEmail.setBounds(lblEmail.getX() + lblEmail.getWidth() + xAxixGap, y, width, height);

		y += height + yAxisGap;
		lblDesgination = new JLabel("Designation");
		lblDesgination.setBounds(x, y, width, height);

		txtDesgination = new JTextField();
		txtDesgination.setBounds(lblDesgination.getX() + lblDesgination.getWidth() + xAxixGap, y, width, height);

		y += height + yAxisGap;
		lblSalary = new JLabel("Salary");
		lblSalary.setBounds(x, y, width, height);

		txtSalary = new JTextField();
		txtSalary.setBounds(lblSalary.getX() + lblSalary.getWidth() + xAxixGap, y, width, height);

		x += 20;
		width = 100;
		xAxixGap = 40;

		y += height + yAxisGap;
		butSave = new JButton("Save");
		butSave.setBounds(x, y, width, height);
		butSave.setMnemonic('S');

		butView = new JButton("View");
		butView.setBounds(butSave.getX() + butSave.getWidth() + xAxixGap, y, width, height);
		butView.setMnemonic('V');

		butClear = new JButton("Clear");
		butClear.setBounds(butView.getX() + butView.getWidth() + xAxixGap, y, width, height);
		butClear.setMnemonic('C');

		entrySubPanel.add(lblId);
		entrySubPanel.add(txtId);
		entrySubPanel.add(lblName);
		entrySubPanel.add(txtName);
		entrySubPanel.add(lblGender);
		entrySubPanel.add(cmbGender);
		entrySubPanel.add(lblPhone);
		entrySubPanel.add(txtPhone);
		entrySubPanel.add(lblEmail);
		entrySubPanel.add(txtEmail);
		entrySubPanel.add(lblDesgination);
		entrySubPanel.add(txtDesgination);
		entrySubPanel.add(lblSalary);
		entrySubPanel.add(txtSalary);
		entrySubPanel.add(butSave);
		entrySubPanel.add(butView);
		entrySubPanel.add(butClear);
		entrySubPanel.setVisible(true);
		return entrySubPanel;
	}

	private JPanel getViewPanel() {
		viewSubPanel = new JPanel();

		defaultTableModel = logicForm.getDefaultTableModel();
		jTable = new JTable(defaultTableModel);

		jScrollPane = new JScrollPane(jTable);
		jScrollPane.setVisible(true);

		viewSubPanel.add(jScrollPane);
		return viewSubPanel;
	}

	private void addActionListeners() {
		butView.addActionListener((ActionEvent e) -> {
			try {
				listViewEmployees = logicForm.view(jTable);
				mainTabbedPane.setSelectedIndex(1);
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		butSave.addActionListener((ActionEvent e) -> {
			try {
				EmployeeEntity employeeEntity = null;
				if (blnEdit) {
					employeeEntity = listViewEmployees.get(jTable.getSelectedRow());
				} else {
					employeeEntity = new EmployeeEntity();
				}
				prepareEmployeeEntity(employeeEntity);
				logicForm.saveEmployee(employeeEntity, blnEdit);
				String message = "Data Saved Successfully";
				JOptionPane.showMessageDialog(null, message);
				butView.doClick();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		butClear.addActionListener((ActionEvent e) -> {
			blnEdit = false;
			txtId.setText("");
			txtName.setText("");
			cmbGender.setSelectedIndex(0);
			txtPhone.setText("");
			txtEmail.setText("");
			txtDesgination.setText("");
			txtSalary.setText("");
			lblId.setVisible(false);
			txtId.setVisible(false);
			txtName.requestFocus();
		});
	}

	private void addKeyListener() {
		jTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					butClear.doClick();
					mainTabbedPane.setSelectedIndex(0);
				} else if (e.getKeyCode() == KeyEvent.VK_E) { // 'E' for Edit employees.
					int option = JOptionPane.showConfirmDialog(null, "Are you sure want to delete ?");
					if (option == JOptionPane.YES_OPTION)
						processEdit();
				} else if (e.getKeyCode() == KeyEvent.VK_D) { // 'D' for Delete employees.
					try {
						logicForm.deleteEmployee(listViewEmployees.get(jTable.getSelectedRow()).getId());
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void prepareEmployeeEntity(EmployeeEntity employeeEntity) {
		employeeEntity.setName(txtName.getText());
		employeeEntity.setGender(cmbGender.getSelectedItem().toString());
		employeeEntity.setPhone(txtPhone.getText());
		employeeEntity.setEmail(txtEmail.getText());
		employeeEntity.setDesignation(txtDesgination.getText());
		employeeEntity.setSalary(Double.valueOf(txtSalary.getText()));
	}

	private void processEdit() {
		blnEdit = true;
		EmployeeEntity employeeEntity = listViewEmployees.get(jTable.getSelectedRow());
		txtId.setText(String.valueOf(employeeEntity.getId()));
		txtName.setText(employeeEntity.getName());
		cmbGender.setSelectedItem(employeeEntity.getGender());
		txtPhone.setText(employeeEntity.getPhone());
		txtEmail.setText(employeeEntity.getEmail());
		txtDesgination.setText(employeeEntity.getDesignation());
		txtSalary.setText(String.valueOf(employeeEntity.getSalary()));
		mainTabbedPane.setSelectedIndex(0);
		lblId.setVisible(true);
		txtId.setVisible(true);
		txtName.requestFocus();
	}
}
