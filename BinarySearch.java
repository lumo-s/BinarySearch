/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.binarysearch;

/**
 *
 * @author 86139
 */
public class BinarySearch {
    
    private BinarySearch(){}
    
    //非递归实现
    public static <E extends Comparable<E>>int search(E[] data,E target){
       
        int l=0,r=data.length-1;
        
        //在data[l,r)的范围中查找
        while(l<=r){
            int mid=l+(r-l)/2;
            if(data[mid].compareTo(target)==0)
                return mid;
        
            if(data[mid].compareTo(target)<0)
                l=mid+1;
            else
                r=mid-1; 
        }
        return -1;
    }
    //递归实现二分查找法
    public static <E extends Comparable<E>>int searchR(E[] data,E target){
        return searchR(data,0,data.length-1,target);
    }
    
    private static <E extends Comparable<E>>int searchR(E[] data,int l,int r,E target){
        if(l>r) return -1;
        
        int mid=l+(r-l)/2;//避免整型溢出
        
        if(data[mid].compareTo(target)==0)
            return mid;
        
        if(data[mid].compareTo(target)<0)
            return searchR(data,mid+1,r,target);
        
        return searchR(data,l,mid-1,target);   
    }
    
    //使用求解>=target的最小值索引的思路，实现search
    //在有序数组data中判断target是否存在，存在则返回相应索引，不存在则返回-1
    public static <E extends Comparable<E>>int search2(E[] data,E target){
        //求解>=target的最小值索引
        int l=0,r=data.length;
        //在data[l,r]中寻找解
        while(l<r){
            int mid=l+(r-l)/2;
            if(data[mid].compareTo(target)<0)
                l=mid+1;
            else
                r=mid;
        }
        if(l<data.length&&data[l].compareTo(target)==0)
            return l;
        return -1;
    }
    
    //>target的最小值索引
    public static<E extends Comparable<E>>int upper(E[] data,E target){
        int l=0,r=data.length;
        
        //在data[l,r]中寻找解
        while(l<r){
            int mid=l+(r-l)/2;
            if(data[mid].compareTo(target)<=0)
                l=mid+1;
            else
                r=mid;          
        }
        return l;
    }
    
    //>target,返回最小值索引
    //==target,返回最大值索引
    public static <E extends Comparable<E>>int ceil(E[]data,E target){
        int u=upper(data,target);
        if(u-1>=0&&data[u-1].compareTo(target)==0)
            return u-1;
        return u;
    }
    
    //<target的最大索引值
    public static <E extends Comparable<E>>int lower(E[]data,E target){
        int l=-1,r=data.length-1;
        
        //在data[l,r]中寻找解
        while(l<r){
            int mid=l+(r-l+1)/2;//上取整
            if(data[mid].compareTo(target)<0)
                l=mid;
            else
                r=mid-1;
        }
        return l;
    }
    
    //<target,返回最大值索引
    //==target,返回最小值索引
     public static <E extends Comparable<E>>int lower_floor(E[]data,E target){
        int l=lower(data,target);
        
        //注意，因为我们要访问data[l+1],所以，我们要先确保l+1不越界
        //即l+1<data.length
       if(l+1<data.length&&data[l+1].compareTo(target)==0)
           return l+1;
       return l;
    }
     
    //<=target最大索引 
    public static<E extends Comparable<E>>int upper_floor(E[]data,E target){
        int l=-1,r=data.length-1;
        //在data[l,r]中寻找解
        while(l<r){
            int mid=l+(r-l+1)/2;//上取整
            if(data[mid].compareTo(target)<=0)
                l=mid;
            else
                r=mid-1;
        }
        return l;    
    }
    
    public static void main(String[] args) {
        Integer[] data={1,1,3,3,5,5};
        System.out.print("lower_floor: ");
        for(int i=0;i<=6;i++)
            System.out.print(BinarySearch.lower_floor(data,i)+" ");
        System.out.println();
        
        System.out.print("upper_floor: ");
        for(int i=0;i<=6;i++)
            System.out.println(BinarySearch.upper_floor(data,i)+" ");
        System.out.println();    
    }
}
