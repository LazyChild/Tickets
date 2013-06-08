package controllers.admin;

import models.User;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class AdminAction extends Action.Simple {
    @Override
    public Result call(Http.Context context) throws Throwable {
        if (!User.isAdmin(context.request().username())) {
            return forbidden();
        }
        return delegate.call(context);
    }
}
