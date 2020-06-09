
public class UndergradTA extends TA {
	private double hourly;
	public UndergradTA(String first, String last, double salary) {
		super(first, last, salary);
		hourly = salary;
	}
	
	@Override
	public double getSalary() {
		double total = hourly * 
				((this.getExams() / 2) + this.getHour());
		return total;
	}
	
	@Override 
	public void setHour(int x) {
		if (x + this.getHour() <= 20) {
			super.setHour(x);
		}
	}

}
