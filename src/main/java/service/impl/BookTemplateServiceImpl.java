package service.impl;

import entity.BookTemplate;
import entity.City;
import entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BookTemplateRepository;
import repository.DriverRepository;
import repository.impl.AnalyticRepositoryImpl;
import service.BookTemplateService;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */
@Service
public class BookTemplateServiceImpl implements BookTemplateService {
    @Autowired
    BookTemplateRepository bookTemplateRepository;

    @Autowired
    AnalyticRepositoryImpl analyticRepositoryImpl;

    @Autowired
    DriverRepository driverRepository;

    @Override
    @Transactional
    public BookTemplate getBookTemplateById(Integer id) {
        return bookTemplateRepository.findOneById(id);
    }

    @Override
    @Transactional
    public List<BookTemplate> getAllBookTemplates() {
        return bookTemplateRepository.findAll();
    }

    @Override
    @Transactional
    public List<BookTemplate> getAllBookTemplatesByPassengerId(Integer id) {
        return bookTemplateRepository.findAllByPassenger_Id(id);
    }

    @Override
    public void addNewBookTemplate(String name, String phone, String toHouse, int fromBlock, String fromStreet, String fromHouse, String fromPlace, City city, Passenger passenger, String toStreet) {
        BookTemplate book = new BookTemplate();
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
        bookTemplateRepository.save(book);
    }
}
