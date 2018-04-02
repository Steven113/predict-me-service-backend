package AppCore;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class HTMLTemplatingEngine {

	final Configuration cfg;
	
	public HTMLTemplatingEngine(String pathToTemplateDirectory) {
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.27) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration();

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		try {
			final File f = new File(HTMLTemplatingEngine.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			
			final File fParentDir = new File((f.getCanonicalPath()));
			
			System.out.println(fParentDir.getCanonicalPath() + " " + fParentDir.exists());
			
			cfg.setDirectoryForTemplateLoading(new File(pathToTemplateDirectory));
		} catch (IOException e) {
			System.out.println("Failure to configure FreeMarker templating. Exiting program");
			e.printStackTrace();
			System.exit(-1);
		}

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		System.out.println("Finished configuring freemarker template");
	}
	
	
	public String generateTemplatedHTML(String templatePath, Map values) throws TemplateException, IOException {
		Map root = new HashMap(values);
        
        Template temp = cfg.getTemplate("index.html");

        /* Merge data-model with template */
        Writer out = new StringWriter();
        temp.process(root, out);
        
        return ((StringWriter)out).toString();
	}
}
