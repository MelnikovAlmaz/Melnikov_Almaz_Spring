package service.impl;

import entity.Book;
import entity.City;
import entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BookRepository;
import repository.DriverRepository;
import repository.impl.AnalyticRepositoryImpl;
import repository.impl.BookRepImpl;
import service.BookService;
import utils.BookFormTransformer;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookRepImpl bookRep;

    @Autowired
    AnalyticRepositoryImpl analyticRepositoryImpl;

    @Autowired
    DriverRepository driverRepository;

    @Override
    @Transactional
    public Book getBookById(Integer id) {
        return bookRepository.findOneById(id);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public List<Book> getAllBooksByPassengerId(Integer id) {
        return bookRepository.findAllByPassenger_Id(id);
    }

    @Override
    public void addNewBook(String name, String phone, String toHouse, int fromBlock, String fromStreet, String fromHouse, String fromPlace, City city, Passenger passenger, String toStreet) {
        Book book = BookFormTransformer.transform(name, phone, toHouse, fromBlock, fromStreet, fromHouse, fromPlace, city, passenger, toStreet);
        bookRepository.save(book);
    }

    @Override
    public Book orderBook(int bookId, int driverId){
        Book book = bookRepository.findOneById(bookId);
        book.setDriver(driverRepository.findOneById(driverId));
        book.setState(1);
        return bookRepository.save(book);
    }
    @Override
    public Book updateBookState(int bookId, int cost, int state){
        Book book = bookRepository.findOneById(bookId);
        book.setCost(cost);
        book.setState(state);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getCityFreeBook(int cityId) {
        return bookRepository.findAllByCityIdAndState(cityId, 0);
    }

    @Override
    public Book getDriverActiveBook(int driverId) {
        return bookRepository.findOneByDriverIdAndState(driverId, 1);
    }

    @Override
    public List<Book> getAllBooksByDriverId(int driverId) {
        return bookRepository.findAllByDriver_IdOrderByDateDesc(driverId);
    }

    @Override
    public List<Book> getAllBooksByNameAndPassenger(String name, Passenger passenger) {
        return bookRep.findAllWhereNameStartsWithByPassengerId(name, passenger.getId());
    }

}
