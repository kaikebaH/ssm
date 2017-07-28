package com.kaikeba.app.utils;

/**
 * Created by Administrator on 2017/7/28.
 */
public class test {
    public static void main(String ages[]){
        int[] a = new int[]{3,1,2,5,4};
        int t;
        for(int i = 0;i < a.length;i++){
            for(int j = 0;j < a.length-1;j++){
                if(a[j] > a[j+1]){
                    t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                }
            }
        }
        System.out.print("冒泡排序的结果为：");
        for(int h : a){
            System.out.print(h + " ");
        }
        System.out.println();
        a = new int[]{1,5,2,4,3};
        int min = 0;
        for(int i = 0;i < a.length;i++){
            min = i;
            for(int j = i+1;j < a.length;j++){
                if(a[j] < a[min]){
                    min = j;
                }
            }
            t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
        System.out.print("选择排序的结果为：");
        for(int s : a){
            System.out.print(s + " ");
        }
    }
}
