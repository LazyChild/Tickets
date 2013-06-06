package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Route extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Airport departAirport;

    @ManyToOne
    @Constraints.Required
    public Airport arriveAirport;

    public static Finder<Long, Route> finder = new Finder<Long, Route>(Long.class, Route.class);
}
