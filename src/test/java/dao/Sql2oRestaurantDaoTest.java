package dao;

import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oRestaurantDaoTest {
    private Sql2oRestaurantDao restaurantDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Restaurant restaurant = setUpNewRestaurant();
        int restaurantId = restaurant.getId();
        restaurantDao.add(restaurant);
        assertNotEquals(restaurantId, restaurant.getId());
    }

    public Restaurant setUpNewRestaurant(){
        return new Restaurant("PhoVan", "1234 SE Division Street", "97206", "503-260-9999", "https://www.phovan.com", "pho@gmail.com", "soup.jpg" );
    }

}