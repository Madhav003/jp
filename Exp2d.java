import java.util.Scanner;
class Exp2d{
    public static void main(String args[]){
    for(int i = 0; i<6; i++){
        for(int j = 0; j<6; j++){
            if((i+j)<5) System.out.print(" ");
            if((i+j)==5) System.out.print("1");
            if((i+j)==6) System.out.print("2");
            if((i+j)==7) System.out.print("3");
            if((i+j)==8) System.out.print("4");
            if((i+j)==9) System.out.print("5");
            if((i+j)==10) System.out.print("6");
        }
        System.out.println();

        
    }
}
}