INSERT INTO permissao values (1, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao values (2, 'ROLE_CONSULTAR_USUARIO');
INSERT INTO permissao values (3, 'ROLE_ALTERAR_USUARIO');
INSERT INTO permissao values (4, 'ROLE_EXCLUIR_USUARIO');

INSERT INTO permissao values (5, 'ROLE_CADASTRAR_FUNCIONARIO');
INSERT INTO permissao values (6, 'ROLE_CONSULTAR_FUNCIONARIO');
INSERT INTO permissao values (7, 'ROLE_ALTERAR_FUNCIONARIO');
INSERT INTO permissao values (8, 'ROLE_EXCLUIR_FUNCIONARIO');

INSERT INTO permissao values (9, 'ROLE_CADASTRAR_CLIENTE');
INSERT INTO permissao values (10, 'ROLE_CONSULTAR_CLIENTE');
INSERT INTO permissao values (11, 'ROLE_ALTERAR_CLIENTE');
INSERT INTO permissao values (12, 'ROLE_EXCLUIR_CLIENTE');

INSERT INTO permissao values (13, 'ROLE_CADASTRAR_FORNECEDOR');
INSERT INTO permissao values (14, 'ROLE_CONSULTAR_FORNECEDOR');
INSERT INTO permissao values (15, 'ROLE_ALTERAR_FORNECEDOR');
INSERT INTO permissao values (16, 'ROLE_EXCLUIR_FORNECEDOR');

INSERT INTO permissao values (17, 'ROLE_CADASTRAR_OS');
INSERT INTO permissao values (18, 'ROLE_CONSULTAR_OS');
INSERT INTO permissao values (19, 'ROLE_ALTERAR_OS');
INSERT INTO permissao values (20, 'ROLE_EXCLUIR_OS');

INSERT INTO permissao values (21, 'ROLE_CADASTRAR_SERVICO');
INSERT INTO permissao values (22, 'ROLE_CONSULTAR_SERVICO');
INSERT INTO permissao values (23, 'ROLE_ALTERAR_SERVICO');
INSERT INTO permissao values (24, 'ROLE_EXCLUIR_SERVICO');

INSERT INTO permissao values (25, 'ROLE_CADASTRAR_PRODUTO');
INSERT INTO permissao values (26, 'ROLE_CONSULTAR_PRODUTO');
INSERT INTO permissao values (27, 'ROLE_ALTERAR_PRODUTO');
INSERT INTO permissao values (28, 'ROLE_EXCLUIR_PRODUTO');


/*Permissões para Administrador */
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(1, 1);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(1, 2);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(1, 3);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(1, 4);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(1, 5);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(1, 6);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(1, 7);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(1, 8);

/*Permissões para Gerente */
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 6);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 9);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 10);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 11);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 12);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 13);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 14);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 15);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 16);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 17);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 18);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 19);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 20);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 21);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 22);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 23);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 24);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 25);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 26);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 27);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(2, 28);

/*Permissões para Comum */
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(3, 10);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(3, 14);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(3, 17);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(3, 18);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(3, 19);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(3, 22);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES(3, 26);

/*Relacionando permissões usuario ROOT */
INSERT INTO grupo_usuario (id_usuario, id_grupo) VALUES ((SELECT id FROM usuario WHERE login ='root'), 1);
INSERT INTO grupo_usuario (id_usuario, id_grupo) VALUES ((SELECT id FROM usuario WHERE login ='root'), 2);
INSERT INTO grupo_usuario (id_usuario, id_grupo) VALUES ((SELECT id FROM usuario WHERE login ='root'), 3);






























