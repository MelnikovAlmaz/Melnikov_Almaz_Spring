package utils;

import entity.Book;
import entity.City;
import entity.Passenger;

/**
 * Created by Almaz on 29.04.2016.
 */
public class BookFormTransformer {
    public static Book transform(String name, String phone,
                                 String toHouse, int fromBlock,
                                 String fromStreet, String fromHouse,
                                 String fromPlace, City city,
                                 Passenger passenger, String toStreet){

        Book book = new Book();
        book.setName(name);
        book.setPhone(phone);
        book.setCity(city);
        book.setTostreet(toStreet);
        book.setTohouse(toHouse);
        book.setFromstreet(fromStreet);
        book.setFromhouse(fromHouse);
        book.setFromblock(fromBlock);
        book.setFromplace(fromPlace);
        book.setPassenger(passenger);
        book.setCost(0);
        book.setState(0);
        return book;
    }
}
