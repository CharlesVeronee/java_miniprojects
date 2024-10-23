public class PercolationDFSFast extends PercolationDFS{
    public PercolationDFSFast(int x) {
        super(x);
    }

    @Override
    protected void updateOnOpen(int row, int col) {
        if (row == 0) {
            dfs(row, col);
        }

        else if ((row != 0 && isFull(row-1, col) || row != myGrid[row].length-1 && isFull(row+1, col)) ||
                (col != 0 && isFull(row, col-1) || col != myGrid[col].length-1 && isFull(row, col+1))) {
            dfs(row, col);
        }

    }

}