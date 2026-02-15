package com.mctait.frontend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class TaskDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String description;

    @NotBlank(message = "Status is required")
    @Size(max = 255)
    private String status;

    @NotNull(message = "Due Date is required")
    private Date dueDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
}
