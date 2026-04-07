import java.util.Scanner;

abstract class Compartment{
    abstract void notice();
}
class FirstClass extends Compartment{
    public void notice(){
        System.out.println("Welcome to First Class");
    }
}
class Ladies extends Compartment{
    public void notice(){
        System.out.println("Welcome to Ladies compartment");
    }
}
class General extends Compartment{
    public void notice(){
        System.out.println("Welcome to General compartment");
    }
}
class Luggage extends Compartment{
    public void notice(){
        System.out.println("This is Luggage compartment. You are a luggage");
    }
}

public class Exp7b {
    public static void main(String args[]){
        Compartment[] comp = new Compartment[10];
        for(int i = 0; i < 10; i++){
            int rand = (int)(Math.random() * 4) + 1;
            if(rand == 1){
                comp[i] = new FirstClass();
            }
            else if(rand == 2){
                comp[i] = new Ladies();
            }
            else if(rand == 3){
                comp[i] = new General();
            }
            else{
                comp[i] = new Luggage();
            }
        }


        for(int i = 0; i < 10; i++){
            comp[i].notice();
        }
        
    }    
}
