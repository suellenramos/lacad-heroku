CREATE TABLE public.imagem (
	im_id bigserial primary key NOT NULL,
    apli_id integer,
	im_data date,
	im_bucket varchar(50),
    im_hash varchar(50),
	CONSTRAINT imagem_fk_2 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id)
);