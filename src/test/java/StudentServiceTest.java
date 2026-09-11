

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import exceptions.*;

public class StudentServiceTest {
	
	@Test
	void testAddStudent() {
		StudentService students = new StudentService();
		students.addStudent("Rustam", "Jahonqulov", 6.4, 7);
		
		assertAll(
				() -> assertTrue(students.hasId(1)),
				() -> assertEquals("Rustam", students.getNameById(1)),
				() -> assertEquals("Jahonqulov", students.getSurnameById(1)),
				() -> assertEquals(6.4, students.getGradeById(1)),
				() -> assertEquals(7, students.getLevelById(1))
		);
	}
	
	@Test
	void testAddMultipleStudent() {
		StudentService students = new StudentService();
		students.addStudent("Rustam", "Jahonqulov", 4.2, 7);
		students.addStudent("Abror", "Boltayev", 5, 4);
		
		assertAll(
				() -> assertTrue(students.hasId(1)),
				() -> assertEquals("Rustam", students.getNameById(1)),
				() -> assertEquals("Jahonqulov", students.getSurnameById(1)),
				() -> assertEquals(4.2, students.getGradeById(1)),
				() -> assertEquals(7, students.getLevelById(1)),
				
				() -> assertTrue(students.hasId(2)),
				() -> assertEquals("Abror", students.getNameById(2)),
				() -> assertEquals("Boltayev", students.getSurnameById(2)),
				() -> assertEquals(5, students.getGradeById(2)),
				() -> assertEquals(4, students.getLevelById(2))
		);
	}
	
	@Test
	void testAddMoreThanTenStudents() {
		StudentService students = new StudentService();
		students.addStudent("Sirojjon", "Toshmurodov", 3.9, 4);
		students.addStudent("Abror", "Boltayev", 4.5, 5);
		students.addStudent("Abdulaziz", "Botirov", 2, 4);
		students.addStudent("Jamshid", "Aliqulov", 3, 6);
		students.addStudent("Bobur", "Hamidov", 9, 3);
		students.addStudent("Said", "Xolmurodov", 4.5, 8);
		students.addStudent("Bahrom", "Murodov", 5, 4);
		students.addStudent("Raxmon", "Temirov", 4.34, 5);
		students.addStudent("Berdi", "Amonov", 4.567, 5);
		students.addStudent("Saidabbos", "Alisherov", 0, 4);
		
		StudentListFullException exception = assertThrows(StudentListFullException.class, () ->
				students.addStudent("Nozanin", "Malikova", 4.32, 3)
		);
		
		assertEquals(exception.getMessage(), "Ro'yxat to'lgan. Boshqa student qo'shib bo'lmaydi!");
		
	}
	
	@Test
	void testViewAll() {
		StudentService students = new StudentService();
		
		students.addStudent("Sirojjon", "Toshmurodov", 3.9, 4);
		students.addStudent("Abror", "Boltayev", 4.5, 5);
		students.addStudent("Abdulaziz", "Botirov", 2, 4);
		students.addStudent("Jamshid", "Aliqulov", 3, 6);
		
		Student[] actual = students.getAll();
		
		assertAll(
				() -> assertEquals(new Student(1, "Sirojjon", "Toshmurodov", 3.9, 4), actual[0]),
				() -> assertEquals(new Student(2, "Abror", "Boltayev", 4.5, 5), actual[1]),
				() -> assertEquals(new Student(3, "Abdulaziz", "Botirov", 2.0, 4), actual[2]),
				() -> assertEquals(new Student(4, "Jamshid", "Aliqulov", 3.0, 6), actual[3])
		);
	}
	
	@Test
	void testViewStudentById() {
		StudentService students = new StudentService();
		
		students.addStudent("Sirojjon", "Toshmurodov", 3.9, 4);
		students.addStudent("Abror", "Boltayev", 4.5, 5);
		students.addStudent("Abdulaziz", "Botirov", 2, 4);
		students.addStudent("Jamshid", "Aliqulov", 3, 6);
		
		assertAll(
				() -> assertEquals(
						new Student(1, "Sirojjon", "Toshmurodov", 3.9, 4),
						students.getStudentById(1)
				),
				() -> assertEquals(
						new Student(2, "Abror", "Boltayev", 4.5, 5),
						students.getStudentById(2)
				),
				() -> assertEquals(
						new Student(3, "Abdulaziz", "Botirov", 2.0, 4),
						students.getStudentById(3)
				),
				() -> assertEquals(
						new Student(4, "Jamshid", "Aliqulov", 3.0, 6),
						students.getStudentById(4)
				)
		);
	}
	
	@Test
	void testUpdateStudent() {
		StudentService students = new StudentService();
		
		students.addStudent("Ali", "Valiyev", 80, 1);
		
		students.updateStudentById(1, "Rustam", "Jahonqulov", 99.5, 3);
		
		
		assertEquals("Rustam", students.getNameById(1));
		assertEquals("Jahonqulov", students.getSurnameById(1));
		assertEquals(99.5, students.getGradeById(1));
		assertEquals(3, students.getLevelById(1));
		
	}
	
	@Test
	void testDeleteStudent() {
		StudentService students = new StudentService();
		
		students.addStudent("Ali", "Valiyev", 90, 1);
		students.addStudent("Vali", "Karimov", 85, 2);
		students.addStudent("Alibek", "Hamroyev", 4.32, 5);
		
		assertTrue(students.deleteStudentById(2));
		
		assertAll(
				() -> assertEquals(2, students.size()),
				() -> assertTrue(students.hasId(1)),
				() -> assertFalse(students.hasId(2)),
				() -> assertTrue(students.hasId(3))
		);
	}
	
	@Test
	void testHasID() {
		StudentService students = new StudentService();
		
		students.addStudent("Rustam", "Jahonqulov", 95, 2);
		
		assertAll(
				() -> assertTrue(students.hasId(1)),
				() -> assertFalse(students.hasId(100))
		);
	}
	
	@Test
	void testSize() {
		StudentService students = new StudentService();
		
		assertEquals(0, students.size());
		
		students.addStudent("Ali", "Valiyev", 90, 1);
		students.addStudent("Vali", "Karimov", 80, 2);
		
		assertEquals(2, students.size());
	}
	
	
	@Test
	void testSearch() {
		
		assertTrue(students.deleteStudentById(2));
		assertTrue(students.deleteStudentById(9));
		students.addStudent("Azimjon", "Rasulov", 3.25, 4);
		
		assertAll(
				() -> assertEquals(students.search("Sirojjon", null, null, null), List.of(1)),
				() -> assertEquals(students.search(null, "Xolmurodov", null, null), List.of(6)),
				() -> assertEquals(students.search(null, null, 10.0, null), List.of()),
				() -> assertEquals(students.search(null, null, null, 4), List.of(1, 11, 3, 7, 10))
		);
	}
	
	private StudentService students;
	
	@BeforeEach
	void setUp() {
		students = new StudentService();
		
		students.addStudent("Sirojjon", "Toshmurodov", 3.9, 4);
		students.addStudent("Abror", "Boltayev", 4.5, 5);
		students.addStudent("Abdulaziz", "Botirov", 2, 4);
		students.addStudent("Jamshid", "Temirov", 3, 6);
		students.addStudent("Abror", "Hamidov", 9, 3);
		students.addStudent("Said", "Xolmurodov", 4.5, 8);
		students.addStudent("Bahrom", "Murodov", 5, 4);
		students.addStudent("Bahrom", "Temirov", 4.34, 5);
		students.addStudent("Said", "Amonov", 4.567, 5);
		students.addStudent("Saidabbos", "Alisherov", 0, 4);
	}
	
	@Test
	void testSortById() {
		
		Student[] sorted = students.sort(Columns.ID);
		
		Student[] expected = {
				new Student(1,  "Sirojjon",  "Toshmurodov", 3.9,   4),
				new Student(2,  "Abror",     "Boltayev",     4.5,   5),
				new Student(3,  "Abdulaziz", "Botirov",      2.0,   4),
				new Student(4,  "Jamshid",   "Temirov",      3.0,   6),
				new Student(5,  "Abror",     "Hamidov",      9.0,   3),
				new Student(6,  "Said",      "Xolmurodov",   4.5,   8),
				new Student(7,  "Bahrom",    "Murodov",      5.0,   4),
				new Student(8,  "Bahrom",    "Temirov",      4.34,  5),
				new Student(9,  "Said",      "Amonov",       4.567, 5),
				new Student(10, "Saidabbos", "Alisherov",    0.0,   4)
		};
		
		assertArrayEquals(expected, sorted);
	}
	
	
	@Test
	void testSortByName() {
		
		Student[] sorted = students.sort(Columns.NAME);
		
		Student[] expected = {
				new Student(3,  "Abdulaziz", "Botirov",      2.0,   4),
				new Student(2,  "Abror",     "Boltayev",     4.5,   5),
				new Student(5,  "Abror",     "Hamidov",      9.0,   3),
				new Student(7,  "Bahrom",    "Murodov",      5.0,   4),
				new Student(8,  "Bahrom",    "Temirov",      4.34,  5),
				new Student(4,  "Jamshid",   "Temirov",      3.0,   6),
				new Student(6,  "Said",      "Xolmurodov",   4.5,   8),
				new Student(9,  "Said",      "Amonov",       4.567, 5),
				new Student(10, "Saidabbos", "Alisherov",    0.0,   4),
				new Student(1,  "Sirojjon",  "Toshmurodov", 3.9,   4)
		};
		
		assertArrayEquals(expected, sorted);
	}
	
	
	@Test
	void testSortBySurname() {
		
		Student[] sorted = students.sort(Columns.SURNAME);
		
		Student[] expected = {
				new Student(10, "Saidabbos", "Alisherov",    0.0,   4),
				new Student(9,  "Said",      "Amonov",       4.567, 5),
				new Student(2,  "Abror",     "Boltayev",     4.5,   5),
				new Student(3,  "Abdulaziz", "Botirov",      2.0,   4),
				new Student(5,  "Abror",     "Hamidov",      9.0,   3),
				new Student(7,  "Bahrom",    "Murodov",      5.0,   4),
				new Student(4,  "Jamshid",   "Temirov",      3.0,   6),
				new Student(8,  "Bahrom",    "Temirov",      4.34,  5),
				new Student(1,  "Sirojjon",  "Toshmurodov", 3.9,   4),
				new Student(6,  "Said",      "Xolmurodov",   4.5,   8)
		};
		
		assertArrayEquals(expected, sorted);
	}
	
	
	@Test
	void testSortByGrade() {
		
		Student[] sorted = students.sort(Columns.GRADE);
		
		Student[] expected = {
				new Student(10, "Saidabbos", "Alisherov",    0.0,   4),
				new Student(3,  "Abdulaziz", "Botirov",      2.0,   4),
				new Student(4,  "Jamshid",   "Temirov",      3.0,   6),
				new Student(1,  "Sirojjon",  "Toshmurodov", 3.9,   4),
				new Student(8,  "Bahrom",    "Temirov",      4.34,  5),
				new Student(2,  "Abror",     "Boltayev",     4.5,   5),
				new Student(6,  "Said",      "Xolmurodov",   4.5,   8),
				new Student(9,  "Said",      "Amonov",       4.567, 5),
				new Student(7,  "Bahrom",    "Murodov",      5.0,   4),
				new Student(5,  "Abror",     "Hamidov",      9.0,   3)
		};
		
		assertArrayEquals(expected, sorted);
	}
	
	
	@Test
	void testSortByLevel() {
		
		Student[] sorted = students.sort(Columns.LEVEL);
		
		Student[] expected = {
				new Student(5,  "Abror",     "Hamidov",      9.0,   3),
				new Student(1,  "Sirojjon",  "Toshmurodov", 3.9,   4),
				new Student(3,  "Abdulaziz", "Botirov",      2.0,   4),
				new Student(7,  "Bahrom",    "Murodov",      5.0,   4),
				new Student(10, "Saidabbos", "Alisherov",    0.0,   4),
				new Student(2,  "Abror",     "Boltayev",     4.5,   5),
				new Student(8,  "Bahrom",    "Temirov",      4.34,  5),
				new Student(9,  "Said",      "Amonov",       4.567, 5),
				new Student(4,  "Jamshid",   "Temirov",      3.0,   6),
				new Student(6,  "Said",      "Xolmurodov",   4.5,   8)
		};
		
		assertArrayEquals(expected, sorted);
	}
	
	
}
