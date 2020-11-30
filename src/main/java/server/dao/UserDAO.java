package server.dao;

import server.interfaces.IDatabaseCommand;
import server.models.User;
import server.repositories.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDAO implements IDatabaseCommand<User>, IUserRepository {
    private PreparedStatement preparedStatement;
    private Connection connection;
    private static final String DELETE_CMD = "DELETE FROM user WHERE id=?";
    private static final String FIND_ALL_CMD = "SELECT * FROM user ORDER BY id";
    private static final String FIND_BY_ID_CMD = "SELECT * FROM user WHERE id=?";
    private static final String INSERT_CMD = "INSERT INTO user(id, username, password, role, balance) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_CMD = "UPDATE user SET id=?, username=?, password=?, role=?, balance=? WHERE id=?";
    
    private static final String FIND_BY_USERNAME = "SELECT * FROM user WHERE username=?";
    private static final String FIND_BY_USERNAME_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";
    
    private static final String INSCREASE_AMOUNT_TO_BALANCE = "UPDATE user SET balance = balance + ? WHERE username=?";
//    private static final String DESCREASE_AMOUNT_TO_BALANCE = "UPDATE user SET balance = balance - ? WHERE username=?";
    
    public UserDAO() {
        connection = this.getConnection();
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> userList = null;
        try {
            userList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(FIND_ALL_CMD);
            preparedStatement.setInt(1, 0);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                Long id = RS.getLong("id");
                String username = RS.getString("username");
                String password = RS.getString("password");
                String role = RS.getString("role");
                Long balance = RS.getLong("balance");
                userList.add(new User(id, username, password, role, balance));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
        }
        return userList;
    }

    @Override
    public User get(Long id) {
        try{
            preparedStatement = connection.prepareStatement(FIND_BY_ID_CMD);
            preparedStatement.setLong(1,id);
            ResultSet RS = preparedStatement.executeQuery();
            while(RS.next()){
                User user = new User(
                        RS.getLong("id"),
                        RS.getString("username"),
                        RS.getString("password"),
                        RS.getString("role"),
                        RS.getLong("balance"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(User user) {
        System.out.println(INSERT_CMD);
        try {
            System.out.println(user.getId());
            preparedStatement = connection.prepareStatement(INSERT_CMD);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setLong(5, user.getBalance());
            preparedStatement.executeUpdate();
            System.out.println("Successful add");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_CMD);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setLong(5, user.getBalance());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("Successful updating");
            return true;   
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }
        return false;
    }
    @Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
    	try {
            preparedStatement = connection.prepareStatement(DELETE_CMD);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Successful deleting");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }
    	return false;
	}
    

    @Override
    public ArrayList<String> getAllName() {
        return null;
    }
    public static void main(String args[]) {
        UserDAO userDAO = new UserDAO();
        long id = new Date().getTime()%10000000;
        User user = new User(id, "dai", "123", "role", 0l);
        userDAO.add(user);
        
        user = new User(id, "daica", "123", "role", 5l);
        userDAO.update(user);
        
        userDAO.delete(4947265l);
    }

	@Override
	public List<User> search(String term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		try{
            preparedStatement = connection.prepareStatement(FIND_BY_USERNAME_PASSWORD);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            
            ResultSet RS = preparedStatement.executeQuery();
            while(RS.next()){
                User user = new User(
                        RS.getLong("id"),
                        RS.getString("username"),
                        RS.getString("password"),
                        RS.getString("role"),
                        RS.getLong("balance"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		try{
            preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1,username);
            
            ResultSet RS = preparedStatement.executeQuery();
            while(RS.next()){
                User user = new User(
                        RS.getLong("id"),
                        RS.getString("username"),
                        RS.getString("password"),
                        RS.getString("role"),
                        RS.getLong("balance"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
		return null;
	}

	@Override
	public boolean increaseAmount2Balance(String username, Long amount) {
		// TODO Auto-generated method stub
		try {
            preparedStatement = connection.prepareStatement(INSCREASE_AMOUNT_TO_BALANCE);
            preparedStatement.setLong(1, amount);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            System.out.println("Successful updating");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }
		return false;
	}

	
}
