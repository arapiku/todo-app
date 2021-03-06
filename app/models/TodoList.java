package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "list")
public class TodoList extends Model {

    public static Finder<Long, TodoList> find = new Finder<>(
        Long.class, TodoList.class
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(length = 30)
    @Constraints.Required(message = "タイトルを入力してください。")
    public String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "list")
    public List<Todo> todo = new ArrayList<>();

    @Override
    public String toString() {
        return ("[id:" + id + ", title:" + title + "]");
    }

}
