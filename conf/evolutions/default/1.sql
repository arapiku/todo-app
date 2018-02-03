# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table todo (
  id                            bigint auto_increment not null,
  title                         varchar(30) not null,
  deadline_at                   datetime(6) not null,
  created_at                    datetime(6) not null,
  status                        BIT DEFAULT FALSE not null,
  list_id                       bigint not null,
  constraint pk_todo primary key (id)
);

create table list (
  id                            bigint auto_increment not null,
  title                         varchar(30) not null,
  constraint pk_list primary key (id)
);

alter table todo add constraint fk_todo_list_id foreign key (list_id) references list (id) on delete restrict on update restrict;
create index ix_todo_list_id on todo (list_id);


# --- !Downs

alter table todo drop foreign key fk_todo_list_id;
drop index ix_todo_list_id on todo;

drop table if exists todo;

drop table if exists list;

