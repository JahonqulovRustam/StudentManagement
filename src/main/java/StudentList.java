

import java.util.*;
import exceptions.*;


enum Columns {
	ID, NAME, SURNAME, GRADE, LEVEL
}


public class StudentList {
	
	private int rows = 0;
	private int nextID = 1;
	private static final int CAPACITY = 10;
	
	private final Object[][] students = new Object[CAPACITY][5];
	
	
	public int size() {
		return rows;
	}
	
	public String getNameById(int id) {
		
		return (String) getStudentById(id)[1];
	}
	
	public void setNameById(int id, String name) {
		
		getStudentById(id)[1] = name;
	}
	
	public String getSurnameById(int id) {
		
		return (String) getStudentById(id)[2];
	}
	
	public void setSurnameById(int id, String surname) {
		
		getStudentById(id)[2] = surname;
	}
	
	public double getGradeById(int id) {
		
		return (double) getStudentById(id)[3];
	}
	
	public void setGradeById(int id, double grade) {
		
		getStudentById(id)[3] = grade;
	}
	
	public int getLevelById(int id) {
		
		return (int) getStudentById(id)[4];
	}
	
	public void setLevelById(int id, int level) {
		
		getStudentById(id)[4] = level;
	}
	
	
	public void addStudent(String name, String surname, double grade, int level) {
		
		if (size() == CAPACITY) {
			throw new StudentListFullException("Ro'yxat to'lgan. Boshqa student qo'shib bo'lmaydi!");
		}
		
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i][0] == null) {
				students[i] = new Object[] {
						nextID++,
						name,
						surname,
						grade,
						level
				};
				
				rows++;
				break;
			}
		}
	}
	
	public Object[][] getAll() {
		Object[][] list = new Object[size()][5];
		int index = 0;
		
		for (int i = 0; i < CAPACITY; i++) {
			
			if (students[i][0] != null) {
				
				list[index++] = Arrays.copyOf(students[i], students[i].length);
			}
		}
		
		return list;
	}
	
	public Object[] getStudentById(int id) {
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i][0] != null && (int) students[i][0] == id) {
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
			getStudentById(id)[0] = null;
			rows--;
			
			return true;
		}
		
		return false;
	}
	
	public boolean hasId(int id) {
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i][0] != null && (int) students[i][0] == id) {
				return true;
			}
		}
		return false;
	}
	
	
	public Object[][] sort(Columns column) {
		
		Object[][] sorted = getAll();
		
		for (int i = 0; i < sorted.length; i++) {
			
			boolean swapped = false;
			
			for (int j = 0; j < sorted.length - i - 1; j++) {
				
				boolean shouldSwap = false;
				
				switch (column) {
					
					case ID:
						shouldSwap = (int) sorted[j][0] > (int) sorted[j+1][0];
						break;
					
					case NAME:
						shouldSwap = sorted[j][1].toString().compareTo(sorted[j+1][1].toString()) > 0;
						break;
					
					case SURNAME:
						shouldSwap = sorted[j][2].toString().compareTo(sorted[j+1][2].toString()) > 0;
						break;
					
					case GRADE:
						shouldSwap = (double) sorted[j][3] > (double) sorted[j+1][3];
						break;
					
					case LEVEL:
						shouldSwap = (int) sorted[j][4] > (int) sorted[j+1][4];
						break;
				}
				
				if (shouldSwap) {
					Object[] temp = sorted[j];
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
		
		Object[][] objects = getAll();
		List<Integer> ids = new ArrayList<>();
		
		for (int i = 0; i < objects.length; i++){
			
			if (name != null && objects[i][1].toString().toLowerCase().contains(name.toLowerCase())) {
				
				ids.add((Integer) objects[i][0]);
			}
			
			if (surname != null && objects[i][2].toString().toLowerCase().startsWith(surname.toLowerCase())) {
				
				ids.add((Integer) objects[i][0]);
			}
			
			if (grade != null && (double)objects[i][3] == grade) {
				
				ids.add((Integer) objects[i][0]);
			}
			
			if (level != null && (int) objects[i][4] == level) {
				
				ids.add((Integer) objects[i][0]);
			}
		}
		
		return ids;
	}
}

