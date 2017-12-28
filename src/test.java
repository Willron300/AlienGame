import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] a = new int[] {1,3};
        int[] b = new int[] {1,3};
        int[] c = new int[] {3,4};
        int[] d = new int[2];
        d[0] = 3;
        d[1] = 4;

        if (Arrays.equals(a, b)) {
            System.out.println("Test1 ");
        }
        if(Arrays.equals(c, d)) {
            System.out.println("Test2 ");
        }
        if(Arrays.equals(a, c)) {
            System.out.println("Test3 ");
        }
    }
}
