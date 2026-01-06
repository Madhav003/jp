import java.util.Scanner;
class Exp2{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter number: ");
    int num = sc.nextInt();
    int temp = num;
    int count = 0;
    if(temp==0){
      count = 1;
    }
    else{
      while(temp>0){
        temp = temp/10;
        count++;
      }
    }
    int a;
    int som = 0;
    int mult = 1;
    temp = num;
    for(int i=0;i<count;i++){
      a = temp % 10;
      temp = temp / 10;
      som = som + a;
      mult = mult * a;
      
    }
    int spec = som + mult;
    if(spec == num){
      System.out.println("It is a special number!");
    }
    else{
      System.out.println("Not special :(");
    }
    sc.close();
  }
}
