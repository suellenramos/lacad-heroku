CREATE TABLE public.professor(
	prof_id serial primary key,
	prof_nome varchar(150) NOT NULL,
	prof_email varchar(255) NOT NULL,
	prof_password varchar(200) NULL,
	prof_data_hora_cadastro timestamp,
	prof_ativo boolean NULL
);

CREATE TABLE public.curso(
	cur_id serial primary key,
	cur_descricao varchar(100) NOT NULL,
	cur_ativo boolean NULL
);

CREATE TABLE public.disciplina(
	disc_id serial primary key,
	disc_descricao varchar(150) NOT NULL,
	disc_ativo boolean NULL
);

CREATE TABLE public.professor_disciplina (
	prof_id integer NULL,
	disc_id integer NULL,
	CONSTRAINT professor_disciplina_fk FOREIGN KEY (prof_id) REFERENCES public.professor(prof_id),
	CONSTRAINT professor_disciplina_fk_1 FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id)
);

CREATE TABLE public.professor_curso (
	prof_id integer NULL,
	cur_id integer NULL,
	CONSTRAINT professor_curso_fk FOREIGN KEY (prof_id) REFERENCES public.professor(prof_id),
	CONSTRAINT professor_curso_fk_1 FOREIGN KEY (cur_id) REFERENCES public.curso(cur_id)
);

CREATE TABLE public.curso_disciplina (
	cur_id integer NULL,
	disc_id integer NULL,
	CONSTRAINT curso_disciplina_fk_1 FOREIGN KEY (cur_id) REFERENCES public.curso(cur_id),
	CONSTRAINT curso_disciplina_fk FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id)

);

CREATE TABLE public.conteudo(
	conte_id serial primary key,
	conte_descricao varchar(150) NOT NULL,
	conte_ativo boolean NULL
);

CREATE TABLE public.disciplina_conteudo (
	disc_id integer NULL,
	conte_id integer NULL,
	CONSTRAINT disciplina_conteudo_fk FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id),
	CONSTRAINT disciplina_conteudo_fk_1 FOREIGN KEY (conte_id) REFERENCES public.conteudo(conte_id)
);

CREATE TABLE public.aplicativo(
	apli_id serial primary key,
	apli_link varchar(2000) NOT NULL,
	apli_descricao varchar(2000) NOT NULL,
    apli_ativo boolean NULL
);

CREATE TABLE public.conteudo_aplicativo (
	ca_id seriaL primary key NOT NULL,
	apli_id integer NULL,
	conte_id integer NULL,
	CONSTRAINT conteudo_aplicativo_fk FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id),
	CONSTRAINT conteudo_aplicativo_fk_1 FOREIGN KEY (conte_id) REFERENCES public.conteudo(conte_id)
);

CREATE TABLE public.imagem (
	im_id bigserial primary key NOT NULL,
    apli_id integer,
	im_data date,
	im_bucket varchar(50),
    im_hash varchar(50),
	CONSTRAINT imagem_fk_2 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id)
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

CREATE TABLE public.favorito(
    favo_id serial primary key,
    apli_id integer not null,
    prof_id integer not null
);
ALTER TABLE public.favorito ADD CONSTRAINT favorito_fk0 FOREIGN KEY (prof_id) REFERENCES public.professor(prof_id);
ALTER TABLE public.favorito ADD CONSTRAINT favorito_fk1 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id);


CREATE TABLE public.perfil (
	perf_id  serial primary key NOT NULL,
	perf_nome varchar(100) NOT NULL,
	perf_descricao varchar(200) NULL,
	perf_ativo bool NOT NULL
);

CREATE TABLE public.regras (
	reg_id serial primary key NOT NULL,
	reg_nome varchar(100) NULL,
	reg_descricao varchar(100) NULL,
	reg_tipo varchar(50) null
);

CREATE TABLE public.perfil_regras (
    perf_id integer NOT NULL,
    reg_id integer NOT NULL
);
ALTER TABLE public.perfil_regras ADD CONSTRAINT fk_perfil_regras_perf_id FOREIGN KEY (perf_id) REFERENCES perfil(perf_id);
ALTER TABLE public.perfil_regras ADD CONSTRAINT fk_perfil_regras_reg_id FOREIGN KEY (reg_id) REFERENCES regras(reg_id);

CREATE TABLE public.professor_perfil(
    prof_id integer  NULL,
    perf_id integer NOT NULL
);
ALTER TABLE public.professor_perfil ADD CONSTRAINT fk_professor_perfil_prof_id FOREIGN KEY (prof_id) REFERENCES professor(prof_id);
ALTER TABLE public.professor_perfil ADD CONSTRAINT fk_professor_perfil_perf_id FOREIGN KEY (perf_id) REFERENCES perfil(perf_id);






