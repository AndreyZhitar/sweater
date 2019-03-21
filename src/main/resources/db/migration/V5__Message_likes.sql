create table message_likes (
  user_id bigint not null references sweater.public.users,
  message_id bigint not null references sweater.public.message,
  primary key (user_id, message_id)
);