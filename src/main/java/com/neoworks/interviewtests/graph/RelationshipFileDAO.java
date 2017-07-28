package com.neoworks.interviewtests.graph;

import com.sun.media.sound.InvalidFormatException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by jguanz on 7/27/17.
 */
public class RelationshipFileDAO implements IDataAccessObject {
    private static final String parseCharacter = ",";
    private static final int expectedNumberOfFields = 3;

    private String _filename;
    private BufferedReader _fileBuffer;
    private Map<String, ArrayList<Relationship>> _relationships = new HashMap<String, ArrayList<Relationship>>();

    RelationshipFileDAO(String filename) throws IOException{
        _filename = filename;
        this._fileBuffer = new BufferedReader(new FileReader(this._filename));
        createRelationships();
    }

    @Override
    public Map getData() {
        return _relationships;
    }

    private void createRelationships() throws IOException {
        String currentLine;

        while ((currentLine = _fileBuffer.readLine()) != null) {
            createRelationship(currentLine);
        }
    }

    private void createRelationship(String currentLine) throws IOException {
        try{
            String[] relationshipFields = parseCSV(currentLine);
            String email = relationshipFields[0];
            RelationshipType type = RelationshipType.valueOf(relationshipFields[1]);
            String relatedEmail = relationshipFields[2];

            if (!_relationships.containsKey(email)){
                _relationships.put(email, new ArrayList());
            }

            _relationships.get(email).add(new Relationship(email, type, relatedEmail));

        } catch (InvalidFormatException e) {
            System.out.println(String.format("Bypassed invalid formatted record: %s", e));
        }
    }

    private String[] parseCSV(String currentLine) throws InvalidFormatException {
        String[] parsedInput = currentLine.split(parseCharacter);

        if (parsedInput.length != expectedNumberOfFields) {
            throw new InvalidFormatException();
        }

        return parsedInput;
    }
}
