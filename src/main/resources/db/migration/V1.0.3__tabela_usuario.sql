CREATE TABLE usuario(
  user_id bigserial PRIMARY KEY,
  user_name varchar(80),
  user_login varchar(40),
  user_password varchar(250)
);

CREATE TABLE role(
  role_id bigserial PRIMARY KEY,
  role_nome varchar(40),
  role_descricao varchar(100)
);

create TABLE role_user(
  role_id int8,
  user_id int8,
  primary key(role_id, user_id)
);

ALTER TABLE role_user ADD CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES public.usuario(user_id);
ALTER TABLE role_user ADD CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES public.role(role_id);