import java.util.Objects;

public class Student {
	
	private Integer id;
	private String name;
	private String surname;
	private Double grade;
	private Integer level;
	
	public Student() {
	
	}
	public Student(Integer id, String name, String surname, Double grade, Integer level) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.grade = grade;
		this.level = level;
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
	
	public void setId(Integer id) {
		this.id = id;
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
