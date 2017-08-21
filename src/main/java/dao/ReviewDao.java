package dao;

import models.Review;

import java.util.List;

public interface ReviewDao {

    //create
    void add(Review review); //F

//    //read
    Review findById(int id);
    List<Review> getAllReviewsByRestaurant(int restaurantId); // H & G

    List<Review> getAll();
//
//    //update
//    //omit for now
//
    //delete
    void deleteReviewById(int id); //M

}
