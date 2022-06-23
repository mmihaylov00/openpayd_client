CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE uuid START WITH 1 INCREMENT BY 1;

CREATE TABLE transaction_model
(
    id             BIGINT       NOT NULL,
    transaction_id VARCHAR(255) NOT NULL,
    amount         DECIMAL      NOT NULL,
    user_id        UUID         NOT NULL,
    status         VARCHAR(255),
    CONSTRAINT pk_transactionmodel PRIMARY KEY (id)
);

CREATE TABLE user_model
(
    id         UUID         NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    holder_id  VARCHAR(255),
    account_id VARCHAR(255),
    balance    DECIMAL      NOT NULL,
    CONSTRAINT pk_usermodel PRIMARY KEY (id)
);

ALTER TABLE transaction_model
    ADD CONSTRAINT uc_transactionmodel_transactionid UNIQUE (transaction_id);

ALTER TABLE user_model
    ADD CONSTRAINT uc_usermodel_email UNIQUE (email);

ALTER TABLE transaction_model
    ADD CONSTRAINT FK_TRANSACTIONMODEL_ON_USER FOREIGN KEY (user_id) REFERENCES user_model (id);