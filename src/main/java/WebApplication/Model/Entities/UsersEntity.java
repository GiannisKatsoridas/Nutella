package WebApplication.Model.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "UsersEntity.findByEmail",
            query = "SELECT u FROM UsersEntity u WHERE u.email = :email"
    ),
    @NamedQuery(
            name = "UsersEntity.loginUser",
            query = "SELECT u FROM UsersEntity u WHERE u.email = :email AND u.password = :password"
    ),
    @NamedQuery(
            name = "UsersEntity.getAllUsers",
            query = "SELECT u FROM UsersEntity u"
    ),
    @NamedQuery(
            name = "UsersEntity.getAllUsersId",
            query = "SELECT u.id FROM UsersEntity u"
    ),
    @NamedQuery(
            name = "UsersEntity.search",
            query = "SELECT u FROM UsersEntity u WHERE u.firstName LIKE :search OR u.lastName LIKE :search"
    ),
    @NamedQuery(
            name = "UsersEntity.getConversations",
            query = "SELECT DISTINCT u FROM UsersEntity u JOIN MessagesEntity m WHERE (u.id = m.userTo AND m.userFrom = :userId) OR (u.id = m.userFrom AND m.userTo = :userId) ORDER BY m.timestamp DESC"
    )
})
@Table(name = "users", schema = "nutella")
public class UsersEntity {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private boolean isAdmin;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "IsAdmin")
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                isAdmin == that.isAdmin &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, phone, isAdmin);
    }
}
