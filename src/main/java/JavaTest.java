import java.util.Set;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class JavaTest {

	public static void main(final String[] args) {

		final Context context = Context.newBuilder() //
			.option("js.shared-array-buffer", "true") //
			.allowAllAccess(true) //
			.build();
		final Set<String> languages = context.getEngine().getLanguages().keySet();
		System.out.println("Current Languages available in GraalVM: " + languages);

		final double[] bigArray = new double[300 * 1000 * 1000];

		// Java
		System.out.println("Java: Hello World: " + bigArray.length);

		// JavaScript
		context.getBindings("js").putMember("bigArray", bigArray);
		context.eval("js", "print('JavaScript: Hello World: ' + bigArray.length)");

		final Value buf = context.eval("js",
			"buf = new SharedArrayBuffer(1000 * 1000 * 1000)");
		System.out.println("SharedArrayBuffer object = " + buf.getClass());
		// TODO: How to use this from Java?

		// NB: For the remaining languages, we use the polyglot bindings.
		context.getPolyglotBindings().putMember("bigArray", bigArray);

		// Python
		context.eval("python", //
			"import polyglot\n" + //
			"big_array = polyglot.import_value('bigArray')\n" + //
			"print('Python: Hello World: ' + str(len(big_array)))");

		// Ruby
		context.eval("ruby", "puts('Ruby: Hello World')");

		// R
		context.eval("R", "print('R: Hello World');");
	}
}
