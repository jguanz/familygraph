package com.neoworks.interviewtests.graph;

import java.io.IOException;
import java.util.*;

public class App {
    public static IDataAccessObject neoworksPeopleFile;
    public static IDataAccessObject neoworksRelationshipsFile;
    public static FamilyGraph neoworksFamilyGraph;

    public static void run() {
        setup();
        buildRelationships();
        testRelationships();
    }

    public static void setup() {
        try {
            neoworksPeopleFile = new PersonFileDAO("src/test/resources/people.csv");
            neoworksRelationshipsFile = new RelationshipFileDAO("src/test/resources/relationships.csv");

        } catch (IOException e) {
            System.out.println(String.format("Reading input failed with: %s", e));
        }
    }

    public static void buildRelationships() {
        Map relationships = neoworksRelationshipsFile.getData();
        Map people = neoworksPeopleFile.getData();

        for (String key : (Set<String>)people.keySet()) {
            if (relationships.containsKey(key)) {
                for (Relationship r : (ArrayList<Relationship>) relationships.get(key)) {
                    ((Person)people.get(key)).addRelationship((Person)people.get(r.relatedEmail), r.type);
                }
            }
        }
    }

    public static void testRelationships(){
        FamilyGraph neoworksFamily = new FamilyGraph(new HashSet<Person>(neoworksPeopleFile.getData().values()));

        for(Person p: neoworksFamily.getFamily()){
            System.out.println(String.format("%s has %s family members", p.name, neoworksFamily.getSizeOfExtendedFamily(p)));
        }
    }

    public static void main(String[] args) {
        run();
    }
}
