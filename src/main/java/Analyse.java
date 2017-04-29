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
                System.out.println(f.getAbsolutePath());
                printSummary(finder.getStats());
                System.out.println();

                totalStats.addAll(finder.getStats());
            }

            System.out.println("total");
            printSummary(totalStats);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static void printSummary(List<Track> stats) {
        HashMap<LoopType, Summary> summaries = new HashMap<>();
        for (LoopType loop : LoopType.values()) {
            summaries.put(loop, new Summary());
        }

        stats.stream()
                .forEach(d -> summaries.get(d.getLoopType()).add(d));

        summaries.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + "," + entry.getValue()));
    }

}