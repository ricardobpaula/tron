CREATE TABLE fornecedor(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cnpj VARCHAR(18) NOT NULL ,
	nome VARCHAR(50) NOT NULL,	
	razao_social VARCHAR(50) NOT NULL,
    telefone VARCHAR(15) ,
    celular VARCHAR(15),
    email VARCHAR(30) NOT NULL,
    descricao TEXT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE produto(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(50) NOT NULL ,
    nome VARCHAR(80) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    lucro DECIMAL(10, 2) NOT NULL,
    qnt_estoque INTEGER,
    id_fornecedor BIGINT(20) NOT NULL,
     descricao TEXT NOT NULL,
     preco_venda DECIMAL(10, 2),
	FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
 
CREATE TABLE cliente(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_fantasia VARCHAR(80) NOT NULL,
	razao_social VARCHAR(80) NOT NULL,
    descricao TEXT NOT NULL,
	cnpj VARCHAR(20) NOT NULL ,
    telefone VARCHAR(15) ,
    celular VARCHAR(15) ,
    email VARCHAR(30) NOT NULL,
    endereço VARCHAR(50) NOT NULL
    )ENGINE=InnoDB DEFAULT CHARSET=utf8; 
    
CREATE TABLE servico(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    descricao TEXT NOT NULL
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE funcionario(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    rg VARCHAR(12) NOT NULL ,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(15),
    celular VARCHAR(15) ,
    email VARCHAR(30) NOT NULL,
    descricao TEXT NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE os(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    modelo VARCHAR(25) NOT NULL,
    placa VARCHAR(7) NOT NULL,
    km INTEGER NOT NULL,
    km_carro INTEGER NOT NULL,
    valor_km DECIMAL(10, 2) NOT NULL, 
    id_cliente BIGINT(20) NOT NULL,
    descricao TEXT NOT NULL,
    FOREIGN KEY (id_cliente) references cliente(id)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE os_servico(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_servico BIGINT(20) NOT NULL,
    id_os BIGINT(20) NOT NULL,
    quantidade INTEGER NOT NULL,
    FOREIGN KEY (id_servico) references servico(id),
    FOREIGN KEY (id_os) references os(id)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE os_produto(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_produto BIGINT(20) NOT NULL,
     id_os BIGINT(20) NOT NULL,
    quantidade INTEGER NOT NULL,
    FOREIGN KEY (id_produto) references produto(id),
    FOREIGN KEY (id_os) references os(id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
   CREATE TABLE os_funcionario(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_funcionario BIGINT(20) NOT NULL,
     id_os BIGINT(20) NOT NULL,
    FOREIGN KEY (id_funcionario) references funcionario(id),
    FOREIGN KEY (id_os) references os(id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
    