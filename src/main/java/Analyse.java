import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.google.common.io.PatternFilenameFilter;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

public class Analyse {

    public static void main(String[] args) {
        try {

            File argument = new File(args[0]);
            File[] toAnalyse;

            if (argument.isDirectory()) {
                toAnalyse = argument.listFiles((dir, name) -> name.endsWith("java"));
            } else {
                toAnalyse = new File[] { argument };
            }

            List<Track> totalStats = new ArrayList<>();

            for (File f : toAnalyse) {
                CompilationUnit compilationUnit = JavaParser.parse(f);

                FindLoopsVisitor finder = new FindLoopsVisitor();
                finder.visit(compilationUnit, null);
                System.out.print(f.getAbsolutePath());
                System.out.println(finder.getStats());

                totalStats.addAll(finder.getStats());
            }

            System.out.print("total");
            System.out.println(totalStats);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}