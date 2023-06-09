package com.productshopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.productshopping.model.Product;
import com.productshopping.utils.ConnectionUtil;

public class ProductDAO {
	public static ProductDAO getProductDAO() {
		return new ProductDAO();
	}

	public List<Product> getAllProducts() {
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM product");) {
			List<Product> result = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");

				result.add(new Product(id, name, description, price));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public int getIdAutoIncrement() {
		try (
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps2 = conn.prepareStatement("SET @@SESSION.information_schema_stats_expiry = 0;");
			PreparedStatement ps = conn.prepareStatement("SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='product' AND TABLE_SCHEMA='shopping';");
		) {
			int row = ps2.executeUpdate();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int addProduct(String name, String description, double price) {
		try (
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO product(name, description, price) VALUE (?, ?, ?)");
		) {
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setDouble(3, price);
			int rowAffect = ps.executeUpdate();
			return rowAffect;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int editProduct(String name, String description, double price, int id) {
		try(
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE product SET name=?, description=?, price=? WHERE id=?");
		) {
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setDouble(3, price);
			ps.setInt(4, id);
			
			int rowAffect = ps.executeUpdate();
			return rowAffect;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deleteProduct(int id) {
		try(
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM product WHERE id=?");
		) {
			ps.setInt(1, id);
			
			int rowAffect = ps.executeUpdate();
			return rowAffect;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
