package com.expenses.Expense_Tracker.Controller;

import com.expenses.Expense_Tracker.Model.ExpenseCategory;
import com.expenses.Expense_Tracker.Service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/expense-categories")
public class ExpenseCategoryController {
    private final ExpenseCategoryService expenseCategoryService;

    @Autowired
    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping
    public String showAllExpenseCategories(Model model) {
        model.addAttribute("expenseCategories", expenseCategoryService.getAllExpenseCategories());
        return "expense-category-list";
    }

    @GetMapping("/add")
    public String showAddExpenseCategoryForm(Model model) {
        model.addAttribute("expenseCategory", new ExpenseCategory());
        return "add-expense-category";
    }

    @PostMapping("/add")
    public String addExpenseCategory(@ModelAttribute ExpenseCategory expenseCategory) {
        expenseCategoryService.addExpenseCategory(expenseCategory);
        return "redirect:/expense-categories";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateExpenseCategoryForm(@PathVariable("id") Long id, Model model) {
        ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategoryById(id);
        model.addAttribute("expenseCategory", expenseCategory);
        return "update-expense-category";
    }

    @PostMapping("/edit/{id}")
    public String updateExpenseCategory(@PathVariable("id") Long id, @ModelAttribute ExpenseCategory updatedCategory) {
        expenseCategoryService.updateExpenseCategory(id, updatedCategory);
        return "redirect:/expense-categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpenseCategory(@PathVariable("id") Long id) {
        expenseCategoryService.deleteExpenseCategory(id);
        return "redirect:/expense-categories";
    }
}
