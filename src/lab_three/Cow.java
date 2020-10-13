package lab_three;

import java.io.Serializable;

/**
 * Do not add, change, or remove properties or functions in this class
 */
public class Cow implements Serializable {
    private final int arrival;
    private final int eating;

    public Cow(int arrival, int eating) {
        this.arrival = arrival;
        this.eating = eating;
    }

    public int getArrival() {
        return arrival;
    }

    public int getEating() {
        return eating;
    }

}
