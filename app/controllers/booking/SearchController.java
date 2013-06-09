package controllers.booking;

import models.Flight;
import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.booking.search;
import views.html.booking.search_result;

import java.util.Date;
import java.util.List;

public class SearchController extends Controller {

    private static Form<Search> searchForm = Form.form(Search.class);

    public static class Search {
        @Constraints.Required
        @Formats.NonEmpty
        public String departCity;

        @Constraints.Required
        @Formats.NonEmpty
        public String arriveCity;

        @Constraints.Required
        @Formats.DateTime(pattern = "yyyy-MM-dd")
        public Date date;
    }

    public static Result index() {
        return ok(search.render(searchForm));
    }

    public static Result search() {
        Form<Search> filledForm = searchForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(search.render(filledForm));
        }
        Search search = filledForm.get();
        List<Flight> flights = Flight.finder.where()
                .eq("route.departAirport.city", search.departCity)
                .eq("route.arriveAirport.city", search.arriveCity)
                .ge("date", search.date).findList();
        return ok(search_result.render(flights, filledForm));
    }
}
