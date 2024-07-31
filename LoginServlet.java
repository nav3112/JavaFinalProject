package serverlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dataAccessImpl.UserDaoImpl;
import dataAccessLayer.UserDao;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDao userDao;

	public LoginServlet() {
		super();
		userDao =new UserDaoImpl();
	}
	public boolean authenticateUser(String email, String password) {
        boolean isValidUser = false;
        try {
        	con = DataSource.getConnection();
            ps  = con.prepareStatement("SELECT * FROM users WHERE userEmail = ? AND userPassword = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
                
            isValidUser = rs.next();
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return isValidUser;
    }
	public String getUserType(String email,String password) {
		String userType = null;
	    try {
	        con = DataSource.getConnection();
	        ps = con.prepareStatement("SELECT usertype FROM users WHERE userEmail = ? AND userPassword = ?");
	        ps.setString(1, email);
	        ps.setString(2, password);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            userType = rs.getString("UserType");
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	    return userType;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("error", "Invalid username or password");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	            dispatcher.forward(request, response);
        
	}
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String email = request.getParameter("email");
		 String password = request.getParameter("password");
		 
		 boolean isAuthenticated = authenticateUser(email, password);
		 if (isAuthenticated) {
	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            
	         // Retrieve userType from the database
	            String userType = getUserType(email,password); 
	            
	         // Redirect the user based on userType
	            switch (userType) {
	                case "Retailer":
	                    response.sendRedirect("/PRoject_NAme/RetailerServlet");
	                    break;
	                case "Consumer":
	                    response.sendRedirect("//PRoject_NAme/ConsumerServlet");
	                    break;
	                case "Charitable_Organization":
	                    response.sendRedirect("//PRoject_NAme/CharitableServlet");
	                    break;
	                default:
	                    // Handle if userType is unknown or not specified
	        
	                    break;
	            }
	           
	        } else {
	            request.setAttribute("error", "Invalid username or password");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	            dispatcher.forward(request, response);
	        }
		 
		 
	    }
	
	
	}


