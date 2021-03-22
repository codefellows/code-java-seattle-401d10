/*
To run a new java file: touch the file: Basics.java
write a class `Basics`
create a main method
compile it `javac Basics.java`
run the file with `java Basics`

When I want to rerun the class with new code
javac Basics.java
java Basics
*/

import java.util.*;
import java.time.LocalDate;
import java.time.*;

// The name of the main class must exactly match the file
public class Basics {
  // this is the entry point to the class / file when we run this file with the command `java Basics`
  /*
  Java is not a scripting language, it does not just run top to bottom,. Java looks for a method with the exact signature
  public static void main(String[] args) and it runs everything in that method top to bottom like a script
  */
  public static void main(String[] args){
    // primitives in java are char int byte double float long short boolean
    // they all have lowercase letters

    // Analagous to console.log
    System.out.println("This is all new, Ginger rules");


    // declaring variables;
    char letterA = 'A';

    // We declare and use char with single quotes
    System.out.print("" + 'I' + ' ' + letterA +'m' + ' ' + 'c' + 'h' + 'a' + 'r' + 's');

    for(int i = 0; i < 3; i++){
      System.out.println(i);
    }

    boolean itsTrue = true;
    while(itsTrue){
      int num = 10;
      // String is an object with keys of indexes of the characters and bunch of other properties,
      // It is a collection of chars in a row
      String message = "Stop";
      System.out.println(message);
      break;
      // System.out.println("We never reach this line");
    }

  // arrays are defined as TYPE[] int[] is said `int array`
    String[] students;
    // this is the same as var x; :students does not have a value yet
    int[] numbers; // this is null right now

    char[] characters = new char[9];

    students = new String[100000];
    numbers = new int[10];
    System.out.println(students.length);
    System.out.println(numbers);

    students = new String[4];
    System.out.println(students.length);

    String[] pets = {"Ginger", "Snowdrop"};
    // We could use a for loop to see what is inside the pets Array
    for(int i = 0; i < pets.length; i++){
      System.out.println(pets[i]);
    }


    students[3] = "Amelia";
    students[1] = "Matt";

    for(int i = 0; i < students.length; i++){
      System.out.println(students[i]);
    }

    System.out.println(addInts(1000000, 999999));
    int sum = addInts(3, 7);
    int sum2 = addInts(5,9);
    int sum3 = addInts(sum, sum2);


    System.out.println(isGingerColdToday());
  }

  // public static TYPEOFRETURN NAMEOFFUNCTION(ARGUMENTS)
  public static int addInts(int num1, int num2){
    return num1 + num2;
  }

  public static boolean isGingerColdToday(){
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int monthNumber = localDate.getMonthValue();
    System.out.println("month : " + monthNumber);

    if(monthNumber == 12){
     return true;
    }
    
    return false;
    
  }
}