package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Airport extends Model {

    @Id
    public Long id;

    @Constraints.Required
    @Formats.NonEmpty
    @Column(unique = true)
    public String name;

    @Constraints.Required
    @Formats.NonEmpty
    public String city;

    public static Finder<Long, Airport> finder = new Finder<Long, Airport>(Long.class, Airport.class);

    public static Map<String, String> options() {
        Map<String, String> options = new LinkedHashMap<String, String>();
        List<Airport> airports = finder.orderBy("name").findList();
        for (Airport airport : airports) {
            options.put(airport.id.toString(), airport.name);
        }
        return options;
    }
}
