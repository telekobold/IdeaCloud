package de.telekobold.ideacloud;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class IdeaCloudAbstractGenerator {

    /**
     * Generates the output file of the {@code IdeaCloudAbstractGenerator}.
     * 
     * @param fileName      the name of the file to be generated
     * @param outputContent the content to write to the file
     * @throws IOException
     */
    static void generateOutputFile(String fileName, HtmlBuilder outputContent) throws IOException {
	// TODO: Gewünschten Speicherort abfragen oder im Home-Verzeichnis in
	// verstecktem Verzeichnis ablegen, oder von Einstellung in GUI abhängig machen.
	File outputFile = new File("/home/telekobold/TestVerzeichnis/IdeaCloudTest/" + fileName);
	outputFile.createNewFile();
	try (BufferedWriter out = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath()));) {
	    out.write(outputContent.toString());
	}

    }
}
