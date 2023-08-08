package com.expenses.Expense_Tracker.Service;

import com.expenses.Expense_Tracker.Model.Expense;
import com.expenses.Expense_Tracker.Model.ExpenseCategory;
import com.expenses.Expense_Tracker.Repository.ExpenseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Override
    public BigDecimal getTotalExpenseAmount() {
        List<Expense> allExpenses = expenseRepository.findAll();
        return allExpenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Map<ExpenseCategory, BigDecimal> getExpenseSummariesByCategory() {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<ExpenseCategory, BigDecimal> expenseSummaries = new HashMap<>();

        for (Expense expense : allExpenses) {
            ExpenseCategory category = expense.getCategory();
            BigDecimal amount = expense.getAmount();

            if (expenseSummaries.containsKey(category)) {
                BigDecimal currentAmount = expenseSummaries.get(category);
                expenseSummaries.put(category, currentAmount.add(amount));
            } else {
                expenseSummaries.put(category, amount);
            }
        }

        return expenseSummaries;
    }


    @Override
    public Map<String, BigDecimal> getExpenseSummariesByMonth() {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<String, BigDecimal> expenseSummaries = new HashMap<>();

        for (Expense expense : allExpenses) {
            String month = expense.getDate().getMonth().toString();
            BigDecimal amount = expense.getAmount();

            if (expenseSummaries.containsKey(month)) {
                BigDecimal currentAmount = expenseSummaries.get(month);
                expenseSummaries.put(month, currentAmount.add(amount));
            } else {
                expenseSummaries.put(month, amount);
            }
        }

        return expenseSummaries;
    }


    @Override
    public Map<Integer, BigDecimal> getExpenseSummariesByYear() {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<Integer, BigDecimal> expenseSummaries = new HashMap<>();

        for (Expense expense : allExpenses) {
            int year = expense.getDate().getYear();
            BigDecimal amount = expense.getAmount();

            if (expenseSummaries.containsKey(year)) {
                BigDecimal currentAmount = expenseSummaries.get(year);
                expenseSummaries.put(year, currentAmount.add(amount));
            } else {
                expenseSummaries.put(year, amount);
            }
        }

        return expenseSummaries;
    }



}
