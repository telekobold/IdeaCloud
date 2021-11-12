package de.telekobold.ideacloud.codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Base class for all IdeaCloud code generator classes.
 * 
 * @author Michael Merz <mail@telekobold.de>
 */
public abstract class IdeaCloudAbstractGenerator {

    // TODO: Add getters and setters for filename and check if filename is a valid
    // file name.
    public String filename;

    public IdeaCloudAbstractGenerator(String filename) {
	this.filename = filename;
    }

    /**
     * Generates the output file of the {@code IdeaCloudAbstractGenerator}.
     * 
     * @param fileName      the name of the file to be generated
     * @param outputContent the content to write to the file
     * @throws IOException
     */
    public void generateOutputFile(AbstractBuilder outputContent) throws IOException {
	File outputFile = new File(filename);
	outputFile.createNewFile();
	try (BufferedWriter out = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath()));) {
	    out.write(outputContent.toString());
	}

    }
}
