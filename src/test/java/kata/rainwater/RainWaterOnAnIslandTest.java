package kata.rainwater;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RainWaterOnAnIslandTest {

    @Parameter
    public int[] ints;

    @Parameter(1)
    public int expected;

    @Parameters(name = "volume is {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
            params(new int[]{0, 0, 0, 0}, 0),
            params(new int[]{1, 0, 3, 0, 2, 0, 3}, 8),
            params(new int[]{1, 3, 2, 4, 1, 3, 1, 4, 5, 2, 2, 1, 4, 2, 2}, 15),
            params(new int[]{1, 3, 2, 4}, 1),
            params(new int[]{3, 2, 4}, 1),
            params(new int[]{4, 1, 3, 1, 4, 5}, 7),
            params(new int[]{4, 5, 2, 2, 1, 4, 2, 2}, 7),
            params(new int[]{5, 2, 2, 1, 4, 2, 2}, 7),
            params(new int[]{5, 2, 2, 1, 4}, 7),
            params(new int[]{1, 2, 2, 1, 0}, 0),
            params(new int[]{5, 2, 2, 1, 2}, 1),
            params(new int[]{5, 4, 3, 1, 0}, 0),
            params(new int[]{1, 2, 3, 4, 5}, 0)
        );
    }

    private static Object[] params(int[] ints, int expected) {
        return new Object[]{ints, expected};
    }

    @Test
    public void calculateVolumeIs() {
        assertThat(RainWaterOnAnIsland.calculateVolume(ints), is(expected));
    }

}
