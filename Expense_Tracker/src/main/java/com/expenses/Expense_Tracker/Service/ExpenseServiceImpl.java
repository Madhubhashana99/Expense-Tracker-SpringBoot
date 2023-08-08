package com.expenses.Expense_Tracker.Service;

import com.expenses.Expense_Tracker.Model.Expense;
import com.expenses.Expense_Tracker.Repository.ExpenseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepo expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepo expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = getExpenseById(id);
        if (existingExpense != null) {
            existingExpense.setDate(updatedExpense.getDate());
            existingExpense.setAmount(updatedExpense.getAmount());
            existingExpense.setCategory(updatedExpense.getCategory());
            existingExpense.setDescription(updatedExpense.getDescription());
            return expenseRepository.save(existingExpense);
        }
        return null; // Or throw an exception if you prefer
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        return optionalExpense.orElse(null);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

}
