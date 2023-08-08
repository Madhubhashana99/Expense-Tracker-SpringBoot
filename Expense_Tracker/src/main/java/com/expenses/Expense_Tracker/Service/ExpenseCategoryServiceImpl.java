package com.expenses.Expense_Tracker.Service;

import com.expenses.Expense_Tracker.Model.ExpenseCategory;
import com.expenses.Expense_Tracker.Repository.ExpenseCategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService{
    private final ExpenseCategoryRepo expenseCategoryRepository;

    @Autowired
    public ExpenseCategoryServiceImpl(ExpenseCategoryRepo expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @Override
    public List<ExpenseCategory> getAllExpenseCategories() {
        return expenseCategoryRepository.findAll();
    }

    @Override
    public ExpenseCategory getExpenseCategoryById(Long id) {
        Optional<ExpenseCategory> optionalExpenseCategory = expenseCategoryRepository.findById(id);
        return optionalExpenseCategory.orElse(null);
    }

    @Override
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    @Override
    public void updateExpenseCategory(Long id, ExpenseCategory updatedCategory) {
        ExpenseCategory existingCategory = getExpenseCategoryById(id);
        if (existingCategory != null) {
            existingCategory.setName(updatedCategory.getName());

        }
    }

    @Override
    public void deleteExpenseCategory(Long id) {
        expenseCategoryRepository.deleteById(id);
    }
}
