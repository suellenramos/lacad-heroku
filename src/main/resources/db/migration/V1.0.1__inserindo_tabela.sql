CREATE TABLE public.professor_aplicativo(
	prof_id integer not null,
	apli_id integer NOT NULL
);

ALTER TABLE public.professor_aplicativo ADD CONSTRAINT professor_aplicativo_fk0 FOREIGN KEY (prof_id) REFERENCES public.professor(prof_id);
ALTER TABLE public.professor_aplicativo ADD CONSTRAINT professor_aplicativo_fk1 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id);