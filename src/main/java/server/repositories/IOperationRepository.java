package server.repositories;

import java.util.List;
import java.util.Optional;

import server.models.Operation;

public interface IOperationRepository {
    List<Operation> findAllDeposits();
    List<Operation> findAllWithdraws();
    List<Operation> findAllByAccountNumber(String accountNumber);
    List<Operation> findAllDepositsByAccountNumber(String accountNumber);
    List<Operation> findAllWithdrawsByAccountNumber(String accountNumber);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
    void transfer(String srcAccountNumber, String destAccountNumber, double amount);
    void printReceipt();
    Optional<Double> getBalance(String accountNumber);
}
