import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl {
	private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

	public List<EmployeeEntity> viewEmployees() throws SQLException, ClassNotFoundException {
		return employeeDAO.viewEmployees();
	}

	public void saveEmployee(EmployeeEntity employeeEntity) throws ClassNotFoundException, SQLException {
		employeeDAO.saveEmployee(employeeEntity);
	}

	public void updateEmployee(EmployeeEntity employeeEntity) throws ClassNotFoundException, SQLException {
		employeeDAO.updateEmployee(employeeEntity);
	}

	public void deleteEmployee(int employeeID) throws ClassNotFoundException, SQLException {
		employeeDAO.deleteEmployee(employeeID);
	}
}
