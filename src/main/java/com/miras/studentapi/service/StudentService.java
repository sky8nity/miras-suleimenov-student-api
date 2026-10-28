package com.miras.studentapi.service;

import com.miras.studentapi.model.Student;
import com.miras.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Optional<Student> getById(Long id) {
        return repository.findById(id);
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Long id, Student data) {
        return repository.findById(id).map(existing -> {
            existing.setName(data.getName());
            existing.setStudentGroup(data.getStudentGroup());
            existing.setStudentId(data.getStudentId());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Student not found: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
