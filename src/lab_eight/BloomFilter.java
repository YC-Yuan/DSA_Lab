package lab_eight;

import java.util.BitSet;

public class BloomFilter {

	//determine the size of bit array
	private final int size = 10;
	//determine the number of hash function (different seeds)
	private final int[] seeds = new int[]{7,13,67,83};
	private final BitSet bits = new BitSet(size);

	public BloomFilter() {}

	//add an element to Bloom Filter
	public void add(String str) {
		for (int i:seeds
			 ) {
			int hash = hash(i,str);
			bits.set(hash,true);
		}
	}

	//query whether Bloom Filter contains the element
	public boolean query(String str) {
		for (int i:seeds
			 ) {
			if (!bits.get(hash(i,str))) return false;
		}
		return true;
	}

	//Your hash function
	private int hash(int seed, String str) {
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			result = seed * result + str.charAt(i);
		}
		return Math.abs(result);
	}
}
