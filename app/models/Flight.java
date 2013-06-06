package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Flight extends Model {

    @Id
    public Long id;

    @ManyToOne
    public Route route;

    @ManyToOne
    public Airline airline;

    @ManyToOne
    public Aircraft aircraft;

    public static Finder<Long, Flight> finder = new Finder<Long, Flight>(Long.class, Flight.class);
}
