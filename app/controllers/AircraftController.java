package controllers;

import models.Aircraft;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.aircraft.aircraft_create;
import views.html.aircraft.aircraft_edit;
import views.html.aircraft.aircraft_index;

public class AircraftController extends Controller {

    private static Form<Aircraft> aircraftForm = Form.form(Aircraft.class);

    public static Result index() {
        return ok(aircraft_index.render(Aircraft.finder.all()));
    }

    public static Result create() {
        return ok(aircraft_create.render(aircraftForm));
    }

    public static Result save() {
        Form<Aircraft> filledForm = aircraftForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("error", "There were errors in the form.");
            return badRequest(aircraft_create.render(filledForm));
        }
        filledForm.get().save();
        return redirect(routes.AircraftController.index());
    }

    public static Result edit(Long id) {
        Aircraft aircraft = Aircraft.finder.byId(id);
        return ok(aircraft_edit.render(aircraftForm.fill(aircraft)));
    }

    public static Result update(Long id) {
        Form<Aircraft> filledForm = aircraftForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("error", "There were errors in the form.");
            return badRequest(aircraft_edit.render(filledForm));
        }
        filledForm.get().update();
        return redirect(routes.AircraftController.index());
    }

    public static Result delete(Long id) {
        Aircraft.finder.ref(id).delete();
        return redirect(routes.AircraftController.index());
    }
}
