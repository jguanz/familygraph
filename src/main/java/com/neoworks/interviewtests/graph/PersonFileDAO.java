package com.neoworks.interviewtests.graph;

import com.sun.media.sound.InvalidFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by jguanz on 7/27/17.
 */
public class PersonFileDAO implements IDataAccessObject {
    private static final String parseCharacter = ",";
    private static final int expectedNumberOfFields = 3;

    private String _filename;
    private BufferedReader _fileBuffer;
    private Map _persons = new HashMap();

    PersonFileDAO(String filename) throws IOException {
        this._filename = filename;
        this._fileBuffer = new BufferedReader(new FileReader(this._filename));
        createPersons();
    }

    @Override
    public Map getData() {
        return _persons;
    }

    private void createPersons() throws IOException {
        String currentLine;

        while ((currentLine = _fileBuffer.readLine()) != null) {
            createPerson(currentLine);
        }
    }

    private void createPerson(String currentLine) throws IOException {
        try {
            String[] personFields = parseCSV(currentLine);
            String name = personFields[0];
            String email = personFields[1];
            int age = Integer.parseInt(personFields[2]);

            _persons.put(email, new Person(name, email, age));
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
