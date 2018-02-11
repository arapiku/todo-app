package models;


import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "todo")
public class Todo extends Model {

    public static Finder<Long, Todo> find = new Finder<>(
        Long.class, Todo.class
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(length = 30)
    @Constraints.Required
    public String title;

    @NotNull
    @Column(name = "deadline_at")
    @Constraints.Required
    public Date deadlineAt;

    @NotNull
    @Column(name = "created_at")
    @CreatedTimestamp
    public Date createdAt;

    @NotNull
    @Column(nullable = false, columnDefinition = "BIT DEFAULT FALSE")
    public boolean status;

    @ManyToOne(optional = false)
    public TodoList list;

    @Override
    public String toString() {
        return ("[id:" + id + ", title:" + title + ", deadline_at:" + deadlineAt + ", created_at:" + createdAt + ", status:" + status + ", list_id:" + list + "]");
    }

}
