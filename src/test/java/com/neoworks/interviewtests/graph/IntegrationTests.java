package com.neoworks.interviewtests.graph;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.*;
import static org.junit.Assert.*;

public class IntegrationTests {
    IDataAccessObject neoworksPeopleFile;
    IDataAccessObject neoworksRelationshipsFile;
    FamilyGraph neoworksFamily;

    @Before
    public void setUp() throws IOException{
        neoworksPeopleFile = new PersonFileDAO("src/test/resources/people.csv");
        neoworksRelationshipsFile = new RelationshipFileDAO("src/test/resources/relationships.csv");
        Map relationships = neoworksRelationshipsFile.getData();
        Map people = neoworksPeopleFile.getData();

        for (String key : (Set<String>)people.keySet()) {
            if (relationships.containsKey(key)) {
                for (Relationship r : (ArrayList<Relationship>) relationships.get(key)) {
                    ((Person)people.get(key)).addRelationship((Person)people.get(r.relatedEmail), r.type);
                }
            }
        }

        neoworksFamily = new FamilyGraph(new HashSet<Person>(neoworksPeopleFile.getData().values()));
    }

    @Test
    public void testTotalPeopleLoadedIsCorrect() throws IOException {
        final int expectedNumberOfPeople = 12;
        assertEquals(neoworksPeopleFile.getData().keySet().size(), expectedNumberOfPeople);
    }

    @Test
    public void testTotalRelationshipsLoadedIsCorrect() throws IOException {
        final int expectedNumberOfRelationships = 16;
        int relationshipCount = 0;
        Map relationships = neoworksRelationshipsFile.getData();

        for(String key: (Set<String>)relationships.keySet()){
            for(Relationship r: (ArrayList<Relationship>)relationships.get(key)){
                relationshipCount++;
            }
        }

        assertEquals(relationshipCount, expectedNumberOfRelationships);
    }

    @Test
    public void testSpecificRelationshipCount() throws IOException {
        //TODO: The expected number of relationships are gathered from my understanding of the data.  These may need to be updated based on feedback from Neoworks.
        final int BobRelationships = 3;
        final int JennyRelationships = 2;
        final int NigelRelationships = 1;
        final int AlanRelationships = 0;

        assertEquals(((Person)neoworksPeopleFile.getData().get("bob@bob.com")).getNumberOfRelationships(), BobRelationships);
        assertEquals(((Person)neoworksPeopleFile.getData().get("jenny@toys.com")).getNumberOfRelationships(), JennyRelationships);
        assertEquals(((Person)neoworksPeopleFile.getData().get("nigel@marketing.com")).getNumberOfRelationships(), NigelRelationships);
        assertEquals(((Person)neoworksPeopleFile.getData().get("alan@lonely.org")).getNumberOfRelationships(), AlanRelationships);
    }

    @Test
    public void testExtendedFamilySize() throws IOException {
        //TODO: The .  These may need to be updated based on feedback from Neoworks.
        final int SizeOfBobExtendedFamily = 4;
        final int SizeOfJennyExtendedFamily = 3;

        assertEquals((neoworksFamily.getSizeOfExtendedFamily((Person)neoworksPeopleFile.getData().get("bob@bob.com"))), SizeOfBobExtendedFamily);
        assertEquals((neoworksFamily.getSizeOfExtendedFamily((Person)neoworksPeopleFile.getData().get("jenny@toys.com"))), SizeOfJennyExtendedFamily);
    }
}
