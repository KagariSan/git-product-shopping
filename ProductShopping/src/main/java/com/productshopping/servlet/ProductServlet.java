package com.productshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.productshopping.dao.ProductDAO;
import com.productshopping.model.Product;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = ProductDAO.getProductDAO().getAllProducts();
		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\r\n"
				+ "<title>Product Shopping</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<table class=\"table\">\r\n"
				+ "  <thead>\r\n"
				+ "    <tr>\r\n"
				+ "      <th scope=\"col\">ID</th>\r\n"
				+ "      <th scope=\"col\">Name</th>\r\n"
				+ "      <th scope=\"col\">Description</th>\r\n"
				+ "      <th scope=\"col\">Price</th>\r\n"
				+ "      <th scope=\"col\">Action</th>\r\n"
				+ "    </tr>\r\n"
				+ "  </thead>\r\n"
				+ "  <tbody>\r\n"
				+ "    <tr>\r\n");
		for(Product product : products) {
			out.append("<tr>\r\n"
					+ "      <th scope=\"row\">" + product.getId() + "</th>\r\n"
					+ "      <td>" + product.getName() + "</td>\r\n"
					+ "      <td>" + product.getDescription() + "</td>\r\n"
					+ "      <td>" + product.getPrice() + "</td>\r\n"
					+ "		 <td>\r\n"	
					+ "      	<form action=\"ProductEdit?id=" + product.getId() + "\" method=\"post\">\r\n"
					+ "      		<input type=\"text\" name=\"id\" value=" + product.getId() + " class=\"visually-hidden\"/>\r\n"
					+ "      		<button type=\"submit\" name=\"edit\" class=\"btn btn-primary\">Edit</button>\r\n"
					+ "      	</form>\r\n"
					+ "      </td>"
					+ "    </tr>");
		}
		out.append("</tbody>\r\n"
				+ "</table>\r\n"
				+ "<form action=\"ProductAdd\" method=\"post\">\r\n"
				+ "		<button type=\"submit\" name=\"add\" class=\"btn btn-primary\"> + Add</button>\r\n"
				+ "</form>\r\n"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
