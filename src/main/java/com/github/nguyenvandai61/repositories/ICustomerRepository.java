package com.github.nguyenvandai61.repositories;

import com.github.nguyenvandai61.models.Customer;
import com.github.nguyenvandai61.repositories.IBaseRepository;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends IBaseRepository<Customer, Long>{
    List<Customer> search(String term);
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByAccountNumber(String accountNumber);
}
