package service;

import entity.Book;
import entity.City;
import entity.Passenger;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */

public interface BookService {
    Book getBookById(Integer id);
    List<Book> getAllBooks();
    List<Book> getAllBooksByPassengerId(Integer id);
    void addNewBook(String name, String phone,
                    String toHouse, int fromBlock,
                    String fromStreet, String fromHouse,
                    String fromPlace, City city,
                    Passenger passenger, String toStreet);

    Book orderBook(int bookId, int driverId);

    Book updateBookState(int bookId, int cost, int state);
    List<Book> getCityFreeBook(int id);
    Book getDriverActiveBook(int id);

    List<Book> getAllBooksByDriverId(int driverId);
}
