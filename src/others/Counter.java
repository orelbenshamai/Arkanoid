package others;

/**
 * this class implements a counter with an a constructor, decrease, increase and get value.
 *
 * @author Orel Ben Shamay 318869658
 */
public class Counter {

    private int value;

    /**
     * constructor.
     *
     */
    public Counter() {
        value = 0;
    }

    /**
     * add number to current count.
     *
     * @param number int
     */
    public void increase(int number) {
        this.value = this.value + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number int
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * get current count.
     *
     * @return int
     */
    public int getValue() {
        return this.value;
    }
}