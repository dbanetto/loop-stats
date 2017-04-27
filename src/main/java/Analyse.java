import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileNotFoundException;

public class Analyse {

    public static void main(String[] args) {
        try {
           CompilationUnit compilationUnit = JavaParser.parse(new File(args[0]));

            new MethodVisitor().visit(compilationUnit, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {

        @Override
        public void visit(MethodDeclaration n, Void args) {
            System.out.println(n.getName());

            super.visit(n, args);
        }

        @Override
        public void visit(WhileStmt whileStmt, Void args) {
           System.out.println(whileStmt);

           super.visit(whileStmt, args);
        }

    }
}
