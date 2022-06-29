ALTER TABLE public.curso_disciplina ADD cur_id integer NULL;
ALTER TABLE public.curso_disciplina ADD disc_id integer NULL;
ALTER TABLE public.curso_disciplina ADD CONSTRAINT curso_disciplina_fk FOREIGN KEY (cur_id) REFERENCES public.curso(cur_id);
ALTER TABLE public.curso_disciplina ADD CONSTRAINT curso_disciplina_fk_1 FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id);

ALTER TABLE public.disciplina_conteudo ADD disc_id integer NULL;
ALTER TABLE public.disciplina_conteudo ADD conte_id integer NULL;
ALTER TABLE public.disciplina_conteudo ADD CONSTRAINT disciplina_conteudo_fk FOREIGN KEY (disc_id) REFERENCES public.disciplina(disc_id);
ALTER TABLE public.disciplina_conteudo ADD CONSTRAINT disciplina_conteudo_fk_1 FOREIGN KEY (conte_id) REFERENCES public.conteudo(conte_id);

ALTER TABLE public.conteudo_aplicativo ADD conte_id integer NULL;
ALTER TABLE public.conteudo_aplicativo ADD apli_id integer NULL;
ALTER TABLE public.conteudo_aplicativo ADD CONSTRAINT conteudo_aplicativo_fk FOREIGN KEY (conte_id) REFERENCES public.conteudo(conte_id);
ALTER TABLE public.conteudo_aplicativo ADD CONSTRAINT conteudo_aplicativo_fk_1 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id);

ALTER TABLE public.professor_aplicativo ADD prof_id integer NULL;
ALTER TABLE public.professor_aplicativo ADD apli_id integer NULL;
ALTER TABLE public.professor_aplicativo ADD CONSTRAINT professor_aplicativo_fk FOREIGN KEY (prof_id) REFERENCES public.professor(prof_id);
ALTER TABLE public.professor_aplicativo ADD CONSTRAINT professor_aplicativo_fk_1 FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id);