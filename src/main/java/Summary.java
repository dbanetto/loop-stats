
public class Summary {

    private int total = 0;
    private int countNestedLoop = 0;
    private int countBranches = 0;
    private int countBreak = 0;
    private int countContinue = 0;
    private int countReturn = 0;

    public int getCountNestedLoop() {
        return countNestedLoop;
    }

    public int getCountBranches() {
        return countBranches;
    }

    public int getCountBreak() {
        return countBreak;
    }

    public int getCountContinue() {
        return countContinue;
    }


    public int getCountReturn() {
        return countReturn;
    }

    public void add(Track track) {
        total += 1;
        countBranches += track.hasBranches() ? 1 : 0;
        countNestedLoop += track.hasNestedLoop() ? 1 : 0;
        countBreak += track.hasBreak() ? 1 : 0;
        countContinue += track.hasContinue() ? 1 : 0;
        countReturn += track.hasReturn() ? 1 : 0;
    }

    @Override
    public String toString() {
        return total + "," + countNestedLoop + "," + countBranches + "," + countBreak + "," + countContinue + "," + countReturn;
    }
}
