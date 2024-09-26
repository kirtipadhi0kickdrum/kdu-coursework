package org.example.question4;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class PubDateAscComparator implements Comparator<Book>{
    public Set<Book> ascComparator(Set<Book> books)
    {
        Set<Book> ascSorted = new TreeSet<>(Comparator.comparing(Book::getYear).thenComparing(Book::getTitle));
        ascSorted.addAll(books);
        return ascSorted;

    }

    @Override
    public int compare(Book o1, Book o2) {
        return 0;
    }
}
