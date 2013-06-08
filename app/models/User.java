package models;

import models.utils.Hash;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {

    @Id
    public Long id;

    @Column(unique = true)
    public String email;

    public String passwordHash;

    public String name;

    public Role role = Role.USER;

    public static Finder<Long, User> finder = new Finder<Long, User>(Long.class, User.class);

    public static User authenticate(String email, String password) {
        User user = finder.where().eq("email", email).findUnique();
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
