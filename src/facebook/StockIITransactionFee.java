package facebook;

/**
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple 
transactions at the same time (ie, you must sell the stock before you buy again).

But with a transaction fee at sell.
 */
public class StockIITransactionFee {

	public static void main(String[] args) {
		int[] prices = {7,5,1,2,1,7,9};
		int[] transFee = {2,2,2,2,2,2,2};
		System.out.println(new StockIITransactionFee().maxProfit(prices, transFee));
	}
	public int maxProfit(int[] p, int[] t) {
		if (p.length == 0 || p.length != t.length)
			return 0;
		int res = 0;
		int buy = Integer.MIN_VALUE, sell = 0;
		for (int i = 0; i < p.length; i++) {
			int tmp = Math.max(Math.max(-p[i], sell-p[i]), buy);
			sell = Math.max(sell, buy + p[i] - t[i]);
			res = Math.max(res, sell);
			buy = tmp;
			res = Math.max(res, buy);
		}
		return res;
	}
}
