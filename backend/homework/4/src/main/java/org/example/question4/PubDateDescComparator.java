package org.example.question4;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class PubDateDescComparator implements Comparator<Book> {

    public Set<Book> descComparator(Set<Book> books)
    {
        Set<Book> descSorted = new TreeSet<>(Comparator.comparing(Book::getYear).thenComparing(Book::getTitle).reversed());
        descSorted.addAll(books);
        return descSorted;

    }
    @Override
    public int compare(Book o1, Book o2) {
        return 0;
    }
}
