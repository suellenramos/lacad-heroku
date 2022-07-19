INSERT INTO public.regras
(reg_id, reg_nome, reg_descricao, reg_tipo)
VALUES(1, 'professor', 'put', 'UPDATE');

INSERT INTO public.regras
(reg_id, reg_nome, reg_descricao, reg_tipo)
VALUES(2, 'professor', 'get', 'SELECT');


INSERT INTO public.perfil (perf_id,perf_nome,perf_descricao,perf_ativo)
	VALUES (1,'ADMINISTRADOR','Controle total',true);
INSERT INTO public.perfil (perf_id,perf_nome,perf_descricao,perf_ativo)
	VALUES (2,'CLIENTE','Limitado',true);


INSERT INTO public.professor
(prof_id, prof_nome, prof_data_hora_cadastro, prof_email, prof_ativo, prof_password)
VALUES (1, 'Suellen', '2021-04-08 00:00:00.000','suellen.barrosramos@gmail.com', true, 'dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w='),
(2, 'Samara', '2021-04-08 00:00:00.000', 'sam@gmail.com', true, 'dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=');


INSERT INTO public.perfil_regras (perf_id,reg_id) VALUES
(1,1),
(1,2),
(2,2);

INSERT INTO public.professor_perfil
(prof_id, perf_id)
VALUES(1, 1), (2, 2);
