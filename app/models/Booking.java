package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Booking extends Model {

    @Id
    public Long id;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Passenger> passengers;

    public static Finder<Long, Booking> finder = new Finder<Long, Booking>(Long.class, Booking.class);
}
