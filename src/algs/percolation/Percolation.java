package algs.percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF uf = null;
	private int[][] grid = null;
	private int count = 0;
	private int n = 0;
	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		if(n <= 0)
			throw new IllegalArgumentException("n and trials must biger 0");
		uf = new WeightedQuickUnionUF(n * n + 2);
		grid = new int[n][n];
		this.n = n;
		for(int i = 1;i <= n;i++) {
			uf.union(i, 0);
			uf.union(n * (n - 1) + i, n * n + 1);
		}
	}
	public void open(int row, int col) {
		// open site (row, col) if it is not open already
		if(row < 1 || row > n || col < 1 || col > n)
    		throw new IndexOutOfBoundsException("row and col must between 0 to n");
		if(!this.isOpen(row, col)) {
			if(col != 1)	//  check left
				if(!uf.connected(n * (row - 1) + col, n * (row - 1) + col - 1))
					uf.union(n * (row - 1) + col, n * (row - 1) + col - 1);
			if(col != n)	//  check right
				if(!uf.connected(n * (row - 1) + col, n * (row - 1) + col + 1))
					uf.union(n * (row - 1) + col, n * (row - 1) + col + 1);
			if(row != 1)	//  check up
				if(!uf.connected(n * (row - 1) + col, n * (row - 2) + col))
					uf.union(n * (row - 1) + col, n * (row - 2) + col);
			if(row != n)	//  check down
				if(!uf.connected(n * (row - 1) + col, n * row + col))
					uf.union(n * (row - 1) + col, n * row + col);
			grid[row - 1][col - 1] = 1;
			count++;
		}
	}
    public boolean isOpen(int row, int col) {
    	// is site (row, col) open?
    	if(row < 1 || row > n || col < 1 || col > n)
    		throw new IndexOutOfBoundsException("row and col must between 0 to n");
    	return grid[row - 1][col - 1] == 1;
    }
    public boolean isFull(int row, int col) {
    	// is site (row, col) full?
    	if(row < 1 || row > n || col < 1 || col > n)
    		throw new IndexOutOfBoundsException("row and col must between 0 to n");
    	return uf.connected(0, n * (row - 1) + col);
    }
	public int numberOfOpenSites() {
		// number of open sites
		return count;
	}
	public boolean percolates() {
		// does the system percolate?
		return uf.connected(0, n * n + 1);
	}

}
