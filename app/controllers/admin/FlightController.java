package controllers.admin;

import models.Flight;
import models.Ticket;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.admin.flight.flight_create;
import views.html.admin.flight.flight_edit;
import views.html.admin.flight.flight_index;
import views.html.admin.flight.flight_view;

import java.util.List;

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
        if (filledForm.hasErrors()) {
            return badRequest(flight_create.render(filledForm));
        }
        filledForm.get().save();
        return redirect(routes.FlightController.index());
    }

    public static Result edit(Long id) {
        Flight flight = Flight.finder.byId(id);
        return ok(flight_edit.render(flightForm.fill(flight)));
    }

    public static Result update() {
        Form<Flight> filledForm = flightForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(flight_edit.render(filledForm));
        }
        filledForm.get().update();
        return redirect(routes.FlightController.index());
    }

    public static Result delete(Long id) {
        Flight.finder.ref(id).delete();
        return redirect(routes.FlightController.index());
    }

    public static Result view(Long id) {
        List<Ticket> tickets = Ticket.finder.where().eq("flight.id", id).findList();
        return ok(flight_view.render(tickets));
    }
}
