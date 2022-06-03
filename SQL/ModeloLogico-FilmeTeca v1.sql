/* ModeloLogico-FilmeTeca v1: */

CREATE TABLE Filme (
    id bigserial PRIMARY KEY,
    nome varchar(512),
    dataLancamento timestamp
);

CREATE TABLE Usuario (
    data_inscricao timestamp,
    email varchar(512),
    senha varchar(50),
    nome varchar(30),
    id serial PRIMARY KEY
);

CREATE TABLE Genero (
    id serial PRIMARY KEY,
    nome varchar(30)
);

CREATE TABLE Favorito (
    fk_Usuario_id serial,
    fk_Filme_id bigserial,
    data timestamp,
    PRIMARY KEY (fk_Usuario_id, fk_Filme_id)
);

CREATE TABLE Interesse (
    fk_Usuario_id serial,
    fk_Filme_id bigserial,
    data timestamp,
    PRIMARY KEY (fk_Usuario_id, fk_Filme_id)
);

CREATE TABLE Comentario (
    fk_Filme_id bigserial,
    fk_Usuario_id serial,
    texto varchar(512),
    data timestamp,
    id bigserial PRIMARY KEY
);

CREATE TABLE Avaliacao (
    fk_Filme_id bigserial,
    fk_Usuario_id serial,
    valor bigint,
    data timestamp,
    PRIMARY KEY (fk_Filme_id, fk_Usuario_id)
);

CREATE TABLE GeneroFilme (
    fk_Filme_id bigserial,
    fk_Genero_id serial,
    PRIMARY KEY (fk_Genero_id, fk_Filme_id)
);
 
ALTER TABLE Favorito ADD CONSTRAINT FK_Favorito_1
    FOREIGN KEY (fk_Usuario_id)
    REFERENCES Usuario (id)
    ON DELETE SET NULL;
 
ALTER TABLE Favorito ADD CONSTRAINT FK_Favorito_2
    FOREIGN KEY (fk_Filme_id)
    REFERENCES Filme (id)
    ON DELETE SET NULL;
 
ALTER TABLE Interesse ADD CONSTRAINT FK_Interesse_1
    FOREIGN KEY (fk_Usuario_id)
    REFERENCES Usuario (id)
    ON DELETE SET NULL;
 
ALTER TABLE Interesse ADD CONSTRAINT FK_Interesse_2
    FOREIGN KEY (fk_Filme_id)
    REFERENCES Filme (id)
    ON DELETE SET NULL;
 
ALTER TABLE Comentario ADD CONSTRAINT FK_Comentario_1
    FOREIGN KEY (fk_Filme_id)
    REFERENCES Filme (id)
    ON DELETE SET NULL;
 
ALTER TABLE Comentario ADD CONSTRAINT FK_Comentario_2
    FOREIGN KEY (fk_Usuario_id)
    REFERENCES Usuario (id)
    ON DELETE SET NULL;
 
ALTER TABLE Avaliacao ADD CONSTRAINT FK_Avaliacao_1
    FOREIGN KEY (fk_Filme_id)
    REFERENCES Filme (id)
    ON DELETE SET NULL;
 
ALTER TABLE Avaliacao ADD CONSTRAINT FK_Avaliacao_2
    FOREIGN KEY (fk_Usuario_id)
    REFERENCES Usuario (id)
    ON DELETE SET NULL;
 
ALTER TABLE GeneroFilme ADD CONSTRAINT FK_GeneroFilme_1
    FOREIGN KEY (fk_Filme_id)
    REFERENCES Filme (id)
    ON DELETE RESTRICT;
 
ALTER TABLE GeneroFilme ADD CONSTRAINT FK_GeneroFilme_2
    FOREIGN KEY (fk_Genero_id)
    REFERENCES Genero (id)
    ON DELETE SET NULL;