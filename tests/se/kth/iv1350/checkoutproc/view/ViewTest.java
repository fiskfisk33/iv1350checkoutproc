package se.kth.iv1350.checkoutproc.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.checkoutproc.controller.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
        private PrintStream stdOut = System.out;
        private ByteArrayOutputStream testOut = new ByteArrayOutputStream();

        private Controller contr;


        @BeforeEach
        void setUp() {

                System.setOut(new PrintStream(testOut));
                contr = new Controller();
        }

        @AfterEach
        void tearDown() {
                System.setOut(stdOut);
        }

        @Test
        void view(){

                //System.setOut(stdOut);
                View view = new View(contr);

                try{
                        File expectedResultFile = new File("ViewTestExpected.txt");
                        BufferedReader expectedOutput = new BufferedReader(new FileReader(expectedResultFile));


                        BufferedReader actualOutput = new BufferedReader(new StringReader(testOut.toString()));

                        String expectedLine;
                        String actualLine;
                        int line = 0;

                        //These lines contain timestamps, we skip comparing those, since they change on every run.
                        List<Integer> linesWithTimestamps = Arrays.asList(48,126,204,282,360);

                        while((expectedLine = expectedOutput.readLine()) != null){
                                actualLine = actualOutput.readLine();
                                line ++;

                                if(! linesWithTimestamps.contains(line))
                                        assertEquals(expectedLine.trim(), actualLine.trim(), "View did not print the expected string on line: " + line);

                        }
                } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }
}