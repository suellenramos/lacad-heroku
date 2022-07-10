CREATE TABLE public.imagem (
	im_id bigserial primary key NOT NULL,
	cur_id integer,
	disc_id integer,
	apli_id integer,
	im_data date,
	im_bucket varchar(50),
    im_hash varchar(50),
	CONSTRAINT imagem_fk FOREIGN KEY (cur_id) REFERENCES public.curso(cur_id),
	CONSTRAINT imagem_fk_1 FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id),
	CONSTRAINT imagem_fk_2 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id)
);