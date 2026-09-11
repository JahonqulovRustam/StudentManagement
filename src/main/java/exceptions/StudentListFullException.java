package exceptions;

public class StudentListFullException extends RuntimeException {
	public StudentListFullException(String message) {
		super(message);
	}
}
