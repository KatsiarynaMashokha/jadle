package dao;

import models.Foodtype;
import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oFoodtypeDao implements FoodtypeDao {
    private final Sql2o sql2o;

    public Sql2oFoodtypeDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Foodtype foodtype) {
        String sql = "INSERT INTO foodtypes (name) VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", foodtype.getName())
                    .addColumnMapping("NAME", "name")
                    .executeUpdate()
                    .getKey();
            foodtype.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Foodtype findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM foodtypes WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Foodtype.class);
        }
    }

    @Override
    public List<Foodtype> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM foodtypes")
                    .executeAndFetch(Foodtype.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from foodtypes WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void addFoodtypeToRestaurant(Foodtype foodtype, Restaurant restaurant){
        String sql = "INSERT INTO restaurants_foodtypes (restaurantid, foodtypeid) VALUES (:restaurantId, :foodtypeId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("restaurantId", restaurant.getId())
                    .addParameter("foodtypeId", foodtype.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Restaurant> getAllRestaurantsForAFoodtype(int foodtypeId) {
        List<Restaurant> restaurants = new ArrayList();
        String joinQuery = "SELECT restaurantid FROM restaurants_foodtypes WHERE foodtypeid = :foodtypeId";//pull out restaurantids for join table when they match a foodtypeId in the same table

        try (Connection con = sql2o.open()) { //keeps connection to the db open
            List<Integer> allRestaurantIds = con.createQuery(joinQuery) //add above string argument to query to retrieve said restaurantids
                    .addParameter("foodtypeId", foodtypeId) //add foodtypeId as the argument in sql query
                    .executeAndFetch(Integer.class); //fetch the restaurantIds which is in the Integer class
            for (Integer restaurantId : allRestaurantIds){ //cycle through the arrayList of restaurantIds
                String restaurantQuery = "SELECT * FROM restaurants WHERE id = :restaurantId"; //search the restaurant table for the values that match said restaurantId--one-by-one
                restaurants.add(  //add what you find from restaurant table to a collection named "restaurants"
                        con.createQuery(restaurantQuery)
                                .addParameter("restaurantId", restaurantId) //add restaurantId to sql query for our search
                                .executeAndFetchFirst(Restaurant.class));//items (objects) pulled out will be from the restaurant class
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return restaurants; //return the values found from above evaluation
    }
}