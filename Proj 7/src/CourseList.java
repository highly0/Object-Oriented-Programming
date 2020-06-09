
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CourseList {
	HashMap< String,Set<String>> map; 
	HashMap< String,Integer> courses; 
	//Set<String> courses = new HashSet<String>();
	
	public CourseList() {
		map = new HashMap< String,Set<String>>();
		courses = new HashMap< String,Integer>();
	}
	
	public void takeCourse(String student, String course) {
		if(student == null || course == null)
			throw new IllegalArgumentException("no bueno");
		
		if(map.containsKey(student)) {
			if(!map.get(student).contains(course)) {
				map.get(student).add(course);
				if(!courses.keySet().contains(course)) {
					courses.put(course, 1);
				} else {
					courses.put(course, courses.get(course) + 1);
				}
			} 
		} else {
			Set<String> set = new HashSet<String>();
			set.add(course);
			map.put(student, set);
		}
	}
	
	public boolean isTakingCourse(String student, String course) 
	throws IllegalArgumentException{
		if(student == null || course == null)
			throw new IllegalArgumentException("no bueno");
		
		if(!map.containsKey(student)) {
			return false;
		} else {
			if(map.get(student).contains(course))
				return true;
			else 
				return false;
		}
	}
	
	public int numCoursesTaking(String student) throws IllegalArgumentException {
		if(student == null)
			throw new IllegalArgumentException("no bueno");
		
		if(map.containsKey(student)) {
			return map.get(student).size();
		} else {
			return 0;
		}	
	}
	
	public int numEnrolled(String course) throws IllegalArgumentException{
		if(course == null)
			throw new IllegalArgumentException("no bueno");
		
		int counter = 0;
		for(String stu : map.keySet()) {
			if(map.get(stu).contains(course)) {
				counter ++;
			}
		}
		return counter;
	}
	
	public String mostPopularCourse() {		
		if(map.isEmpty()) 
			return null;
		
		int max = 0;
		String result = "";
		for(String course : courses.keySet()) {
			if(courses.get(course) > max) {
				max = courses.get(course);
				result = course;
			}
		}
		return result;
	}
	
	public boolean dropCourse(String student, String course) 
			throws IllegalArgumentException {
		if(student == null || course == null)
			throw new IllegalArgumentException("no bueno");
		
		boolean result = false;	
		if(map.containsKey(student)) {
			if(map.get(student).contains(course)) {
				map.get(student).remove(course);
				courses.remove(course);
				result = true;
			} else 
				result = false;
		}
		return result;
		
	}
}
