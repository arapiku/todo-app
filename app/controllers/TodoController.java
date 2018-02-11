package controllers;

import models.Todo;
import play.mvc.*;

public class TodoController extends Controller {

    public Result index(Long id) {
        Todo todo = Todo.find.byId(id);
        return ok(views.html.id.render(todo));
    }
}
