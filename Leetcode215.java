/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.leetcode215;

/**
 *
 * @author 86139
 */

import java.util.Random;
public class Leetcode215 {
    
     public int findKthLargest(int[] nums, int k){
         Random rnd=new Random();
         return selectKR(nums,0,nums.length,nums.length-k,rnd);
     }
     
    //改写非递归实现
    public int selectK(int[] arr,int k,Random rnd){
        int l=0,r=arr.length;
        //在arr[l,r)范围内寻找第k小的数字
        while(l<r){
            int p=partition(arr,l,r,rnd);
            if(k==p) return arr[p];
            if(k<p) r=p;
            else l=p+1;
        }
        throw new RuntimeException("No Solution");
    }
    //改写递归实现
    //在arr[l,r)范围内寻找第k小的数字
     private int selectKR(int[] arr,int l,int r,int k,Random rnd){
         int p=partition(arr,l,r,rnd);
         if(k==p)return arr[p];
         if(k<p) return selectKR(arr,l,p,k,rnd);
         return selectKR(arr,p+1,r,k,rnd);  
     }
     //在arr[l,r)进行partition
     private int partition(int[]arr,int l,int r,Random rnd){
      //生成[l.r)之间的随机索引
        int p=l+rnd.nextInt(r-l);
        swap(arr,l,p);

        //arr[l+1..i-1]<=v;arr[j+1...r]>=v
        int i=l+1,j=r-1;
        while(true){
            
            while(i<=j&&arr[i]<arr[l])
                i++;
            while(j>=i&&arr[j]>arr[l])
                j--;
            if(i>=j) break;
            swap(arr,i,j);
            i++;
            j--;    
        } 
        swap(arr,l,j);
        return j;
     }
     
      private static void swap(int [] arr,int i,int j){
        
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }
}
