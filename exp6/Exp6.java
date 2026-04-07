/* * 
Create a class Patient. The class Patient has following members:
• No argument constructor which initialize age to zero and name
& address to blank string.
• Parameterize constructor which initialize the class variables as
per arguments given.
• void display() – to print information of Patient class
Create a class CovidParameters which inherits Patient class. The class
CovidParameters following members:
• No argument constructor which initialize CTScore, dimer
and platelet to zero and also call
• the parent class constructor.
• Parameterize constructor which initialize the class variables as
per arguments given and also call the parent class constructor.
• float CTScore - which is used to store CT scan score of patient.
• float dimer - which is used to store dimer score of patient.
• int platelet- which is used to store platelet count of patient.
• void Display() - which is used to display CTScore, dimer,
platelet and call the Display() method of Patient class also.
• void Getter() - which is used to scan values of name, age,
address, CTScore, dimer and platelet of patient.
•
Create a class Test. The class Test has following functionalities:
• Create the array of objects for CovidParameters class. The size
of the array is entered by the user.
• Call Getter() and Display() method for array of objects for
CovidParameters class.
• Also display patients information in descending order of
CTScore

*/

import java.util.Scanner;

class Patient{
  int age;
  String name;
  String address;
  Patient(){
    age = 0;
    name = "";
    address = "";
  }
  Patient(int a, String n, String add){
    age = a;
    name = n;
    address = add;
  }
  void display(){
    System.out.println("Name: " + name + "\n age: " + age + "\n address: " + address );
    
  }
}
class CovidParameters extends Patient{
  float ctscore;
  float dimer;
  int platlet;
  CovidParameters(){
    super();
    ctscore = 0;
    dimer = 0;
    platlet = 0;
  }
  CovidParameters(float ct, float dd, int plat){
    ctscore = ct;
    dimer = dd;
    platlet = plat;
  }
  void display(){
    super.display();
    System.out.println("CT Score: " + ctscore + "\n Dimer: " + dimer + "\n Platlet: " + platlet);
    System.out.println("--------------------------------------");
  }
  void getter(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter name: ");
    name = sc.nextLine();
    System.out.println("Enter age: ");
    age = sc.nextInt();
    sc.nextLine();
    System.out.println("Enter address: ");
    address = sc.nextLine();
    System.out.println("Enter CT Score: ");
    ctscore = sc.nextFloat();
    System.out.println("Enter Dimer: ");
    dimer = sc.nextFloat();
    System.out.println("Enter Platelet Count: ");
    platlet = sc.nextInt();
    System.out.println("--------------------------------------");
  }

}

public class Exp6{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number of patients: ");
    int n = sc.nextInt();
    CovidParameters[] patients = new CovidParameters[n];
    for(int i = 0; i < n; i++){
      patients[i] = new CovidParameters();
      patients[i].getter();
    }
    for(int i = 0; i < n; i++){
      System.out.println("--------------------------------------");
      patients[i].display();
    }

    for(int i = 0; i < n; i++){
      for(int j = 0; j < n - i - 1; j++){
        if(patients[j].ctscore < patients[j + 1].ctscore){
          CovidParameters temp = patients[j];
          patients[j] = patients[j + 1];
          patients[j + 1] = temp;
        }
      }
    }

    System.out.println("\nPatients information in descending order of CTScore:");
    for(int i = 0; i < n; i++){
      patients[i].display();
    }


  }
}
