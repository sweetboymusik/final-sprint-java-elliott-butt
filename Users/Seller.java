package Users;

public class Seller extends User {
    // instance variables (based on seller information)
    private int sellerId;
    private String storeName;
    private String storeDescription;
    private String contactNumber;
    private String storeEmail;
    private String url;
    private String address;

    // constructors
    public Seller() {
        super("seller");
    }

    public Seller(int id, String username, String password, String email, int sellerId, String storeDescription,
            String contactNumber, String storeEmail, String url, String address) {
        super(id, username, password, email, "seller");

        this.sellerId = sellerId;
        this.storeDescription = storeDescription;
        this.contactNumber = contactNumber;
        this.storeEmail = storeEmail;
        this.url = url;
        this.address = address;
    }

    // getters and setters
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
