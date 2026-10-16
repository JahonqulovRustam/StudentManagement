import java.util.Comparator;

public enum Columns {
	ID(new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return o1.getId()-o2.getId();
		}
	}),
	NAME(new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}),
	SURNAME(new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return o1.getSurname().compareTo(o2.getSurname());
		}
	}),
	GRADE(new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return Double.compare(o1.getGrade(), o2.getGrade());
		}
	}),
	LEVEL(new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return Integer.compare(o1.getLevel(), o2.getLevel());
		}
	});
	
	private final Comparator<Student> comparator;
	Columns(Comparator<Student> comparator) {
		this.comparator = comparator;
	}
	
	public Comparator<Student> getComparator() {
		return comparator;
	}
}


