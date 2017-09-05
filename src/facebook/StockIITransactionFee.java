package facebook;

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
