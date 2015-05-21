package org.wut;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: Project <br>
 * @version generated on Thu May 21 22:15:53 CEST 2015 by Pawel
 */

public interface Project extends WrappedIndividual {

    /* ***************************************************
     * Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasTask
     */
     
    /**
     * Gets all property values for the hasTask property.<p>
     * 
     * @returns a collection of values for the hasTask property.
     */
    Collection<? extends Task> getHasTask();

    /**
     * Checks if the class has a hasTask property value.<p>
     * 
     * @return true if there is a hasTask property value.
     */
    boolean hasHasTask();

    /**
     * Adds a hasTask property value.<p>
     * 
     * @param newHasTask the hasTask property value to be added
     */
    void addHasTask(Task newHasTask);

    /**
     * Removes a hasTask property value.<p>
     * 
     * @param oldHasTask the hasTask property value to be removed.
     */
    void removeHasTask(Task oldHasTask);


    /* ***************************************************
     * Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasDescription
     */
     
    /**
     * Gets all property values for the hasDescription property.<p>
     * 
     * @returns a collection of values for the hasDescription property.
     */
    Collection<? extends String> getHasDescription();

    /**
     * Checks if the class has a hasDescription property value.<p>
     * 
     * @return true if there is a hasDescription property value.
     */
    boolean hasHasDescription();

    /**
     * Adds a hasDescription property value.<p>
     * 
     * @param newHasDescription the hasDescription property value to be added
     */
    void addHasDescription(String newHasDescription);

    /**
     * Removes a hasDescription property value.<p>
     * 
     * @param oldHasDescription the hasDescription property value to be removed.
     */
    void removeHasDescription(String oldHasDescription);



    /* ***************************************************
     * Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasEndDate
     */
     
    /**
     * Gets all property values for the hasEndDate property.<p>
     * 
     * @returns a collection of values for the hasEndDate property.
     */
    Collection<? extends Object> getHasEndDate();

    /**
     * Checks if the class has a hasEndDate property value.<p>
     * 
     * @return true if there is a hasEndDate property value.
     */
    boolean hasHasEndDate();

    /**
     * Adds a hasEndDate property value.<p>
     * 
     * @param newHasEndDate the hasEndDate property value to be added
     */
    void addHasEndDate(Object newHasEndDate);

    /**
     * Removes a hasEndDate property value.<p>
     * 
     * @param oldHasEndDate the hasEndDate property value to be removed.
     */
    void removeHasEndDate(Object oldHasEndDate);



    /* ***************************************************
     * Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasStartDate
     */
     
    /**
     * Gets all property values for the hasStartDate property.<p>
     * 
     * @returns a collection of values for the hasStartDate property.
     */
    Collection<? extends Object> getHasStartDate();

    /**
     * Checks if the class has a hasStartDate property value.<p>
     * 
     * @return true if there is a hasStartDate property value.
     */
    boolean hasHasStartDate();

    /**
     * Adds a hasStartDate property value.<p>
     * 
     * @param newHasStartDate the hasStartDate property value to be added
     */
    void addHasStartDate(Object newHasStartDate);

    /**
     * Removes a hasStartDate property value.<p>
     * 
     * @param oldHasStartDate the hasStartDate property value to be removed.
     */
    void removeHasStartDate(Object oldHasStartDate);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
