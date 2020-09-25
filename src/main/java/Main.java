public class Main {
    public static void main(String[] args) {
        int[] array = arrayCreator(100000000);

// время выполнения обоих задач сравнялось при размере массива - 1 млн.
// до этого порога медленнее работал метод ForkJoin

        {
            long lStartTime = System.currentTimeMillis();
            averageSumCommon(array);
            long lEndTime = System.currentTimeMillis();
            long output = lEndTime - lStartTime;
            System.out.println("Время выполнения задачи №1: " + output);
        }

        System.out.println("-----------------------");

        {
            long lStartTime = System.currentTimeMillis();
            AverageSumForkJoin.sumArray(array);
            long lEndTime = System.currentTimeMillis();
            long output = lEndTime - lStartTime;
            System.out.println("Время выполнения задачи №2: " + output);
        }

    }

    public static int[] arrayCreator (int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random()*(200+1)) - 100;
        }
        return array;
    }

    public static int averageSumCommon (int[] array) {
        int sum = 0;
        for (int i : array) sum += i;
        return sum;
    }
}
