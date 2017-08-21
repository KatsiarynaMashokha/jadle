//package models;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
//public class Review {
//    private String writtenBy;
//    private int rating;
//    private Timestamp createdAt;
//    private int id;
//    private int restaurantId;
//    private String content;
//
//    public Review(String writtenBy, int rating, int restaurantId, String content) {
//        this.writtenBy = writtenBy;
//        this.rating = rating;
//        this.restaurantId = restaurantId;
//        this.content = content;
//        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
//    }
//
//    public String getWrittenBy() {
//        return writtenBy;
//    }
//
//    public void setWrittenBy(String writtenBy) {
//        this.writtenBy = writtenBy;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
//
//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getRestaurantId() {
//        return restaurantId;
//    }
//
//    public void setRestaurantId(int restaurantId) {
//        this.restaurantId = restaurantId;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Review review = (Review) o;
//
//        if (rating != review.rating) return false;
//        if (id != review.id) return false;
//        if (restaurantId != review.restaurantId) return false;
//        if (!writtenBy.equals(review.writtenBy)) return false;
//        if (createdAt != null ? !createdAt.equals(review.createdAt) : review.createdAt != null) return false;
//        return content != null ? content.equals(review.content) : review.content == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = writtenBy.hashCode();
//        result = 31 * result + rating;
//        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
//        result = 31 * result + id;
//        result = 31 * result + restaurantId;
//        result = 31 * result + (content != null ? content.hashCode() : 0);
//        return result;
//    }
//}
