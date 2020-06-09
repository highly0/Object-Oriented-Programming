
import java.util.ArrayList;

public class SubmitServer {
	
	// array holds all students
	private ArrayList<Student> students;
	private int maxSubs;
	
	// constructor
	public SubmitServer(int maxSubmissions) {
		students = new ArrayList<Student>();
		maxSubs = maxSubmissions;	
	}

		
	public boolean addStudent(String newStudent) {
		Student newStu = new Student(newStudent);
		int flag = 0;
		
		if(newStudent == null || newStudent.isEmpty() == true 
				|| exist(newStudent) == true) {
			return false;
		}
		students.add(newStu);
		return true;
	}

	public int numStudents() {
		return students.size();
	}

	public boolean addSubmission(String name, int[] testResults) {
		int totalScore = 0, currTotal = 0, flag = 0;
		if(exist(name) == false || testResults == null || 
				testResults.length == 0 || isNeg(testResults) == true) {
			return false;
		}
		for(int i = 0; i < testResults.length; i ++) {
			currTotal += testResults[i];
		}
		for(int i = 0; i < students.size(); i ++) {
			Student currStu = students.get(i);
			if(strcmp(currStu.getName(), name) == 0) {
				totalScore = currStu.getTotalScore();
				if(currStu.getSubmissions() < maxSubs 
						&& currTotal > totalScore) {
					currStu.setTestResults(testResults);
					currStu.setSubmissions();
				} else if (currStu.getSubmissions() < maxSubs 
						&& currTotal <= totalScore) {
					currStu.setSubmissions();
				} else if (currStu.getSubmissions() >= maxSubs) {
					flag = 1;
				}
			}
		}
		if (flag == 1) return false;
		else return true;
	}
	
	public boolean exist(String name) {
		boolean flag = false;
		if(name == null) return false;
		for(int i = 0; i < students.size(); i ++) {
			if(strcmp(students.get(i).getName(), name) == 0) {
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean isNeg(int[] arr) {
		boolean flag = false;
		for(int i = 0; i < arr.length; i ++) {
			if(arr[i] < 0) {
				flag = true;
			}
		}
		return flag;
	}


	public int score(String name) {
		int totalScore = 0, flag = 0;
		if(exist(name) == false) {
			return -1;
		}
		for(int i = 0; i < students.size(); i ++) {
			if(strcmp(students.get(i).getName(), name) == 0) {
				if(students.get(i).getSubmissions() == 0) flag = 1;	
				totalScore = students.get(i).getTotalScore();
			}
		}
		if (flag == 1) return 0;
		else return totalScore;
	}


	public int numSubmissions(String name) {
		int subs = 0;
		
		if(exist(name) == false) return -1;
		for(int i = 0; i < students.size(); i ++) {
			if(strcmp(students.get(i).getName(), name) == 0) {
				subs =  students.get(i).getSubmissions();
			}
		}
		return subs;
	}


	public int numSubmissions() {
		int total = 0;
		for(int i = 0; i < students.size(); i ++) {
			total += students.get(i).getSubmissions();
		}
		return total;
	}

	public boolean satisfactory(String name) {
		int totalTests = 0, numberPassed = 0;
		boolean flag = false;
		for (int i = 0; i < students.size(); i ++) {
			if(strcmp(students.get(i).getName(), name) == 0) {
				totalTests = students.get(i).getNumTests();
				numberPassed = students.get(i).numberPassed();
				if((float) numberPassed / (float) totalTests >= 0.5) {
					flag = true;
				}
			}
		}
		return flag;
	}

	public boolean gotExtraCredit(String name) {
		boolean flag = false;
		for(int i = 0; i < students.size(); i ++) {
			if(strcmp(students.get(i).getName(), name) == 0) {
				Student currStu = students.get(i);
				if(currStu.getSubmissions() == 1 &&
						currStu.numberPassed() == currStu.getNumTests()) {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	public static int strcmp(String str1, String str2) {
		int l1 = str1.length();
		int l2 = str2.length();
		int lmin = Math.min(l1, l2);

		for (int i = 0; i < lmin; i++) {
			int str1_ch = (int) str1.charAt(i);
			int str2_ch = (int) str2.charAt(i);

			if (str1_ch != str2_ch) {
				return str1_ch - str2_ch;
			}
		}
		// Edge case for strings like
		// String 1="Geeks" and String 2="Geeksforgeeks"
		if (l1 != l2) {
			return l1 - l2;
		} else {
			return 0;
		}
	}
}
