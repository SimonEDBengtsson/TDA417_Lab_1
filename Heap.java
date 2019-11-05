public class Heap{
    public static void test(){
        int[] array=new int[1000];
        for(int i=0;i<array.length;i++){
            array[i]=(int)(Math.random()*1000);
        }
        sort(array);
        for (int i:array) {
            System.out.println(i);
        }
    }
    public static void sort(int[] array){
        sort(array,0,array.length-1);
    }
    public static void sort(int[] array, int minBound, int maxBound){
        heapify(array,minBound,maxBound);// structure ca as a max heap
        for(int i=maxBound;i>=minBound;i--){
            swap(array,minBound,i);// swap the largest element in the heap to the sorted region
            siftDown(array,minBound,i);// rebuild the heap
        }
    }
    private static void swap(int[] array, int element_1, int element_2){// swap element 1 and 2 in an array
        int mem=array[element_1];
        array[element_1]=array[element_2];
        array[element_2]=mem;
    }
    private static void siftDown(int[] array, int i, int bound){// iff ca[i] is smaller than one of its children, swap it with the bigger and continue down
        int child_1_index=i*2+1;
        int child_2_index=child_1_index+1;
        if(child_1_index>=bound){
            return;// base case
        }
        else if(child_2_index>=bound){// child 2 is outside the heap, child 1 inside
            if(array[i]<array[child_1_index]){
                swap(array,i,child_1_index);
            }
            return;
        }
        else if(array[i]>=array[child_1_index] && array[i]>=array[child_2_index]){
            return;
        }
        if(array[child_1_index]>array[child_2_index]){
            swap(array,i,child_1_index);
            siftDown(array,child_1_index,bound);
        }
        else{
            swap(array,i,child_2_index);
            siftDown(array,child_2_index,bound);
        }
    }
    private static void heapify(int[] array, int minBound, int maxBound){
        for (int i = minBound+(maxBound-minBound)/2; i>=minBound; i--){
            siftDown(array,i,array.length);
        }
    }
}