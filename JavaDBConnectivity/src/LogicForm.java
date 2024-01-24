import java.awt.Color;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LogicForm {
	private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

	public DefaultTableModel getDefaultTableModel() {
		String[] columnNames = { "Id", "Name", "Gender", "Phone", "Email", "Desgination", "Salary" };
		DefaultTableModel defaultTableModel = new DefaultTableModel(columnNames, 0);
		return defaultTableModel;
	}

	public List<EmployeeEntity> view(JTable jTable) throws SQLException, ClassNotFoundException {
		jTable.setModel(getDefaultTableModel());
		List<EmployeeEntity> listEmployees = employeeServiceImpl.viewEmployees();
		for (EmployeeEntity employeeEntity : listEmployees) {
			Object[] row = { employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getGender(),
					employeeEntity.getPhone(), employeeEntity.getEmail(), employeeEntity.getDesignation(),
					employeeEntity.getSalary() };
			((DefaultTableModel) jTable.getModel()).addRow(row);
		}
		jTable.setRequestFocusEnabled(true);
		jTable.setSelectionBackground(Color.yellow);
		jTable.getSelectionModel().setSelectionInterval(0, 0);
		jTable.requestFocus();
		return listEmployees;
	}

	public void saveEmployee(EmployeeEntity employeeEntity, boolean blnEdit)
			throws ClassNotFoundException, SQLException {
		if (!blnEdit)
			employeeServiceImpl.saveEmployee(employeeEntity);
		else
			employeeServiceImpl.updateEmployee(employeeEntity);
	}

	public void deleteEmployee(int employeeID) throws ClassNotFoundException, SQLException {
		employeeServiceImpl.deleteEmployee(employeeID);
	}
}
