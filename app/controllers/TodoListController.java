package controllers;

import models.TodoList;
import play.mvc.*;
import views.html.index;

import java.util.List;

public class TodoListController extends Controller {

    public Result index() {
        /**
         * アクセス時処理
         *
         * @return 処理結果
         */


        List<TodoList> todoListAll = TodoList.find.all();
        return Results.ok(index.render("DBサンプル", todoListAll));
    }
}
