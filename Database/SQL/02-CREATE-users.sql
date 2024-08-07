CREATE TABLE public.users
(
    id serial NOT NULL,
    username character varying(128) NOT NULL,
    password character varying(128) NOT NULL,
    email character varying(128) NOT NULL,
    role character varying(16) NOT NULL,
    last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);