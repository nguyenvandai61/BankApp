package server.models;

public class Customer extends Entity{

    private String name;
    private String address;
    private String accountNumber;
    private String accountType;
    private String signature;
    protected Customer() {

    }
    public Customer(Long id, String name, String address, String accountNumber, String accountType, String signature) {
        this.setId(id);
        this.name = name;
        this.address = address;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.signature = signature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
