package kdu.homework.APIResponseParser;

import java.util.logging.Logger;

public class APIResponseParser  {

    private static final Logger logger = Logger.getLogger(APIResponseParser.class.getName());

    public static String parse(String response, String startRule, String endRule) {
        int temp_sp = response.indexOf(startRule);
        int sp = startRule.length() + temp_sp;
        int ep = response.indexOf(endRule, temp_sp);
        int lod = ep-sp;

        if (sp == -1 || ep == -1) {
            // Handle the case where startRule or endRule is not found
            return null;
        }

        return response.substring(sp, sp+lod);
    }


    public static Book parse(String response) {


        Book book = new Book();
        String endRule = "</title>";
        String startRule = "<title>";
        String title = parse(response, startRule, endRule);
        book.setTitle(title);


        // Your code


//        String startRule_name = "<name>";
        String name = parse(response, "<name>", "/<name>");
        book.setAuthor(name);

//        String startRule_year = "<original_publication_year type=\"integer\">";
        String year = parse(response, "<original_publication_year type=\"integer\">", "</original_publication_year>");
        book.setPublicationYear(Integer.parseInt(year));

//        String startRule_rating = "<average_rating>";
        String rating = parse(response, "<average_rating>", "</average_rating>");
        String temp_rating = rating.replaceAll(",","");
        book.setAverageRating(Double.parseDouble(temp_rating));

//        String startRule_count = "<ratings_count type=\"integer\">";
        String count = parse(response, "<ratings_count type=\"integer\">", "</ratings_count>");
        String temp_count = count.replaceAll(",","");
        book.setRatingsCount(Integer.parseInt(temp_count));

//        String startRule_url = "<image_url>";
        String url = parse(response, "<image_url>", "</image_url>");
        book.setImageUrl(url);


        return book;
    }


    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +
                "</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";

        Book obj = APIResponseParser.parse(response);
        // Logging the info of book
        logger.info("Book Information is - ");
        logger.info("Title of book is - " + obj.getTitle());
        logger.info("Author of book is - " + obj.getAuthor());
        logger.info("Publication Year of the book is - " + obj.getPublicationYear());
        logger.info("Average Rating of the book is - " + obj.getAverageRating());
        logger.info("Ratings Count of the book is - " + obj.getRatingsCount());
        logger.info("Image URL of the book is -" + obj.getImageUrl());
    }
}
