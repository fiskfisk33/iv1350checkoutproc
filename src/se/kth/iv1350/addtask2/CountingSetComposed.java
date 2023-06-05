package se.kth.iv1350.addtask2;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

public class CountingSetComposed<E> {
        private int noOfAddedItems;

        private Set<E> set = new HashSet<>();

        public boolean add(E item){
                if (! set.contains(item))
                        noOfAddedItems ++;
                return set.add(item);
        }

        public boolean addAll(Collection<E> items){
                boolean changed = false;
                for(E item : items){
                        changed |= this.add(item);
                }
                return changed;
        }

        public int getNoOfAddedItems() {
                return noOfAddedItems;
        }
}
