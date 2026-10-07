import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import exceptions.*;

public class StudentListTest {

	@Test
	void testAddStudent() {
		StudentList students = new StudentList();
		Student student = new Student("Rustam", "Jahonqulov", 6.4, 7);
		
		assertEquals(0, students.size());
		
		students.addStudent(student);
		
		assertEquals(1, students.size());
	}

	@Test
	void add() {

		StudentList list = new StudentList();

		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
				list.addStudent(null));

		assertEquals("Student null bo'lishi mumkin emas!", e.getMessage());
	}
	
	@Test
	void testAddDuplicateStudent() {
		StudentList students = new StudentList();
		
		Student student1 = new Student("Rustam", "Jahonqulov", 7.0, 3);
		students.addStudent(student1);
		Student student2 = new Student("Alisher", "Karimov", 5.6, 2);
		students.addStudent(student2);
		Student student3 = new Student("Rustam", "Jahonqulov", 7.0, 3);
		
		DuplicateStudentException exception = assertThrows(DuplicateStudentException.class, () ->
				students.addStudent(student3)
		);
		
		assertEquals("Ushbu student allaqachon mavjud!", exception.getMessage());
	}

	@Test
	void testGetAllVulnerability() {
		StudentList students = new StudentList();

		students.addStudent(new Student("Rustam", "Jahonqulov", 6.4, 7));
		students.addStudent(new Student("Ruslan", "Sharipov", 6.0, 9));

		List<Student> allStudents = students.getAll();
		allStudents.set(0, null);
		assertNotNull(students.getStudentById(1));
	}

	@Test
	void testViewAll() {
		StudentList students = new StudentList();

		students.addStudent(new Student("Sirojjon", "Toshmurodov", 3.9, 4));
		students.addStudent(new Student("Abror", "Boltayev", 4.5, 5));
		students.addStudent(new Student("Abdulaziz", "Botirov", 2.0, 4));
		students.addStudent(new Student("Jamshid", "Aliqulov", 3.0, 6));

		List<Student> actual = students.getAll();

		assertAll(
				() -> assertEquals(new Student( "Sirojjon", "Toshmurodov", 3.9, 4), actual.getFirst()),
				() -> assertEquals(new Student( "Abror", "Boltayev", 4.5, 5), actual.get(1)),
				() -> assertEquals(new Student( "Abdulaziz", "Botirov", 2.0, 4), actual.get(2)),
				() -> assertEquals(new Student( "Jamshid", "Aliqulov", 3.0, 6), actual.getLast())
		);
	}

	@Test
	void testGetStudentById() {
		StudentList students = new StudentList();

		students.addStudent(new Student("Sirojjon", "Toshmurodov", 3.9, 4));
		students.addStudent(new Student("Abror", "Boltayev", 4.5, 5));
		students.addStudent(new Student("Abdulaziz", "Botirov", 2.0, 4));
		students.addStudent(new Student("Jamshid", "Aliqulov", 3.0, 6));

		assertAll(
				() -> assertEquals(
						new Student("Sirojjon", "Toshmurodov", 3.9, 4),
						students.getStudentById(1)
				),
				() -> assertEquals(
						new Student("Abror", "Boltayev", 4.5, 5),
						students.getStudentById(2)
				),
				() -> assertEquals(
						new Student("Abdulaziz", "Botirov", 2.0, 4),
						students.getStudentById(3)
				),
				() -> assertEquals(
						new Student( "Jamshid", "Aliqulov", 3.0, 6),
						students.getStudentById(4)
				)
		);
	}

	@Test
	void testUpdateStudent() {
		StudentList students = new StudentList();

		students.addStudent(new Student("Ali", "Valiyev", 80.0, 1));
		Student student = new Student("Rustam", "Jahonqulov", 99.5, 3);

		students.updateStudentById(1, student);

		assertEquals(student, students.getStudentById(1));
	}

	@Test
	@DisplayName("Update throws when the new data duplicates another student")
	void testUpdateStudent_throwsOnDuplicate() {
		StudentList students = new StudentList();

		students.addStudent(new Student("Sirojjon", "Toshmurodov", 3.9, 4));
		students.addStudent(new Student("Abror", "Boltayev", 4.5, 5));
		Student student = new Student("Sirojjon", "Toshmurodov", 3.9, 4);

		DuplicateStudentException exception = assertThrows(DuplicateStudentException.class, () ->
				students.updateStudentById(2, student)
		);

		assertEquals("Ushbu student allaqachon mavjud!", exception.getMessage());
	}

	@Test
	void testDeleteStudent() {
		StudentList students = new StudentList();

		students.addStudent(new Student("Ali", "Valiyev", 90.0, 1));
		students.addStudent(new Student("Vali", "Karimov", 85.0, 2));
		students.addStudent(new Student("Alibek", "Hamroyev", 4.32, 5));

		assertTrue(students.deleteStudentById(2));
		assertEquals(2, students.size());
	}

	@Test
	void deleteNotFoundStudent() {
		StudentList students = new StudentList();

		assertFalse(students.deleteStudentById(1));
	}

	@Test
	void testSize() {
		StudentList students = new StudentList();

		assertEquals(0, students.size());

		students.addStudent(new Student("Ali", "Valiyev", 90.0, 1));
		students.addStudent(new Student("Vali", "Karimov", 80.0, 2));

		assertEquals(2, students.size());
	}

	StudentList students;

	@BeforeEach
	void setUp() {
		students = new StudentList();

		students.addStudent(new Student("Sirojjon", "Toshmurodov", 3.9, 4));
		students.addStudent(new Student("Abror", "Boltayev", 4.5, 5));
		students.addStudent(new Student("Abdulaziz", "Botirov", 2.0, 4));
		students.addStudent(new Student("Jamshid", "Temirov", 3.0, 6));
		students.addStudent(new Student("Abror", "Hamidov", 9.45, 3));
		students.addStudent(new Student("Said", "Xolmurodov", 4.5, 8));
		students.addStudent(new Student("Bahrom", "Murodov", 5.0, 4));
		students.addStudent(new Student("Bahrom", "Temirov", 4.34, 5));
		students.addStudent(new Student("Said", "Amonov", 4.567, 5));
		students.addStudent(new Student("Saidabbos", "Alisherov", 0.0, 4));
	}

	@Test
	void testSearch() {

		assertAll(
				() -> assertEquals(students.search("Sirojjon", null, null, null),
						List.of(new Student("Sirojjon", "Toshmurodov", 3.9, 4))),
				() -> assertEquals(students.search(null, "Xolmurodov", null, null),
						List.of(new Student("Said", "Xolmurodov", 4.5, 8))),
				() -> assertEquals(students.search(null, null, 10.0, null),
						List.of()),
				() -> assertEquals(students.search(null, null, null, 4),
						List.of(new Student("Sirojjon", "Toshmurodov", 3.9, 4),
								new Student("Abdulaziz", "Botirov", 2.0, 4),
								new Student("Bahrom", "Murodov", 5.0, 4),
								new Student("Saidabbos", "Alisherov", 0.0, 4)
						))
		);
	}

	@Test
	void testSortById() {

		List<Student> sorted = students.sort(Columns.ID);

		List<Student> expected = List.of(
				new Student("Sirojjon",  "Toshmurodov", 3.9,   4),
				new Student("Abror",     "Boltayev",     4.5,   5),
				new Student("Abdulaziz", "Botirov",      2.0,   4),
				new Student("Jamshid",   "Temirov",      3.0,   6),
				new Student("Abror",     "Hamidov",      9.45,   3),
				new Student("Said",      "Xolmurodov",   4.5,   8),
				new Student("Bahrom",    "Murodov",      5.0,   4),
				new Student("Bahrom",    "Temirov",      4.34,  5),
				new Student("Said",      "Amonov",       4.567, 5),
				new Student("Saidabbos", "Alisherov",    0.0,   4)
		);

		assertEquals(expected, sorted);
	}

	@Test
	void testSortByName() {

		List<Student> sorted = students.sort(Columns.NAME);

		List<Student> expected = List.of(
				new Student("Abdulaziz", "Botirov",      2.0,   4),
				new Student("Abror",     "Boltayev",     4.5,   5),
				new Student("Abror",     "Hamidov",      9.45,   3),
				new Student("Bahrom",    "Murodov",      5.0,   4),
				new Student("Bahrom",    "Temirov",      4.34,  5),
				new Student("Jamshid",   "Temirov",      3.0,   6),
				new Student("Said",      "Xolmurodov",   4.5,   8),
				new Student("Said",      "Amonov",       4.567, 5),
				new Student("Saidabbos", "Alisherov",    0.0,   4),
				new Student("Sirojjon",  "Toshmurodov", 3.9,   4)
		);

		assertEquals(expected, sorted);
	}

	@Test
	void testSortBySurname() {

		List<Student> sorted = students.sort(Columns.SURNAME);

		List<Student> expected = List.of(
				new Student("Saidabbos", "Alisherov",    0.0,   4),
				new Student("Said",      "Amonov",       4.567, 5),
				new Student("Abror",     "Boltayev",     4.5,   5),
				new Student("Abdulaziz", "Botirov",      2.0,   4),
				new Student("Abror",     "Hamidov",      9.45,   3),
				new Student("Bahrom",    "Murodov",      5.0,   4),
				new Student("Jamshid",   "Temirov",      3.0,   6),
				new Student("Bahrom",    "Temirov",      4.34,  5),
				new Student("Sirojjon",  "Toshmurodov", 3.9,   4),
				new Student("Said",      "Xolmurodov",   4.5,   8)
		);

		assertEquals(expected, sorted);
	}

	@Test
	void testSortByGrade() {

		List<Student> sorted = students.sort(Columns.GRADE);

		List<Student> expected = List.of(
				new Student("Saidabbos", "Alisherov",    0.0,   4),
				new Student("Abdulaziz", "Botirov",      2.0,   4),
				new Student("Jamshid",   "Temirov",      3.0,   6),
				new Student("Sirojjon",  "Toshmurodov", 3.9,   4),
				new Student("Bahrom",    "Temirov",      4.34,  5),
				new Student("Abror",     "Boltayev",     4.5,   5),
				new Student("Said",      "Xolmurodov",   4.5,   8),
				new Student("Said",      "Amonov",       4.567, 5),
				new Student("Bahrom",    "Murodov",      5.0,   4),
				new Student("Abror",     "Hamidov",      9.45,   3)
		);

		assertEquals(expected, sorted);
	}

	@Test
	void testSortByLevel() {

		List<Student> sorted = students.sort(Columns.LEVEL);

		List<Student> expected = List.of(
				new Student("Abror",     "Hamidov",      9.45,   3),
				new Student("Sirojjon",  "Toshmurodov", 3.9,   4),
				new Student("Abdulaziz", "Botirov",      2.0,   4),
				new Student("Bahrom",    "Murodov",      5.0,   4),
				new Student("Saidabbos", "Alisherov",    0.0,   4),
				new Student("Abror",     "Boltayev",     4.5,   5),
				new Student("Bahrom",    "Temirov",      4.34,  5),
				new Student("Said",      "Amonov",       4.567, 5),
				new Student("Jamshid",   "Temirov",      3.0,   6),
				new Student("Said",      "Xolmurodov",   4.5,   8)
		);

		assertEquals(expected, sorted);
	}
}
