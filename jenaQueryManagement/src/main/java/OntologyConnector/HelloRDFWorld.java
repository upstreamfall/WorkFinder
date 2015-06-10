package OntologyConnector;

import dto.ProgrammerDTO;

public class HelloRDFWorld {
    public static void main(String[] args) {
        WorkFinderQueryExecutor wf = new WorkFinderQueryExecutor("workfinder.owl");

//		wf.getSkillRoots();
//		wf.getSubSkills("Developing");
//		wf.getSubSkills("Database");
//		wf.getSkillIndividuals("MobileTechnology");

//        wf.getWorkerSkillsInfo("MobileDeveloper");
//        System.out.println(wf.createNewWorker(new ProgrammerDTO("Sam211", "sam@sa1m", null)));
//        System.out.println(wf.deleteWorker("Sam211"));

//        wf.compareProgrammerWithJob("Adam", "MobileDeveloper");
        wf.countProgrammerScore("Adam", "MobileDeveloper");
    }

}
