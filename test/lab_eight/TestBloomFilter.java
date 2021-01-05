package lab_eight;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

import java.util.UUID;

public class TestBloomFilter {

    private static final int ADD_SIZE = 1 << 20;
    private static final int QUERY_SIZE = 1 << 20;

    private static String[] toAdd = new String[ADD_SIZE];
    private static String[] toQuery = new String[QUERY_SIZE];

    static {
        for (int i = 0; i < ADD_SIZE; i++) {
            toAdd[i] = UUID.randomUUID().toString();
        }
        for (int i = 0; i < QUERY_SIZE; i++) {
            toQuery[i] = UUID.randomUUID().toString();
        }
    }

    @Test
    public void testBloomFilter() {
        BloomFilter bf = new BloomFilter();
        for (String str : toAdd) {
            bf.add(str);
        }
        int falsePositiveCount = 0;
        for (String str : toQuery) {
            if (bf.query(str)) {
                falsePositiveCount++;
            }
        }
        System.out.printf("FP: %d", falsePositiveCount);

        int num=0;
        for (int i=0;i<bf.bits.length;i++){
            if (bf.bits[i]) num++;
        }
        System.out.println("ratio of 1:" + (float) num / bf.bits.length);
        assertTrue(falsePositiveCount < 0.0005 * QUERY_SIZE);
    }

}
