import java.util.Comparator;

public enum Columns implements Comparator<Student> {
	ID {
		@Override
		public int compare(Student o1, Student o2) {
			return Integer.compare(o1.getId(), o2.getId());
		}
	},
	NAME {
		@Override
		public int compare(Student o1, Student o2) {
			return o1.getName().compareTo(o2.getName());
		}
	},
	SURNAME {
		@Override
		public int compare(Student o1, Student o2) {
			return o1.getSurname().compareTo(o2.getSurname());
		}
	},
	GRADE {
		@Override
		public int compare(Student o1, Student o2) {
			return Double.compare(o1.getGrade(), o2.getGrade());
		}
	},
	LEVEL {
		@Override
		public int compare(Student o1, Student o2) {
			return Integer.compare(o1.getLevel(), o2.getLevel());
		}
	}
}


