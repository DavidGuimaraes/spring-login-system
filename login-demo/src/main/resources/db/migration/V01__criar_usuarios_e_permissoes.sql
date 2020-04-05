CREATE SEQUENCE hibernate_sequence START 1;


CREATE SEQUENCE public.permissao_id_permissao_seq;

CREATE TABLE public.permissao (
                id_permissao BIGINT NOT NULL DEFAULT nextval('public.permissao_id_permissao_seq'),
                descricao VARCHAR(50) NOT NULL,
                CONSTRAINT permissao_pk PRIMARY KEY (id_permissao)
);


ALTER SEQUENCE public.permissao_id_permissao_seq OWNED BY public.permissao.id_permissao;

CREATE SEQUENCE public.usuario_id_usuario_seq;

CREATE TABLE public.usuario (
                id_usuario BIGINT NOT NULL DEFAULT nextval('public.usuario_id_usuario_seq'),
                nome VARCHAR(50) NOT NULL,
                email VARCHAR(50) NOT NULL,
                senha VARCHAR(100) NOT NULL,
                CONSTRAINT usuario_pk PRIMARY KEY (id_usuario)
);


ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;

CREATE TABLE public.usuario_permissao (
                id_usuario BIGINT NOT NULL,
                id_permissao BIGINT NOT NULL,
                CONSTRAINT usuario_permissao_pk PRIMARY KEY (id_usuario, id_permissao)
);


ALTER TABLE public.usuario_permissao ADD CONSTRAINT permissao_usuario_permissao_fk
FOREIGN KEY (id_permissao)
REFERENCES public.permissao (id_permissao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.usuario_permissao ADD CONSTRAINT usuario_usuario_permissao_fk
FOREIGN KEY (id_usuario)
REFERENCES public.usuario (id_usuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;