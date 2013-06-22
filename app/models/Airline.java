package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Airline extends Model {

    @Id
    public Long id;

    @Constraints.Required
    @Formats.NonEmpty
    public String name;

    public static Finder<Long, Airline> finder = new Finder<Long, Airline>(Long.class, Airline.class);

    public static Map<String, String> options() {
        Map<String, String> options = new LinkedHashMap<String, String>();
        List<Airline> airlines = Airline.finder.order("name").findList();
        for (Airline airline : airlines) {
            options.put(airline.id.toString(), airline.name);
        }
        return options;
    }
}
