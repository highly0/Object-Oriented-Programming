
public class Course {
	private String department;
	private int courseNumber;
	private int maxNumTas;
	private TA[] taArr;
	private String courseType;
	private int currTas = 0;

	public Course(String department, int courseNumber, 
			int maxNumTas, String courseType) {
		this.department = department;
		this.courseNumber = courseNumber;
		this.maxNumTas = maxNumTas;
		this.courseType = courseType;
		taArr = new TA[maxNumTas];
	}

	public String getCourseName() {
		String courseName = department + " " + courseNumber;
		return courseName;
	}

	public boolean hireUndergraduateTA(String firstName, 
			String lastName, double hourlySalary) {
		if(firstName == null || firstName.isEmpty() || lastName == null 
				|| lastName.isEmpty() || hourlySalary <= 0 
				) {
			return false;
		}
		TA newTa = new UndergradTA(firstName, lastName, hourlySalary);
		boolean flag = false;
		
		if(currTas == 0) taArr[currTas++] = newTa;
		else {
			if(exist(firstName, lastName) == true) return false;
			// can only add if less than limit
			if(strcmp(courseType, "regular") == 0) {
				if(numTAs() < maxNumTas) {
					taArr[currTas++] = newTa;
					flag = true;
				} else flag = false;
			} else { // don't have a limit
				if(numTAs() >= maxNumTas) {
					increaseTACapacity(1);
					taArr[maxNumTas - 1] = newTa;
				} else {
					taArr[currTas] = newTa;
				}
				currTas++;
				flag = true;
			}	
		}	
		return flag;
	}

	public boolean hireGraduateTA(String firstName, 
			String lastName, double yearlySalary) {
		if(firstName == null || firstName.isEmpty() || 
			lastName == null || lastName.isEmpty() || yearlySalary <= 0) {
			return false;
		}
		TA newTa = new GradTA(firstName, lastName, yearlySalary);
		boolean flag = false;
		
		if(currTas == 0) taArr[currTas++] = newTa;
		else {
			if(exist(firstName, lastName) == true) return false;
			// can only add if less than limit
			if(strcmp(courseType, "regular") == 0) {
				if(numTAs() < maxNumTas) {
					taArr[currTas++] = newTa;
					flag = true;
				} else flag = false;
			} else { // don't have a limit
				if(numTAs() >= maxNumTas) {
					increaseTACapacity(1);
					taArr[maxNumTas - 1] = newTa;
				} else {
					taArr[currTas] = newTa;
				}
				currTas++;
				flag = true;
			}
		}
		return flag;
	}
	
	public void copyArr(TA[] copy, TA[] dest) {
		for(int i = 0; i < copy.length; i ++) {
			dest[i] = copy[i];
		}
	}
	
	public boolean exist(String first, String last) {
		boolean flag = false;
		for(int i = 0; i < taArr.length; i++) {
			if(taArr[i] != null) {
				if((strcmp(first, taArr[i].getFirst()) == 0) 
						&& (strcmp(last, taArr[i].getLast()) == 0)) {
					flag = true;
				}
			}	
		}
		return flag;
	}

	public int numTAs() {
		return currTas;
	}

	public int taCapacity() {
		return maxNumTas;
	}

	public boolean increaseTACapacity(int numTAsToAdd) {
		if(numTAsToAdd <= 0) return false;
		TA[] newArr = new TA[maxNumTas];
		copyArr(taArr, newArr);
		
		maxNumTas += numTAsToAdd;
		taArr = new TA[maxNumTas];
		for(int i = 0; i < newArr.length; i ++) {
			taArr[i] = newArr[i];
		}
		return true;
	}

	public String[] getTANames() {
		String[] result = new String[currTas];
		if (currTas == 0)
			return new String[0];
		for (int i = 0; i < taArr.length - 1; i++) {
			for (int j = i + 1; j < taArr.length; j++) {
				if(taArr[i] != null && taArr[j] != null) {
					if(strcmp(taArr[i].getLast(), taArr[j].getLast()) == 0) {
						if (taArr[i].getFirst().compareTo(taArr[j].getFirst()) > 0) {
							TA temp = taArr[i];
							taArr[i] = taArr[j];
							taArr[j] = temp;
						}
					} else {
						if (taArr[i].getLast().compareTo(taArr[j].getLast()) > 0) {
							TA temp = taArr[i];
							taArr[i] = taArr[j];
							taArr[j] = temp;
						}			
					}			
				}	
			}
		}
		for (int i = 0; i < currTas; i++) {
			if(taArr[i] != null) {
				result[i] = taArr[i].getFirst() + " " + taArr[i].getLast();
			}
		}
		return result;
	}

	public boolean holdOfficeHours(String firstName, 
			String lastName, int numHours) {
		if (firstName == null || firstName.isEmpty() == true || lastName == null
				|| lastName.isEmpty() == true || exist(firstName, lastName) == false
				|| numHours <= 0) {
				return false;
			}
		
		boolean flag = false;
		for(int i = 0; i < taArr.length; i++) {
			if(taArr[i] != null) {
				if (strcmp(taArr[i].getFirst(), firstName) == 0 
						&& strcmp(taArr[i].getLast(), lastName) == 0) {
					if(taArr[i].getClass() == GradTA.class) {
						taArr[i].setHour(numHours);
						flag = true;
					} else {
						int oldHour = taArr[i].getHour();
						taArr[i].setHour(numHours);
						if(oldHour < taArr[i].getHour())
							flag = true;
					}
				}
			}
		}
		return flag;
	}

	public int numOfficeHours(String firstName, String lastName) {
		if (firstName == null || firstName.isEmpty() == true || lastName == null
			|| lastName.isEmpty() == true || exist(firstName, lastName) == false) {
			return -1;
		}
		int total = 0;
		for (int i = 0; i < taArr.length; i++) {
			if (taArr[i] != null) {
				if (strcmp(taArr[i].getFirst(), firstName) == 0 
						&& strcmp(taArr[i].getLast(), lastName) == 0) {
					total = taArr[i].getHour();
				}
			}
		}
		return total;
	}

	public boolean gradeExams(String firstName, 
			String lastName, int numExams) {
		if (firstName == null || firstName.isEmpty() == true || lastName == null
				|| lastName.isEmpty() == true || exist(firstName, lastName) == false
				|| numExams <= 0 || (numExams % 2 == 1)) {
				return false;
			}
		boolean flag = false;
		for(int i = 0; i < taArr.length; i++) {
			if(taArr[i] != null) {
				if (strcmp(taArr[i].getFirst(), firstName) == 0 
						&& strcmp(taArr[i].getLast(), lastName) == 0) {
					if(taArr[i].getClass() == GradTA.class) {
						if((numExams + taArr[i].getExams()) <= 150) {
							taArr[i].setExams(numExams);
							flag = true;
						}
					} else {
						if((numExams/2 + numOfficeHours(firstName, lastName) + 
								taArr[i].getExams()/2) <= 20) {
							taArr[i].setExams(numExams);
							flag = true;
						}

					}
				}
			}
		}
		return flag;
	}

	public int numExamsGraded(String firstName, String lastName) {
		if (firstName == null || firstName.isEmpty() == true || lastName == null
				|| lastName.isEmpty() == true || exist(firstName, lastName) == false) {
				return -1;
			}
		
		int total = 0;
		for (int i = 0; i < taArr.length; i++) {
			if (taArr[i] != null) {
				if (strcmp(taArr[i].getFirst(), firstName) == 0 
						&& strcmp(taArr[i].getLast(), lastName) == 0) {
					total = taArr[i].getExams();
				}
			}
		}
		return total;
	}

	public double getPaycheckAmount(String firstName, String lastName) {
		if (firstName == null || firstName.isEmpty() == true || lastName == null
				|| lastName.isEmpty() == true || exist(firstName, lastName) == false) {
				return 0.0;
			}
		double total = 0.0;
		for(int i = 0; i < taArr.length; i++) {
			if(taArr[i] != null) {
				if (strcmp(taArr[i].getFirst(), firstName) == 0 
						&& strcmp(taArr[i].getLast(), lastName) == 0) {
					total = taArr[i].getSalary();
				}
			}
		}
		return total;
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
