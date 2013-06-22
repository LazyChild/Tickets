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
public class Aircraft extends Model {

    @Id
    public Long id;

    @Constraints.Required
    @Formats.NonEmpty
    public String name;

    @Constraints.Required
    public Integer economy;

    @Constraints.Required
    public Integer first;

    public static Finder<Long, Aircraft> finder = new Finder<Long, Aircraft>(Long.class, Aircraft.class);

    public static Map<String, String> options() {
        Map<String, String> options = new LinkedHashMap<String, String>();
        List<Aircraft> aircrafts = Aircraft.finder.order("name").findList();
        for (Aircraft aircraft : aircrafts) {
            options.put(aircraft.id.toString(), aircraft.name);
        }
        return options;
    }
}
