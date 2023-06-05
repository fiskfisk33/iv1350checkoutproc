package se.kth.iv1350.addtask2;

import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] lol){
                List<String>  list = new ArrayList<>();
                list.add("Sträng");
                list.add("Foo");
                list.add("Bar");

                System.out.println("Inherited");

                CountingSetInherited<String> countingSetInherited = new CountingSetInherited<>();
                countingSetInherited.add("Sträng");
                System.out.println(countingSetInherited.getNoOfAddedItems()); //one string added, expect 1
                countingSetInherited.add("Sträng");
                System.out.println(countingSetInherited.getNoOfAddedItems()); //same string, should still be 1
                countingSetInherited.add("Annan sträng");
                System.out.println(countingSetInherited.getNoOfAddedItems()); //new string added, should be 2

                countingSetInherited.addAll(list);
                System.out.println(countingSetInherited.getNoOfAddedItems()); //one old and two new strings added, expect 4

                ///


                System.out.println("Composed");

                CountingSetComposed<String> countingSetComposed = new CountingSetComposed<>();
                countingSetComposed.add("Sträng");
                System.out.println(countingSetComposed.getNoOfAddedItems()); //one string added, expect 1
                countingSetComposed.add("Sträng");
                System.out.println(countingSetComposed.getNoOfAddedItems()); //same string, should still be 1
                countingSetComposed.add("Annan sträng");
                System.out.println(countingSetComposed.getNoOfAddedItems()); //new string added, should be 2

                countingSetComposed.addAll(list);
                System.out.println(countingSetComposed.getNoOfAddedItems()); //one old and two new strings added, expect 4

        }

}
