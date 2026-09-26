import java.util.Objects;

public class Student {
	
	private final Integer id;
	private String name;
	private String surname;
	private Double grade;
	private Integer level;
	private static int counter = 1;
	
	public Student(String name, String surname, Double grade, Integer level) {
		this.id = counter++;
		this.name = name;
		this.surname = surname;
		this.grade = grade;
		this.level = level;
	}
	
	//this is for student copy
	public Student(Student other) {
		this.id = other.id;
		this.name = other.name;
		this.surname = other.surname;
		this.grade = other.grade;
		this.level = other.level;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	
	public Double getGrade() {
		return grade;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public Integer getId() {
		return id;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Student student = (Student) o;
		
		return id.equals(student.id)
				&& Double.compare(grade, student.grade) == 0
				&& level.equals(student.level)
				&& Objects.equals(name, student.name)
				&& Objects.equals(surname, student.surname);
	}
}
