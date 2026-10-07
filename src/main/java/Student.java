import java.util.Objects;

public class Student implements Cloneable {
	
	private Integer id;
	private String name;
	private String surname;
	private Double grade;
	private Integer level;
	
	public Student(String name, String surname, Double grade, Integer level) {
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
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		if (this.id == null) {
			this.id = id;
			return;
		}
		
		throw new RuntimeException("Can't change ID!");
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Student student = (Student) o;
		
		return Double.compare(grade, student.grade) == 0
				&& level.equals(student.level)
				&& Objects.equals(name.toLowerCase(), student.name.toLowerCase())
				&& Objects.equals(surname.toLowerCase(), student.surname.toLowerCase());
	}
	
	@Override
	public String toString() {
		return String.format(
				"%-5d %-15s %-15s %-8.2f %-5d",
				id,
				name,
				surname,
				grade,
				level
		);
	}
	
	@Override
	public Student clone() {
		try {
			// TODO: copy mutable state here, so the clone can't change the internals of the original
			return (Student) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
