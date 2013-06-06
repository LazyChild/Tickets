package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Passenger extends Model {

    @Id
    @Constraints.Required
    @Constraints.MinLength(value = 18)
    @Constraints.MaxLength(value = 18)
    public String identify;

    @Constraints.Required
    public String name;

    public static Finder<String, Passenger> finder = new Finder<String, Passenger>(String.class, Passenger.class);
}
