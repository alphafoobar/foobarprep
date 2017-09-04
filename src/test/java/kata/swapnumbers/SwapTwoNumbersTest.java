package kata.swapnumbers;

import static java.lang.Integer.MAX_VALUE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SwapTwoNumbersTest {

    @Test
    public void swap10And7() {
        SwapTwoNumbers numbers = new SwapTwoNumbers();
        numbers.numberOne = 10;
        numbers.numberTwo = 7;

        numbers.swapNumbers();

        assertThat(numbers.numberOne, is(7));
        assertThat(numbers.numberTwo, is(10));
    }

    @Test
    public void swapNeg10And7() {
        SwapTwoNumbers numbers = new SwapTwoNumbers();
        numbers.numberOne = -10;
        numbers.numberTwo = 7;

        numbers.swapNumbers();

        assertThat(numbers.numberOne, is(7));
        assertThat(numbers.numberTwo, is(-10));
    }

    @Test
    public void swapIntMaxAnd7() {
        SwapTwoNumbers numbers = new SwapTwoNumbers();
        numbers.numberOne = MAX_VALUE;
        numbers.numberTwo = 7;

        numbers.swapNumbers();

        assertThat(numbers.numberOne, is(7));
        assertThat(numbers.numberTwo, is(MAX_VALUE));
    }

    @Test
    public void swapIntMaxAndMax() {
        SwapTwoNumbers numbers = new SwapTwoNumbers();
        numbers.numberOne = MAX_VALUE;
        numbers.numberTwo = MAX_VALUE;

        numbers.swapNumbers();

        assertThat(numbers.numberOne, is(MAX_VALUE));
        assertThat(numbers.numberTwo, is(MAX_VALUE));
    }

}