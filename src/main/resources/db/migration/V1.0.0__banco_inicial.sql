CREATE EXTENSION IF NOT EXISTS unaccent SCHEMA "public" VERSION "1.1";

CREATE TABLE public.professor(
	prof_id serial primary key,
	prof_nome varchar(150) NOT NULL,
	prof_email varchar(255) NOT NULL,
	prof_senha varchar(12) NOT NULL
);

CREATE TABLE public.curso(
	cur_id serial primary key,
	cur_descricao varchar(50) NOT NULL
);

CREATE TABLE public.disciplina(
	disc_id serial primary key,
	disc_descricao varchar(50) NOT NULL
);

CREATE TABLE public.conteudo(
	conte_id serial primary key,
	conte_descricao varchar(50) NOT NULL
);

CREATE TABLE public.aplicativo(
	apli_id serial primary key,
	apli_link varchar(500) NOT NULL,
	apli_descricao varchar(500) NOT NULL
);


CREATE TABLE public.avaliacao(
    ava_id serial primary key,
    ava_nota integer not null,
    ava_comentarios varchar(500) not null,
    apli_id integer not null,
    prof_id integer not null
);

ALTER TABLE public.avaliacao ADD CONSTRAINT avaliacao_fk0 FOREIGN KEY (prof_id) REFERENCES public.professor(prof_id);
ALTER TABLE public.avaliacao ADD CONSTRAINT avaliacao_fk1 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id);




