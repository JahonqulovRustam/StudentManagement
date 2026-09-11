
import java.io.*;

public class StudentStorage {

	private static final String FILENAME = "C:\\Users\\user\\OneDrive\\Desktop\\StudentList.txt";
	private static final String DELIMITER = ",";

	public void saveToFile(StudentService list) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {

			Student[] students = list.getAll();

			for (Student student : students) {

				String line = student.getId() + DELIMITER + student.getName() + DELIMITER + student.getSurname() + DELIMITER + student.getGrade() + DELIMITER + student.getLevel();
				writer.write(line);
				writer.newLine();
			}

		} catch (IOException e) {
			System.out.println("Faylga yozishda xatolik: " + e.getMessage());
		}
	}

	public StudentService loadFromFile() {

		StudentService students = new StudentService();

		try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {

			String line;
			while ((line = reader.readLine()) != null) {
				String[] student = line.split(DELIMITER);
				students.addStudent(student[1], student[2], Double.parseDouble(student[3]), Integer.parseInt(student[4]));
			}

		} catch (IOException e) {
			System.out.println("Fayldan olishda xatolik: " + e.getMessage());
		}

		return students;
	}
}
