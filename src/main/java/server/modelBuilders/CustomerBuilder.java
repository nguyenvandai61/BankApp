package server.modelBuilders;

import server.models.Customer;
public class CustomerBuilder extends Customer {
    private  Long id;
    private String name;
    private String address;
    private String accountNumber;
    private String accountType;
    private String signature;

    public CustomerBuilder() {
        super();
    }

    public CustomerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return  this;
    }

    public CustomerBuilder withAddress(String address) {
        this.address= address;
        return  this;
    }
    public CustomerBuilder withAccountNumber(String number) {
        this.accountNumber =number;
        return this;
    }

    public CustomerBuilder withAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }
    public CustomerBuilder withSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public Customer build() {
        return new Customer(id, name, address, accountNumber, accountType, signature);
    }
}
