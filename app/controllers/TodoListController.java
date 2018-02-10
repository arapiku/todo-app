package controllers;

import models.TodoList;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.util.List;

import static play.data.Form.*;

public class TodoListController extends Controller {

    public Result index() {
        /**
         * アクセス時処理
         *
         * @return 処理結果
         */


        List<TodoList> todoListAll = TodoList.find.all();
        return Results.ok(index.render(todoListAll));
    }

    public Result add() {
        Form<TodoList> f = form(TodoList.class);
        return ok(add.render(f));
    }

    public Result create() {
        Form<TodoList> f = form(TodoList.class).bindFromRequest();
        if (!f.hasErrors()) {
            TodoList data = f.get();
            data.save();
            return redirect("/");
        } else {
            return badRequest(add.render(f));
        }
    }
}
