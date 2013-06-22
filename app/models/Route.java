package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Route extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Airport departAirport;

    @ManyToOne
    @Constraints.Required
    public Airport arriveAirport;

    @Constraints.Required
    @Formats.DateTime(pattern = "HH:mm")
    public Date departTime;

    @Constraints.Required
    @Formats.DateTime(pattern = "HH:mm")
    public Date arriveTime;

    public String validate() {
        if (departAirport == null || departAirport.id == null) {
            return "请指定一个起飞机场";
        }
        if (arriveAirport == null || arriveAirport.id == null) {
            return "请指定一个降落机场";
        }
        if (arriveTime.before(departTime)) {
            return "降落时间不应早于起飞时间";
        }
        return null;
    }

    public static Finder<Long, Route> finder = new Finder<Long, Route>(Long.class, Route.class);

    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public static Map<String, String> options() {
        Map<String, String> options = new LinkedHashMap<String, String>();
        List<Route> routes = Route.finder.order("id").findList();
        for (Route route : routes) {
            options.put(route.id.toString(), route.departAirport.name + " " + format.format(route.departTime)
                    + " -> " + route.arriveAirport.name + " " + format.format(route.arriveTime));
        }
        return options;
    }
}
