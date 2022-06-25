CREATE TABLE public.curso_disciplina (
	cd_id serial NOT NULL,
	cur_id integer NOT NULL,
	disc_id integer NOT NULL,
	CONSTRAINT curso_disciplina_pk PRIMARY KEY (cd_id),
	CONSTRAINT curso_disciplina_fk FOREIGN KEY (cur_id) REFERENCES public.curso(cur_id),
	CONSTRAINT curso_disciplina_fk_1 FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id)
);

CREATE TABLE public.disciplina_conteudo (
	dc_id serial NOT NULL,
	disc_id integer NOT NULL,
	conte_id integer NOT NULL,
	CONSTRAINT disciplina_conteudo_pk PRIMARY KEY (dc_id),
	CONSTRAINT disciplina_conteudo_fk_1 FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id),
	CONSTRAINT disciplina_conteudo_fk FOREIGN KEY (conte_id) REFERENCES public.conteudo(conte_id)
);

CREATE TABLE public.conteudo_aplicativo (
	ca_id serial NOT NULL,
	conte_id integer NOT NULL,
	apli_id integer NOT NULL,
	CONSTRAINT conteudo_aplicativo_pk PRIMARY KEY (ca_id),
	CONSTRAINT conteudo_aplicativo_fk FOREIGN KEY (conte_id) REFERENCES public.conteudo(conte_id),
	CONSTRAINT conteudo_aplicativo_fk_1 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id)
);

CREATE TABLE public.professor_aplicativo (
	pa_id serial NOT NULL,
	prof_id integer NOT NULL,
	apli_id integer NOT NULL,
	CONSTRAINT professor_aplicativo_pk PRIMARY KEY (pa_id),
	CONSTRAINT professor_aplicativo_fk FOREIGN KEY (prof_id) REFERENCES public.professor(prof_id),
	CONSTRAINT professor_aplicativo_fk_1 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id)
);