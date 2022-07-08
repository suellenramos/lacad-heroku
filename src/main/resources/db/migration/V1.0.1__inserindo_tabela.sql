CREATE TABLE public.curso_disciplina (
	cd_id serial primary key NOT NULL,
	cur_id integer NOT NULL,
	disc_id integer NOT NULL,
	prof_id integer NOT NULL,
	CONSTRAINT curso_disciplina_fk FOREIGN KEY (cur_id) REFERENCES public.curso(cur_id),
	CONSTRAINT curso_disciplina_fk_1 FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id),
	CONSTRAINT curso_disciplina_fk_2 FOREIGN KEY (prof_id) REFERENCES public.professor(prof_id)
);

CREATE TABLE public.disciplina_conteudo (
	dc_id serial  primary key NOT NULL,
	cd_id integer NOT NULL,
	conte_id integer NOT NULL,
	CONSTRAINT disciplina_conteudo_fk_1 FOREIGN KEY (cd_id) REFERENCES public.curso_disciplina(cd_id),
	CONSTRAINT disciplina_conteudo_fk FOREIGN KEY (conte_id) REFERENCES public.conteudo(conte_id)
);

CREATE TABLE public.conteudo_aplicativo (
	ca_id serial primary key NOT NULL,
	dc_id integer NOT NULL,
	apli_id integer NOT NULL,
	CONSTRAINT conteudo_aplicativo_fk FOREIGN KEY (dc_id) REFERENCES public.disciplina_conteudo(dc_id),
	CONSTRAINT conteudo_aplicativo_fk_1 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id)
);

CREATE TABLE public.aplicativo_favorito (
	af_id serial primary key NOT NULL,
	af_favorito boolean NULL,
	ca_id integer NOT NULL,
	CONSTRAINT aplicativo_favorito_fk FOREIGN KEY (ca_id) REFERENCES public.conteudo_aplicativo(ca_id)
);