package models;

public class Restaurant {
    private String name;
    private String address;
    private String zipcode;
    private String phone;
    private String website;
    private String email;
    private String image;
    private int id;


    public Restaurant(String name, String address, String zipcode, String phone) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.website = "no website listed";
        this.email = "no email available";
        this.image = "/resources/images/uploads";
    }
    public Restaurant(String name, String address, String zipcode, String phone, String website, String email, String image) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.website = website;
        this.email = email;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!address.equals(that.address)) return false;
        if (!zipcode.equals(that.zipcode)) return false;
        if (!phone.equals(that.phone)) return false;
        if (!website.equals(that.website)) return false;
        if (!email.equals(that.email)) return false;
        return image.equals(that.image);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + website.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + id;
        return result;
    }
}
