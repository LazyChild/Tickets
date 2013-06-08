import com.avaje.ebean.Ebean;
import models.Airline;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {
        if (Ebean.find(Airline.class).findRowCount() == 0) {
            Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");

            Ebean.save(all.get("airlines"));
        }
    }
}
