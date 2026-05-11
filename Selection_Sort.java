import java.util.*;

public class Selection_Sort {

    public static void main(String[] args) {
        
        int arr[]={49,74,25,36,88,18,31};

        for (int i = 0; i < arr.length; i++) {
            
            int min =i;

            for (int j = i+1; j < arr.length; j++) {
                
                if(arr[min]>arr[j]){
                    min = j;
                }
            }

            int temp= arr[min];
            arr[min]= arr[i];
            arr[i] = temp;
        }

        System.out.print("Sorted Elements are :");

        for (int i : arr) {
            System.out.print(" "+i );
        }
    }

    
}




// 2> Implement greedy search algo for selection sort,  prince algo and dijiktra minimum spanning tree algo