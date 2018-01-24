import java.util.ArrayList;
import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        Map spielfeld = new Map(10, 10, 5, 1);

        int[][] a = new int[][]{{1,2,3,4,5}};
        int[][] b = new int[a.length][];
        for(int i = 0; i < a.length; i++)
            b[i] = a[i].clone();

        b[0][1] = 132;

        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));

    }

}