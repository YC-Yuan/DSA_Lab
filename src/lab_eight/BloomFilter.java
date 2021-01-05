package lab_eight;

import java.util.Arrays;
import java.util.BitSet;

public class BloomFilter {

    //determine the size of bit array
    private final int size = 1 << 28;

    //determine the number of hash function (different seeds)
    //private final int[] seeds = new int[]{13,17,19,20,21,22,78,31,93,30};
    private final int[] seeds = new int[]{2,3};
    final boolean[] bits = new boolean[size];//过滤器数组

    public BloomFilter() {
        for (int i = 0; i < size; i++) {
            bits[i] = false;//默认false
        }
    }

    //add an element to Bloom Filter
    public void add(String str) {
        for (int i : seeds
        ) {
            int hash = hash(i, str);
//            int hash = hashUniversal(i, str);
            bits[hash] = true;//hash之后设置为true
        }
    }

    //query whether Bloom Filter contains the element
    public boolean query(String str) {
        for (int i : seeds
        ) {
            int hash = hash(i, str);
//            int hash = hashUniversal(i, str);
            if (!bits[hash]) return false;//找到0就判断为不包含
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

    //尝试全域散列函数....太烂了
    private int hashUniversal(int seed, String str) {
        final int prime = 3922507;//取大素数
        int strCode=str.hashCode();
        //System.out.println(Math.abs(((seed * strCode + 73) % prime) % size));
        return Math.abs(((seed * strCode + 73) % prime) % size);
    }
}
