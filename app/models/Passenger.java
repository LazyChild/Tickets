package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Passenger extends Model {

    @Id
    public String identify;

    public String name;

    public static Finder<String, Passenger> finder = new Finder<String, Passenger>(String.class, Passenger.class);
}
