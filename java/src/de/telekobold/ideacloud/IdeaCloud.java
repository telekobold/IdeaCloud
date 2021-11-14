package de.telekobold.ideacloud;

import java.io.IOException;

import de.telekobold.ideacloud.codegenerator.IdeaCloudCssGenerator;
import de.telekobold.ideacloud.codegenerator.IdeaCloudHtmlGenerator;

/**
 * The main method of this class contains test code for the generation of a new
 * idea cloud.
 * 
 * @author telekobold
 */
public class IdeaCloud {

    static IdeaCloudHtmlGenerator ic_html;
    static IdeaCloudCssGenerator ic_css;

    public static void main(String[] args) throws IOException {
	String filepath = "/home/telekobold/TestVerzeichnis/IdeaCloudTest/";
	ic_html = new IdeaCloudHtmlGenerator(filepath + "testIdeaCloud.html");

	System.out.println("Starting generation...");
	// Test data
	IdeaCloudTriple<String, Integer, String>[] ideaCloudTriple = new IdeaCloudTriple[3];
	ideaCloudTriple[0] = new IdeaCloudTriple<String, Integer, String>("blubgnampf1", 12, "2021-11-10");
	ideaCloudTriple[1] = new IdeaCloudTriple<String, Integer, String>("blubgnampf2", 2, null);
	ideaCloudTriple[2] = new IdeaCloudTriple<String, Integer, String>("blubgnampf3", 6, null);
	ic_html.generateIdeaCloudHtmlContent(ideaCloudTriple);
	ic_html.generateOutputFile();

	ic_css = new IdeaCloudCssGenerator(filepath + "testIdeaCloud.css");
	ic_css.generateIdeaCloudCssContent();
	ic_css.generateOutputFile();
	System.out.println("generation finished.");
    }
}
