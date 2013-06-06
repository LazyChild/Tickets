package controllers;

import models.Airline;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.airline.airline_index;
import views.html.airline.airline_create;
import views.html.airline.airline_edit;

public class AirlineController extends Controller {

    private static Form<Airline> airlineForm = Form.form(Airline.class);

    public static Result index() {
        return ok(airline_index.render(Airline.finder.all()));
    }

    public static Result create() {
        return ok(airline_create.render(airlineForm));
    }

    public static Result edit(Long id) {
        Airline airline = Airline.finder.byId(id);
        return ok(airline_edit.render(airlineForm.fill(airline)));
    }

    public static Result update(Long id) {
        Form<Airline> filledForm = airlineForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("error", "There were errors in the form");
            Airline airline = Airline.finder.byId(id);
            return ok(airline_edit.render(airlineForm.fill(airline)));
        }
        filledForm.get().update();
        return redirect(routes.AirlineController.index());
    }

    public static Result save() {
        Form<Airline> filledForm = airlineForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("error", "There were errors in the form.");
            return ok(airline_create.render(filledForm));
        }
        filledForm.get().save();
        return redirect(routes.AirlineController.index());
    }

    public static Result delete(Long id) {
        Airline.finder.byId(id).delete();
        return redirect(routes.AirlineController.index());
    }
}
