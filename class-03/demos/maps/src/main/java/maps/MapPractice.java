package maps;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class MapPractice {
//    ArrayList has methods for push, it has a dynamic length
//    Hashset is a list of distinct values
//    return a set of unique students
    public static Set<String> getUniqueStudents(ArrayList<String> students){
        Set<String> distinctStudents = new HashSet<>();
//        for(String student : students){
//            distinctStudents.add(student)
//        }
        distinctStudents.addAll(students);
        return distinctStudents;
    }

    public static HashMap<String, Integer> countStudents(ArrayList<String> students){
        Set<String> distinctStudents = getUniqueStudents(students);
        HashMap<String, Integer> studentCount = new HashMap<>();
//        Iterates over the set with a forEach
        for(String studentName : distinctStudents){
            System.out.println(studentName);
//            updates the hasnmap to have values of StudentName : 0
            studentCount.put(studentName, 0);
//                    "Victor" : 0
        }
//        Iterate over the ArrayList and count each name
        for(String studentName : students){
            // update the hashmap to have a larger number
            // this requires us to know what the current number is
            int currentCount = studentCount.get(studentName); // 0
            currentCount++;
            studentCount.put(studentName, currentCount);
        }
//        Big O: O(n);
        return studentCount;
    }

    public static void weirdNess(){
        HashMap<String[], Boolean> stringy= new HashMap<>();
        stringy.put(new String[]{"yo", "this is weird"}, true);
        stringy.put(new String[]{"huh"}, false);
        System.out.println(stringy);

        HashMap<HashMap<String[], Boolean>, ArrayList<String>> huh = new HashMap<>();
        ArrayList<String> one = new ArrayList<>();
        one.add("this is so strange");

        huh.put(stringy, one);
        System.out.println(huh);
    }
}