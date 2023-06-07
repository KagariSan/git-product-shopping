package com.productshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import com.productshopping.dao.ProductDAO;
import com.productshopping.model.Product;

/**
 * Servlet implementation class ProductAdd
 */
public class ProductAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\r\n"
				+ "<title>Product Shopping</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"container\">\r\n"
				+ "		<form action=\"\" method=\"post\">\r\n"
				+ "			  <div class=\"mb-3\">\r\n"
				+ "			    <label for=\"id\" class=\"form-label\">ID</label>\r\n"
				+ "      			<input type=\"text\" name=\"id\" id=\"id\" class=\"form-control\" value=" + (ProductDAO.getProductDAO().getCountProduct() + 1) +" disabled>\r\n"
				+ "			  </div>\r\n"
				+ "			  <div class=\"mb-3\">\r\n"
				+ "			    <label for=\"name\" class=\"form-label\">Name</label>\r\n"
				+ "      			<input type=\"text\" name=\"name\" id=\"name\" class=\"form-control\">\r\n"
				+ "			  </div>\r\n"
				+ "			  <div class=\"mb-3\">\r\n"
				+ "			    <label for=\"description\" class=\"form-label\">Description</label>\r\n"
				+ "      			<input type=\"text\" name=\"description\" id=\"description\" class=\"form-control\">\r\n"
				+ "			  </div>\r\n"
				+ "			  <div class=\"mb-3\">\r\n"
				+ "			    <label for=\"price\" class=\"form-label\">Price</label>\r\n"
				+ "      			<input type=\"text\" name=\"price\" id=\"price\" class=\"form-control\">\r\n"
				+ "			  </div>\r\n"
				+ "			  <button type=\"submit\" name=\"add\" class=\"btn btn-primary\">+ Add</button>\r\n"
				+ "		</form>\r\n"
				+ "	</div>\r\n"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		
		if(Objects.nonNull(name) && Objects.nonNull(description) && Objects.nonNull(price)) {
			ProductDAO.getProductDAO().addProduct(name, description, Double.parseDouble(price));
		}
		response.sendRedirect("ProductServlet");
	}

}
