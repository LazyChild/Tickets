package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Flight extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Route route;

    @ManyToOne
    @Constraints.Required
    public Airline airline;

    @ManyToOne
    @Constraints.Required
    public Aircraft aircraft;

    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date;

    @Constraints.Required
    @Constraints.Min(0)
    public Integer firstPrice;

    @Constraints.Required
    @Constraints.Min(0)
    public Integer economyPrice;

    public String validate() {
        if (aircraft == null || aircraft.id == null) {
            return "请指定一架飞机";
        }
        if (airline == null || airline.id == null) {
            return "请指定一家航空公司";
        }
        if (route == null || route.id == null) {
            return "请指定一条航线";
        }
        return null;
    }

    public static Finder<Long, Flight> finder = new Finder<Long, Flight>(Long.class, Flight.class);
}
