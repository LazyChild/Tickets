package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ticket extends Model {

    @Id
    public Long id;

    @ManyToOne
    public Booking booking;

    @ManyToOne
    public Flight flight;

    @ManyToOne
    public Passenger passenger;

    public Boolean firstClass;

    public Integer getPrice() {
        return firstClass ? flight.firstPrice : flight.economyPrice;
    }

    public static Finder<Long, Ticket> finder = new Finder<Long, Ticket>(Long.class, Ticket.class);

    public static void create(Ticket ticket) {
        if (Passenger.finder.byId(ticket.passenger.identify) == null) {
            ticket.passenger.save();
        }
        ticket.save();
    }
}
