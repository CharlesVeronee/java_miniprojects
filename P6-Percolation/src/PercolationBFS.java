import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {

    public PercolationBFS(int x) {
        super(x);
    }

    @Override
    protected void dfs(int row, int col) {

        if (isFull(row, col)) {
            return;
        }

        if (!isOpen(row, col)) {
            return;
        }

        if (!inBounds(row, col)) {
            return;
        }

        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};
        Queue<int[]> queue = new LinkedList<>();
        myGrid[row][col] = FULL;
        queue.add(new int[]{row, col});

        while (queue.size() != 0) {
            int[] temp = queue.remove();
            for (int i = 0; i < rowDelta.length; i++) {
                row = temp[0] + rowDelta[i];
                col = temp[1] + colDelta[i];
                if (inBounds(row, col) && isOpen(row, col) && !isFull(row, col)) {
                    myGrid[row][col] = FULL;
                    queue.add(new int[]{row, col});
                }
            }

        }
    }
}
