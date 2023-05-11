package se.kth.iv1350.checkoutproc.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this class is used to write to the log text file
 */
public class LogFileWriter implements Logger{

        private PrintWriter logStream;

        public LogFileWriter(String file) {
                try {
                        logStream = new PrintWriter(new FileWriter(file + ".log"), true);
                } catch (IOException ioe) {
                        System.out.println("CAN NOT LOG.");
                        ioe.printStackTrace();
                }
        }

        @Override
        /**
         * writes msg to log file
         * @param msg will be written to log file
         */
        public void log(String msg) {
                logStream.println(msg);
        }
}
