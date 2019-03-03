delete from sweatertest."public".user_role;
delete from sweatertest."public".users;


insert into sweatertest."public".users(id, active, password, username) values
(1, true, '$2a$08$EYpHy33pdjkcjrniP8tQEuaPGztkvO0c4x3Si1xoLtwhQgbtzJDd2', 'Andrey'),
(2, true, '$2a$08$EYpHy33pdjkcjrniP8tQEuaPGztkvO0c4x3Si1xoLtwhQgbtzJDd2', 'Mike');

insert into sweatertest."public".user_role(user_id, roles) values
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');