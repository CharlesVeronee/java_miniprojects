public class PercolationUF implements IPercolate{

    private IUnionFind myFinder;
    private boolean [][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;


    public PercolationUF (IUnionFind finder, int size) {
        myFinder = finder;
        myFinder.initialize(size*size+2);
        myGrid = new boolean [size][size];
        VTOP = size * size;
        VBOTTOM = size * size +1;
        myOpenCount = 0;
    }

    @Override
    public boolean isOpen(int row, int col){
        if(!inBounds(row,col)){
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }

        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col){
        if(!inBounds(row,col)){
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }

        return myFinder.connected(row*myGrid.length+col, VTOP);
    }


    @Override
    public boolean percolates() {
        return myFinder.connected(VBOTTOM, VTOP);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }


    private boolean inBounds (int row, int col){
        if (row < 0 || row >= myGrid.length){
            return false;
        }

        if (col < 0 || col >= myGrid[0].length){
            return false;
        }

        return true;
    }

    @Override
    public void open(int row, int col){
        if(!inBounds(row,col)){
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }

        if (myGrid[row][col]){
            return;
        }

        if (row == 0){
            myFinder.union(VTOP, row*myGrid.length+col);
        }

        if (row == myGrid.length-1) {
            myFinder.union(VBOTTOM, row*myGrid.length+col);
        }

        myOpenCount++;
        myGrid[row][col] = true;

        if((inBounds(row, col + 1) && isOpen(row, col + 1))) {
            myFinder.union(row*myGrid.length + col, (row)*myGrid.length + col + 1);
        }

        if((inBounds(row + 1, col) && isOpen(row + 1, col))) {
            myFinder.union(row*myGrid.length + col, (row+1)*myGrid.length + col);
        }

        if((inBounds(row - 1, col) && isOpen(row - 1, col))) {
            myFinder.union(row*myGrid.length + col, (row-1)*myGrid.length + col);
        }

        if((inBounds(row, col - 1) && isOpen(row, col - 1))) {
            myFinder.union(row*myGrid.length + col, (row)*myGrid.length + col - 1);
        }
    }
}
