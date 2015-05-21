package org.wut;

import org.wut.impl.*;

import java.util.Collection;

import org.protege.owl.codegeneration.CodeGenerationFactory;
import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.FactoryHelper;
import org.protege.owl.codegeneration.impl.ProtegeJavaMapping;
import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.protege.owl.codegeneration.inference.SimpleInference;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 * A class that serves as the entry point to the generated code providing access
 * to existing individuals in the ontology and the ability to create new individuals in the ontology.<p>
 * 
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: MyFactory<br>
 * @version generated on Thu May 21 22:15:53 CEST 2015 by Pawel
 */
public class MyFactory implements CodeGenerationFactory {
    private OWLOntology ontology;
    private ProtegeJavaMapping javaMapping = new ProtegeJavaMapping();
    private FactoryHelper delegate;
    private CodeGenerationInference inference;

    public MyFactory(OWLOntology ontology) {
	    this(ontology, new SimpleInference(ontology));
    }
    
    public MyFactory(OWLOntology ontology, CodeGenerationInference inference) {
        this.ontology = ontology;
        this.inference = inference;
        javaMapping.initialize(ontology, inference);
        delegate = new FactoryHelper(ontology, inference);
    }

    public OWLOntology getOwlOntology() {
        return ontology;
    }
    
    public void saveOwlOntology() throws OWLOntologyStorageException {
        ontology.getOWLOntologyManager().saveOntology(ontology);
    }
    
    public void flushOwlReasoner() {
        delegate.flushOwlReasoner();
    }
    
    public boolean canAs(WrappedIndividual resource, Class<? extends WrappedIndividual> javaInterface) {
    	return javaMapping.canAs(resource, javaInterface);
    }
    
    public  <X extends WrappedIndividual> X as(WrappedIndividual resource, Class<? extends X> javaInterface) {
    	return javaMapping.as(resource, javaInterface);
    }
    
    public Class<?> getJavaInterfaceFromOwlClass(OWLClass cls) {
        return javaMapping.getJavaInterfaceFromOwlClass(cls);
    }
    
    public OWLClass getOwlClassFromJavaInterface(Class<?> javaInterface) {
	    return javaMapping.getOwlClassFromJavaInterface(javaInterface);
    }
    
    public CodeGenerationInference getInference() {
        return inference;
    }

    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#AuxSkill
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#AuxSkill", AuxSkill.class, DefaultAuxSkill.class);
    }

    /**
     * Creates an instance of type AuxSkill.  Modifies the underlying ontology.
     */
    public AuxSkill createAuxSkill(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_AUXSKILL, DefaultAuxSkill.class);
    }

    /**
     * Gets an instance of type AuxSkill with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public AuxSkill getAuxSkill(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_AUXSKILL, DefaultAuxSkill.class);
    }

    /**
     * Gets all instances of AuxSkill from the ontology.
     */
    public Collection<? extends AuxSkill> getAllAuxSkillInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_AUXSKILL, DefaultAuxSkill.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#BackEnd
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#BackEnd", BackEnd.class, DefaultBackEnd.class);
    }

    /**
     * Creates an instance of type BackEnd.  Modifies the underlying ontology.
     */
    public BackEnd createBackEnd(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_BACKEND, DefaultBackEnd.class);
    }

    /**
     * Gets an instance of type BackEnd with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public BackEnd getBackEnd(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_BACKEND, DefaultBackEnd.class);
    }

    /**
     * Gets all instances of BackEnd from the ontology.
     */
    public Collection<? extends BackEnd> getAllBackEndInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_BACKEND, DefaultBackEnd.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Certificate
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Certificate", Certificate.class, DefaultCertificate.class);
    }

    /**
     * Creates an instance of type Certificate.  Modifies the underlying ontology.
     */
    public Certificate createCertificate(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_CERTIFICATE, DefaultCertificate.class);
    }

    /**
     * Gets an instance of type Certificate with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Certificate getCertificate(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_CERTIFICATE, DefaultCertificate.class);
    }

    /**
     * Gets all instances of Certificate from the ontology.
     */
    public Collection<? extends Certificate> getAllCertificateInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_CERTIFICATE, DefaultCertificate.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Course
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Course", Course.class, DefaultCourse.class);
    }

    /**
     * Creates an instance of type Course.  Modifies the underlying ontology.
     */
    public Course createCourse(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_COURSE, DefaultCourse.class);
    }

    /**
     * Gets an instance of type Course with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Course getCourse(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_COURSE, DefaultCourse.class);
    }

    /**
     * Gets all instances of Course from the ontology.
     */
    public Collection<? extends Course> getAllCourseInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_COURSE, DefaultCourse.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Database
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Database", Database.class, DefaultDatabase.class);
    }

    /**
     * Creates an instance of type Database.  Modifies the underlying ontology.
     */
    public Database createDatabase(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_DATABASE, DefaultDatabase.class);
    }

    /**
     * Gets an instance of type Database with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Database getDatabase(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_DATABASE, DefaultDatabase.class);
    }

    /**
     * Gets all instances of Database from the ontology.
     */
    public Collection<? extends Database> getAllDatabaseInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_DATABASE, DefaultDatabase.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Design
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Design", Design.class, DefaultDesign.class);
    }

    /**
     * Creates an instance of type Design.  Modifies the underlying ontology.
     */
    public Design createDesign(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_DESIGN, DefaultDesign.class);
    }

    /**
     * Gets an instance of type Design with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Design getDesign(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_DESIGN, DefaultDesign.class);
    }

    /**
     * Gets all instances of Design from the ontology.
     */
    public Collection<? extends Design> getAllDesignInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_DESIGN, DefaultDesign.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Developing
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Developing", Developing.class, DefaultDeveloping.class);
    }

    /**
     * Creates an instance of type Developing.  Modifies the underlying ontology.
     */
    public Developing createDeveloping(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_DEVELOPING, DefaultDeveloping.class);
    }

    /**
     * Gets an instance of type Developing with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Developing getDeveloping(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_DEVELOPING, DefaultDeveloping.class);
    }

    /**
     * Gets all instances of Developing from the ontology.
     */
    public Collection<? extends Developing> getAllDevelopingInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_DEVELOPING, DefaultDeveloping.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#FrontEnd
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#FrontEnd", FrontEnd.class, DefaultFrontEnd.class);
    }

    /**
     * Creates an instance of type FrontEnd.  Modifies the underlying ontology.
     */
    public FrontEnd createFrontEnd(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_FRONTEND, DefaultFrontEnd.class);
    }

    /**
     * Gets an instance of type FrontEnd with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public FrontEnd getFrontEnd(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_FRONTEND, DefaultFrontEnd.class);
    }

    /**
     * Gets all instances of FrontEnd from the ontology.
     */
    public Collection<? extends FrontEnd> getAllFrontEndInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_FRONTEND, DefaultFrontEnd.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Job
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Job", Job.class, DefaultJob.class);
    }

    /**
     * Creates an instance of type Job.  Modifies the underlying ontology.
     */
    public Job createJob(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_JOB, DefaultJob.class);
    }

    /**
     * Gets an instance of type Job with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Job getJob(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_JOB, DefaultJob.class);
    }

    /**
     * Gets all instances of Job from the ontology.
     */
    public Collection<? extends Job> getAllJobInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_JOB, DefaultJob.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#MobileTechnology
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#MobileTechnology", MobileTechnology.class, DefaultMobileTechnology.class);
    }

    /**
     * Creates an instance of type MobileTechnology.  Modifies the underlying ontology.
     */
    public MobileTechnology createMobileTechnology(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_MOBILETECHNOLOGY, DefaultMobileTechnology.class);
    }

    /**
     * Gets an instance of type MobileTechnology with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public MobileTechnology getMobileTechnology(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_MOBILETECHNOLOGY, DefaultMobileTechnology.class);
    }

    /**
     * Gets all instances of MobileTechnology from the ontology.
     */
    public Collection<? extends MobileTechnology> getAllMobileTechnologyInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_MOBILETECHNOLOGY, DefaultMobileTechnology.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#NetworkAdministration
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#NetworkAdministration", NetworkAdministration.class, DefaultNetworkAdministration.class);
    }

    /**
     * Creates an instance of type NetworkAdministration.  Modifies the underlying ontology.
     */
    public NetworkAdministration createNetworkAdministration(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_NETWORKADMINISTRATION, DefaultNetworkAdministration.class);
    }

    /**
     * Gets an instance of type NetworkAdministration with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public NetworkAdministration getNetworkAdministration(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_NETWORKADMINISTRATION, DefaultNetworkAdministration.class);
    }

    /**
     * Gets all instances of NetworkAdministration from the ontology.
     */
    public Collection<? extends NetworkAdministration> getAllNetworkAdministrationInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_NETWORKADMINISTRATION, DefaultNetworkAdministration.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#NoSQL
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#NoSQL", NoSQL.class, DefaultNoSQL.class);
    }

    /**
     * Creates an instance of type NoSQL.  Modifies the underlying ontology.
     */
    public NoSQL createNoSQL(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_NOSQL, DefaultNoSQL.class);
    }

    /**
     * Gets an instance of type NoSQL with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public NoSQL getNoSQL(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_NOSQL, DefaultNoSQL.class);
    }

    /**
     * Gets all instances of NoSQL from the ontology.
     */
    public Collection<? extends NoSQL> getAllNoSQLInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_NOSQL, DefaultNoSQL.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#OnlineSchool
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#OnlineSchool", OnlineSchool.class, DefaultOnlineSchool.class);
    }

    /**
     * Creates an instance of type OnlineSchool.  Modifies the underlying ontology.
     */
    public OnlineSchool createOnlineSchool(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_ONLINESCHOOL, DefaultOnlineSchool.class);
    }

    /**
     * Gets an instance of type OnlineSchool with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public OnlineSchool getOnlineSchool(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_ONLINESCHOOL, DefaultOnlineSchool.class);
    }

    /**
     * Gets all instances of OnlineSchool from the ontology.
     */
    public Collection<? extends OnlineSchool> getAllOnlineSchoolInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_ONLINESCHOOL, DefaultOnlineSchool.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Person
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Person", Person.class, DefaultPerson.class);
    }

    /**
     * Creates an instance of type Person.  Modifies the underlying ontology.
     */
    public Person createPerson(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_PERSON, DefaultPerson.class);
    }

    /**
     * Gets an instance of type Person with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Person getPerson(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_PERSON, DefaultPerson.class);
    }

    /**
     * Gets all instances of Person from the ontology.
     */
    public Collection<? extends Person> getAllPersonInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_PERSON, DefaultPerson.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Programmer
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Programmer", Programmer.class, DefaultProgrammer.class);
    }

    /**
     * Creates an instance of type Programmer.  Modifies the underlying ontology.
     */
    public Programmer createProgrammer(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_PROGRAMMER, DefaultProgrammer.class);
    }

    /**
     * Gets an instance of type Programmer with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Programmer getProgrammer(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_PROGRAMMER, DefaultProgrammer.class);
    }

    /**
     * Gets all instances of Programmer from the ontology.
     */
    public Collection<? extends Programmer> getAllProgrammerInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_PROGRAMMER, DefaultProgrammer.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Project
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Project", Project.class, DefaultProject.class);
    }

    /**
     * Creates an instance of type Project.  Modifies the underlying ontology.
     */
    public Project createProject(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_PROJECT, DefaultProject.class);
    }

    /**
     * Gets an instance of type Project with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Project getProject(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_PROJECT, DefaultProject.class);
    }

    /**
     * Gets all instances of Project from the ontology.
     */
    public Collection<? extends Project> getAllProjectInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_PROJECT, DefaultProject.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#RelationalDatabase
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#RelationalDatabase", RelationalDatabase.class, DefaultRelationalDatabase.class);
    }

    /**
     * Creates an instance of type RelationalDatabase.  Modifies the underlying ontology.
     */
    public RelationalDatabase createRelationalDatabase(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_RELATIONALDATABASE, DefaultRelationalDatabase.class);
    }

    /**
     * Gets an instance of type RelationalDatabase with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public RelationalDatabase getRelationalDatabase(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_RELATIONALDATABASE, DefaultRelationalDatabase.class);
    }

    /**
     * Gets all instances of RelationalDatabase from the ontology.
     */
    public Collection<? extends RelationalDatabase> getAllRelationalDatabaseInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_RELATIONALDATABASE, DefaultRelationalDatabase.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Skill
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Skill", Skill.class, DefaultSkill.class);
    }

    /**
     * Creates an instance of type Skill.  Modifies the underlying ontology.
     */
    public Skill createSkill(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_SKILL, DefaultSkill.class);
    }

    /**
     * Gets an instance of type Skill with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Skill getSkill(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_SKILL, DefaultSkill.class);
    }

    /**
     * Gets all instances of Skill from the ontology.
     */
    public Collection<? extends Skill> getAllSkillInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_SKILL, DefaultSkill.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Task
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Task", Task.class, DefaultTask.class);
    }

    /**
     * Creates an instance of type Task.  Modifies the underlying ontology.
     */
    public Task createTask(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_TASK, DefaultTask.class);
    }

    /**
     * Gets an instance of type Task with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Task getTask(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_TASK, DefaultTask.class);
    }

    /**
     * Gets all instances of Task from the ontology.
     */
    public Collection<? extends Task> getAllTaskInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_TASK, DefaultTask.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Testing
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Testing", Testing.class, DefaultTesting.class);
    }

    /**
     * Creates an instance of type Testing.  Modifies the underlying ontology.
     */
    public Testing createTesting(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_TESTING, DefaultTesting.class);
    }

    /**
     * Gets an instance of type Testing with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Testing getTesting(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_TESTING, DefaultTesting.class);
    }

    /**
     * Gets all instances of Testing from the ontology.
     */
    public Collection<? extends Testing> getAllTestingInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_TESTING, DefaultTesting.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#Tutorial
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#Tutorial", Tutorial.class, DefaultTutorial.class);
    }

    /**
     * Creates an instance of type Tutorial.  Modifies the underlying ontology.
     */
    public Tutorial createTutorial(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_TUTORIAL, DefaultTutorial.class);
    }

    /**
     * Gets an instance of type Tutorial with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Tutorial getTutorial(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_TUTORIAL, DefaultTutorial.class);
    }

    /**
     * Gets all instances of Tutorial from the ontology.
     */
    public Collection<? extends Tutorial> getAllTutorialInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_TUTORIAL, DefaultTutorial.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/pawel/ontologies/workfinder#WebTechnology
     */

    {
        javaMapping.add("http://www.semanticweb.org/pawel/ontologies/workfinder#WebTechnology", WebTechnology.class, DefaultWebTechnology.class);
    }

    /**
     * Creates an instance of type WebTechnology.  Modifies the underlying ontology.
     */
    public WebTechnology createWebTechnology(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_WEBTECHNOLOGY, DefaultWebTechnology.class);
    }

    /**
     * Gets an instance of type WebTechnology with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public WebTechnology getWebTechnology(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_WEBTECHNOLOGY, DefaultWebTechnology.class);
    }

    /**
     * Gets all instances of WebTechnology from the ontology.
     */
    public Collection<? extends WebTechnology> getAllWebTechnologyInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_WEBTECHNOLOGY, DefaultWebTechnology.class);
    }


}