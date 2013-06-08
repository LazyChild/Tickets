package controllers.admin;

import controllers.routes;
import models.Flight;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.flight.flight_index;
import views.html.flight.flight_create;
import views.html.flight.flight_edit;

@Security.Authenticated(Secured.class)
@With(AdminAction.class)
public class FlightController extends Controller {

    private static Form<Flight> flightForm = Form.form(Flight.class);

    public static Result index() {
        return ok(flight_index.render(Flight.finder.all()));
    }

    public static Result create() {
        return ok(flight_create.render(flightForm));
    }

    public static Result save() {
        Form<Flight> filledForm = flightForm.bindFromRequest();
        Flight flight = validate(filledForm);
        if (flight == null) {
            return badRequest(flight_create.render(filledForm));
        }
        flight.save();
        return redirect(routes.FlightController.index());
    }

    public static Result edit(Long id) {
        Flight flight = Flight.finder.byId(id);
        return ok(flight_edit.render(flightForm.fill(flight)));
    }

    public static Result update() {
        Form<Flight> filledForm = flightForm.bindFromRequest();
        Flight flight = validate(filledForm);
        if (flight == null) {
            return badRequest(flight_edit.render(filledForm));
        }
        flight.update();
        return redirect(routes.FlightController.index());
    }

    public static Result delete(Long id) {
        Flight.finder.ref(id).delete();
        return redirect(routes.FlightController.index());
    }

    private static Flight validate(Form<Flight> form) {
        if (form.hasErrors()) {
            return null;
        }
        Flight flight = form.get();
        if (flight.aircraft.id == null || flight.airline.id == null || flight.route.id == null) {
            return null;
        }
        return flight;
    }
}
