
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SqlConn.SqlConnection;

/**
 * Servlet implementation class NewItemServlet
 */
@WebServlet("/NewItemServlet")
public class NewItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection conn = SqlConnection.getConnection()) {
				System.out.println("Connection to WareHouse.Item succesfull!\n");
				String name = request.getParameter("name");
				int type_id = Integer.parseInt(request.getParameter("type"));
				int id = SqlConnection.InsertItem(name,type_id);

				try {
					writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
					writer.println(
							"<div class=\"menu\"><a href=\"index.html\"> <button class=\"button\"><span>New Item " + id
									+ "</span></button>");
				} finally {
					writer.close();
				}
			}
		} catch (Exception ex) {
			System.out.println("Connection failed...");

			System.out.println(ex);
			try {
				writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
				writer.println("<div class=\"menu\"><a href=\"index.html\"> <button class=\"button\"><span>Error</span></button>");
			} finally {
				writer.close();
			}
		}

	}
}
