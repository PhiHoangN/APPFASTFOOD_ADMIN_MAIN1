package phinhph25802.example.appfastfood_admin_main.DTO;

public class Products {
    String id;
    String tensp;
    int giasp;
    String image;
    String mota;
    String category;

    public Products() {
    }

    public Products(String tensp, int giasp, String image, String mota) {
        this.tensp = tensp;
        this.giasp = giasp;
        this.image = image;
        this.mota = mota;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

}
