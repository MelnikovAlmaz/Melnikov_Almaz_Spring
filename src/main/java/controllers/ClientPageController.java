package controllers;

import entity.City;
import entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.BookService;
import service.CityService;
import service.PassengerService;

import java.util.List;

/**
 * Created by Almaz on 14.04.2016.
 */
@Controller
@RequestMapping("/client")
public class ClientPageController {
    @Autowired
    CityService cityService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    BookService bookService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        return "redirect:/client/cabinet";
    }


    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String cabinetGET(ModelMap model) {
        List cities = cityService.getAllCities();
        Passenger passenger = getPrincipal();
        model.addAttribute("user", passenger);
        model.addAttribute("cities", cities);
        return "/client/cabinet";
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.POST)
    public String cabinetPOST(ModelMap model,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "city") int cityId,
                              @RequestParam(value = "phone") String phone,
                              @RequestParam(value = "fromStreet") String fromStreet,
                              @RequestParam(value = "fromHouse") String fromHouse,
                              @RequestParam(value = "fromPlace") String fromPlace,
                              @RequestParam(value = "fromBlock") int fromBlock,
                              @RequestParam(value = "toHouse") String toHouse,
                              @RequestParam(value = "toStreet") String toStreet) {
        Passenger passenger = getPrincipal();
        City city = cityService.getCityById(cityId);
        bookService.addNewBook(name, phone, toHouse, fromBlock, fromStreet, fromHouse, fromPlace, city, passenger, toStreet);
        return "redirect:/client/cabinet";
    }

    @RequestMapping(value = "/cabinet/orders", method = RequestMethod.GET)
    public String ordersGET(ModelMap model) {
        Passenger passenger = getPrincipal();
        List books = bookService.getAllBooksByPassengerId(passenger.getId());
        model.addAttribute("books", books);
        model.addAttribute("user", passenger);
        return "/client/orders";
    }

    @RequestMapping(value = "/cabinet/orders", method = RequestMethod.POST)
    public String ordersPOST(ModelMap model) {
        return "/client/orders";
    }

    @RequestMapping(value = "/cabinet/profile", method = RequestMethod.GET)
    public String profileGET(ModelMap model) {
        List cities = cityService.getAllCities();
        Passenger passenger = getPrincipal();
        model.addAttribute("user", passenger);
        model.addAttribute("cities", cities);
        return "/client/profile";
    }

    @RequestMapping(value = "/cabinet/profile", method = RequestMethod.POST)
    public String profilePOST(ModelMap model,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "city") String cityId,
                              @RequestParam(value = "street") String street,
                              @RequestParam(value = "house") String house,
                              @RequestParam(value = "flat") int flat,
                              @RequestParam(value = "sex") int sex) {

        City city = cityService.getCityById(Integer.parseInt(cityId.charAt(1)+""));
        Passenger passenger = getPrincipal();
        passengerService.update(passenger, name, street, house, flat, sex, city);
        return "redirect:/client/cabinet/profile";
    }

    @RequestMapping(value = "/cabinet/feedback", method = RequestMethod.GET)
    public String feedbackGET(ModelMap model) {
        List cities = cityService.getAllCities();
        Passenger passenger = getPrincipal();
        model.addAttribute("user", passenger);
        model.addAttribute("cities", cities);
        return "/client/feedback";
    }

    @RequestMapping(value = "/cabinet/feedback", method = RequestMethod.POST)
    public String feedbackPOST(ModelMap model) {
        return "redirect:/client/cabinet/feedback";
    }

    private Passenger getPrincipal(){
        Passenger passenger = (Passenger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passenger;
    }
    @RequestMapping(value = "/cabinet/export", method = RequestMethod.GET)
    public ModelAndView getExcel() {
        Passenger passenger = getPrincipal();
        List bookList = bookService.getAllBooksByPassengerId(passenger.getId());
        return new ModelAndView("BookListExel", "bookList", bookList);
    }
}
