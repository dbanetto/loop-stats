import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.google.common.io.PatternFilenameFilter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.stream.Collectors;

public class Analyse {

    public static void main(String[] args) {
        try {

            File argument = new File(args[0]);
            List<File> toAnalyse;

            if (argument.isDirectory()) {
                System.out.println("Looking in " + argument + " for java files");
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.java");

                toAnalyse = Files.walk(argument.toPath(), Integer.MAX_VALUE)
                        .filter(Files::isRegularFile)
                        .filter(Files::isReadable)
                        .filter(matcher::matches)
                        .map(p -> p.toFile())
                        .collect(Collectors.toList());

                 argument.listFiles((dir, name) -> name.endsWith("java"));
            } else {
                toAnalyse = new ArrayList<>();
                toAnalyse.add(argument);
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
        } catch (IOException e) {
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