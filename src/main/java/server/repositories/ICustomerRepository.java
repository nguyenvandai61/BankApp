package server.repositories;

import server.models.Customer;
import server.repositories.IBaseRepository;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends IBaseRepository<Customer, Long>{
    List<Customer> search(String term);
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByAccountNumber(String accountNumber);
}
