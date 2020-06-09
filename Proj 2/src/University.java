
public class University {
	public enum CourseType {DIFFERENTIAL, REGULAR}

	public static Course createCourse(String department, int courseNumber,
			int maxNumTAs, CourseType courseType) {  
		if(department == null || department.isEmpty() == true || 
				courseNumber <= 99 || maxNumTAs <= 0) {
			return null;
		}
		Course newCourse;
		if(courseType == CourseType.DIFFERENTIAL) {
			newCourse = new Course(department, courseNumber, maxNumTAs, "differential");
		}
		else {
		newCourse = new Course(department, courseNumber, maxNumTAs, "regular");
		}
		return newCourse;
	}
}
