package lee;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by le on 17-6-3.
 */
public class recTo3darray {
    //public int[][] array;
   //显示
    public void tetsArray(int[][] array, int row, int col) {
         System.out.println("-----------------------------");
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if(array[i][j] == 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
                if (j == col - 1) System.out.println();
            }
        System.out.println("-----------end-------------------\n");
    }

    //统计周围8个细胞存活数
    public void count(int[][] arr, int m, int n, int[][] res) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    res[i][j] += 1;
                    res[i][j + 1] += 1;
                    res[i][j + 2] += 1;
                    res[i + 1][j] += 1;
                    res[i + 1][j + 2] += 1;
                    res[i + 2][j] += 1;
                    res[i + 2][j + 1] += 1;
                    res[i + 2][j + 2] += 1;
                }
            }
        }

    }

    //更新array
    public void update(int[][] array, int m, int n, int[][] res) {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (res[i][j] > 3 || res[i][j] < 2)
                    array[i - 1][j - 1] = 0;
                else if (res[i][j] == 3)
                    array[i - 1][j - 1] = 1;
                else
                    continue;
            }
        }
    }

    //生成输入矩阵
    public int[][] getArr(int c) {
        if (c == 0) {
            Scanner in = new Scanner(System.in);
            Random r = new Random();
            System.out.println("请输入矩阵的长和宽（以空格分开）：");
            int row = in.nextInt();
            int col = in.nextInt();
            int[][] array = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int init = r.nextInt(2);
                    array[i][j] = init;
                }

            }
            return array;
        }
        else {
            //readfromfle
            String fileName = "juzhen.txt";
            try {
                LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(fileName)));
                lnr.skip(Long.MAX_VALUE);
                int t=lnr.getLineNumber();

                Scanner scanner = new Scanner(new File(fileName));
                String line = scanner.nextLine().trim();
                String[] str=line.split(" ");
                int[][] les = new int[t][str.length];
                for (int j = 0; j < str.length; j++)
                    les[0][j] = Integer.parseInt(str[j]);
                int i=1;
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                    System.out.println(line);
                    for (int j = 0; j < str.length; j++)
                        les[i][j] = Integer.parseInt(str[j]);
                    i++;
                }
                scanner.close();
                return les;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        recTo3darray obj = new recTo3darray();
        String ANSI_CLS = "\u001b[2J";

        System.out.println("----------------------------细胞更新展示---------------------------------");
        System.out.println("--------------菜单----------------");
        System.out.println("0.随机生成细胞的初始存活状态");
        System.out.println("1.从文件读入细胞的初始存活状态");
        System.out.println("请选择初始存活状态的生成方式（输入0,1）：");
        int choice = sc.nextInt();
        while(choice!=0&&choice!=1){
            System.out.println("输入有误,请重新选择（输入0,1）：");
            choice= sc.nextInt();
        }

        sc.nextLine();
        System.out.println("请输入细胞的繁殖时间（以ms为单位）：");
        long d=sc.nextLong();

        //生成细胞初始状态
        int[][] array=obj.getArr(choice);
        int row=array.length;
        int col= array[0].length;
        int[][] res= new int[row + 2][col + 2];

        //更新显示
        while(true) {
                //统计周围8个细胞存活数
            obj.count(array, row, col, res);
                //obj.count(array, row, col, res);

                //更新array
            obj.update(array, row, col, res);

                //显示更新后得细胞存活状态
            obj.tetsArray(array, row, col);

                //重置周围细胞存活数
            for (int i = 0; i < row + 2; i++)
                Arrays.fill(res[i], 0);

                Thread.sleep(d);
                System.out.println(ANSI_CLS);
                System.out.flush();
            }
        }
        }
