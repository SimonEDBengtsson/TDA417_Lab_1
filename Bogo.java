public class Bogo {
    public static void sort(int[] a){
        sort(a,0,a.length-1);
    }
    public static void sort(int[] a,int minBound,int maxBound){
        while(!isSorted(a,minBound,maxBound)) {
            randomize(a,minBound,maxBound);
        }
    }
    public static void linearTimeSort(int[] a, int minBound, int maxBound)throws Throwable{
        for(int i=0;i<10;i++) {
            if (isSorted(a, minBound, maxBound)) {
                return;
            }
            randomize(a, minBound, maxBound);
        }
        throw new RuntimeException("Hardware fault");
    }
    private static boolean isSorted(int[] a, int minBound, int maxBound){
        for(int i=minBound;i<maxBound;i++){
            if(a[i]>a[i+1]){
                return false;
            }
        }
        return true;
    }
    private static void randomize(int[] a, int minBound, int maxBound){
        for (int i = 0; i <= maxBound; i++) {
            int mem = a[i];
            int random = minBound + (int) ((maxBound - minBound + 1) * Math.random());
            a[i] = a[random];
            a[random] = mem;
        }
    }
}
