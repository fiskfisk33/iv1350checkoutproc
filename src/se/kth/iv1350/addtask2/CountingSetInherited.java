package se.kth.iv1350.addtask2;

import java.util.HashSet;
import java.util.Set;

public class CountingSetInherited<E> extends HashSet<E> {
        private int noOfAddedItems;

        @Override
        public boolean add(E element){
                if (!super.contains(element))
                        noOfAddedItems ++;
                return super.add(element);
        }

        public int getNoOfAddedItems(){
                return noOfAddedItems;
        }

}
