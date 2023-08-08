package com.expenses.Expense_Tracker.Controller;


import com.expenses.Expense_Tracker.Model.Expense;
import com.expenses.Expense_Tracker.Model.ExpenseCategory;
import com.expenses.Expense_Tracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public String showAllExpenses(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "expense-list";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/add")
    public String showAddExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "add-expense";
    }

    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expense expense) {
        expenseService.addExpense(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateExpenseForm(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "update-expense";
    }

    @PostMapping("/edit/{id}")
    public String updateExpense(@PathVariable("id") Long id, @ModelAttribute Expense updatedExpense) {
        expenseService.updateExpense(id, updatedExpense);
        return "redirect:/expenses";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable("id") Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    @GetMapping("/charts")
    public String showExpenseCharts(Model model) {
        BigDecimal totalExpenseAmount = expenseService.getTotalExpenseAmount();
        Map<ExpenseCategory, BigDecimal> expenseSummariesByCategory = expenseService.getExpenseSummariesByCategory();
        Map<String, BigDecimal> expenseSummariesByMonth = expenseService.getExpenseSummariesByMonth();
        Map<Integer, BigDecimal> expenseSummariesByYear = expenseService.getExpenseSummariesByYear();

        model.addAttribute("totalExpenseAmount", totalExpenseAmount);
        model.addAttribute("expenseSummariesByCategory", expenseSummariesByCategory);
        model.addAttribute("expenseSummariesByMonth", expenseSummariesByMonth);
        model.addAttribute("expenseSummariesByYear", expenseSummariesByYear);

        return "expense-charts";
    }
}
