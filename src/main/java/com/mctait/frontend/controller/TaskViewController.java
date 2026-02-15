package com.mctait.frontend.controller;

import com.mctait.frontend.dto.TaskDTO;
import com.mctait.frontend.service.TaskApiClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskApiClient apiClient;

    public TaskViewController(TaskApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // parse date in yyyy-MM-dd format from HTML input
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", apiClient.getAllTasks());
        return "tasks";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new TaskDTO());
        return "create-task";
    }

    @PostMapping("/create")
    public String createTask(@Valid @ModelAttribute("task") TaskDTO task,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "create-task"; // show form again with validation errors
        }
        apiClient.createTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        apiClient.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/update-status/{id}")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam String status) {
        apiClient.updateStatus(id, status);
        return "redirect:/tasks";
    }
}
