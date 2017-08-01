package com.neoworks.interviewtests.graph;

import java.util.*;

public class FamilyGraph {
    final static int countSelf = 1;
    private Set<Person> _familyGraph;

    FamilyGraph(Set<Person> familyGraph) {
        _familyGraph = familyGraph;
    }

    public Set<Person> getFamily(){
        return _familyGraph;
    }

    public int getSizeOfExtendedFamily(Person p){
        Set<Person> visitedNodes = new HashSet<Person>();

        return doDepthFirstSearch(p, visitedNodes);
    }

    private int doDepthFirstSearch(Person person, Set visitedNodes) {
        if(visitedNodes.contains(person)){
            return 0;
        }

        visitedNodes.add(person);

        int tempCounter = 0;
        for(Person p: (Set<Person>)person.getRelationships().keySet()){
            if(person.getRelationships().get(p) == RelationshipType.FAMILY){
                tempCounter = doDepthFirstSearch(p, visitedNodes) + tempCounter;
            }
        }

        return tempCounter + 1;
    }
}
