public class Track {

    private final LoopType loopType;

    private boolean hasNestedLoop = false;
    private boolean hasBranches = false;
    private boolean hasBreak = false;
    private boolean hasContinue = false;
    private boolean hasReturn = false;

    public Track(LoopType loopType) {
        this.loopType = loopType;
    }

    public LoopType getLoopType() {
        return loopType;
    }

    public boolean hasNestedLoop() {
        return hasNestedLoop;
    }

    public void hasNestedLoop(boolean hasNestedLoop) {
        this.hasNestedLoop = hasNestedLoop;
    }

    public boolean hasBranches() {
        return hasBranches;
    }

    public void hasBranches(boolean hasBranches) {
        this.hasBranches = hasBranches;
    }

    public boolean hasBreak() {
        return hasBreak;
    }

    public void hasBreak(boolean hasBreak) {
        this.hasBreak = hasBreak;
    }

    public boolean hasContinue() {
        return hasContinue;
    }

    public void hasContinue(boolean hasContinue) {
        this.hasContinue = hasContinue;
    }

    public boolean hasReturn() {
        return hasReturn;
    }

    public void hasReturn(boolean hasReturn) {
        this.hasReturn = hasReturn;
    }

    @Override
    public String toString() {
        return "Track{" +
                "loopType=" + loopType +
                ", hasNestedLoop=" + hasNestedLoop +
                ", hasBranches=" + hasBranches +
                ", hasBreak=" + hasBreak +
                ", hasContinue=" + hasContinue +
                ", hasReturn=" + hasReturn +
                '}';
    }
}
