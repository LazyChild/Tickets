package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Ticket extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Flight flight;

    @ManyToOne
    @Constraints.Required
    public Passenger passenger;

    public static Finder<Long, Ticket> finder = new Finder<Long, Ticket>(Long.class, Ticket.class);
}
