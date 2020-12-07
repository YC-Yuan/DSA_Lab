/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lab_five.generator;

import java.util.Random;

/**
 *
 * @author tcolburn
 */
public abstract class AbstractKeyGenerator implements lab_five.generator.KeyGenerator {
    
    public void reset() { }

    protected static int getRandom(int size) {
        return random.nextInt(size);
    }

    private static Random random = new Random();

    protected static final int MULTIPLIER = 1000000;

}
