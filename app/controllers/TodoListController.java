package controllers;

import models.TodoList;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.util.List;

import static play.data.Form.form;

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

    public Result setItem() {
        Form<TodoList> f = form(TodoList.class);
        return ok(single.render("ID番号を入力", f));
    }

    public Result edit() {
        Form<TodoList> f = form(TodoList.class).bindFromRequest();
        if (!f.hasErrors()) {
            TodoList obj = f.get();
            Long id = obj.id;
            obj = TodoList.find.byId(id);
            if (obj != null) {
                f = form(TodoList.class).fill(obj);
                return ok(edit.render("ID=" + id + "の投稿を編集。", f));
            } else {
                return ok(single.render("ERROR:IDの投稿が見つかりません。", f));
            }
        } else {
            return ok(single.render("ERROR:入力に問題があります。", f));
        }
    }

    public Result update() {
        Form<TodoList> f = form(TodoList.class).bindFromRequest();
        if (!f.hasErrors()) {
            TodoList data = f.get();
            data.update();
            return redirect("/");
        } else {
            return ok(edit.render("ERROR:再度入力してください。", f));
        }
    }
}
