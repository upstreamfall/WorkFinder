package org.wut;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: Course <br>
 * @version generated on Thu May 21 22:15:53 CEST 2015 by Pawel
 */

public interface Course extends WrappedIndividual {

    /* ***************************************************
     * Property http://www.semanticweb.org/pawel/ontologies/workfinder#hasCertificate
     */
     
    /**
     * Gets all property values for the hasCertificate property.<p>
     * 
     * @returns a collection of values for the hasCertificate property.
     */
    Collection<? extends Certificate> getHasCertificate();

    /**
     * Checks if the class has a hasCertificate property value.<p>
     * 
     * @return true if there is a hasCertificate property value.
     */
    boolean hasHasCertificate();

    /**
     * Adds a hasCertificate property value.<p>
     * 
     * @param newHasCertificate the hasCertificate property value to be added
     */
    void addHasCertificate(Certificate newHasCertificate);

    /**
     * Removes a hasCertificate property value.<p>
     * 
     * @param oldHasCertificate the hasCertificate property value to be removed.
     */
    void removeHasCertificate(Certificate oldHasCertificate);


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
