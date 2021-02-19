package server.repositories;

import java.util.List;
import java.util.Optional;

import server.models.User;

public interface IUserRepository {
    List<User> search(String term);
    User findUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    
    boolean increaseAmount2Balance(String username, Long amount);
}
