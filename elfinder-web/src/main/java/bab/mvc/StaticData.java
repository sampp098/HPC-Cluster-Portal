package bab.mvc;

import java.util.ArrayList;
import java.util.List;

import bab.mvc.data.entities.pure.Applications;
import bab.mvc.data.entities.pure.Faculty;
import bab.mvc.data.entities.pure.Grade;
import bab.mvc.data.entities.pure.University;
import bab.mvc.data.services.ApplicationsService;
import bab.mvc.data.services.FacultyService;
import bab.mvc.data.services.GradeService;
import bab.mvc.data.services.UniversityService;


public class StaticData {
	
	//public static final String imageRepository="M:/ImageStorage/";

	private static List<University> university=new ArrayList<University>();
	private static List<Faculty> faculty=new ArrayList<Faculty>();
	private static List<Grade> grade=new ArrayList<Grade>();
	private static List<Applications> applications=new ArrayList<Applications>();
	//private static List<Queue> queue=new ArrayList<Queue>();
	
	public StaticData() {
		university=new UniversityService().Read();
		faculty=new FacultyService().Read();
		grade=new GradeService().Read();
		//applications=new ApplicationsService().ReadActives();
		//queue=new QueueService().read();
	}

	public static List<University> getUniversity() {
		return university;
	}

	public static List<Faculty> getFaculty() {
		return faculty;
	}

	public static List<Grade> getGrade() {
		return grade;
	}


}
