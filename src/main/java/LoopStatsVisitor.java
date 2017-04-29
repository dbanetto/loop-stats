import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LoopStatsVisitor extends VoidVisitorAdapter<Void> {

    private final Track track;
    private boolean initialLoop = true;

    public LoopStatsVisitor(LoopType loopType) {
        super();
        this.track = new Track(loopType);
    }

    // Entry methods

    public void visit(WhileStmt whileStmt){
        super.visit(whileStmt, null);
    }

    public void visit(ForStmt whileStmt) {
        super.visit(whileStmt, null);
    }

    public void visit(ForeachStmt whileStmt) {
        super.visit(whileStmt, null);
    }


    //

    @Override
    public void visit(WhileStmt whileStmt, Void arg) {
       if (initialLoop) {
           initialLoop = false;
       } else {
           track.hasNestedLoop(true);
       }

       super.visit(whileStmt, arg);
    }

    @Override
    public void visit(ForStmt forStmt, Void arg) {
        if (initialLoop) {
            initialLoop = false;
        } else {
            track.hasNestedLoop(true);
        }

        super.visit(forStmt, arg);
    }

    @Override
    public void visit(ForeachStmt foreachStmt, Void arg) {
        if (initialLoop) {
            initialLoop = false;
        } else {
            track.hasNestedLoop(true);
        }

        super.visit(foreachStmt, arg);
    }

    @Override
    public void visit(ReturnStmt n, Void arg) {
        track.hasReturn(true);
        super.visit(n, arg);
    }

    @Override
    public void visit(BreakStmt n, Void arg) {
        track.hasBreak(true);
        super.visit(n, arg);
    }

    @Override
    public void visit(ContinueStmt n, Void arg) {
        track.hasContinue(true);
        super.visit(n, arg);
    }

    @Override
    public void visit(IfStmt n, Void arg) {
        track.hasBranches(true);
        super.visit(n, arg);
    }

    @Override
    public void visit(SwitchStmt n, Void arg) {
        track.hasBranches(true);
        super.visit(n, arg);
    }

    public Track getTrack() {
        return track;
    }
}
