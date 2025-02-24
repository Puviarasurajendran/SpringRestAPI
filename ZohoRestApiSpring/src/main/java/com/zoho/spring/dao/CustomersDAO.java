 package com.zoho.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.zoho.spring.handler.v2.Customerv2;
import com.zoho.spring.model.Customer;

@Component
public class CustomersDAO implements CustomersInterface {

	@Override
	public List<Customer> getAllCustomers() {

		System.out.println("Inside Customers DAO Get All Customers Method ");
		List<Customer> customers = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = CustomersDBConnectionModel.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM customers");
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setAge(rs.getInt("age"));
				customer.setCountry(rs.getString("country"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CustomersDBConnectionModel.closeConnection(con);
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//        System.out.println(customers);
		return customers;
	}

	@Override
	public Map<String, Object> getCustomerByID_v1(int id) {
		System.out.println("Inside Customers DAO Get Customer by id Method ");
		Connection con = null;
		Map<String, Object> customerMap = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = CustomersDBConnectionModel.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM customers WHERE id = ?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				customerMap = new HashMap<>();
				customerMap.put("id", rs.getInt("id"));
				customerMap.put("firstName", rs.getString("first_name"));
				customerMap.put("lastName", rs.getString("last_name"));
				customerMap.put("email", rs.getString("email"));
				customerMap.put("age", rs.getInt("age"));
				customerMap.put("country", rs.getString("country"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CustomersDBConnectionModel.closeConnection(con);
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customerMap;
	}

	@Override
	public void createCustomer_v1(Customer c) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = CustomersDBConnectionModel.getConnection();
			pstmt = con.prepareStatement(
					"INSERT INTO customers (first_name, last_name, email, age, country) VALUES (?, ?, ?, ?, ?)");
			pstmt.setString(1, c.getFirstName());
			pstmt.setString(2, c.getLastName());
			pstmt.setString(3, c.getEmail());
			pstmt.setInt(4, c.getAge());
			pstmt.setString(5, c.getCountry());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CustomersDBConnectionModel.closeConnection(con);
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	    public void createCustomer_v2(Customerv2 c) {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        try {
	            con = CustomersDBConnectionModel.getConnection();
	            pstmt = con.prepareStatement(
	                    "INSERT INTO customers (first_name, last_name, email, age, country) VALUES (?, ?, ?, ?, ?)");
	            pstmt.setString(1, c.getFullname().getFirstName());
	            pstmt.setString(2, c.getFullname().getLastName());
	            pstmt.setString(3, c.getEmail());
	            pstmt.setInt(4, c.getAge());
	            pstmt.setString(5, c.getCountry());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            CustomersDBConnectionModel.closeConnection(con);
	            try {
	                if (pstmt != null) {
	                    pstmt.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	@Override
	public void updateCustomer_v1(Customer c) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = CustomersDBConnectionModel.getConnection();
			pstmt = con.prepareStatement(
					"UPDATE customers SET first_name = ?, last_name = ?, email = ?, age = ?, country = ? WHERE id = ?");
			pstmt.setString(1, c.getFirstName());
			pstmt.setString(2, c.getLastName());
			pstmt.setString(3, c.getEmail());
			pstmt.setInt(4, c.getAge());
			pstmt.setString(5, c.getCountry());
			pstmt.setInt(6, c.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CustomersDBConnectionModel.closeConnection(con);
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteCustomer_v1(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = CustomersDBConnectionModel.getConnection();
			pstmt = con.prepareStatement("DELETE FROM customers WHERE id = ?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CustomersDBConnectionModel.closeConnection(con);
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(id + "Record deletd Successfully..");
	}

	@Override
	public List<Map<String, Object>> getTotalCutomers_v1() {

		List<Map<String, Object>> customers = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = CustomersDBConnectionModel.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM customers");
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("id", rs.getInt("id"));
				map.put("email", rs.getString("email"));
				map.put("age", rs.getInt("age"));
				map.put("country", rs.getString("country"));
				map.put("firstName", rs.getString("first_name"));
				map.put("lastName", rs.getString("last_name"));
				customers.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CustomersDBConnectionModel.closeConnection(con);
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//	        System.out.println(customers);
		return customers;
	}

	public List<Map<String, Object>> getTotalCutomers_v2() {

		List<Map<String, Object>> listOfCutomers = getTotalCutomers_v1();
		List<Map<String, Object>> updatedCutomersList = listOfCutomers.stream()
				.map(dataMap -> {
					Map<String, Object> fullnameMap = new HashMap<String, Object>();
					fullnameMap.put("firstName", dataMap.remove("firstName"));
					fullnameMap.put("lastName", dataMap.remove("lastName"));
					dataMap.put("full name", fullnameMap);
					return dataMap;
				}).collect(Collectors.toList());

		return updatedCutomersList;

	}

	public Map<String, Object> getCustomerByID_v2(int id) {

		Map<String, Object> cutomerMap = new HashMap<>();
		cutomerMap = getCustomerByID_v1(id);
		System.out.println("Inside CustomersDAO getCustomerByIdVersion2 Method " + cutomerMap);
		if (cutomerMap != null) {
			HashMap fullNameMap = new HashMap();
			fullNameMap.put("First name", cutomerMap.remove("firstName"));
			fullNameMap.put("Last name", cutomerMap.remove("lastName"));
			cutomerMap.put("full name", fullNameMap);
		}

		return cutomerMap;
	}
}
