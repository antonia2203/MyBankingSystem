package com.antonia.bank_app.controller;

import com.antonia.bank_app.service.BankService;
import entity.Account;
import entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BankController {

    @Autowired
    private BankService bankService;

    // Display list of all accounts
    @GetMapping("/")
    public String viewAllAccounts(Model model) {
       List<Account> accountsList = bankService.getAllAccounts();
       model.addAttribute("accountList", accountsList);

       return "Accounts";
    }


    // Add a new account
    @PostMapping("/accounts/add")
    public String createAccount(@RequestParam String holderName, @RequestParam double initialBalance) {
        bankService.createAccount(holderName, initialBalance);

        return "redirect:/";
    }


    // Deposit to an account
    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable("id") Long accountId, @PathVariable("amount") double amount) {
        bankService.deposit(accountId, amount);

        return "redirect:/";
    }


    // Withdraw from an account
    @PostMapping("/{id}/deposit")
    public String withdraw(@PathVariable("id") Long accountId, @RequestParam("amounts") double amount) {
        bankService.deposit(accountId, amount);

        return "redirect:/";
    }


    //View transactions history
    public String viewTransactions(@PathVariable("id") Long accountId, @RequestParam String type, @RequestParam LocalDateTime startDate,
                                   @RequestParam LocalDateTime endDate,
                                   Model model) {
        List<Transaction> transactions = bankService
                .getTransactionHistory(accountId, type , startDate, endDate);
        model.addAttribute("transactions" , transactions );
        model.addAttribute("accountId", accountId);

        return "Transactions";
    }


}
