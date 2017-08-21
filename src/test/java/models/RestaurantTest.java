package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("Panda Express", testRestaurant.getName());
    }

    @Test
    public void getAddressReturnsCorrectAddress() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("123 NW Cornell Avenue", testRestaurant.getAddress());
    }

    @Test
    public void getZipReturnsCorrectZip() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("97214", testRestaurant.getZipcode());
    }
    @Test
    public void getPhoneReturnsCorrectPhone() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("503-234-9876", testRestaurant.getPhone());
    }

    @Test
    public void getWebsiteReturnsCorrectWebsite() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("http://pandaexpress.com", testRestaurant.getWebsite());
    }

    @Test
    public void getEmailReturnsCorrectEmail() throws Exception {
        Restaurant testRestaurant = setupRestaurant2();
        assertEquals("no email available", testRestaurant.getEmail());
    }

    @Test
    public void getImageReturnsCorrectImage() throws Exception {
        Restaurant testRestaurant = setupRestaurant2();
        assertNotEquals("/resources/images/uploads/no_image.jpg", testRestaurant.getImage());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setName("Jin Wah");
        assertNotEquals("Panda Express",testRestaurant.getName());
    }

    @Test
    public void setAddressSetsCorrectAddress() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setAddress("678 SE Divison");
        assertNotEquals("123 NW Cornell Avenue", testRestaurant.getAddress());
    }

    @Test
    public void setZipSetsCorrectZip() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setZipcode("92010");
        assertNotEquals("97214", testRestaurant.getZipcode());
    }
    @Test
    public void setPhoneSetsCorrectPhone() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setPhone("971-494-3540");
        assertNotEquals("503-234-9876", testRestaurant.getPhone());
    }

    @Test
    public void setWebsiteSetsCorrectWebsite() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setWebsite("http://jinwah.com");
        assertNotEquals("http://pandaexpress.com", testRestaurant.getWebsite());
    }

    @Test
    public void setEmailSetsCorrectEmail() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setEmail("hello@jinwah.com");
        assertNotEquals("wahmei@pandaexpress.com", testRestaurant.getEmail());
    }

    @Test
    public void setImageSetsCorrectImage() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setImage("noodles.jpg");
        assertNotEquals("panda.jpg", testRestaurant.getImage());
    }

    public Restaurant setupRestaurant () {
        return new Restaurant("Panda Express", "123 NW Cornell Avenue", "97214", "503-234-9876", "http://pandaexpress.com", "heythere@pandaexapress.com", "panda.jpg");
    }
    public Restaurant setupRestaurant2() {
        return new Restaurant("Olive Garden", "415 SW 107th", "97005", "503-123-4567");
    }
}