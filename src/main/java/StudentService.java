

import exceptions.*;

import java.util.ArrayList;
import java.util.List;


public class StudentService {
	
	private int rows = 0;
	private int nextID = 1;
	private static final int CAPACITY = 10;
	
	private final Student[] students = new Student[CAPACITY];
	
	public int size() {
		return rows;
	}
	
	public String getNameById(int id) {
		
		return getStudentById(id).getName();
	}
	
	public void setNameById(int id, String name) {
		
		getStudentById(id).setName(name);
	}
	
	public String getSurnameById(int id) {
		
		return getStudentById(id).getSurname();
	}
	
	public void setSurnameById(int id, String surname) {
		
		getStudentById(id).setSurname(surname);
	}
	
	public double getGradeById(int id) {
		
		return getStudentById(id).getGrade();
	}
	
	public void setGradeById(int id, double grade) {
		
		getStudentById(id).setGrade(grade);
	}
	
	public int getLevelById(int id) {
		
		return getStudentById(id).getLevel();
	}
	
	public void setLevelById(int id, int level) {
		
		getStudentById(id).setLevel(level);
	}
	
	
	public void addStudent(String name, String surname, double grade, int level) {
		
		if (size() == CAPACITY) {
			throw new StudentListFullException("Ro'yxat to'lgan. Boshqa student qo'shib bo'lmaydi!");
		}
		
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i] == null) {
				students[i] = new Student(
						nextID++,
						name,
						surname,
						grade,
						level);
				
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
				
				list[index++] = students[i];
			}
		}
		
		return list;
	}

	public Student getStudentById(int id) {
		
		for (int i = 0; i < CAPACITY; i++) {
			
			if (students[i] != null && students[i].getId() == id) {
				
				return students[i];
			}
		}
		
		throw new StudentNotFoundException("Bunday student topilmadi!");
	}
	
	public void updateStudentById(int id, String name, String surname, double grade, int level) {
		setNameById(id, name);
		setSurnameById(id, surname);
		setGradeById(id, grade);
		setLevelById(id, level);
	}
	
	public boolean deleteStudentById(int id) {
		
		if (hasId(id)) {
			
			for (int i = 0; i < CAPACITY; i++) {
				
				if (students[i] != null && students[i].getId() == id) {
					students[i] = null;
				}
			}
			
			rows--;
			
			return true;
		}
		
		return false;
	}
	
	public boolean hasId(int id) {
		
		for (int i = 0; i < CAPACITY; i++) {
			
			if (students[i] != null && students[i].getId() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public Student[] sort(Columns column) {
		
		Student[] sorted = getAll();
		
		for (int i = 0; i < sorted.length; i++) {
			
			boolean swapped = false;
			
			for (int j = 0; j < sorted.length - i - 1; j++) {
				
				boolean shouldSwap = false;
				
				switch (column) {
					
					case ID:
						shouldSwap = sorted[j].getId() > sorted[j+1].getId();
						break;
					
					case NAME:
						shouldSwap = sorted[j].getName().compareTo(sorted[j+1].getName()) > 0;
						break;
					
					case SURNAME:
						shouldSwap = sorted[j].getSurname().compareTo(sorted[j+1].getSurname()) > 0;
						break;
					
					case GRADE:
						shouldSwap = sorted[j].getGrade() > sorted[j+1].getGrade();
						break;
					
					case LEVEL:
						shouldSwap = sorted[j].getLevel() > sorted[j+1].getLevel();
						break;
				}
				
				if (shouldSwap) {
					Student temp = sorted[j];
					sorted[j] = sorted[j+1];
					sorted[j+1] = temp;
					swapped = true;
				}
			}
			
			if (!swapped) {
				break;
			}
		}
		
		return sorted;
	}
	
	public List<Integer> search(String name, String surname, Double grade, Integer level) {
		
		Student[] objects = getAll();
		List<Integer> ids = new ArrayList<>();
		
		for (int i = 0; i < objects.length; i++){
			
			if (name != null && objects[i].getName().toLowerCase().contains(name.toLowerCase())) {
				
				ids.add(objects[i].getId());
			}
			
			if (surname != null && objects[i].getSurname().toLowerCase().startsWith(surname.toLowerCase())) {
				
				ids.add(objects[i].getId());
			}
			
			if (objects[i].getGrade().equals(grade)) {
				
				ids.add(objects[i].getId());
			}
			
			if (objects[i].getLevel().equals(level)) {
				
				ids.add(objects[i].getId());
			}
		}
		
		return ids;
	}
}

