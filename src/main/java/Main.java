
import java.util.*;
import exceptions.*;

public class Main {
	public static void  main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StudentService students = new StudentService();
		StudentStorage storage = new StudentStorage();
		
		
		while (true) {
			
			System.out.println();
			menu();
			
			System.out.print("\nAmaliyotni bajarish uchun tanlang: ");
			int num = sc.nextInt();
			
			switch (num) {
				
				
				case 1:
					
					try {
						System.out.print("Ismni kiriting: ");
						String name = sc.next();
						
						System.out.print("Familiyani kiriting: ");
						String surname = sc.next();
						
						System.out.print("Reytingini kiriting: ");
						double grade = sc.nextDouble();
						
						System.out.print("Kursini kiriting: ");
						int level = sc.nextInt();
						
						students.addStudent(name, surname, grade, level);
						
						System.out.println("Student muvaffaqiyatli qo'shildi!");
					} catch (StudentListFullException e) {
						System.out.println(e.getMessage());
					}
					break;
				
				
				case 2:
					
					try {
						if (students.size() != 0) {
							
							System.out.printf("%-5s %-15s %-15s %-8s %-5s%n",
									"ID", "Ism", "Familiya", "Reyting", "Kurs");
							
							System.out.println("-------------------------------------------------------");
							
							for (int i = 0; i < students.size(); i++) {
								System.out.printf("%-5d %-15s %-15s %-8.2f %-5d%n",
										students.getAll()[i].getId(),
										students.getAll()[i].getName(),
										students.getAll()[i].getSurname(),
										students.getAll()[i].getGrade(),
										students.getAll()[i].getLevel());
							}
						}
					} catch (StudentNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				
				
				case 3:
					
					try {
						System.out.print("ID ni kiriting: ");
						int id = sc.nextInt();
						
						
						System.out.printf("%-5s %-15s %-15s %-8s %-5s%n",
								"ID", "Ism", "Familiya", "Reyting", "Kurs");
						
						System.out.println("-------------------------------------------------------");
						
						System.out.printf("%-5d %-15s %-15s %-8.2f %-5d%n",
								students.getStudentById(id).getId(),
								students.getStudentById(id).getName(),
								students.getStudentById(id).getSurname(),
								students.getStudentById(id).getGrade(),
								students.getStudentById(id).getLevel());
					} catch (StudentNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				
				
				case 4:
					
					try {
						System.out.print("ID ni kiriting: ");
						int id = sc.nextInt();
						sc.nextLine();
						
						System.out.println("Ismi: " + students.getNameById(id));
						System.out.print("Yangi ismi: ");
						String newName = sc.nextLine();
						
						System.out.println("Familiyasi: " + students.getSurnameById(id));
						System.out.print("Yangi familiyasi: ");
						String newSurname = sc.nextLine();
						
						System.out.println("Reytingi: " + students.getGradeById(id));
						System.out.print("Yangi bahosi: ");
						String newGrade = sc.nextLine();
						
						System.out.println("Kursi: " + students.getLevelById(id));
						System.out.print("Yangi kursi: ");
						String newLevel = sc.nextLine();
						
						students.updateStudentById(
								id,
								newName.isEmpty() ? students.getNameById(id) : newName,
								newSurname.isEmpty() ? students.getSurnameById(id) : newSurname,
								newGrade.isEmpty() ? students.getGradeById(id) : Double.parseDouble(newGrade),
								newLevel.isEmpty() ? students.getLevelById(id) : Integer.parseInt(newLevel)
						);
						
						System.out.println("Student muvaffaqiyatli yangilandi.");
						
					} catch (StudentNotFoundException e) {
						System.out.println(e.getMessage());
					} catch (NumberFormatException e) {
						System.out.println("Son noto'g'ri formatda kiritildi");
					} catch (InputMismatchException e) {
						System.out.println("Noto'gri ma'lumot kiritildi");
					}
					
					break;
				
				
				case 5:
					
					try {
						System.out.print("ID ni kiriting: ");
						int ayd = sc.nextInt();
						
						boolean result = students.deleteStudentById(ayd);
						
						if (result) {
							System.out.println("Ushbu ID elementi muvaffaiyatli o'chirildi!");
						}
					} catch (StudentNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				
				
				case 6:
					
					System.out.println("""
								ID bo'yicha sortlash(1)
								Ism bo'yicha sortlash(2)
								Familiya bo'yicha sortlash(3)
								Reytingi bo'yicha sortlash(4)
								Kursi bo'yicha sortlash(5)
								Tugatish(6)
								""");
					
					System.out.print("Birortasini tanlang: ");
					int a = sc.nextInt();
					
					Student[] sorted = null;
					
					switch (a) {
						case 1:
							sorted = students.sort(Columns.ID);
							break;
						case 2:
							sorted = students.sort(Columns.NAME);
							break;
						case 3:
							sorted = students.sort(Columns.SURNAME);
							break;
						case 4:
							sorted = students.sort(Columns.GRADE);
							break;
						case 5:
							sorted = students.sort(Columns.LEVEL);
							break;
						case 6:
							break;
						default:
							System.out.println("Noto'g'ri tanlov!");
							break;
					}
					
					if (sorted != null) {
						System.out.printf("%-5s %-15s %-15s %-8s %-5s%n",
								"ID", "Ism", "Familiya", "Reyting", "Kurs");
						
						System.out.println("-------------------------------------------------------");
						
						for (Student student : sorted) {
							System.out.printf("%-5d %-15s %-15s %-8.2f %-5d%n",
									student.getId(),
									student.getName(),
									student.getSurname(),
									student.getGrade(),
									student.getLevel());
						}
					}
					
					break;
				
				case 7:
					
					sc.nextLine();
					
					List<Integer> foundStudentIds;
					
					System.out.print("Ism bo'yicha qidirish uchun ismni kiriting: ");
					String name = sc.nextLine();
					
					System.out.print("Familiya bo'yicha qidirish uchun familiyani kiriting: ");
					String surname = sc.nextLine();
					
					System.out.print("Reytingi bo'yicha qidirish uchun reytingni kiriting: ");
					String grade = sc.nextLine();
					
					
					System.out.print("Darajasi bo'yicha qidirish uchun darajani kiriting: ");
					String level = sc.nextLine();
					
					if (!name.isEmpty()) {
						foundStudentIds = students.search(name, null, null, null);
					} else if (!surname.isEmpty()) {
						foundStudentIds = students.search(null, surname, null, null);
					} else if (!grade.isEmpty()) {
						foundStudentIds = students.search(null, null, Double.parseDouble(grade), null);
					} else {
						foundStudentIds = students.search(null, null, null, Integer.parseInt(level));
					}
					
					System.out.printf("%-5s %-15s %-15s %-8s %-5s%n",
							"ID", "Ism", "Familiya", "Reyting", "Kurs");
					
					System.out.println("-------------------------------------------------------");
					
					for (int i = 0; i < foundStudentIds.size(); i++) {
						int ids = foundStudentIds.get(i);
						
						Student student = students.getStudentById(ids);
						System.out.printf("%-5d %-15s %-15s %-8.2f %-5d%n",
								student.getId(),
								student.getName(),
								student.getSurname(),
								student.getGrade(),
								student.getLevel());
					}
					
					break;
				
				
				case 8:
					
					storage.saveToFile(students);
					System.out.println("Ma'lumotlar faylga saqlandi!");
					break;
				
				
				case 9:
					
					storage.loadFromFile();
					System.out.println("Ma'lumotlar fayldan yuklandi!");
					break;
				
				
				case 10:
					
					System.out.println("Dasturdan chiqildi.");
					sc.close();
					return;
			}
		}
		
	}
	
	public static void menu() {
		System.out.println("1. Student qo'shish");
		System.out.println("2. Studentlar ro'yxati");
		System.out.println("3. ID bo'yicha qidirish");
		System.out.println("4. Tahrirlash");
		System.out.println("5. O'chirish");
		System.out.println("6. Sortlash");
		System.out.println("7. Qidirish");
		System.out.println("8. Faylga yozish");
		System.out.println("9. Fayldan o'qish");
		System.out.println("10. Chiqish");
	}
}
