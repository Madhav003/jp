/*
Create an abstract class Instrument which is having the abstract
function play.
Create three more sub classes from Instrument which is Piano,
Flute and Guitar. Override the play method inside all three classes
printing a message.
“Piano is playing tan tan tan tan” for Piano class
“Flute is playing toot toot toot toot” for Flute class
“Guitar is playing tin tin tin” for Guitar class
You must not allow the user to declare an object of
Instrument class.
Create an array of 10 Instruments.
Assign different type of instrument-to-instrument reference.
Check for the polymorphic behavior of play method.

*/ 
import java.util.Scanner;

abstract class Instrument {
    abstract void play();
}
class piano extends Instrument{
    public void play(){
        System.out.println("Piano is playing tan tan tan tan");
    }
}
class flute extends Instrument{
    public void play(){
        System.out.println("Flute is playing toot toot toot toot");
    }
}
class guitar extends Instrument{
    public void play(){
        System.out.println("Guitar is playing tin tin tin");
    }
}

public class Exp7a {
    public static void main(String args[]){
        Instrument[] instruments = new Instrument[10];
        instruments[0] = new piano();
        instruments[1] = new flute();
        instruments[2] = new guitar();
        instruments[3] = new piano();
        instruments[4] = new flute();
        instruments[5] = new guitar();
        instruments[6] = new piano();
        instruments[7] = new flute();
        instruments[8] = new guitar();
        instruments[9] = new piano();

        for(int i = 0; i < 10; i++){
            instruments[i].play();
        }
    }
    
}
