
public class GradTA extends TA {
	private double yearly;
	public GradTA(String first, String last, double salary) {
		super(first, last, salary);
		yearly = salary;
	}	
	
	@Override
	public double getSalary() {		
		return (yearly / 21);
	}
}
