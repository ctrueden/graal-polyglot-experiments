import java.io.PrintStream;
import java.util.Set;

import org.graalvm.polyglot.Context;

public class JavaTest {

	public static void main(final String[] args) {

		final PrintStream output = System.out;
		final Context context = Context.newBuilder().allowAllAccess(true).build();
		final Set<String> languages = context.getEngine().getLanguages().keySet();
		output.println("Current Languages available in GraalVM: " + languages);

		System.out.println("Java: Hello World");

		context.eval("js", "print('JavaScript: Hello World')");
		context.eval("R", "print('R: Hello World');");
		context.eval("python", "print('Python: Hello World');");
	}
}
