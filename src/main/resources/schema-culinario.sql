CREATE TABLE IF NOT EXISTS receita (
    id UUID PRIMARY KEY,
    dtype VARCHAR(31) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    modo_preparo TEXT,
    categoria VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS ingrediente (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    quantidade VARCHAR(100),
    receita_id UUID,
    
    CONSTRAINT fk_receita FOREIGN KEY (receita_id) REFERENCES receita(id) ON DELETE CASCADE
);