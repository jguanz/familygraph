package com.neoworks.interviewtests.graph;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by jguanz on 7/27/17.
 */
public class PersonTest {
    @Test public void testGetName(){
        Person testPerson = new Person("John", "test@gmail.com", 23);

        assertEquals("John", testPerson.name);
    }
}
