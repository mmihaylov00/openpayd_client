CREATE SEQUENCE uuid START WITH 1 INCREMENT BY 1;

CREATE TABLE user_model
(
    id         UUID         NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    holder_id  VARCHAR(255) NOT NULL,
    account_id VARCHAR(255) NOT NULL,
    balance    DECIMAL      NOT NULL,
    CONSTRAINT pk_usermodel PRIMARY KEY (id)
);

ALTER TABLE user_model
    ADD CONSTRAINT uc_usermodel_email UNIQUE (email);