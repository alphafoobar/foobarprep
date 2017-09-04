package kata.swapnumbers;

/**
 * This class swaps two numbers without using a temporary variable.
 * http://javarevisited.blogspot.co.nz/2016/01/top-20-amazon-and-google-programming-interview-questions.html?m=1
 */
class SwapTwoNumbers {

    int numberOne;
    int numberTwo;

    /**
     * Swaps the given numbers without using a temp variable. Any simple mathematical formula could
     * be used.
     */
    void swapNumbers() {
        numberOne += numberTwo;
        numberTwo = numberOne - numberTwo;
        numberOne -= numberTwo;
    }
}
