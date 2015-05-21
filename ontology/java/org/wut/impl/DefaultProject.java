package org.wut.impl;

import org.wut.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultProject <br>
 * @version generated on Thu May 21 22:15:53 CEST 2015 by Pawel
 */
public class DefaultProject extends WrappedIndividualImpl implements Project {

    public DefaultProject(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasTask
     */
     
    public Collection<? extends Task> getHasTask() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASTASK,
                                               DefaultTask.class);
    }

    public boolean hasHasTask() {
	   return !getHasTask().isEmpty();
    }

    public void addHasTask(Task newHasTask) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASTASK,
                                       newHasTask);
    }

    public void removeHasTask(Task oldHasTask) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASTASK,
                                          oldHasTask);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasDescription
     */
     
    public Collection<? extends String> getHasDescription() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDESCRIPTION, String.class);
    }

    public boolean hasHasDescription() {
		return !getHasDescription().isEmpty();
    }

    public void addHasDescription(String newHasDescription) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDESCRIPTION, newHasDescription);
    }

    public void removeHasDescription(String oldHasDescription) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDESCRIPTION, oldHasDescription);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasEndDate
     */
     
    public Collection<? extends Object> getHasEndDate() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASENDDATE, Object.class);
    }

    public boolean hasHasEndDate() {
		return !getHasEndDate().isEmpty();
    }

    public void addHasEndDate(Object newHasEndDate) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASENDDATE, newHasEndDate);
    }

    public void removeHasEndDate(Object oldHasEndDate) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASENDDATE, oldHasEndDate);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasStartDate
     */
     
    public Collection<? extends Object> getHasStartDate() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASSTARTDATE, Object.class);
    }

    public boolean hasHasStartDate() {
		return !getHasStartDate().isEmpty();
    }

    public void addHasStartDate(Object newHasStartDate) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASSTARTDATE, newHasStartDate);
    }

    public void removeHasStartDate(Object oldHasStartDate) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASSTARTDATE, oldHasStartDate);
    }


}
