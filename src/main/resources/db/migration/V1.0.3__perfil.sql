ALTER TABLE public.professor ADD prof_data_hora_cadastro timestamp NULL;
ALTER TABLE public.professor ADD prof_ativo bool  NULL;
ALTER TABLE public.professor ALTER COLUMN prof_senha TYPE varchar(200) USING prof_senha::varchar;

CREATE TABLE public.perfil (
	perf_id  serial primary key NOT NULL,
	perf_nome varchar(100) NOT NULL,
	perf_descricao varchar(200) NULL,
	perf_ativo bool NOT NULL
);

CREATE TABLE public.professor_perfil(
    prof_id integer NOT NULL,
    perf_id integer NOT NULL
);
ALTER TABLE public.professor_perfil ADD CONSTRAINT fk_professor_perfil_prof_id FOREIGN KEY (prof_id) REFERENCES professor(prof_id);
ALTER TABLE public.professor_perfil ADD CONSTRAINT fk_professor_perfil_perf_id FOREIGN KEY (perf_id) REFERENCES perfil(perf_id);


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

CREATE EXTENSION IF NOT EXISTS unaccent;
