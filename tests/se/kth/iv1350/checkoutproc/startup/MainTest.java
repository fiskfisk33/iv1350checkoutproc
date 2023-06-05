package se.kth.iv1350.checkoutproc.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
        Main main;
        private PrintStream stdOut = System.out;
        private ByteArrayOutputStream testOut = new ByteArrayOutputStream();

        @BeforeEach
        void setUp() {
                main = new Main();
                System.setOut(new PrintStream(testOut));
        }

        @AfterEach
        void tearDown() {
                System.setOut(stdOut);
        }

        @Test
        void mainTest(){
                main.main(new String[0]);

                String expectedResult = "---Ready To Scan---";
                String result = testOut.toString().trim();
                result = result.substring(0, result.indexOf("\n"));
                assertEquals(expectedResult, result, "The program did not print the expected message");
        }

}