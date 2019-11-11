public class Gnome {
    public static void sort(int[] a){
        int pos=1;
        while (pos<a.length){
            if (pos<1 || a[pos-1]<=a[pos]){
                pos++;
            }
            else {
                int mem=a[pos];
                a[pos]=a[--pos];
                a[pos]=mem;
            }
        }
    }
}
