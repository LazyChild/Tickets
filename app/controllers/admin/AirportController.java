package controllers.admin;

import controllers.routes;
import models.Airport;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.airport.airport_create;
import views.html.airport.airport_edit;
import views.html.airport.airport_index;

@Security.Authenticated(Secured.class)
@With(AdminAction.class)
public class AirportController extends Controller {

    private static Form<Airport> airportForm = Form.form(Airport.class);

    public static Result index() {
        return ok(airport_index.render(Airport.finder.all()));
    }

    public static Result create() {
        return ok(airport_create.render(airportForm));
    }

    public static Result edit(Long id) {
        Airport airport = Airport.finder.byId(id);
        return ok(airport_edit.render(airportForm.fill(airport)));
    }

    public static Result update() {
        Form<Airport> filledForm = airportForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return ok(airport_edit.render(filledForm));
        }
        filledForm.get().update();
        return redirect(routes.AirportController.index());
    }

    public static Result save() {
        Form<Airport> filledForm = airportForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("error", "There were errors in the form.");
            return badRequest(airport_create.render(filledForm));
        }
        filledForm.get().save();
        return redirect(routes.AirportController.index());
    }

    public static Result delete(Long id) {
        Airport.finder.ref(id).delete();
        return redirect(routes.AirportController.index());
    }
}
