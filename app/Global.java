import com.avaje.ebean.Ebean;
import models.*;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {
        Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
        if (Ebean.find(Airline.class).findRowCount() == 0) {
            Ebean.save(all.get("airlines"));
        }
        if (Ebean.find(Airport.class).findRowCount() == 0) {
            Ebean.save(all.get("airports"));
        }
        if (Ebean.find(Aircraft.class).findRowCount() == 0) {
            Ebean.save(all.get("aircrafts"));
        }
        if (Ebean.find(Route.class).findRowCount() == 0) {
            Ebean.save(all.get("routes"));
        }
        if (Ebean.find(Flight.class).findRowCount() == 0) {
            Ebean.save(all.get("flights"));
        }
        if (Ebean.find(User.class).findRowCount() == 0) {
            Ebean.save(all.get("users"));
        }
    }
}
