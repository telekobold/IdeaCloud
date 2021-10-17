package de.telekobold.ideacloud;

import java.io.IOException;

import de.telekobold.ideacloud.codegenerator.IdeaCloudHtmlGenerator;

/**
 * 
 * @author Michael Merz <mail@telekobold.de>
 */
public class IdeaCloud {

    static IdeaCloudHtmlGenerator ic_html;
    // static IdeaCloudCssGenerator ic_css;

    public static void main(String[] args) throws IOException {
	ic_html = new IdeaCloudHtmlGenerator();
	// ic_css = new IdeaCloudCssGenerator();

	// Test data
	// TODO: substitute with input data from GUI
	IdeaCloudTriple<String, Integer, String>[] ideaCloudTriple = new IdeaCloudTriple[3];
	ideaCloudTriple[0] = new IdeaCloudTriple<String, Integer, String>("blubgnampf1", 12, "2021-11-10");
	ideaCloudTriple[1] = new IdeaCloudTriple<String, Integer, String>("blubgnampf2", 2, null);
	ideaCloudTriple[2] = new IdeaCloudTriple<String, Integer, String>("blubgnampf3", 6, null);
	ic_html.generateIdeaCloudHtmlFile(ideaCloudTriple);
    }
}
