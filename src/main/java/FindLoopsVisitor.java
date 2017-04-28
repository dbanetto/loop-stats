import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class FindLoopsVisitor extends VoidVisitorAdapter<Void> {

    private final List<Track> stats = new ArrayList<>();

    @Override
    public void visit(WhileStmt whileStmt, Void args) {
        System.out.println(whileStmt);

        LoopStatsVisitor stat = new LoopStatsVisitor(LoopType.WHILE);
        stat.visit(whileStmt);
        stats.add(stat.getTrack());

        super.visit(whileStmt, args);
    }

    @Override
    public void visit(ForStmt forStmt, Void args) {
        System.out.println(forStmt);

        LoopStatsVisitor stat = new LoopStatsVisitor(LoopType.FOR);
        stat.visit(forStmt);
        stats.add(stat.getTrack());

        super.visit(forStmt, args);
    }

    @Override
    public void visit(ForeachStmt foreachStmt, Void args) {
        System.out.println(foreachStmt);

        LoopStatsVisitor stat = new LoopStatsVisitor(LoopType.FOREACH);
        stat.visit(foreachStmt);
        stats.add(stat.getTrack());

        super.visit(foreachStmt, args);
    }

    public List<Track> getStats() {
        return stats;
    }
}
