import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class AnalyseTest {


    @Test
    public void ForSimple() {
        FindLoopsVisitor stats = new FindLoopsVisitor();
        stats.visit(getAst("For_Simple"), null);
    }


    private CompilationUnit getAst(String resource) {
        try {
            return JavaParser.parse(new File("src/test/resources/examples/" + resource + ".java"));
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
            throw new RuntimeException(e);
        }
    }

}