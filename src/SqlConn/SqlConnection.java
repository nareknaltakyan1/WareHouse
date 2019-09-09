package SqlConn;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SqlConnection {

	private static Connection conn;

	public static Connection getConnection() throws SQLException, IOException {
		if (conn == null) {

			Properties props = new Properties();
			try (InputStream in = Files.newInputStream(Paths.get(
					"C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\WareHouse\\WebContent\\database.properties"))) {
				props.load(in);
			}
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}

//---------------------------------------------------------------------------------------------------------------------------INSERT TYPE------------------
	public static int InsertType(String name) {
		int id = -1;
		try (Connection conn = SqlConnection.getConnection()) {

			String sql = "INSERT INTO Type(name) Values (?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);

			int rows = preparedStatement.executeUpdate();

			System.out.printf("%d rows added\n", rows);

			sql = "select id from Type where name = (?)";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				id = resultSet.getInt(1);

				System.out.printf("%d \n", id);

			}

		}

		catch (Exception ex) {
			System.out.println("Command failed...");

			System.out.println(ex);

		}
		return id;
	}

//------------------------------------------------------------------------------------------------------------INSERT ITEM--------------------------------	
	public static int InsertItem(String name, int type_id) {
		int id = -1;
		try (Connection conn = SqlConnection.getConnection()) {

			String sql = "INSERT INTO Item(name,Type_id) Values (?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, type_id);

			int rows = preparedStatement.executeUpdate();

			System.out.printf("%d rows added\n", rows);

			sql = "select id from Item where name = (?) and Type_id=(?)";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, type_id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				id = resultSet.getInt(1);

				System.out.printf("%d \n", id);

			}

		}

		catch (Exception ex) {
			System.out.println("Command failed...");

			System.out.println(ex);

		}
		return id;
	}

	// ------------------------------------------------------------------------------------------------------------INSERT
	// ITEM--------------------------------
	public static List<String> SelectItemList() {
		List<String> list = new LinkedList<String>();
		try (Connection conn = SqlConnection.getConnection()) {

			

			String sql = "select name from Item";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
		

			ResultSet resultSet = preparedStatement.executeQuery();
			String name;
			while (resultSet.next()) {

				name = resultSet.getString(1);
				list.add(name);
				System.out.printf("%S \n", name);

			}

		}

		catch (Exception ex) {
			System.out.println("Command failed...");

			System.out.println(ex);

		}
		return list;
	}
//	public static List<String> SelectUserList() throws SQLException, IOException {
//		List<String> list = new LinkedList<String>();
//		try (Connection conn = SqlConnection.getConnection()) {
//			PreparedStatement preparedStatement = conn
//					.prepareStatement("SELECT login FROM Users");
//
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				String userLogin = resultSet.getString("login");
//				list.add(userLogin);
//			
//			}
//		} catch (Exception ex) {
//			System.out.println("Command failed...");
//
//			System.out.println(ex);
//		}
//		return list;
//	}
}