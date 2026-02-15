package com.mctait.frontend.service;

import com.mctait.frontend.dto.TaskDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskApiClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8080/taskapi";

    public List<TaskDTO> getAllTasks() {
        TaskDTO[] tasks = restTemplate.getForObject(API_URL + "/get-all-tasks", TaskDTO[].class);
        return Arrays.asList(tasks);
    }

    public TaskDTO getTaskById(Long id) {
        return restTemplate.getForObject(API_URL + "/get-task/" + id, TaskDTO.class);
    }

    public TaskDTO createTask(TaskDTO task) {
        return restTemplate.postForObject(API_URL + "/create", task, TaskDTO.class);
    }

    public void deleteTask(Long id) {
        restTemplate.delete(API_URL + "/delete/" + id);
    }

    public void updateStatus(Long id, String status) {
        String url = API_URL + "/update-status/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Wrap the status in a JSON object that matches the backend UpdateStatusRequest
        String jsonBody = "{ \"status\": \"" + status + "\" }";

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        restTemplate.postForObject(url, request, String.class);
    }

    private record UpdateStatusRequest(String status) {}
}
