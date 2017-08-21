package dao;

import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class Sql2oReviewDaoTest {
    private Sql2oReviewDao reviewDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Review review = setupNewReview();
        int origReviewId = review.getId();
        reviewDao.add(review);
        assertNotEquals(origReviewId, review.getId());
    }

    @Test
    public void ExistingReviewCanBeFoundById() throws Exception {
        Review review = setupNewReview();
        reviewDao.add(review);
        Review foundReview = reviewDao.findById(review.getId());
        assertEquals(review, foundReview);
    }

    @Test
    public void returnAlladdedReviewsFromgetAll() throws Exception {
        Review review = setupNewReview();
        reviewDao.add(review);
        assertEquals(1, reviewDao.getAll().size());
    }
    @Test
    public void noReviewsReturnsEmptyList() throws Exception {
        assertEquals(0, reviewDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectReview() throws Exception {
        Review review = setupNewReview();
        reviewDao.add(review);
        reviewDao.deleteReviewById(review.getId());
        assertEquals(0, reviewDao.getAll().size());
    }
    @Test
    public void teamIdIsReturnedCorrectly() throws Exception {
        Review review = setupNewReview();
        int originalTeamId = review.getRestaurantId();
        reviewDao.add(review);
        assertEquals(originalTeamId, reviewDao.findById(review.getId()).getRestaurantId());
    }

    public Review setupNewReview() {return new Review("Kim", 5, 1, "Love it");}
}