package server.controller;

import java.util.List;
import java.util.Optional;

import server.dao.UserDAO;
import server.models.User;
import server.repositories.IUserRepository;

public class UserController {
	UserDAO userDao = new UserDAO();
	
	public boolean isUser(String username, String password) {
		return userDao.findUsernameAndPassword(username, password)!= null; 
	}
	
	public static void main(String args[]) {
		UserController uc = new UserController();
		System.out.print(uc.isUser("Dai", "123"));
	}
}
