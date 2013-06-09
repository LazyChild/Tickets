package controllers.booking;

import controllers.admin.Secured;
import models.Booking;
import models.Flight;
import models.Status;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.booking.booking;
import views.html.booking.order;

import java.util.List;

@Security.Authenticated(Secured.class)
public class BookController extends Controller {

    private static Form<Booking> bookingForm = Form.form(Booking.class);

    public static Result index() {
        List<Booking> bookings = Booking.getBookings(request().username());
        return ok(order.render(bookings));
    }

    public static Result bookForm(Long id) {
        Flight flight = Flight.finder.byId(id);
        return ok(booking.render(flight, bookingForm));
    }

    public static Result book(Long id) {
        Form<Booking> filledForm = bookingForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            Flight flight = Flight.finder.byId(id);
            return ok(booking.render(flight, filledForm));
        }
        Booking booking = filledForm.get();
        Booking.create(booking);
        return redirect(routes.BookController.index());
    }

    public static Result pay(Long id) {
        Booking booking = Booking.finder.byId(id);
        booking.status = models.Status.PAID;
        booking.update();
        return redirect(routes.BookController.index());
    }
}
