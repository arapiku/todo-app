package controllers;

import models.*;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.*;
import views.html.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static play.data.Form.form;

public class TodoListController extends Controller {

    public static String remainingTodo(Long id) {
        List<Todo> todoAll = Todo.find.where().eq("list_id", id).findList();
        int count = 0;
        if(todoAll.size() != 0) {
            for (models.Todo todo : todoAll) {
                if (todo.status) {
                    count++;
                }
            }
            String result = todoAll.size() + "個中" + count + "個がチェック済み";
            return result;
        } else {
            return "Todoがありません。";
        }
    }

    public static String closeToTheDeadLine(Long id) {
        List<Todo> todoAll = Todo.find.where().eq("list_id", id).order().asc("deadline_at").findList();
        Date date = null;
        if (todoAll.size() != 0) {
            for (models.Todo todo : todoAll) {
                if (!todo.status) {
                    date = todo.deadlineAt;
                    break;
                }
            }
            if(date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                String result = "~" + sdf.format(date);
                return result;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public Result index() {
        /**
         * アクセス時処理
         *
         * @return 処理結果
         */
        List<TodoList> todoListAll = TodoList.find.all();
        Form<TodoList> f = form(TodoList.class);
        return Results.ok(index.render("Todoリスト",todoListAll, f, ""));

    }

    public Result create() {
        List<TodoList> todoListAll = TodoList.find.all();
        Form<TodoList> f = form(TodoList.class).bindFromRequest();
        if (!f.hasErrors()) {
            TodoList todoListGet = f.get();
            todoListGet.save();
            return Results.created(index.render("Todoリスト", todoListAll, f, "新しいTodoリストが作成されました。"));
        } else {
            return badRequest(index.render("Todoリスト", todoListAll, f, ""));
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

    public Result delete() {
        Form<TodoList> f = form(TodoList.class);
        return ok(delete.render("削除するID番号", f));
    }

    public Result remove() {
        Form<TodoList> f = form(TodoList.class).bindFromRequest();
        if (!f.hasErrors()) {
            TodoList obj = f.get();
            Long id = obj.id;
            obj = TodoList.find.byId(id);
            if(obj != null) {
                obj.delete();
                return redirect("/");
            } else {
                return ok(single.render("ERROR:IDの投稿が見つかりません。", f));
            }
        } else {
            return ok(single.render("ERROR:入力に問題があります。", f));
        }
    }

    public static class FindForm {
        @Constraints.Required(message = "タイトルを入力してください。")
        public String input;
    }

    public Result find() {
        Form<FindForm> f = form(FindForm.class).bindFromRequest();
        List<TodoList> todos = null;
        if (!f.hasErrors()) {
            String input = f.get().input;
            String q = "title like '%" + input + "%'";
            todos = TodoList.find.where().raw(q).findList();
        }
        return ok(find.render("投稿の検索", f, todos));
    }
}
