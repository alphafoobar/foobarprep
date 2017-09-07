package kata.rainwater;

/**
 * {@see https://techdevguide.withgoogle.com/paths/advanced/volume-of-water/#code-challenge}
 *
 * <p>The idea is to find the 2-dimensional 'volume' of the lakes on an island. The hint is that it
 * is something of a shortest-path problem.
 */
class RainWaterOnAnIsland {

    static int calculateVolume(int[] values) {
        if (values.length < 3) {
            return 0; // Body of water must be contained.
        }

        int total = 0;
        for (int startIndex = 0; startIndex < values.length; ) {
            // Assume the first is high, it might not be and this is the expensive part.
            int startingMax = values[startIndex];
            int localMaxIndex = findLocalMax(values, startIndex);
            if (localMaxIndex > startIndex && localMaxIndex < values.length) {
                int minPeakValue = Math.min(values[localMaxIndex], startingMax);
                total += addVolume(values, startIndex, localMaxIndex, minPeakValue);
                startIndex = localMaxIndex;
            } else {
                startIndex++;
            }
        }

        return total;
    }

    private static int addVolume(int[] values, int startIndex, int localMax, int minPeakValue) {
        int total = 0;
        // calculate the volume
        for (int i = startIndex + 1; i < localMax; i++) {
            total += minPeakValue - values[i];
        }
        return total;
    }

    private static int findLocalMax(int[] values, int startIndex) {
        int localMax = startIndex + 1;

        for (int i = localMax; i < values.length; i++) {
            if (values[i] > values[startIndex]) {
                // Since this value is greater than the initial, return it as new max.
                return i;
            }
            if (values[localMax] <= values[i]) { // Find the last body that is max.
                localMax = i;
            }
        }
        return localMax;
    }

}
