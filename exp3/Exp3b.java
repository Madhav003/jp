import java.util.Scanner;
class Exp3b{
  public static void main(String args[]){
    int arr[] = new int[5];
    Scanner sc = new Scanner(System.in);
    for(int i = 0; i<5; i++){
    arr[i] = sc.nextInt();
    }
    int sum = 0;
    boolean found = false;
    loop:
    {
    for(int i = 0; i<5; i++){
      sum = 0;
      for(int j=i;j<5;j++){
        sum = sum + arr[j];
        System.out.print(arr[j]+" ");
        if(sum == 0){
          found = true;
        }
      }
      System.out.println("");
    }
    }
    if(found == true) System.out.println("Yes");
    if(found == false) System.out.println("No");
    sc.close();
  }
}
