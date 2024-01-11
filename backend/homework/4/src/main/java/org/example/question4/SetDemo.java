package org.example.question4;

import java.util.Comparator;

import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

public class SetDemo {
    private static final Logger logger = Logger.getLogger(SetDemo.class.getName());

    public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        if(comparator == null)
        {
            for(Book book : books)
            {
                logger.info(String.format("Normal Order - %s" , book));
            }
        }
        else if(comparator instanceof PubDateAscComparator)
        {
            PubDateAscComparator pubAscComp = new PubDateAscComparator();
            pubAscComp.ascComparator(books);
            return books;
        }
        else if(comparator instanceof PubDateDescComparator)
        {
            PubDateDescComparator pubDescComp = new PubDateDescComparator();
            pubDescComp.descComparator(books);
            return books;
        }

        return books;
    }

    public static void main(String[] args) {

        treeSetDemo(null);
        logger.info(" ");
        PubDateAscComparator pubAscComp = new PubDateAscComparator();
        Set<Book> book = treeSetDemo(pubAscComp);
        for(Book bookIt : book)
        {
            logger.info(String.format("Ascending Order - %s" , bookIt));
        }
        logger.info(" ");
        PubDateDescComparator pubDescComp = new PubDateDescComparator();
        Set<Book> book2 = treeSetDemo(pubDescComp);
        for(Book bookIt : book2)
        {
            logger.info(String.format("Descending Order - %s" , bookIt));
        }

    }

}