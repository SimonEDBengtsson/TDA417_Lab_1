public class Heap{
    public static void main(String[] args){
        int[] array=new int[5];
        for(int i=0;i<array.length;i++){
            array[i]=(int)(Math.random()*1000);
        }
        sort(array);
        for(int i:array){
            System.out.println(i);
        }
    }
    public static void sort(int[] array){
        sort(array,0,array.length-1);
    }
    public static void sort(int[] array, int minBound, int maxBound){
        heapify(array,minBound,maxBound);// structure array as a max heap
        for(int i=maxBound;i>minBound;i--){
            swap(array,minBound,i);// swap the largest element in the heap to the sorted region
            siftDown(array,minBound,minBound,i-1);// rebuild the heap
        }
    }
    private static void swap(int[] array, int element_1, int element_2){// swap element 1 and 2 in an array
        int mem=array[element_1];
        array[element_1]=array[element_2];
        array[element_2]=mem;
    }
    private static void siftDown(int[] array, int i, int minBound, int maxBound){// swaps an element down to maintain the heap
        int child_1_index=2*i-minBound+1;
        if (child_1_index>maxBound) {
            return;
        }
        int child_2_index=child_1_index+1;
        if (child_2_index>maxBound) {
            if (array[child_1_index]>array[i]) {
                swap(array,i,child_1_index);
            }
        }
        else {
            int largerChildIndex=array[child_1_index]>array[child_2_index] ? child_1_index : child_2_index;
            if (array[largerChildIndex]>array[i]) {
                swap(array,i,largerChildIndex);
                siftDown(array,largerChildIndex,minBound,maxBound);
            }
        }
    }
    private static void heapify(int[] array, int minBound, int maxBound){
        for (int i = (maxBound+minBound)/2; i>=minBound; i--){
            siftDown(array,i,minBound,maxBound);
        }
    }
}