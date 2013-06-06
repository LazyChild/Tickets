package controllers;

import models.Route;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.route.route_create;
import views.html.route.route_edit;
import views.html.route.route_index;

public class RouteController extends Controller {

    private static Form<Route> routeForm = Form.form(Route.class);

    public static Result index() {
        return ok(route_index.render(Route.finder.all()));
    }

    public static Result create() {
        return ok(route_create.render(routeForm));
    }

    public static Result save() {
        Form<Route> filledForm = routeForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("error", "There were errors in the form.");
            return badRequest(route_create.render(filledForm));
        }
        filledForm.get().save();
        return redirect(routes.RouteController.index());
    }

    public static Result edit(Long id) {
        Route route = Route.finder.byId(id);
        return ok(route_edit.render(routeForm.fill(route)));
    }

    public static Result update(Long id) {
        Form<Route> filledForm = routeForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("error", "There were errors in the form.");
            return badRequest(route_edit.render(filledForm));
        }
        filledForm.get().update();
        return redirect(routes.RouteController.index());
    }

    public static Result delete(Long id) {
        Route.finder.byId(id).delete();
        return redirect(routes.RouteController.index());
    }
}
