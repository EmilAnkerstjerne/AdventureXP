package com.example.demo.controller;

import com.example.demo.model.Assignment;
import com.example.demo.model.Instructor;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkScheduleRestController {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @GetMapping("/work-schedule/instructors")
    public List<Instructor> getInstructors(){
        return instructorRepository.findAll();
    }

    @DeleteMapping("/work-schedule/delete-instructor")
    public ResponseEntity<Instructor> removeInstructor(@RequestBody Instructor instructor){
        if (instructorRepository.findById(instructor.getId()).isEmpty()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        assignmentRepository.deleteByInstructor(instructor);
        instructorRepository.delete(instructor);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/work-schedule/new-instructor")
    public ResponseEntity<Instructor> newInstructor(@RequestBody Instructor instructor){
        Instructor newInstructor = instructorRepository.save(instructor);
        if (newInstructor == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newInstructor, HttpStatus.OK);
        }
    }



    @GetMapping("/work-schedule/assignments/{year}/{month}")
    public List<Assignment> getAssignmentsByMonth(@PathVariable int year, @PathVariable int month){
        return assignmentRepository.getAssignmentsByYearMonth(year, month);
    }

    @DeleteMapping("/work-schedule/delete-assignment")
    public ResponseEntity removeAssignment(@RequestBody Assignment assignment){
        if (assignmentRepository.findById(assignment.getId()).isEmpty()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        assignmentRepository.delete(assignment);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/work-schedule/new-assignment")
    public ResponseEntity<Assignment> newAssignment(@RequestBody Assignment assignment){
        Assignment newAssignment = assignmentRepository.save(assignment);
        if (newAssignment == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newAssignment, HttpStatus.OK);
        }
    }
}
