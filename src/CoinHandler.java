import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 */

/**
 * @author suki
 *
 */
public class CoinHandler {
	
	private static ArrayList<HashMap<Integer, Integer>> countCoinCombination(
			final int targetValue, 
			final int []coinValues, 
			final int []coinTotals, 
			final int []coinDividers) {
		ArrayList<HashMap<Integer, Integer>> result = new ArrayList<HashMap<Integer, Integer>>();
	    
	    int totalCoinType = coinValues.length;
	    
	    int totalPossibleCombination = 1;
	    for(int i = 0; i < totalCoinType; i++) {
	      totalPossibleCombination *= (coinTotals[i] + 1);
	    }
	    
	    // Loop all possible combination
	    HashMap<Integer, Integer> combination = new HashMap<Integer, Integer>();
	    for(int method = 0; method < totalPossibleCombination; method++) {
	      
	    	int totalCombination = 0;
	      
	    	int totalCoinUsed[] = new int[totalCoinType];
	      
	    	for(int i = 0; i < totalCoinType; i++) {
	    		int iTotalCoinUsed = ((int) (method / coinDividers[i])) % (coinTotals[i] + 1);
	    		totalCoinUsed[i] = iTotalCoinUsed;
	    		totalCombination += (coinValues[i] * iTotalCoinUsed);
	    	}
	      
	      // Check if totalCombination == desired value
	    	if(totalCombination == targetValue) {
	        
	    		combination = new HashMap<Integer, Integer>();
	        
	    		// create the combination AND store it to the result list
	    		for(int i = 0; i < totalCoinType; i++) {
	    			int iUsedCoinTotal = (int) (Math.floor(method / coinDividers[i]) % (coinTotals[i] + 1));
	    			combination.put(coinValues[i], iUsedCoinTotal);
	    		}
	    		result.add(combination);
	        
//	    		System.out.println("Combination Found =  " + totalCoinUsed[0] + "x" + coinValues[0] + " + " + totalCoinUsed[1] + "x" + coinValues[1] + " + " + totalCoinUsed[2] + "x" + coinValues[2] + " + " + totalCoinUsed[3] + "x" + coinValues[3]);
	    	}
	    }
	    
	    return result;
	  }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//TEST CASE
		//Let's say the available coin value variant in the cashier are 1 (3 coin), 2 (3 coin), 3 (2 coin), 4 (3 coin).
		//The cashier have to return 7 to customer.
		//How many unique variant combination that the cashier can return the money


		int targetValue = 7;				//How much changes we exchange to 
		int[] coinValues = {1,2,3,4};		//Coin Types (in ascending order)
		int[] coinTotals = {3,3,2,3};		//Coin amount of each coin type (in ascending order with coin type accordingly)
		
		//Calculate Coin Divider of each coin type, start with 1
		int[] coinDividers = new int[coinTotals.length];
		int totalPreviousDivider = 1;
		
		for(int i = 0; i < coinTotals.length; i++) {
			coinDividers[i] = totalPreviousDivider;
			totalPreviousDivider *= (coinTotals[i]+1);
		}
		
		ArrayList<HashMap<Integer, Integer>> combinations = CoinHandler.countCoinCombination(targetValue, coinValues, coinTotals, coinDividers);
		
		System.out.println("TOTAL COMBINATION: " + combinations.size());
		
		for(HashMap<Integer, Integer> combination : combinations){
			int i = 1;
			for(Entry<Integer, Integer> entry : combination.entrySet()) {
				System.out.print(entry.getValue() + "x" + entry.getKey());
				if (i < combination.size()) {
					System.out.print(" + ");
				}
				i++;
			}
			System.out.println();
		}
	}

}