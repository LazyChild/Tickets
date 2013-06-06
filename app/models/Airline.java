package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Airline extends Model {

    @Id
    public Long id;

    @Constraints.Required
    @Formats.NonEmpty
    public String name;

    public static Finder<Long, Airline> finder = new Finder<Long, Airline>(Long.class, Airline.class);
}
