package Homework3;

public class Main {
    public static void main(String[] args) {
        System.out.println(Calculator.subtract(10, 5));
        Integer[] array1 = {1, 2, 3, 4};
        Integer[] array2 = {4, 5, 10, 11};
        Integer[] array3 = {1};
        Double[] array4 = {1.0, 2.0, 3.0, 4.0};

        System.out.println(compareArrays(array1, array2));
        System.out.println(compareArrays(array1, array3));
        System.out.println(compareArrays(array1, array4));

        Pair<Integer, String> pair = new Pair<>(1, "Hello");

        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);
    }

    public static <T, U> boolean compareArrays(T[] array1, U[] array2) {
        if (array1.length != array2.length) return false;

        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].getClass().equals(array2[i].getClass())){
                return false;
            }
        }

        return true;
    }
}
