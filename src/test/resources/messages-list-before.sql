delete from sweatertest."public".message;

insert into sweatertest."public".message(id, tag, text, user_id) values
(1, 'my-tag1', 'first', 1),
(2, 'my-tag2', 'second', 1),
(3, 'my-tag1', 'third', 1),
(4, 'my-tag4', 'fourth', 1);

alter sequence hibernate_sequence restart with 10;