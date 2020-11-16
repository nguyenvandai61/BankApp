package com.github.nguyenvandai61.repositories;

import com.github.nguyenvandai61.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<User> search(String term);
    Optional<User> findUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
}
