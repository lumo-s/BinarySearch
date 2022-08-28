
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.binarysearch;

/**
 *
 * @author 86139
 */
public class leetcode704 {
    
    public int search(int[] nums,int target){
        return search(nums,0,nums.length-1,target);
    }
    
     private static int search(int[] data,int l,int r,int target){
        if(l>r) return -1;
        
        int mid=l+(r-l)/2;//避免整型溢出
        
        if(data[mid]==target)
            return mid;
        
        if(data[mid]<target)
            return search(data,mid+1,r,target);
        
        return search(data,l,mid-1,target);   
    }
    
     //非递归实现
     public int search1(int[] nums,int target){ 
        int l=0,r=nums.length-1;
        
        while(l<=r){
            int mid=l+(r-l)/2;
            if(nums[mid]==target)
                return mid;
        
            if(nums[mid]<target)
                 l=mid+1;
            else
                 r=mid-1; 
        }
        return -1;
    }
     
      //改变循环不变量
     public int search2(int[] nums,int target){ 
        int l=0,r=nums.length;
        
        //在nums[l,r)的范围中查找target
        while(l<=r){
            
            int mid=l+(r-l)/2;
            
            if(nums[mid]==target)
                return mid;
        
            if(nums[mid]<target)
                 l=mid+1;//继续在nums[mid+1,r)范围内寻找解
            else
                 r=mid; //继续在nums[l,mid)范围内寻找解
        }
        return -1;
    }
}
