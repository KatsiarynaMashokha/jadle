import com.google.gson.Gson;
import dao.Sql2oFoodtypeDao;
import dao.Sql2oRestaurantDao;
import dao.Sql2oReviewDao;
import models.Foodtype;
import models.Restaurant;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oFoodtypeDao foodtypeDao;
        Sql2oRestaurantDao restaurantDao;
        Sql2oReviewDao reviewDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; //check me!
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();

        //CREATE
        post("/restaurants/new", "application/json", (req, res) -> {
            Restaurant restaurant = gson.fromJson(req.body(), Restaurant.class);
            restaurantDao.add(restaurant);
            res.status(201);;
            return gson.toJson(restaurant);
        });

        //READ
        get("/restaurants", "application/json", (req, res) -> {
            return gson.toJson(restaurantDao.getAll());
        });

        get("/restaurants/:id", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));
            return gson.toJson(restaurantDao.findById(restaurantId));
        });

        //UPDATE
        post("/restaurants/:id/update", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));
            Restaurant restaurant = gson.fromJson(req.body(), Restaurant.class);
            restaurantDao.update(restaurantId, restaurant.getName(), restaurant.getAddress(), restaurant.getZipcode(), restaurant.getPhone(), restaurant.getWebsite(), restaurant.getEmail(), restaurant.getImage());
            res.status(201);
            return gson.toJson(restaurant);
        });

        post("/restaurants/:id/foodtypes/:foodtypeId", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));
            int foodtypeId = Integer.parseInt(req.params("foodtypeId"));
            Foodtype foodtypes = foodtypeDao.findById(foodtypeId);
            Restaurant restaurant = restaurantDao.findById(restaurantId);
            restaurantDao.addRestaurantToFoodtype(restaurant, foodtypes);
            res.status(201);
            return gson.toJson(restaurantDao.getAllFoodtypesForARestaurant(restaurantId));
        });

        get("/restaurants/:id/foodtypes", "application/json", (req, res) ->{
            int restaurantId = Integer.parseInt(req.params("id"));
            restaurantDao.getAllFoodtypesForARestaurant(restaurantId);
            return gson.toJson(restaurantDao.getAllFoodtypesForARestaurant(restaurantId));
        });

        //DELETE
        get("/restaurants/:id/delete", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));
            restaurantDao.deleteById(restaurantId);
            return restaurantId;
        });

        get("/restaurants/delete/all", "application/json", (req, res) -> {
            restaurantDao.clearAllRestaurants();
            return restaurantDao.getAll().size();
        });

        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });

        // FOODTYPES
        //READ

        post("/foodtypes/new", "application/json", (req, res) -> {
            Foodtype foodtype = gson.fromJson(req.body(), Foodtype.class);
            foodtypeDao.add(foodtype);
            res.status(201);;
            return gson.toJson(foodtype);
        });

        get("/foodtypes", "application/json", (req, res) -> {
            return gson.toJson(foodtypeDao.getAll());
        });

        get("/foodtypes/:id", "application/json", (req, res) -> {
            int foodtypeId = Integer.parseInt(req.params("id"));
            return gson.toJson(foodtypeDao.findById(foodtypeId));
        });

        get("/foodtypes/:id/restaurants", "application/json", (req, res) ->{
            int foodTypeId = Integer.parseInt(req.params("id"));
            foodtypeDao.getAllRestaurantsForAFoodtype(foodTypeId);
            return gson.toJson(foodtypeDao.getAllRestaurantsForAFoodtype(foodTypeId));
        });

        get("/foodtypes/:id/delete", "application/json", (req,res)->{
            int foodTypeId = Integer.parseInt(req.params("id"));
            foodtypeDao.deleteById(foodTypeId);
            return gson.toJson(foodTypeId);
        });

        //REVIEWS

        // Create
        post("/restaurants/:restaurantId/reviews/new", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("restaurantId"));
            Review review = gson.fromJson(req.body(), Review.class);
            review.setRestaurantId(restaurantId); //why do I need to set separately?
            reviewDao.add(review);
            res.status(201);
            return gson.toJson(review);
        });

        // Delete
        get("/reviews/:id/delete", "application/json", (req,res)->{
            int reviewId = Integer.parseInt(req.params("id"));
            reviewDao.deleteReviewById(reviewId);
            return gson.toJson(reviewId);
        });

        // Read
        get("/restaurants/:id/reviews", "application/json", (req,res)->{
            int restaurantId = Integer.parseInt(req.params("id"));
            reviewDao.getAllReviewsByRestaurant(restaurantId);
            return gson.toJson(reviewDao.getAllReviewsByRestaurant(restaurantId));
        });
    }
}