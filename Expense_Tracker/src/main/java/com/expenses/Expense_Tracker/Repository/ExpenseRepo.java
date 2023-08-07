package com.expenses.Expense_Tracker.Repository;

import com.expenses.Expense_Tracker.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
}
