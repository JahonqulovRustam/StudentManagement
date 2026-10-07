

import exceptions.*;
import java.util.*;

public class StudentList {
	
	private int nextId = 0;
	private final List<Student> students = new ArrayList<>();
	
	public int size() {
		return students.size();
	}
	
	public void addStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Student null bo'lishi mumkin emas!");
		}
		
		if (isDuplicateStudent(student)) {
			throw new DuplicateStudentException("Ushbu student allaqachon mavjud!");
		}
		
		student.setId(++nextId);
		students.add(student);
	}
	
	public List<Student> getAll() {
		List<Student> list = new ArrayList<>();
		
		for (Student student : students) {
			list.add(student.clone());
		}
		
		return list;
	}

	public Student getStudentById(int id) {
		for (Student student : students) {
			if (student.getId().equals(id)) {
				return student.clone();
			}
		}
		
		throw new StudentNotFoundException("Bunday student topilmadi!");
	}
	
	public void updateStudentById(int id, Student student) {
		
		if (isDuplicateStudent(student)) {
			throw new DuplicateStudentException("Ushbu student allaqachon mavjud!");
		}
		
		for (Student st : students) {
			if (st.getId().equals(id)) {
				st.setName(student.getName());
				st.setSurname(student.getSurname());
				st.setGrade(student.getGrade());
				st.setLevel(student.getLevel());
				return;
			}
		}
		
		throw new StudentNotFoundException("Bunday student topilmadi!");
	}
	
	public boolean deleteStudentById(int id) {
		return students.removeIf(
				student -> student.getId().equals(id)
		);
	}
	
	public List<Student> sort(Columns column) {
		List<Student> sorted = new ArrayList<>(students);
		
		switch (column) {
			case ID -> Collections.sort(sorted, StudentComparators.BY_ID);
			
			case NAME -> Collections.sort(sorted, StudentComparators.BY_NAME);
			
			case SURNAME -> Collections.sort(sorted, StudentComparators.BY_SURNAME);
			
			case GRADE -> Collections.sort(sorted, StudentComparators.BY_GRADE);
			
			case LEVEL -> Collections.sort(sorted, StudentComparators.BY_LEVEL);
		}
		
		return sorted;
	}
	
	public List<Student> search(String name, String surname, Double grade, Integer level) {
		
		List<Student> searchedStudents = new ArrayList<>();
		
		for (Student student : students) {
			
			if (name != null && student.getName().toLowerCase().contains(name.toLowerCase())) {
				
				searchedStudents.add(student.clone());
				
			} else if (surname != null && student.getSurname().toLowerCase().contains(surname.toLowerCase())) {
				
				searchedStudents.add(student.clone());
				
			} else if (student.getGrade().equals(grade)) {
				
				searchedStudents.add(student.clone());
				
			} else if (student.getLevel().equals(level)) {
				
				searchedStudents.add(student.clone());
			}
		}
		
		return searchedStudents;
	}
	
	public boolean isDuplicateStudent(Student student) {
		
		for (Student s : students) {
			if (s == null) continue;
			
			if (!s.getId().equals(student.getId()) && s.equals(student)) {
				return true;
			}
		}
		
		return false;
	}
}
