import lee.recTo3darray;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
//import org.junit.Test;

/**
 * Created by le on 17-6-3.
 */
public class testupdate {
   @Test
    public void test_Simple()throws Exception{
        int[][] arr = {{ 1,0,0},{0,0,0},{0,1,0}};
        int[][] result =new int[5][5];
        new recTo3darray().count(arr, 3, 3, result);
        //int[][] resequ={{0,0,0,0,0},{0,1,1,1,0},{0,1,0,1,0},{0,1,1,1,0},{0,0,0,0,0}};
        //assertEquals(resequ,result);
        new recTo3darray().update(arr, 3, 3, result);
        int[][] arrequ={{0,0,0},{0,0,0},{0,0,0}};
        for(int i=0;i<arr.length;i++)
            for(int j=0;j<arr[0].length;j++)
                assertEquals(arrequ[i][j],arr[i][j]);
    }

    @Test
    public void test_Row5() throws Exception{
        int[][] arr = {{1, 1, 0, 0, 0},{1,1, 0, 0, 0},{1, 0, 1, 0, 0},{0, 1, 1, 0, 0},{0, 0, 1, 1, 0}};
        int[][] result =new int[7][7];
        new recTo3darray().count(arr, 5, 5, result);
        //int[][] resequ={{0,0,0,0,0},{0,1,1,1,0},{0,1,0,1,0},{0,1,1,1,0},{0,0,0,0,0}};
        //assertEquals(resequ,result);
        new recTo3darray().update(arr, 5, 5, result);
        int[][] arrequ={{1, 1, 0, 0, 0},{0 ,0, 1, 0, 0},{1, 0, 1, 0, 0},{0, 0, 0, 0, 0},{0, 1, 1, 1, 0}};
        for(int i=0;i<arr.length;i++)
            for(int j=0;j<arr[0].length;j++)
        assertEquals(arrequ[i][j],arr[i][j]);
    }
}
