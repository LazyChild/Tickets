package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Booking extends Model {

    @Id
    public Long id;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.REMOVE)
    public List<Ticket> tickets;

    @ManyToOne
    @Constraints.Required
    public User customer;

    public Status status = Status.UNPAID;

    public Integer getPrice() {
        int sum = 0;
        for (Ticket ticket : tickets) {
            sum += ticket.getPrice();
        }
        return sum;
    }

    public String validate() {
        for (Ticket ticket: tickets) {
            if (ticket.passenger.identify.length() != 18) {
                return "请输入有效的18位证件号！";
            }
            if (ticket.passenger.name.trim().length() == 0) {
                return "乘客姓名不能为空！";
            }
        }
        for (int i = 0; i < tickets.size(); ++i) {
            String id1 = tickets.get(i).passenger.identify;
            for (int j = i + 1; j < tickets.size(); ++j) {
                String id2 = tickets.get(j).passenger.identify;
                if (id1.equals(id2)) {
                    return "请勿添加重复的乘客！";
                }
            }
        }
        return null;
    }

    public static Finder<Long, Booking> finder = new Finder<Long, Booking>(Long.class, Booking.class);

    public static List<Booking> getBookings(String email) {
        return finder.where().eq("customer.email", email).findList();
    }

    public static void create(Booking booking) {
        booking.save();
        for (Ticket ticket : booking.tickets) {
            ticket.booking = booking;
            Ticket.create(ticket);
        }
    }
}
