
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class StudentStorage {
	
	private static final Path FILENAME = Paths.get("StudentList.txt");
	private static final String DELIMITER = ",";
	
	public void saveToFile(Student[] students) {
		
		try (BufferedWriter writer = Files.newBufferedWriter(FILENAME)) {
			
			for (Student student : students) {
				if (student == null) continue;
				
				writer.write(String.join(DELIMITER,
						student.getId().toString(),
						student.getName(),
						student.getSurname(),
						student.getGrade().toString(),
						student.getLevel().toString()));
				
				writer.newLine();
			}
			
		} catch (IOException e) {
			System.out.println("Faylga yozishda xatolik: " + e.getMessage());
		}
	}
	
	public StudentList loadFromFile() {
		
		StudentList students = new StudentList();
		
		if (!Files.exists(FILENAME)) {
			return students;
		}
		
		try (BufferedReader reader = Files.newBufferedReader(FILENAME)) {
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				if (line.isBlank()) {
					continue;
				}
				
				String[] student = line.split(DELIMITER);
				
				Student s = new Student(
						student[1],
						student[2],
						Double.parseDouble(student[3]),
						Integer.parseInt(student[4])
				);
				
				students.addStudent(s);
			}
			
		} catch (IOException e) {
			System.out.println("Fayldan olishda xatolik: " + e.getMessage());
		}
		
		return students;
	}
}