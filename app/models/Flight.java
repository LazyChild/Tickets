package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Flight extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Route route;

    @ManyToOne
    @Constraints.Required
    public Airline airline;

    @ManyToOne
    @Constraints.Required
    public Aircraft aircraft;

    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date;

    public static Finder<Long, Flight> finder = new Finder<Long, Flight>(Long.class, Flight.class);
}
