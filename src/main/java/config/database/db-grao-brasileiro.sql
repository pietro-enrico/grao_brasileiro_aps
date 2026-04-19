CREATE TABLE IF NOT EXISTS "User" (
                                      "id_user" INTEGER NOT NULL UNIQUE,
                                      "full_name" VARCHAR NOT NULL,
                                      "email" VARCHAR NOT NULL UNIQUE,
                                      "cpf" VARCHAR NOT NULL UNIQUE,
                                      "password" VARCHAR NOT NULL,
                                      "createdAt" TIMESTAMP NOT NULL,
                                      "isVoluntary" BOOLEAN DEFAULT 0,
                                      "fk_id_company" VARCHAR,
                                      PRIMARY KEY("id_user"),
                                      FOREIGN KEY ("fk_id_company") REFERENCES "CompanyOng"("cnpj")
                                          ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS "Donate" (
                                        "id_donate" INTEGER NOT NULL UNIQUE,
                                        "category" VARCHAR NOT NULL,
                                        "sub_category" VARCHAR,
                                        "quantity" REAL,
                                        "unity" VARCHAR,
                                        "value_payment" REAL,
                                        "method_value_payment" VARCHAR,
                                        "donation_was_sent" BOOLEAN DEFAULT 0,
                                        "date_donation" TIMESTAMP NOT NULL,
                                        "fk_id_user" INTEGER NOT NULL,
                                        PRIMARY KEY("id_donate"),
                                        FOREIGN KEY ("fk_id_user") REFERENCES "User"("id_user")
                                            ON UPDATE NO ACTION ON DELETE NO ACTION,
                                        FOREIGN KEY ("id_donate") REFERENCES "Address"("fk_id_donate")
                                            ON UPDATE NO ACTION ON DELETE NO ACTION
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
                                         "fk_id_company" VARCHAR NOT NULL,
                                         "fk_id_donate" INTEGER,
                                         PRIMARY KEY("id_address"),
                                         FOREIGN KEY ("fk_id_company") REFERENCES "CompanyOng"("cnpj")
                                             ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO "CompanyOng" VALUES ('73.884.979/0001-81', 'Grão Brasileiro');