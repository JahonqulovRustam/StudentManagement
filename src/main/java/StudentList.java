import exceptions.*;

import java.util.ArrayList;
import java.util.List;


public class StudentList {
	
	private int rows = 0;
	private static final int CAPACITY = 10;
	private int nextId = 0;
	
	private final Student[] students = new Student[CAPACITY];
	
	public int size() {
		return rows;
	}
	
	public void addStudent(Student student) {
		
		if (size() == CAPACITY) {
			throw new StudentListFullException("Ro'yxat to'lgan. Boshqa student qo'shib bo'lmaydi!");
		}
		
		if (isDuplicateStudent(student)) {
			throw new DuplicateStudentException("Ushbu student allaqachon mavjud!");
		}
		
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i] == null) {
				student.setId(++nextId);
				students[i] = student;
				
				rows++;
				break;
			}
		}
	}
	
	public Student[] getAll() {
		Student[] list = new Student[size()];
		int index = 0;
		
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i] != null) {
				list[index++] = students[i].clone();
			}
		}
		
		return list;
	}

	public Student getStudentById(int id) {
		
		for (int i = 0; i < CAPACITY; i++) {
			
			if (students[i] != null && students[i].getId() == id) {
				
				return students[i].clone();
			}
		}
		
		throw new StudentNotFoundException("Bunday student topilmadi!");
	}
	
	public void updateStudentById(int id, Student student) {
		
		if (isDuplicateStudent(student)) {
			throw new DuplicateStudentException("Ushbu student allaqachon mavjud!");
		}
		
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i] != null && students[i].getId() == id) {
				students[i].setName(student.getName());
				students[i].setSurname(student.getSurname());
				students[i].setGrade(student.getGrade());
				students[i].setLevel(student.getLevel());
				
				return;
			}
		}
		
		throw new StudentNotFoundException("Bunday student topilmadi!");
	}
	
	public boolean deleteStudentById(int id) {
			for (int i = 0; i < CAPACITY; i++) {
				
				if (students[i] != null && students[i].getId() == id) {
					students[i] = null;
					
					rows--;
					return true;
				}
			}
		
		return false;
	}
	
	
	public Student[] sort(Columns column) {
		
		Student[] sortedStudents = getAll();
		
		for (int i = 0; i < sortedStudents.length; i++) {
			
			boolean swapped = false;
			
			for (int j = 0; j < sortedStudents.length - i - 1; j++) {
				
				boolean shouldSwap = false;
				
				switch (column) {
					
					case ID ->
						shouldSwap = sortedStudents[j].getId() > sortedStudents[j+1].getId();
					
					case NAME ->
						shouldSwap = sortedStudents[j].getName().compareTo(sortedStudents[j+1].getName()) > 0;
					
					case SURNAME ->
						shouldSwap = sortedStudents[j].getSurname().compareTo(sortedStudents[j+1].getSurname()) > 0;
					
					case GRADE ->
						shouldSwap = sortedStudents[j].getGrade() > sortedStudents[j+1].getGrade();
					
					case LEVEL ->
						shouldSwap = sortedStudents[j].getLevel() > sortedStudents[j+1].getLevel();
				}
				
				if (shouldSwap) {
					Student temp = sortedStudents[j];
					sortedStudents[j] = sortedStudents[j+1];
					sortedStudents[j+1] = temp;
					swapped = true;
				}
			}
			
			if (!swapped) {
				break;
			}
		}
		
		return sortedStudents;
	}
	
	public List<Student> search(String name, String surname, Double grade, Integer level) {
		
		List<Student> searchedStudents = new ArrayList<>();
		
		for (int i = 0; i < CAPACITY; i++) {
			
			if (students[i] == null) continue;
			
			if (name != null && students[i].getName().toLowerCase().contains(name.toLowerCase())) {
				
				searchedStudents.add(students[i].clone());
				
			} else if (surname != null && students[i].getSurname().toLowerCase().contains(surname.toLowerCase())) {
				
				searchedStudents.add(students[i].clone());
				
			} else if (grade != null && students[i].getGrade().equals(grade)) {
				
				searchedStudents.add(students[i].clone());
				
			} else if (level != null && students[i].getLevel().equals(level)) {
				
				searchedStudents.add(students[i].clone());
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
