package dao;

import models.Foodtype;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class Sql2oFoodtypeDaoTest {

    private Sql2oFoodtypeDao foodtypeDao;
    private Connection conn;
    private Sql2oRestaurantDao restaurantDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingCourseSetsId() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        int origFoodtypeId = foodtype.getId();
        foodtypeDao.add(foodtype);
        assertNotEquals(origFoodtypeId, foodtype.getId());
    }
    @Test
    public void existingFoodtypeCanBeFoundById() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        foodtypeDao.add(foodtype);
        Foodtype foundFoodtype = foodtypeDao.findById(foodtype.getId());
        assertEquals(foodtype, foundFoodtype);
    }

    @Test
    public void addedFoodtypesAreReturnedFromGetAll() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        foodtypeDao.add(foodtype);
        assertEquals(1, foodtypeDao.getAll().size());
    }

    @Test
    public void noFoodtypesReturnsEmptyList() throws Exception {
        assertEquals(0, foodtypeDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectFoodtype() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        foodtypeDao.add(foodtype);
        foodtypeDao.deleteById(foodtype.getId());
        assertEquals(0, foodtypeDao.getAll().size());
    }

    public Foodtype setupNewFoodtype() { return new Foodtype("Chinese");}
    public Foodtype setupNewFoodtype2() { return new Foodtype("American");}
}