package models;


import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "todo")
public class Todo extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(length = 30)
    public String title;

    @NotNull
    @Column(name = "deadline_at")
    public Date deadlineAt;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "created_at")
    public Date createdAt;

    @NotNull
    @Column(nullable = false, columnDefinition = "BIT DEFAULT FALSE")
    public boolean status;

    @ManyToOne(optional = false)
    public TodoList list;

}
