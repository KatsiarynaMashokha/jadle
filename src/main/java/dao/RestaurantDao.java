package dao;

import models.Foodtype;
import models.Restaurant;

import java.util.List;

public interface RestaurantDao {

        //create
        void add (Restaurant restaurant); //J

        void addRestaurantToFoodtype(Restaurant restaurant, Foodtype foodtype); //D & E

        //read
        List<Restaurant> getAll(); //A

        List<Foodtype> getAllFoodtypesForARestaurant(int restaurantId); //D & E

        Restaurant findById(int id); //B & C

        //update restaurant info
        void update(int id, String name, String address, String zipcode, String phone, String website, String email, String image); //L

        //delete individual restaurant
        void deleteById(int id); //K

        //delete all restaurants
        void clearAllRestaurants();
    }

