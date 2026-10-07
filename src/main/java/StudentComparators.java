import java.util.Comparator;

public final class StudentComparators {
	private StudentComparators() {}
	
	public static final Comparator<Student> BY_ID = Comparator.comparingInt(Student::getId);
	
	public static final Comparator<Student> BY_NAME = Comparator.comparing(Student::getName);
	
	public static final Comparator<Student> BY_SURNAME = Comparator.comparing(Student::getSurname);
	
	public static final Comparator<Student> BY_GRADE = Comparator.comparingDouble(Student::getGrade);
	
	public static final Comparator<Student> BY_LEVEL = Comparator.comparingInt(Student::getLevel);
}
