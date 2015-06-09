package OntologyConnector;

public class HelloRDFWorld {
	public static void main(String[] args) {
		WorkFinderQueryExecutor wf = new WorkFinderQueryExecutor("workfinder.owl");
		
		SPARQLConnector sparql = SPARQLConnector.getInstance();
		sparql.connect();

//		wf.compareProgrammerWithJob("Adam", "MobileDeveloper");
//		wf.getSkillRoots();
//		wf.getSubSkills("Developing");
//		wf.getSubSkills("Database");
//		wf.getSkillIndividuals("MobileTechnology");
//
//		wf.createNewProgrammer(new ProgrammerDTO("Sam23", "sam@sam"));
//		wf.deleteProgrammer("Sam23");
//        wf.getProgrammerInfo("Sam23");
//
//        wf.addSkill("Sam23", new Skill("MySQL", 5, 0));
        wf.getProgrammerSkills("Adam");
	}
}
