package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Aircraft extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public Integer economy;

    @Constraints.Required
    public Integer first;

    public static Finder<Long, Aircraft> finder = new Finder<Long, Aircraft>(Long.class, Aircraft.class);
}
