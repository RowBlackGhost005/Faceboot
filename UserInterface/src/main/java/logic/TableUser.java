package logic;

import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

public class TableUser {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private String gender;
    private String birthDate;
    private String password;

    public TableUser() {
    }

    public TableUser(SimpleStringProperty email, String password) {
        this.email = email;
        this.password = password;
    }

    public TableUser(SimpleStringProperty name, SimpleStringProperty email, SimpleStringProperty phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public TableUser(SimpleStringProperty id, SimpleStringProperty name, SimpleStringProperty email, SimpleStringProperty phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public TableUser(SimpleStringProperty name, SimpleStringProperty email, SimpleStringProperty phone, String gender, String birthDate, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.password = password;
    }

    public TableUser(SimpleStringProperty id, SimpleStringProperty name, SimpleStringProperty email, SimpleStringProperty phone, String gender, String birthDate, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.password = password;
    }

    public String getId() {
        return id.get();
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(SimpleStringProperty phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableUser other = (TableUser) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", birthDate=" + birthDate + ", password=" + password + '}';
    }
}
