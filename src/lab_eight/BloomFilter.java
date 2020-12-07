package lab_eight;

import java.util.Arrays;
import java.util.BitSet;

public class BloomFilter {

    //determine the size of bit array
    private final int size = 1 << 28;

    //determine the number of hash function (different seeds)
    private final int[] seeds = new int[]{1, 2, 3};
    private final boolean[] bits = new boolean[size];

    public BloomFilter() {
        for (int i = 0; i < size; i++) {
            bits[i] = false;
        }
    }

    //add an element to Bloom Filter
    public void add(String str) {
        for (int i : seeds
        ) {
            int hash = hash(i, str);
            bits[hash] = true;
        }
    }

    //query whether Bloom Filter contains the element
    public boolean query(String str) {
        for (int i : seeds
        ) {
            int hash = hash(i, str);
            if (!bits[hash]) return false;
        }
        return true;
    }

    //Your hash function
    private int hash(int seed, String str) {
        int hash, i;
        switch (seed) {
            case 1:
                for (hash = str.length(), i = 0; i < str.length(); i++)
                    hash += str.charAt(i);
                return (hash % size);
            case 2:
                return Math.abs(str.hashCode()) % size;
            case 3:
                for (hash = str.length(), i = 0; i < str.length(); ++i)
                    hash = (hash << 4) ^ (hash >> 28) ^ str.charAt(i);
                return (Math.abs(hash) % size);
            default:
                return 0;
        }
    }
}
