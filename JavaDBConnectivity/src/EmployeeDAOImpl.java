import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl {
	private ConnectionUtil connectionUtil = new ConnectionUtil();

	public List<EmployeeEntity> viewEmployees() throws SQLException, ClassNotFoundException {
		Connection connection = null;
		Statement statement = null;
		try {
			List<EmployeeEntity> listEmployees = new ArrayList<>();
			connection = connectionUtil.getConnection();
			statement = connection.createStatement();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			queryBuilder.append("* ");
			queryBuilder.append("FROM ");
			queryBuilder.append("employee;");
			ResultSet resultSet = statement.executeQuery(queryBuilder.toString());
			while (resultSet.next()) {
				EmployeeEntity employeeEntity = new EmployeeEntity();
				employeeEntity.setId(resultSet.getInt("id"));
				employeeEntity.setName(resultSet.getString("name"));
				employeeEntity.setGender(resultSet.getString("gender"));
				employeeEntity.setPhone(resultSet.getString("phone"));
				employeeEntity.setEmail(resultSet.getString("email"));
				employeeEntity.setDesignation(resultSet.getString("designation"));
				employeeEntity.setSalary(resultSet.getDouble("salary"));
				listEmployees.add(employeeEntity);
			}
			return listEmployees;
		} finally {
			if (null != statement) {
				statement.close();
			}
			connectionUtil.closeConnection();
		}
	}

	public int saveEmployee(EmployeeEntity employeeEntity) throws ClassNotFoundException, SQLException {
		int savedEmployeeId = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionUtil.getConnection();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO employee ");
			queryBuilder.append("(name, gender, phone, email, designation, salary) ");
			queryBuilder.append("VALUES (?, ?, ?, ?, ?, ?)");

			preparedStatement = connection.prepareStatement(queryBuilder.toString(), Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, employeeEntity.getName());
			preparedStatement.setString(2, employeeEntity.getGender());
			preparedStatement.setString(3, employeeEntity.getPhone());
			preparedStatement.setString(4, employeeEntity.getEmail());
			preparedStatement.setString(5, employeeEntity.getDesignation());
			preparedStatement.setDouble(6, employeeEntity.getSalary());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					savedEmployeeId = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != preparedStatement) {
				preparedStatement.close();
			}
			connectionUtil.closeConnection();
		}
		return savedEmployeeId;
	}

	public int updateEmployee(EmployeeEntity employeeEntity) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionUtil.getConnection();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("UPDATE employee SET ");
			queryBuilder.append("name = ?, gender = ?, phone = ?, email = ? , designation = ? , salary = ? ");
			queryBuilder.append("WHERE id = ?");
			preparedStatement = connection.prepareStatement(queryBuilder.toString());

			preparedStatement.setString(1, employeeEntity.getName());
			preparedStatement.setString(2, employeeEntity.getGender());
			preparedStatement.setString(3, employeeEntity.getPhone());
			preparedStatement.setString(4, employeeEntity.getEmail());
			preparedStatement.setString(5, employeeEntity.getDesignation());
			preparedStatement.setDouble(6, employeeEntity.getSalary());
			preparedStatement.setInt(7, employeeEntity.getId());

			preparedStatement.executeUpdate();
		} finally {
			if (null != preparedStatement) {
				preparedStatement.close();
			}
			connectionUtil.closeConnection();
		}
		return employeeEntity.getId();
	}

	public void deleteEmployee(int employeeID) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionUtil.getConnection();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("DELETE FROM employee WHERE id = ?");
			preparedStatement = connection.prepareStatement(queryBuilder.toString());

			preparedStatement.setInt(1, employeeID);

			preparedStatement.execute();
		} finally {
			if (null != preparedStatement) {
				preparedStatement.close();
			}
			connectionUtil.closeConnection();
		}
	}
}
