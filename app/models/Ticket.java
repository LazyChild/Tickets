package models;

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
    public Flight flight;

    @ManyToOne
    public Passenger passenger;

    public static Finder<Long, Ticket> finder = new Finder<Long, Ticket>(Long.class, Ticket.class);

    public static List<Ticket> all() {
        return finder.all();
    }

    public static void create(Ticket ticket) {
        ticket.save();
    }

    public static void delete(Long id) {
        finder.byId(id).delete();
    }
}
