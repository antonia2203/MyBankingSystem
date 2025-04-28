package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    private String holderName;

    private double balance;

   @OneToMany(mappedBy = "account" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<Transaction>();

   public Account() {}

    public Account(String holderName, double balance) {
       super();
       this.holderName = holderName;
       this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
