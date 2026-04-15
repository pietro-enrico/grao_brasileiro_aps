CREATE TABLE IF NOT EXISTS "User" (
	"id_user" INTEGER NOT NULL UNIQUE,
	"full_name" VARCHAR NOT NULL,
	"email" VARCHAR NOT NULL UNIQUE,
	"cpf" VARCHAR NOT NULL UNIQUE,
	"password" VARCHAR NOT NULL,
	"createdAt" TIMESTAMP NOT NULL,
	"isVoluntary" BOOLEAN DEFAULT 0,
	PRIMARY KEY("id_user")
);

CREATE TABLE IF NOT EXISTS "Donate" (
	"id_donate" INTEGER NOT NULL UNIQUE,
	"category" VARCHAR NOT NULL,
	"sub_category" VARCHAR,
	"quantity" INTEGER,
	"value_payment" REAL,
	"method_value_payment" VARCHAR,
	PRIMARY KEY("id_donate")
);

CREATE TABLE IF NOT EXISTS "Storage" (
	"id_storage" INTEGER NOT NULL UNIQUE,
	"volume_kg" REAL NOT NULL DEFAULT 0,
	"volume_li" REAL NOT NULL DEFAULT 0,
	"data_kg" TIMESTAMP NOT NULL,
	"data_li" TIMESTAMP NOT NULL,
	PRIMARY KEY("id_storage")
);

CREATE TABLE IF NOT EXISTS "CompanyOng" (
	"cnpj" VARCHAR NOT NULL UNIQUE,
	"name" VARCHAR NOT NULL,
	PRIMARY KEY("cnpj")
);

CREATE TABLE IF NOT EXISTS "Address" (
	"id_address" INTEGER NOT NULL UNIQUE,
	"street" VARCHAR NOT NULL,
	"number" INTEGER NOT NULL,
	"bairro" VARCHAR NOT NULL,
	"cep" INTEGER NOT NULL,
	"cidade" VARCHAR NOT NULL DEFAULT 'São Paulo',
	"estado" VARCHAR NOT NULL DEFAULT 'SP',
	PRIMARY KEY("id_address")
);
