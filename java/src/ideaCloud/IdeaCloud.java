package ideaCloud;

import java.io.IOException;

public class IdeaCloud {

    static IdeaCloudHtmlGenerator ic_html;
    // static IdeaCloudCssGenerator ic_css;

    public static void main(String[] args) throws IOException {
	ic_html = new IdeaCloudHtmlGenerator();
	// ic_css = new IdeaCloudCssGenerator();
	ic_html.generateIdeaCloudHtmlFile();
    }
}
