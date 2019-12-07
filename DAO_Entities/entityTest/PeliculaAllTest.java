package urjc.etsit.isi.entity.test;

//
// Instrucciones de compilación:
// javac -cp .:./hamcrest-core-1.3.jar:./junit-4.12.jar AllTests.java
//
// Ejecución:
// java -cp .:./hamcrest-core-1.3.jar:./junit-4.12.jar AllTests
//


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.JUnit4TestAdapter;

// This section declares all of the test classes in the program.
@RunWith (Suite.class)
@Suite.SuiteClasses ({ PeliculaTest.class })  // Add test classes here.

public class PeliculaAllTest
{
    // Execution begins in main(). This test class executes a
    // test runner that tells the tester if any fail.
    public static void main (String[] args)
    {
       junit.textui.TestRunner.run (suite());
    }

    // The suite() method helps when using JUnit 3 Test Runners or Ant.
    public static junit.framework.Test suite()
    {
       return new JUnit4TestAdapter (PeliculaAllTest.class);
    }
}