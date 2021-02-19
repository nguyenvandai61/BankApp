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
	public Long getBalance(String username) {
		User u = userDao.findByUsername(username);
		return u.getBalance();
	}
	public boolean increaseBalance(String username, Long amount) {
		boolean isSuccess = userDao.increaseAmount2Balance(username, amount);
		return isSuccess;
	}
	public boolean descreaseBalance(String username, Long amount) {
		User u = userDao.findByUsername(username);
		Long newValue = u.getBalance()-amount;
		if (newValue < 0) return false;
		u.setBalance(newValue);
		boolean isSuccess = userDao.update(u);
		return isSuccess;
	}
	public boolean transferMoney(String src, String des, Long amount) {
		
		boolean desc = descreaseBalance(src, amount);
		System.out.println("Desc"+desc);
		boolean incr = increaseBalance(des, amount);
		System.out.println("Incr"+incr);
		
		return false;
	}
	public static void main(String args[]) {
		UserController uc = new UserController();
//		System.out.print(uc.isUser("Dai", "123"));
//		System.out.println(uc.addBalance("Dai", -500000l));
		System.out.println(uc.descreaseBalance("Dai", 100000l));
		
//		System.out.println(uc.getBalance("daica"));
//		System.out.println(uc.getBalance("Dai"));
//		
//		uc.transferMoney("daica", "Dai", 200000l);
//		System.out.println(uc.getBalance("daica"));
//		System.out.println(uc.getBalance("Dai"));
		
	}
}
