ALTER TABLE public.imagem ADD apli_id integer NULL;
ALTER TABLE public.imagem ADD CONSTRAINT imagem_fk FOREIGN KEY (apli_id) REFERENCES public.aplicativo(apli_id);
