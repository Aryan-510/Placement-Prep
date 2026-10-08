package com.placementprep.controller;

import com.placementprep.entity.*; import com.placementprep.service.PlacementService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;

@RestController @RequestMapping("/api")
public class ApiController {
    private final PlacementService service; public ApiController(PlacementService service){this.service=service;}
    @GetMapping("/applications") public List<JobApplication> applications(){return service.applications();}
    @GetMapping("/applications/{id}") public JobApplication application(@PathVariable long id){return service.application(id);}
    @PostMapping("/applications") public ResponseEntity<JobApplication> createApplication(@Valid @RequestBody JobApplication item){return ResponseEntity.status(HttpStatus.CREATED).body(service.saveApplication(item));}
    @PutMapping("/applications/{id}") public JobApplication updateApplication(@PathVariable long id,@Valid @RequestBody JobApplication item){JobApplication saved=service.application(id);saved.setCompanyName(item.getCompanyName());saved.setJobRole(item.getJobRole());saved.setLocation(item.getLocation());saved.setApplicationDate(item.getApplicationDate());saved.setStatus(item.getStatus());saved.setJobType(item.getJobType());saved.setNotes(item.getNotes());return service.saveApplication(saved);}
    @DeleteMapping("/applications/{id}") public ResponseEntity<Void> deleteApplication(@PathVariable long id){service.deleteApplication(id);return ResponseEntity.noContent().build();}
    @GetMapping("/topics") public List<PreparationTopic> topics(@RequestParam(required=false) String category){return service.topics(category);}
    @GetMapping("/topics/{id}") public PreparationTopic topic(@PathVariable long id){return service.topic(id);}
    @PostMapping("/topics") public ResponseEntity<PreparationTopic> createTopic(@Valid @RequestBody PreparationTopic item){return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTopic(item));}
    @PutMapping("/topics/{id}") public PreparationTopic updateTopic(@PathVariable long id,@Valid @RequestBody PreparationTopic item){PreparationTopic saved=service.topic(id);saved.setName(item.getName());saved.setCategory(item.getCategory());saved.setDifficulty(item.getDifficulty());saved.setCompleted(item.isCompleted());saved.setNotes(item.getNotes());return service.saveTopic(saved);}
    @DeleteMapping("/topics/{id}") public ResponseEntity<Void> deleteTopic(@PathVariable long id){service.deleteTopic(id);return ResponseEntity.noContent().build();}
    @GetMapping("/study-sessions") public List<StudySession> sessions(){return service.sessions();}
    @PostMapping("/study-sessions") public ResponseEntity<StudySession> createSession(@Valid @RequestBody StudySession item){return ResponseEntity.status(HttpStatus.CREATED).body(service.saveSession(item));}
    @DeleteMapping("/study-sessions/{id}") public ResponseEntity<Void> deleteSession(@PathVariable long id){service.deleteSession(id);return ResponseEntity.noContent().build();}
    @GetMapping("/mock-tests") public List<MockTest> tests(){return service.tests();}
    @GetMapping("/mock-tests/{id}") public MockTest test(@PathVariable long id){return service.test(id);}
    @PostMapping("/mock-tests") public ResponseEntity<MockTest> createTest(@Valid @RequestBody MockTest item){return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTest(item));}
    @PutMapping("/mock-tests/{id}") public MockTest updateTest(@PathVariable long id,@Valid @RequestBody MockTest item){MockTest saved=service.test(id);saved.setTestName(item.getTestName());saved.setSubject(item.getSubject());saved.setDate(item.getDate());saved.setScore(item.getScore());saved.setTotalMarks(item.getTotalMarks());return service.saveTest(saved);}
    @DeleteMapping("/mock-tests/{id}") public ResponseEntity<Void> deleteTest(@PathVariable long id){service.deleteTest(id);return ResponseEntity.noContent().build();}
    @GetMapping("/dashboard") public Map<String,Object> dashboard(){return service.dashboard();}
}

