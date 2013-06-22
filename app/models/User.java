package models;

import models.utils.Hash;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class User extends Model {

    @Id
    public String email;

    public String passwordHash;

    public Role role = Role.USER;

    public static Finder<String, User> finder = new Finder<String, User>(String.class, User.class);

    public static User authenticate(String email, String password) {
        User user = finder.byId(email);
        if (user != null) {
            if (Hash.checkPassword(password, user.passwordHash)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isAdmin(String email) {
        return finder.where().eq("email", email).eq("role", Role.ADMIN).findRowCount() > 0;
    }
}
