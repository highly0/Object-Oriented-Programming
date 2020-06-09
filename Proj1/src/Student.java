
public class Student {
	private String name;
	private int submissions;
	//private int[] testResults;
	private int[] testResults;
	
	public Student(String name) {
		this.name = name;
		submissions = 0;
		testResults = new int[0];
	}
	
	public int getTotalScore() {
		int totalScore = 0;
		for(int i = 0; i < testResults.length; i ++) {
			totalScore += testResults[i];
		}
		return totalScore;
	}
	
	public int numberPassed() {
		int total = 0;
		for(int i = 0; i < testResults.length; i ++) {
			if (testResults[i] > 0) {
				total ++;
			}
		}
		return total;
	}
	
	public void setSubmissions() {
		this.submissions ++;
	}
	
	public void setTestResults(int arr[]) {
		testResults = new int[arr.length];
		for(int i = 0; i < arr.length; i ++) {
			testResults[i] = arr[i];
		}
	}
	
	public int getSubmissions() {
		return this.submissions;
	}
	
	public int getNumTests() {
		return this.testResults.length;
	}
	
	public String getName() {
		return this.name;
	}
}
