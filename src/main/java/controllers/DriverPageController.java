package controllers;

import entity.Book;
import entity.City;
import entity.Driver;
import form.BasicInformationForm;
import form.CountInMonthForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.AnalyticService;
import service.BookService;
import service.CityService;
import service.DriverService;

import java.util.List;

/**
 * Created by Almaz on 14.04.2016.
 */
@Controller
@RequestMapping("/driver")
public class DriverPageController {
    @Autowired
    CityService cityService;
    @Autowired
    DriverService driverService;
    @Autowired
    BookService bookService;
    @Autowired
    AnalyticService analyticService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        return "redirect:/driver/cabinet";
    }


    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String cabinetGET(ModelMap model) {
        Driver driver = getPrincipal();
        Book activeBook = bookService.getDriverActiveBook(driver.getId());
        model.addAttribute("user", driver);
        model.addAttribute("book", activeBook);
        return "/driver/cabinet";
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.POST)
    public String cabinetPOST(ModelMap model,
                              @RequestParam(value = "cost") int cost,
                              @RequestParam(value = "id") int bookId,
                              @RequestParam(value = "state") int state) {
        Driver driver = getPrincipal();
        Book book = bookService.updateBookState(bookId, cost, state);
        return "redirect:/driver/cabinet";
    }

    @RequestMapping(value = "/cabinet/orders", method = RequestMethod.GET)
    public String orders(ModelMap model, @RequestParam(value = "error", required = false) String error) {
        Driver driver = getPrincipal();
        List books = bookService.getCityFreeBook(driver.getCity().getId());
        model.addAttribute("books", books);
        model.addAttribute("user", driver);
        model.addAttribute("error", error);
        return "/driver/free_orders";
    }

    @RequestMapping(value = "/cabinet/orders/{id}", method = RequestMethod.GET)
    public String orderFix(ModelMap model, @PathVariable Integer id) {
        Driver driver = getPrincipal();
        String error = "";
        if(bookService.getDriverActiveBook(driver.getId()) != null){
            error = "У Вас уже иммется активный заказ!";
            model.addAttribute("error", error);
            return "redirect:/driver/cabinet/orders";
        }
        Book book = bookService.getBookById(id);
        if(book.getState() != 0){
            model.addAttribute("error", error);
            return "redirect:/driver/cabinet/orders";
        }
        bookService.orderBook(book.getId(), driver.getId());
        return "redirect:/driver/cabinet";
    }

    @RequestMapping(value = "/cabinet/profile", method = RequestMethod.GET)
    public String profileGET(ModelMap model) {
        List cities = cityService.getAllCities();
        Driver driver = getPrincipal();
        model.addAttribute("user", driver);
        model.addAttribute("cities", cities);
        return "/driver/profile";
    }

    @RequestMapping(value = "/cabinet/statistic", method = RequestMethod.GET)
    public String myOrders(ModelMap model) {
        Driver driver = getPrincipal();
        List<Book> books = bookService.getAllBooksByDriverId(driver.getId());
        model.addAttribute("user", driver);
        model.addAttribute("books", books);
        return "/driver/my_orders";
    }

    @RequestMapping(value = "/cabinet/statistic/analitycs", method = RequestMethod.GET)
    public String analitycs(ModelMap model) {
        Driver driver = getPrincipal();
        List<Book> books = bookService.getAllBooksByDriverId(driver.getId());
        List<CountInMonthForm> countInMonthForms = analyticService.findAllInMonthByDriverId(driver.getId(), 4);
        BasicInformationForm basicInformationForm = analyticService.findBasicInfoByDriver_Id(driver.getId());
        model.addAttribute("basicDataSet", basicInformationForm);
        model.addAttribute("dataSet", countInMonthForms);
        model.addAttribute("user", driver);
        model.addAttribute("books", books);
        return "/driver/analitycs";
    }

    @RequestMapping(value = "/cabinet/profile", method = RequestMethod.POST)
    public String profilePOST(ModelMap model,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "city") String cityId,
                              @RequestParam(value = "street") String street,
                              @RequestParam(value = "house") String house,
                              @RequestParam(value = "flat") int flat,
                              @RequestParam(value = "sex") int sex) {

        City city = cityService.getCityById(Integer.parseInt(cityId.substring(1)));
        Driver driver = getPrincipal();
        //driverService.update(driver.getId(), name, street, house, flat, sex, birth, email, city);
        return "redirect:/driver/cabinet/profile";
    }

    @RequestMapping(value = "/cabinet/feedback", method = RequestMethod.GET)
    public String feedbackGET(ModelMap model) {
        List cities = cityService.getAllCities();
        Driver driver = getPrincipal();
        model.addAttribute("user", driver);
        model.addAttribute("cities", cities);
        return "/driver/feedback";
    }

    @RequestMapping(value = "/cabinet/feedback", method = RequestMethod.POST)
    public String feedbackPOST(ModelMap model) {
        return "redirect:/driver/cabinet/feedback";
    }

    private Driver getPrincipal(){
        Driver driver = (Driver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return driver;
    }
}
