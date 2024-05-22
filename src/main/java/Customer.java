
import java.io.Serializable;


public class Customer implements Serializable {
    private String name;
    private String CCCD;
    private int age;
    private String quequan;
    private String phone;
    private int time;
    private int money;
    private String address;
    private String gioitinh;
    private String ngayhethan;

    public Customer(String name, String CCCD, int age, String quequan, String phone,
      int time, int money, String address, String gioitinh, String ngayhethan) {
        this.name = name;
        this.CCCD = CCCD;
        this.age = age;
        this.quequan = quequan;
        this.phone = phone;
        this.time = time;
        this.money = money;
        this.address = address;
        this.gioitinh = gioitinh;
        this.ngayhethan = ngayhethan;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(String ngayhethan) {
        this.ngayhethan = ngayhethan;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMoney() {
        return money*=time;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
