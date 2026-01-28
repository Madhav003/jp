public class OK{
    public static void main(String args[]){
        for(int i = 4; i>0; i--){
            for(int j = 0; j<4; j++){
                if(i+j <= 4) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println(" ");
        }
    }
}
// 1 2 3 4
//3 *
//2 * * 
//1 * * *
