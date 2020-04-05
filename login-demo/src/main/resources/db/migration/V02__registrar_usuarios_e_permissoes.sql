

INSERT INTO usuario (id_usuario, nome, email, senha) values (1, 'David Guimaraes', 'david.ce@live.com', '$2a$10$KmMV43STfA8H.MYai9Q3curQbzUV286Mw.F3X.CmKaaYs3VyefjCa');
INSERT INTO usuario (id_usuario, nome, email, senha) values (2, 'Amanda Verao', 'amandaverrr@hotmail.com', '$2a$10$KmMV43STfA8H.MYai9Q3curQbzUV286Mw.F3X.CmKaaYs3VyefjCa');

INSERT INTO permissao (id_permissao, descricao) values (1, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (id_permissao, descricao) values (2, 'ROLE_REMOVER_USUARIO');
INSERT INTO permissao (id_permissao, descricao) values (3, 'ROLE_PESQUISAR_USUARIO');
INSERT INTO permissao (id_permissao, descricao) values (4, 'ROLE_EDITAR_USUARIO');

INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);

INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 3);