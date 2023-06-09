package com.productshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

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
					+ "      		<input type=\"text\" name=\"name\" value=" + product.getName() + " class=\"visually-hidden\"/>\r\n"
					+ "      		<input type=\"text\" name=\"description\" value=" + product.getDescription() + " class=\"visually-hidden\"/>\r\n"
					+ "      		<input type=\"text\" name=\"price\" value=" + product.getPrice() + " class=\"visually-hidden\"/>\r\n"
					+ "      		<button type=\"submit\" name=\"edit\" class=\"btn btn-primary\" value=\"edit\">Edit</button>\r\n"
					+ "      	</form>\r\n"
					+ "      	<form action=\"\" method=\"post\">\r\n"
					+ "      		<input type=\"text\" name=\"id\" value=" + product.getId() + " class=\"visually-hidden\"/>\r\n"
					+ "      		<button type=\"submit\" name=\"delete\" class=\"btn btn-primary mt-2\" value=\"delete\">Delete</button>\r\n"
					+ "      	</form>\r\n"
					+ "      </td>"
					+ "    </tr>");
		}
		out.append("</tbody>\r\n"
				+ "</table>\r\n"
				+ "<form action=\"ProductAdd\" method=\"post\">\r\n"
				+ "		<button type=\"submit\" name=\"add\" class=\"btn btn-primary\" value=\"add\"> + Add</button>\r\n"
				+ "</form>\r\n"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String add = request.getParameter("add");
		String edit = request.getParameter("edit");
		String delete = request.getParameter("delete");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		
		if(Objects.nonNull(add) && add.equals("add") && Objects.nonNull(name) && Objects.nonNull(description) && Objects.nonNull(price)) {
			ProductDAO.getProductDAO().addProduct(name, description, Double.parseDouble(price));
		}
		
		if(Objects.nonNull(edit) && edit.equals("edit") && Objects.nonNull(name) && Objects.nonNull(description) && Objects.nonNull(price)) {
			ProductDAO.getProductDAO().editProduct(name, description, Double.parseDouble(price), Integer.parseInt(id));
		}
		
		if(Objects.nonNull(delete) && delete.equals("delete") && Objects.nonNull(id)) {
			ProductDAO.getProductDAO().deleteProduct(Integer.parseInt(id));
		}
	
		doGet(request, response);
	}

}
