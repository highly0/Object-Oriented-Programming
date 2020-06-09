
public class TA {
	private String Firstname;
	private String Lastname;
	protected double salary;
	private int hours;
	private int exams;
	
	public TA(String first, String last, double salary) {
		Firstname = first;
		Lastname = last;
		this.salary = salary;
	}
	
	public void setHour(int x) {
		hours += x;
	}
	public void setExams(int x) {
		exams += x;
	}
	
	public String getFirst() {
		return Firstname;
	}
	public String getLast() {
		return Lastname;
	}
	public int getHour() {
		return hours;
	}
	public int getExams() {
		return exams;
	}
	public double getSalary() {
		return salary;
	}
}
