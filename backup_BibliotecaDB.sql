--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autor (
    id integer NOT NULL,
    apellido character varying(255),
    email character varying(255),
    nombre character varying(255)
);


ALTER TABLE public.autor OWNER TO postgres;

--
-- Name: autor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.autor_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.autor_seq OWNER TO postgres;

--
-- Name: editorial; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.editorial (
    id integer NOT NULL,
    nombre character varying(255)
);


ALTER TABLE public.editorial OWNER TO postgres;

--
-- Name: editorial_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.editorial_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.editorial_seq OWNER TO postgres;

--
-- Name: libro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.libro (
    id integer NOT NULL,
    publicacion date,
    titulo character varying(255),
    autor_id integer,
    editorial_id integer,
    nombre_autor character varying(255)
);


ALTER TABLE public.libro OWNER TO postgres;

--
-- Name: libro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.libro_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.libro_seq OWNER TO postgres;

--
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.autor (id, apellido, email, nombre) FROM stdin;
1	Cortazar	jcor@gmail.com	Julio
2		hoe@hotmail.com	Homero
3	Austen	jana@gmail.com	Jane
4	Neruda	paneruda@gmail.com	Pablo
5	Harper	leha@hotmail.com	Lee
6	Dostoievski	fedor@hotmail.com	Fedor
7	Marquez	ggmarquez@gmail.com	Gabriel Garcia
402	Prueba	prueba@gmail.com	Prueba
\.


--
-- Data for Name: editorial; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.editorial (id, nombre) FROM stdin;
2	Sudamericana
52	Alba Editoria
53	J.B. Lippincott & Co.
54	Océano
55	Austral
56	Club Internacional del Libro
57	Gran Travesía
203	Prueba
252	Alameo
\.


--
-- Data for Name: libro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.libro (id, publicacion, titulo, autor_id, editorial_id, nombre_autor) FROM stdin;
252	1963-06-28	Rayuela	1	2	\N
302	1966-02-12	La Odisea	2	56	\N
303	1813-01-28	Orgullo y Prejucio	3	55	\N
304	1950-04-30	Canto General	4	54	\N
305	1960-07-11	Matar a un Ruiseñor	5	53	\N
309	1866-09-25	Crimen y Castigo	6	52	\N
853	2023-09-03	Prueba	402	203	\N
308	2023-09-29	100 Años de Soledad	7	252	\N
\.


--
-- Name: autor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.autor_seq', 451, true);


--
-- Name: editorial_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.editorial_seq', 301, true);


--
-- Name: libro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.libro_seq', 901, true);


--
-- Name: autor autor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (id);


--
-- Name: editorial editorial_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editorial
    ADD CONSTRAINT editorial_pkey PRIMARY KEY (id);


--
-- Name: libro libro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_pkey PRIMARY KEY (id);


--
-- Name: libro uk_ho9uhbna03g968c8qn2nngynn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT uk_ho9uhbna03g968c8qn2nngynn UNIQUE (editorial_id);


--
-- Name: libro fk79q7g2604hcmfdxw6ek3jt4el; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT fk79q7g2604hcmfdxw6ek3jt4el FOREIGN KEY (editorial_id) REFERENCES public.editorial(id);


--
-- Name: libro fke1ss87ymon6qj17bhr6jfh0c4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT fke1ss87ymon6qj17bhr6jfh0c4 FOREIGN KEY (autor_id) REFERENCES public.autor(id);


--
-- PostgreSQL database dump complete
--

