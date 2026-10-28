package com.miras.studentapi;

import com.miras.studentapi.model.Student;
import com.miras.studentapi.repository.StudentRepository;
import com.miras.studentapi.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentApiApplicationTests {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void contextLoads() {
        System.out.println("=== Test context loaded: Miras Suleimenov | IT2-2313 | ID: 37233 ===");
    }

    @Test
    void testCreateStudent() {
        Student s = new Student("Miras Suleimenov", "IT2-2313", 37233);
        when(studentRepository.save(any(Student.class))).thenReturn(s);

        Student result = studentService.create(s);

        assertEquals("Miras Suleimenov", result.getName());
        assertEquals("IT2-2313", result.getStudentGroup());
        assertEquals(37233, result.getStudentId());
        verify(studentRepository, times(1)).save(s);
    }

    @Test
    void testGetAllStudents() {
        Student s1 = new Student("Miras Suleimenov", "IT2-2313", 37233);
        Student s2 = new Student("Test Student", "IT2-2313", 99999);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Student> students = studentService.getAll();

        assertEquals(2, students.size());
        assertEquals("Miras Suleimenov", students.get(0).getName());
    }

    @Test
    void testGetStudentById_Found() {
        Student s = new Student("Miras Suleimenov", "IT2-2313", 37233);
        s.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(s));

        Optional<Student> result = studentService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Miras Suleimenov", result.get().getName());
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Student> result = studentService.getById(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById(1L);
        assertDoesNotThrow(() -> studentService.delete(1L));
        verify(studentRepository, times(1)).deleteById(1L);
    }
}
