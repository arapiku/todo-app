package models;

import com.avaje.ebean.Model;

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
    public String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "list")
    public List<Todo> todo = new ArrayList<>();

}
