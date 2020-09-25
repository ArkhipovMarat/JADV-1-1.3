import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class AverageSumForkJoin extends RecursiveTask<Long> {
    private static final int THRESHOLD = 5000000;
    private int low;
    private int high;
    private int[] array;

    public AverageSumForkJoin(int[] array, int low, int high) {
        this.low = low;
        this.high = high;
        this.array = array;
    }

    @Override
    protected Long compute() {
        if (high - low <= THRESHOLD) {
            long sum = 0;
            for (int i = low; i < high; ++i) sum += array[i];
            return sum;
        } else {
            int mid = low + (high - low) / 2;
            AverageSumForkJoin left = new AverageSumForkJoin(array, low, mid);
            AverageSumForkJoin right = new AverageSumForkJoin(array, mid, high);
            left.fork();
            long rightAns = right.compute();
            long leftAns = left.join();
            return leftAns + rightAns;
        }
    }

    static long sumArray(int[] array) {
        return ForkJoinPool.commonPool().invoke(new AverageSumForkJoin(array, 0, array.length));
    }
}
