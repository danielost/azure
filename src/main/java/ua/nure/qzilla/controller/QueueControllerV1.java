package ua.nure.qzilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.qzilla.model.Queue;
import ua.nure.qzilla.service.QueueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/queues")
@CrossOrigin
public class QueueControllerV1 {

    @Autowired
    private QueueService queueService;

    @GetMapping("")
    public ResponseEntity<List<Queue>> getAll() {
        return ResponseEntity.ok(queueService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Queue queue) {
        try {
            return ResponseEntity.ok(queueService.create(queue));
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/enroll/{id}")
    public ResponseEntity<String> enroll(@PathVariable long id) {
        try {
            queueService.enroll(id);
            return ResponseEntity.ok("You have been enrolled to the queue");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
