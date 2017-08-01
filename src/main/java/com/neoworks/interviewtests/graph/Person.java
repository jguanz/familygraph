package com.neoworks.interviewtests.graph;

import java.util.*;

public class Person {
    String name;
    String email = "";
    int age;
    private Map<Person, RelationshipType> _relationships = new HashMap();

    Person (String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public void addRelationship(Person person, RelationshipType type){
        _relationships.put(person, type);
    }

    public Map getRelationships(){
        return _relationships;
    }

    public int getNumberOfRelationships(){
        if(_relationships.isEmpty()){
            return 0;
        } else {
            return _relationships.size();
        }
    }

    public String toString(){
        Set<String> relationshipsToString = new HashSet();
        for(Person p: _relationships.keySet()){
            relationshipsToString.add(p.name);
        }
        return String.format("name: %s, email: %s, age: %s, relationships: %s", name, email, age, relationshipsToString);
    }
}
