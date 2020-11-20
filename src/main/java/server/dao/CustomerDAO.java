package server.dao;

import server.interfaces.IDatabaseCommand;
import server.modelBuilders.CustomerBuilder;
import server.models.Customer;
import server.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CustomerDAO implements IDatabaseCommand<Customer> {
    private PreparedStatement preparedStatement;
    private Connection connection;
    private static final String DELETE_CMD = "DELETE FROM Customer WHERE id=?";
    private static final String FIND_ALL_CMD = "SELECT * FROM Customer ORDER BY id";
    private static final String FIND_BY_ID_CMD = "SELECT * FROM Customer WHERE id=?";
    private static final String FIND_BY_NAME_CMD = "SELECT * FROM Customer WHERE name=?";
    private static final String INSERT_CMD = "INSERT INTO Customer(id, name, address, account_number, account_type" +
            ", signature) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CMD = "UPDATE Customer SET id=?, name=?, address=?, account_number=?, " +
            "account_type=?, signature=? WHERE id=?";
    public CustomerDAO() {
		// TODO Auto-generated constructor stub
    	connection = getConnection();
    }
    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> customerList = null;
        try {
            customerList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(FIND_ALL_CMD);
            preparedStatement.setInt(1, 0);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                Long id = RS.getLong("id");
                String name = RS.getString("name");
                String address = RS.getString("address");
                String accountNumber = RS.getString("account_number");
                String accountType = RS.getString("account_type");
                String signature = RS.getString(("signature"));
                Customer customer = new CustomerBuilder()
                        .withId(id)
                        .withName(name)
                        .withAddress(address)
                        .withAccountNumber(accountNumber)
                        .withAccountType(accountType)
                        .withSignature(signature)
                        .build();
                customerList.add(customer);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
        }
        return customerList;
    }

    @Override
    public Customer get(Long id) {
        try{
            preparedStatement = connection.prepareStatement(FIND_BY_ID_CMD);
            preparedStatement.setLong(1,id);
            ResultSet RS = preparedStatement.executeQuery();
            while(RS.next()){
                String name = RS.getString("name");
                String address = RS.getString("address");
                String accountNumber = RS.getString("account_number");
                String accountType = RS.getString("account_type");
                String signature = RS.getString(("signature"));
                Customer customer = new CustomerBuilder()
                        .withId(id)
                        .withName(name)
                        .withAddress(address)
                        .withAccountNumber(accountNumber)
                        .withAccountType(accountType)
                        .withSignature(signature)
                        .build();
                return customer;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Customer customer) {
        try {
            System.out.println(INSERT_CMD);
            preparedStatement = connection.prepareStatement(INSERT_CMD);
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getAccountNumber());
            preparedStatement.setString(5, customer.getAccountType());
            preparedStatement.setString(6, customer.getSignature());
            preparedStatement.executeUpdate();
            System.out.println("Success add");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_CMD);
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getAccountNumber());
            preparedStatement.setString(5, customer.getAccountType());
            preparedStatement.setString(6, customer.getSignature());
            preparedStatement.setLong(7, customer.getId());
            preparedStatement.executeUpdate();
            System.out.println("Success updating");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }
    }


    @Override
    public ArrayList<String> getAllName() {
        return null;
    }
    public static void main(String args[]) {
    	CustomerDAO customerDAO = new CustomerDAO();
        long id = new Date().getTime()%10000000;
        Customer customer = new CustomerBuilder()
        		.withId(id)
        		.withName("Nguyễn Đại")
        		.withAddress("Thừa Thiên Huế")
        		.withAccountNumber("01135112")
        		.withAccountType("VISA")
        		.withSignature("/01135112.jpg")
        		.build();
        customerDAO.add(customer);
        customer = new CustomerBuilder()
        		.withId(id)
        		.withName("Nguyễn Văn Đại")
        		.withAddress("Thừa Thiên Huế")
        		.withAccountNumber("01135112")
        		.withAccountType("VISA")
        		.withSignature("/01135112.jpg")
        		.build();
        
        customerDAO.update(customer);
        
        customerDAO.delete(4947265l);
    }

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		try {
            preparedStatement = connection.prepareStatement(DELETE_CMD);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Success Delete");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }
	}
}
