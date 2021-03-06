package com.neoworks.interviewtests.graph;

public class Relationship {
    String email;
    RelationshipType type;
    String relatedEmail;

    Relationship (String email, RelationshipType type, String relatedEmail){
        this.email = email;
        this.type = type;
        this.relatedEmail = relatedEmail;
    }

    public String toString(){
        return String.format("email: %s, type: %s, relatedEmail: %s", email, type, relatedEmail);
    }
}
