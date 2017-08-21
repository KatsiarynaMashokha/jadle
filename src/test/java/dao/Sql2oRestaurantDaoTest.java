package dao;

import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class Sql2oRestaurantDaoTest {
    private Sql2oRestaurantDao restaurantDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
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
        Restaurant restaurant = setupNewRestaurant();
        int restaurantId = restaurant.getId();
        restaurantDao.add(restaurant);
        assertNotEquals(restaurantId, restaurant.getId());
    }

    @Test
    public void canFindRestaurantById() throws Exception {
        Restaurant restaurant = setupNewRestaurant();
        restaurantDao.add(restaurant);
        Restaurant foundRestaurant = restaurantDao.findById(restaurant.getId());
        assertEquals(restaurant, foundRestaurant);
    }

    @Test
    public void returnAllAddedRestaurants() throws Exception {
        Restaurant restaurant = setupNewRestaurant();
        restaurantDao.add(restaurant);
        assertEquals(1, restaurantDao.getAll().size());
    }

    @Test
    public void noRestaurantsReturnsIfEmpty() throws Exception {
        assertEquals(0, restaurantDao.getAll().size());
    }

    @Test
    public void updateRestaurantzInfo() throws Exception {
        String initialName = "Pho Van";
        Restaurant restaurant = setupNewRestaurant();
        restaurantDao.add(restaurant);

        restaurantDao.update(restaurant.getId(),"Panda Express", "1234 SE Division Street", "97206", "503-260-9999", "https://www.phovan.com", "pho@gmail.com","soup.jpg");
        Restaurant updatedRestaurant = restaurantDao.findById(restaurant.getId());
        assertNotEquals(initialName, updatedRestaurant.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectRestaurant() throws Exception {
        Restaurant restaurant = setupNewRestaurant();
        restaurantDao.add(restaurant);
        restaurantDao.deleteById(restaurant.getId());
        assertEquals(0, restaurantDao.getAll().size());
    }

    @Test
    public void clearAllRestaurants(){
        Restaurant restaurant = setupNewRestaurant();
        Restaurant restaurant2 = setupNewRestaurant2();
        restaurantDao.add(restaurant);
        restaurantDao.add(restaurant2);
        int daoSize = restaurantDao.getAll().size();
        restaurantDao.clearAllRestaurants();
        assertTrue(daoSize > 0 && daoSize >restaurantDao.getAll().size());

    }

    public Restaurant setupNewRestaurant(){
        return new Restaurant("PhoVan", "1234 SE Division Street", "97206", "503-260-9999", "https://www.phovan.com", "pho@gmail.com", "soup.jpg" );
    }
    public Restaurant setupNewRestaurant2(){
        return new Restaurant("Olive Garden", "415 SW 107th", "97005", "503-123-4567");
    }

}