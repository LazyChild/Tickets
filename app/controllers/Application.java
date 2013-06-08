package controllers;

import models.User;
import models.utils.Hash;
import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.libs.Crypto;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;
import views.html.register;

public class Application extends Controller {

    private static Form<Login> loginForm = Form.form(Login.class);
    private static Form<Register> registerForm = Form.form(Register.class);

    public static class Login {
        @Constraints.Required
        @Formats.NonEmpty
        public String email;

        @Constraints.Required
        public String password;

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "用户名或密码错误！";
            }
            return null;
        }
    }

    public static class Register {
        @Constraints.Required
        @Formats.NonEmpty
        public String email;

        @Constraints.Required
        public String password;

        @Constraints.Required
        @Formats.NonEmpty
        public String repeatPassword;

        @Constraints.Required
        @Formats.NonEmpty
        public String name;

        public String validate() {
            if (!password.equals(repeatPassword)) {
                return "两次密码不匹配！";
            }
            return null;
        }
    }

    public static Result index() {
        return ok(index.render("Hello world!"));
    }

    public static Result login() {
        return ok(login.render(loginForm));
    }

    public static Result authenticate() {
        Form<Login> filledForm = loginForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(login.render(filledForm));
        }
        session("email", filledForm.get().email);
        return redirect(routes.Application.index());
    }

    public static Result logout() {
        session().clear();
        return redirect(routes.Application.index());
    }

    public static Result register() {
        return ok(register.render(registerForm));
    }

    public static Result saveUser() {
        Form<Register> filledForm = registerForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(register.render(filledForm));
        }
        User user = new User();
        Register register = filledForm.get();
        user.email = register.email;
        user.passwordHash = Hash.createPassword(register.password);
        user.name = register.name;
        user.save();
        return redirect(routes.Application.login());
    }
}
