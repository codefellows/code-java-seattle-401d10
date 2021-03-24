package maps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class MapPracticeTest {
    @Test
    public void testDistinctStudents(){
        ArrayList<String> studentsToTest = new ArrayList<>();
        studentsToTest.add("Victor");
        studentsToTest.add("Victor");
        studentsToTest.add("Victor");
        studentsToTest.add("James");
        studentsToTest.add("Seamus");
        studentsToTest.add("Seamus");
        studentsToTest.add("Seamus");
        studentsToTest.add("Seamus");
        studentsToTest.add("Seamus");
        studentsToTest.add("Stephen");
        studentsToTest.add("Victor");
        studentsToTest.add("Victor");
        studentsToTest.add("Victor");

        System.out.println(studentsToTest);

        System.out.println(MapPractice.getUniqueStudents(studentsToTest));
    }

    @Test public void testStudentCount(){
        ArrayList<String> studentsToTest = new ArrayList<>();
        studentsToTest.add("Victor");
        studentsToTest.add("Victor");
        studentsToTest.add("Victor");
        studentsToTest.add("James");
        studentsToTest.add("Seamus");
        studentsToTest.add("Seamus");
        studentsToTest.add("Seamus");
        studentsToTest.add("Seamus");
        studentsToTest.add("Seamus");
        studentsToTest.add("Stephen");
        studentsToTest.add("Victor");
        studentsToTest.add("Victor");
        studentsToTest.add("Victor");
        System.out.println(MapPractice.countStudents(studentsToTest));
    }

    @Test public void testWeirdness(){
        MapPractice.weirdNess();
    }
}
