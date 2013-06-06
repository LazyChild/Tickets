package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Passenger extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    @Constraints.MinLength(value = 18)
    @Constraints.MaxLength(value = 18)
    public String identify;

    public static Finder<Long, Passenger> finder = new Finder<Long, Passenger>(Long.class, Passenger.class);
}
