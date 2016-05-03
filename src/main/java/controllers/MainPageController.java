package controllers;

import entity.Car;
import entity.Driver;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.CityService;
import service.DriverService;
import service.PassengerService;
import service.impl.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Almaz on 07.04.2016.
 */
@Controller
@RequestMapping("")
public class MainPageController {
    @Autowired
    CityService cityService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    DriverService driverService;
    @Autowired
    CarServiceImpl carService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainPage(ModelMap model, @RequestParam(required = false, value = "city", defaultValue = "Москва") String city) {
        List cities = cityService.getAllCities();
        model.addAttribute("city", city);
        model.addAttribute("cities", cities);
        return "/main/main";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contactsPage(ModelMap model, @RequestParam(required = false, value = "city") String city) {
        List cities = cityService.getAllCities();
        model.addAttribute("city", city);
        model.addAttribute("cities", cities);
        return "/main/contacts";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model, @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "/cabinet/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String cabinet(ModelMap model) {
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = String.valueOf(person.getRole());

        if (role.equals("ROLE_PASSENGER")) {
            return "redirect:/client";
        }

        if (role.equals("ROLE_DRIVER")) {
            return "redirect:/driver";
        }
        return "/cabinet/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET(ModelMap model) {
        return "/cabinet/register";
    }

    @RequestMapping(value = "/register/client", method = RequestMethod.GET)
    public String registerClientGET(ModelMap model) {
        List cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "/cabinet/register_client";
    }

    @RequestMapping(value = "/register/client", method = RequestMethod.POST)
    public String registerClientPOST(ModelMap model,
                               @RequestParam(value = "username") String username,
                               @RequestParam(value = "city") int cityId,
                               @RequestParam(value = "phone") String phone,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "confirmPassword") String confirmPassword) {
        boolean isError=false;
        String errorMessage = "";
        if(passengerService.getPassengerByUsername(username) != null){
            isError=true;
            errorMessage += "Логин уже используется ";
        }
        if(!password.equals(confirmPassword)){
            isError=true;
            errorMessage += "Порторный ввод пароля неверен";
        }
        if(isError){
            List cities = cityService.getAllCities();
            model.addAttribute("error", errorMessage);
            model.addAttribute("cities", cities);
            model.addAttribute("username", username);
            model.addAttribute("cityError", cityId);
            model.addAttribute("phone", phone);
            return "/cabinet/register_client";
        }
        passengerService.addNewPassenger(username, cityId, phone, password);
        return "redirect:/login";
    }

    @RequestMapping(value = "/register/driver", method = RequestMethod.GET)
    public String registerDriverGET(ModelMap model) {
        List cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "/cabinet/register_driver";
    }

    @RequestMapping(value = "/register/driver", method = RequestMethod.POST)
    public String registerDriverPOST(ModelMap model,
                               @RequestParam(value = "username") String username,
                               @RequestParam(value = "city") int cityId,
                               @RequestParam(value = "phone") String phone,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "confirmPassword") String confirmPassword,
                               @RequestParam(value = "carNumber") String carNumber,
                               @RequestParam(value = "carModel") String carModel,
                               @RequestParam(value = "carRegion") int carRegion,
                               @RequestParam(value = "carColor") String carColor)
    {
        boolean isError=false;
        String errorMessage = "";
        if(driverService.getDriverByUsername(username) != null){
            isError=true;
            errorMessage += "Логин уже используется ";
        }
        if(!password.equals(confirmPassword)){
            isError=true;
            errorMessage += "Порторный ввод пароля неверен";
        }
        if(isError){
            List cities = cityService.getAllCities();
            model.addAttribute("error", errorMessage);
            model.addAttribute("cities", cities);
            model.addAttribute("username", username);
            model.addAttribute("cityError", cityId);
            model.addAttribute("phone", phone);
            return "/cabinet/register_driver";
        }
        Car car = carService.addNewCar(carNumber, carModel, carRegion, carColor);
        Driver driver = driverService.addNewDriver(username, cityId, phone, password, car);
        return "redirect:/login";
    }
}
