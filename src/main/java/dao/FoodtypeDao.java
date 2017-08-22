
package dao;

import models.Foodtype;
import models.Restaurant;

import java.util.List;

public interface FoodtypeDao {

    //create
    void add(Foodtype foodtype); // N

    void addFoodtypeToRestaurant(Foodtype foodtype, Restaurant restaurant); // D

    //read
    //find individual foodtype
    Foodtype findById(int id);

    List<Foodtype> getAll(); // we may need this in the future. Even though it does not 100% match a specific user story, it should be implemented so we can retrieve all Foodtypes, for example to programmatically generate some UI.

    List<Restaurant> getAllRestaurantsForAFoodtype(int id); //E
//
//        //update
//        //omit for now
//
        //delete
    void deleteById(int id);
}