package com.neoworks.interviewtests.graph;

import java.io.IOException;

public class App {
    public String getGreeting() {
        return "Hello World";
    }

    public static void main(String[] args) {
        try {
            IDataAccessObject neoworksPeopleFile = new PersonFileDAO("src/test/resources/people.csv");
            IDataAccessObject neoworksRelationshipsFile = new RelationshipFileDAO("src/test/resources/relationships.csv");



        } catch (IOException e) {
            System.out.println(String.format("Reading input failed with: %s", e));
        }

    }
}
