package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oReviewDao implements ReviewDao {
    private final Sql2o sql2o;

    public Sql2oReviewDao(Sql2o sql2o) {this.sql2o = sql2o;}

    @Override
    public void add(Review review) {
        String sql = "INSERT INTO reviews (writtenBy, rating, restaurantId, content) VALUES (:writtenBy, :rating, :restaurantId, :content)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("writtenBy", review.getWrittenBy())
                    .addParameter("rating", review.getRating())
                    .addParameter("restaurantId", review.getRestaurantId())
                    .addParameter("content", review.getContent())
                    .addColumnMapping("WRITTENBY", "writtenBy")
                    .addColumnMapping("RATING", "rating")
                    .addColumnMapping("RESTAURANTID", "restaurantId")
                    .addColumnMapping("CONTENT", "content")
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


}
