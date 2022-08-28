# BinarySearch

一、二分查找法

1.对于有序数列，才能使用二分查找法（排序的作用）
2.时间复杂度：o(logn) ，没有把排序的时间算进去，排序叫做二分查找法的前置条件，如果计算排序时间：O(nlogn)
3.应用：多次查找
4.bug:mid=(l+r)/2   可能整型溢出
  改进:mid=l+(r-l)/2

二、二分查找法的递归实现

public class BinarySearch {
    
    private BinarySearch(){}
    
    public static <E extends Comparable<E>>int search(E[] data,E target){
        return search(data,0,data.length-1,target);
    }
    
    private static <E extends Comparable<E>>int search(E[] data,int l,int r,E target){
        if(l>r) return -1;
        
        int mid=l+(r-l)/2;//避免整型溢出
        
        if(data[mid].compareTo(target)==0)
            return mid;
        
        if(data[mid].compareTo(target)<0)
            return search(data,mid+1,r,target);
        
        return search(data,l,mid-1,target);   
    }

    public static void main(String[] args) {
        Integer[] data={1,2,3,4,5,6,7};
        int target=5;
        System.out.println(BinarySearch.search(data,target));
    }
}

leetcode704 二分查找法

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
    
}

三、二分查找法的非递归实现

 public static <E extends Comparable<E>>int search(E[] data,E target){
       
        int l=0,r=data.length-1;
        
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

leetcode704非递归实现

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

leetcode215 slcectK非递归实现

 public int selectK(int[] arr,int k,Random rnd){
        int l=0,r=arr.length-1;
        while(l<=r){
            int p=partition(arr,l,r,rnd);
            if(k==p) return arr[p];
            if(k<p) r=p-1;
            else l=p+1;
        }
        throw new RuntimeException("No Solution");
    }

四、换个定义实现二分查找法

修改循环不变量实现二分查找法

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

五、换个定义实现selectK

import java.util.Random;
public class Leetcode215 {
    
     public int findKthLargest(int[] nums, int k){
         Random rnd=new Random();
         return selectKR(nums,0,nums.length,nums.length-k,rnd);
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

六、改变定义重写归并排序算法

import java.util.Arrays;
public class MergeSortMain {
    
    private MergeSortMain(){}
    
    //自顶向下
    public static<E extends Comparable<E>>void sort(E[] arr){
        E[] temp=Arrays.copyOf(arr,arr.length);
        sort(arr,0,arr.length,temp);
    }
  
    //对arr[l,r)范围进行排序
    private static<E extends Comparable<E>>void sort(E[] arr,int l,int r,E [] temp){
       
        if(r-l<=1) return;
        
        int mid=l+(r-l)/2;
        sort(arr,l,mid,temp);
        sort(arr,mid,r,temp);
        
        if(arr[mid-1].compareTo(arr[mid])>0)
             merge(arr,l,mid,r,temp);
            
    }
    //合并两个有序的区间arr[l,mid)和arr[mid,r)
    private static<E extends Comparable<E>>void merge(E[] arr,int l,int mid,int r,E[] temp){
        
        System.arraycopy(arr, l, temp, l, r-l);
        
        int i=l,j=mid;
        //每轮为arr[k]赋值
        for(int k=l;k<r;k++){
            //arr[i]和arr[j]
            if(i>=mid){ 
                arr[k]=temp[j];
                j++;
            }
            else if(j>=r){
                arr[k]=temp[i];
                i++;
            }
            else if(temp[i].compareTo(temp[j])<=0){
                arr[k]=temp[i];
                i++;
            } 
            else{
                arr[k]=temp[j];
                j++;
            }
        }   
    }
         
    public static void main(String[] args) {
       int n=5000000;
       Integer[] arr=ArrayGenerator.generateRandomArray(n,n);
       Integer[] arr2=Arrays.copyOf(arr, arr.length);
       
       SortingHelper.sortTest("MergeSort", arr); 
       SortingHelper.sortTest("MergeSortBU", arr2); 
     
    }
}

七、二分查找法的变种

1.upper,求解>target的最小值索引

public class BinarySearch {
    
    private BinarySearch(){}

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

    public static void main(String[] args) {
        Integer[] data={1,1,2,3,4,5,6,7};
        for(int i=0;i<=6;i++)
            System.out.println(BinarySearch.upper(data,i)+" ");
        //System.out.println(BinarySearch.search(data,target));
        System.out.println();
        
    }
}

2.ceil

如果数组中存在元素，返回最大索引，如果数组中不存在元素，返回upper

//复用upper

public class BinarySearch {
    
    private BinarySearch(){}

  //>target,返回最小值索引
    //==target,返回最大值索引
    public static <E extends Comparable<E>>int ceil(E[]data,E target){
        int u=upper(data,target);
        if(u-1>=0&&data[u-1].compareTo(target)==0)
            return u-1;
        return u;
    }

    public static void main(String[] args) {
        Integer[] data={1,1,3,3,5,5};
        for(int i=0;i<=6;i++)
            System.out.println(BinarySearch.ceil(data,i)+" ");
        //System.out.println(BinarySearch.search(data,target));
        System.out.println();
        
    }
}

3.lower

查找小于target的最大值

搜索范围arr[l,r]   l=-1

if(arr[mid]<target) l=mid
if(arr[mid]>=target) r=mid-1

m=l+(r-l)/2=0+(1-0）/2=0
搜索空间没有变化，这个问题只有在l和r相邻时存在
解决方案：m=l+(r-l+1)/2

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

4.lower_floor

如果数组中存在元素，返回最小索引
如果不存在元素，返回lower

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


5.upper_floor

如果数组中存在元素，返回最大索引
如果数组中不存在元素，返回lower

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

八、换个方式实现二分查找

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

九、二分查找相关习题

1.leetcode875

class Solution {

    private int eatingTime(int[]piles,int k){
        
        int res=0;
        for(int pile:piles)
           res+= pile/k+(pile%k>0?1:0);
        return res;
    }

    public int minEatingSpeed(int[] piles, int H) {
        
        int l=1,r=Arrays.stream(piles).max().getAsInt();
        while(l<r){
            int mid=l+(r-l)/2;
            if(eatingTime(piles,mid)<=H)
                r=mid;
            else
                l=mid+1;
    }
    return l;
    }
   
}

2.leetcode1011

public class leetcode1011 {
    
     public int shipWithinDays(int[] weights, int days) {

        int l=Arrays.stream(weights).max().getAsInt();
        int r=Arrays.stream(weights).sum();
        while(l<r){
            int mid=l+(r-l)/2;
            if(days(weights,mid)<=days)
               r=mid;
            else 
               l=mid+1;
        }
        return l;
    }

    private int days(int[] weights,int k){
        int cur=0,res=0;
        for(int weight:weights)
            if(cur+weight<=k) cur+=weight;
            else{
                res++;
                cur=weight;
            }
            res++;
            return res;
    }
    
}
