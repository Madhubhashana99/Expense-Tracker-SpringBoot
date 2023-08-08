package com.expenses.Expense_Tracker.Repository;

import com.expenses.Expense_Tracker.Model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepo extends JpaRepository<ExpenseCategory, Long> {

}
