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
    private static final String INSERT_CMD = "INSERT INTO user(id, username, password, role) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_CMD = "UPDATE user SET id=?, username=?, password=?, role=? WHERE id=?";
    
    private static final String FIND_BY_USERNAME_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";
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
                userList.add(new User(id, username, password, role));
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
                        RS.getString("role"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(User user) {
        System.out.println(INSERT_CMD);
        try {
            System.out.println(user.getId());
            preparedStatement = connection.prepareStatement(INSERT_CMD);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();
            System.out.println("Successful add");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    @Override
    public void update(User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_CMD);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("Successful updating");
                
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }
    }
    @Override
	public void delete(long id) {
		// TODO Auto-generated method stub
    	try {
            preparedStatement = connection.prepareStatement(DELETE_CMD);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Successful deleting");
            
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
        UserDAO userDAO = new UserDAO();
        long id = new Date().getTime()%10000000;
        User user = new User(id, "dai", "123", "role");
        userDAO.add(user);
        
        user = new User(id, "daica", "123", "role");
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
                        RS.getString("role"));
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
		return null;
	}

	
}
