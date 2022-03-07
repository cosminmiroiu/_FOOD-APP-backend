/*
package com.finalproject.springbootfoodapp.config;

import com.finalproject.springbootfoodapp.entity.ProductCategory;
import com.finalproject.springbootfoodapp.entity.Restaurant;
import com.finalproject.springbootfoodapp.entity.Role;
import com.finalproject.springbootfoodapp.entity.User;
import com.finalproject.springbootfoodapp.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@Service
public class PopulateDataBase {

    private final RestaurantService restaurantService;
    private final ProductCategoryService productCategoryService;
    private final RoleService roleService;
    private final UserService userService;

    public PopulateDataBase(RestaurantService restaurantService, ProductCategoryService productCategoryService, ProductService productService, RoleService roleService, UserService userService) {
        this.restaurantService = restaurantService;
        this.productCategoryService = productCategoryService;
        this.roleService = roleService;
        this.userService = userService;
    }

    //@Bean // POPULATE DB WITH PRODUCT CATEGORIES
    private void populateProductCategory() {
        ProductCategory productCategory1 = new ProductCategory(
                null,
                "FOODS",
                null);

        ProductCategory productCategory2 = new ProductCategory(
                null,
                "DRINKS",
                null);

        ProductCategory productCategory3 = new ProductCategory(
                null,
                "DESSERTS",
                null);

        productCategoryService.addProductCategory(productCategory1);
        productCategoryService.addProductCategory(productCategory2);
        productCategoryService.addProductCategory(productCategory3);
    }

    //@Bean // POPULATE DB WITH RESTAURANTS
    private void populateRestaurants() {
        Restaurant restaurant1 = new Restaurant(
                null,
                "Mc Donald's",
                "assets/images/restaurant/mcd.png",
                null);

        Restaurant restaurant2 = new Restaurant(
                null,
                "KFC",
                "assets/images/restaurant/kfc.png",
                null);

        Restaurant restaurant3 = new Restaurant(
                null,
                "Spartan",
                "assets/images/restaurant/spartan.png",
                null);


        restaurantService.addRestaurant(restaurant1);
        restaurantService.addRestaurant(restaurant2);
        restaurantService.addRestaurant(restaurant3);
    }

    //@Bean // POPULATE DB WITH ROLES
    private void populateRoles() {

//        // Clients Role / Basic users
//        Role standardUserRole = new Role();
//        standardUserRole.setRoleName("STANDARD");
//        standardUserRole.setRestaurantId(999L);
//
//        // Super Administrator Role / Website Administrators
//        Role administratorUserRole = new Role();
//        administratorUserRole.setRoleName("ADMINISTRATOR");
//        administratorUserRole.setRestaurantId(999L);
//
//        // Mc Donald's Administrator Role
//        Role mcdAdministratorUserRole = new Role();
//        mcdAdministratorUserRole.setRoleName("R_ADMINISTRATOR");
//        mcdAdministratorUserRole.setRestaurantId(1L); // MCD restaurant ID
//
//        // KFC Administrator Role
//        Role kfcAdministratorUserRole = new Role();
//        kfcAdministratorUserRole.setRoleName("R_ADMINISTRATOR");
//        kfcAdministratorUserRole.setRestaurantId(2L); // KFC restaurant ID
//
//        // Spartan Administrator Role
//        Role spartanAdministratorUserRole = new Role();
//        spartanAdministratorUserRole.setRoleName("R_ADMINISTRATOR");
//        spartanAdministratorUserRole.setRestaurantId(3L); // Spartan restaurant ID
//
//        // Delivery Staff Role
//        Role deliveryUserRole = new Role();
//        deliveryUserRole.setRoleName("DELIVERY");
//        deliveryUserRole.setRestaurantId(999L);

//        this.roleService.createNewRole(standardUserRole);
//        this.roleService.createNewRole(administratorUserRole);
//        this.roleService.createNewRole(mcdAdministratorUserRole);
//        this.roleService.createNewRole(kfcAdministratorUserRole);
//        this.roleService.createNewRole(spartanAdministratorUserRole);
//        this.roleService.createNewRole(deliveryUserRole);

    }

    //@Bean // POPULATE DB WITH USERS
    private void populateUsers() throws ParseException {

        //        1, 999, STANDARD
        //        2, 999, ADMINISTRATOR
        //        3, 1, R_ADMINISTRATOR MCD
        //        4, 2, R_ADMINISTRATOR KFC
        //        5, 3, R_ADMINISTRATOR SPARTAN
        //        6, 999, DELIVERY

//        String s = "02/01/1985";
//        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
//        Date date1 = sd.parse(s);
//
//        User standardUser = new User();
//        standardUser.setFirstName("John");
//        standardUser.setLastName("Doe");
//        standardUser.setAddress("street George Bacovia nr. 11 ap. 7");
//        standardUser.setDateOfBirth(date1);
//        standardUser.setPassword("1234");
//        standardUser.setEmail("food@foodapp.com");
//        standardUser.setPhone("0737999888");
//        standardUser.setRole(roleService.getRoleByRoleId(1L)); // STANDARD ROLE
//        this.userService.register(standardUser);

//        String ss = "07/04/1993";
//        SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy");
//        Date date2 = sds.parse(ss);
//
//        User administratorUser = new User();
//        administratorUser.setFirstName("Cosmin");
//        administratorUser.setLastName("Miroiu");
//        administratorUser.setAddress("street Saturn nr. 37 ap. 11");
//        administratorUser.setDateOfBirth(date2);
//        administratorUser.setPassword("1234");
//        administratorUser.setEmail("cosmin@google.com");
//        administratorUser.setPhone("0737018555");
//        administratorUser.setRole(roleService.getRoleByRoleId(2L)); // SUPER ADMINISTRATOR ROLE
//        this.userService.register(administratorUser);
//
//        String sss = "22/09/1989";
//        SimpleDateFormat sdss = new SimpleDateFormat("dd/MM/yyyy");
//        Date date3 = sdss.parse(sss);
//
//        User rAdministratorUser = new User();
//        rAdministratorUser.setFirstName("Sorin");
//        rAdministratorUser.setLastName("Popescu");
//        rAdministratorUser.setAddress("street Calea Bucuresti nr. 88 ap. 3");
//        rAdministratorUser.setDateOfBirth(date3);
//        rAdministratorUser.setPassword("1234");
//        rAdministratorUser.setEmail("sorin@google.com");
//        rAdministratorUser.setPhone("0797333444");
//        rAdministratorUser.setRole(roleService.getRoleByRoleId(3L)); // RESTAURANT MCD ADMINISTRATOR ROLE
//        this.userService.register(rAdministratorUser);
//
//        String ssss = "22/09/1989";
//        SimpleDateFormat sdsss = new SimpleDateFormat("dd/MM/yyyy");
//        Date date4 = sdsss.parse(ssss);
//
//        User deliveryUser = new User();
//        deliveryUser.setFirstName("Elon");
//        deliveryUser.setLastName("Musk");
//        deliveryUser.setAddress("street Calea Bucuresti nr. 88 ap. 3");
//        deliveryUser.setDateOfBirth(date4);
//        deliveryUser.setPassword("1234");
//        deliveryUser.setEmail("elon@space.com");
//        deliveryUser.setPhone("0123456789");
//        deliveryUser.setRole(roleService.getRoleByRoleId(6L)); // DELIVERY ROLE
//        this.userService.register(deliveryUser);
//

//        String s = "21/08/1998";
//        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
//        Date date1 = sd.parse(s);
//        User standardUser = new User();
//        standardUser.setFirstName("Bill");
//        standardUser.setLastName("Gates");
//        standardUser.setAddress("street Memorandului nr. 44 ap. 18");
//        standardUser.setDateOfBirth(date1);
//        standardUser.setPassword("1234");
//        standardUser.setEmail("bill.gates@yahoo.com");
//        standardUser.setPhone("0781975281");
//        standardUser.setRole(roleService.getRoleByRoleId(1L)); // STANDARD ROLE
//        standardUser.setActive(true);
//        this.userService.register(standardUser);

    }

}
*/