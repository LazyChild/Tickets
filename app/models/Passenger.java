package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Passenger extends Model {

    @Id
    public String identify;

    public String name;

    public static Finder<String, Passenger> finder = new Finder<String, Passenger>(String.class, Passenger.class);
}
