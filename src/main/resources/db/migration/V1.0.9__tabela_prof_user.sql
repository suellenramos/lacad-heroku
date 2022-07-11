ALTER TABLE public.professor ADD user_id integer NULL;
ALTER TABLE public.professor ADD CONSTRAINT professor_fk FOREIGN KEY (user_id) REFERENCES public.usuario(user_id);
