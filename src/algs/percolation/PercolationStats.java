package algs.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int t = 0;
	private double[] y = null;
	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		this.t = trials;
		y = new double[t];
		if(n <= 0 || trials <= 0)
			throw new IllegalArgumentException("n and trials must biger 0");
		for(int i = 0;i < trials;i++) {
			Percolation p = new Percolation(n);
			while(!p.percolates()) {
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				p.open(row, col);
			}
			y[i] = (double)p.numberOfOpenSites() / (n * n);
		}
	}
	   public double mean() {
		   // sample mean of percolation threshold
		   return StdStats.mean(y);
	   }
	   public double stddev() {
		   // sample standard deviation of percolation threshold
		   return StdStats.stddev(y);
	   }
	   public double confidenceLo() {
		   // low  endpoint of 95% confidence interval
		   return 0.0;
	   }
	   public double confidenceHi() {
		   // high endpoint of 95% confidence interval
		   return 0.0;
	   }
	
	   public static void main(String[] args) {
		   // test client (described below)
			   }
	
	}
