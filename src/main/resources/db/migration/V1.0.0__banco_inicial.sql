CREATE EXTENSION IF NOT EXISTS unaccent SCHEMA "public" VERSION "1.1";

CREATE TABLE public.professor(
	prof_id serial primary key,
	prof_nome varchar(150) NOT NULL,
	prof_email varchar(255) NOT NULL,
	prof_senha varchar(12) NOT NULL

);

