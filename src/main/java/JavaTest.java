import org.graalvm.polyglot.*;
import java.io.PrintStream;
import java.util.Set;

public class JavaTest {

public static void main(String[] args) {

    PrintStream output = System.out;
    Context context = Context.newBuilder().allowAllAccess(true).build();
    Set<String> languages = context.getEngine().getLanguages().keySet();
    output.println("Current Languages available in GraalVM: " + languages);

    System.out.println("Java: Hello World");

    context.eval("js","print('JavaScript: Hello World')");
    context.eval("R", "print('R: Hello World');");
    context.eval("python", "print('Python: Hello World');");
  }
}

