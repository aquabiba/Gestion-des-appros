--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

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
-- Name: fiche_stock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fiche_stock (
    id_f integer NOT NULL,
    numero_fiche integer,
    emplacement character varying(50),
    "position" character varying(50)
);


ALTER TABLE public.fiche_stock OWNER TO postgres;

--
-- Name: fiche_stock_id_f_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fiche_stock_id_f_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fiche_stock_id_f_seq OWNER TO postgres;

--
-- Name: fiche_stock_id_f_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fiche_stock_id_f_seq OWNED BY public.fiche_stock.id_f;


--
-- Name: fichiste; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fichiste (
    id_fichiste integer NOT NULL,
    nom character varying(50),
    prenom character varying(50),
    grade character varying(50),
    mdp character varying(50)
);


ALTER TABLE public.fichiste OWNER TO postgres;

--
-- Name: fichiste_id_fichiste_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fichiste_id_fichiste_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fichiste_id_fichiste_seq OWNER TO postgres;

--
-- Name: fichiste_id_fichiste_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fichiste_id_fichiste_seq OWNED BY public.fichiste.id_fichiste;


--
-- Name: mouvement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mouvement (
    id_m integer NOT NULL,
    n_folio character varying(50),
    type_m character varying(50),
    quantite_m integer,
    nomenclature character varying(100) DEFAULT 'DELETED'::character varying,
    date_mouvement date
);


ALTER TABLE public.mouvement OWNER TO postgres;

--
-- Name: mouvement_id_m_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mouvement_id_m_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.mouvement_id_m_seq OWNER TO postgres;

--
-- Name: mouvement_id_m_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.mouvement_id_m_seq OWNED BY public.mouvement.id_m;


--
-- Name: pdr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pdr (
    nomenclature character varying(100) NOT NULL,
    designation character varying(100),
    codebarre character varying(100),
    photo_url character varying(255),
    qte_stock integer,
    prix_unitaire double precision,
    "position" character varying(15),
    typevehicule character varying(50),
    groupe character varying(50),
    isdeleted boolean DEFAULT false
);


ALTER TABLE public.pdr OWNER TO postgres;

--
-- Name: pdr_historique; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pdr_historique (
    id_h integer NOT NULL,
    nomenclature character varying(50),
    designation character varying(100),
    qte_stock integer,
    type character varying(50),
    date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    utilisateur character varying(50)
);


ALTER TABLE public.pdr_historique OWNER TO postgres;

--
-- Name: pdr_historique_id_h_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pdr_historique_id_h_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pdr_historique_id_h_seq OWNER TO postgres;

--
-- Name: pdr_historique_id_h_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pdr_historique_id_h_seq OWNED BY public.pdr_historique.id_h;


--
-- Name: fiche_stock id_f; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fiche_stock ALTER COLUMN id_f SET DEFAULT nextval('public.fiche_stock_id_f_seq'::regclass);


--
-- Name: fichiste id_fichiste; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fichiste ALTER COLUMN id_fichiste SET DEFAULT nextval('public.fichiste_id_fichiste_seq'::regclass);


--
-- Name: mouvement id_m; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mouvement ALTER COLUMN id_m SET DEFAULT nextval('public.mouvement_id_m_seq'::regclass);


--
-- Name: pdr_historique id_h; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pdr_historique ALTER COLUMN id_h SET DEFAULT nextval('public.pdr_historique_id_h_seq'::regclass);


--
-- Data for Name: fiche_stock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fiche_stock (id_f, numero_fiche, emplacement, "position") FROM stdin;
\.


--
-- Data for Name: fichiste; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fichiste (id_fichiste, nom, prenom, grade, mdp) FROM stdin;
1	boualam	younesse	capitaine	123
2	lakouti	anass	lieutenant	3456
3	hatout	mehdi	capitaine	1234
\.


--
-- Data for Name: mouvement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mouvement (id_m, n_folio, type_m, quantite_m, nomenclature, date_mouvement) FROM stdin;
3	22233333	Entrée	4	2940005806283	2024-09-09
4	22233334	Sortie	1	2940005806283	2024-09-09
15	102344	Entrée	22	123456789	2024-09-06
16	102345	Sortie	20	123456789	2024-09-06
2	45555	Sortie	4	AAAA	2024-09-09
\.


--
-- Data for Name: pdr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pdr (nomenclature, designation, codebarre, photo_url, qte_stock, prix_unitaire, "position", typevehicule, groupe, isdeleted) FROM stdin;
6620001792548	THERMOSTAT	6620001792548	file:/C:/Users/YOUNESSE/Desktop/photoQppro/THER;OSTAT.JPG	35	0	\N	\N	\N	f
4720007413492	FLEXIBLE DE FREIN	4720007413492	file:/C:/Users/YOUNESSE/Desktop/photoQppro/FLEXIBLE%20FREIN.JPG	106	77	\N	\N	\N	f
3110007883619	ROULEMENT	3110007883619	file:/C:/Users/YOUNESSE/Desktop/photoQppro/ROULEMENT.JPG	72	300	\N	\N	\N	f
2910009070673	POUSSOIR INJECTION CARBURANT	2910009070673	file:/C:/Users/YOUNESSE/Desktop/photoQppro/POUSSOIR%20INJ%20CARBURANT.JPG	120	150	\N	\N	\N	f
2540007975609	POIGNET PORTE INT	2540007975609	file:/C:/Users/YOUNESSE/Desktop/photoQppro/IMG_5969.JPG	100	120	\N	\N	\N	f
2920002371211	PORTE CHARBON  	2920002371211	file:/C:/Users/YOUNESSE/Desktop/photoQppro/PORTE%20CHARBON.JPG	107	90	\N	\N	\N	f
3020008488405	POLIE ALTERNATEUR	3020008488405	file:/C:/Users/YOUNESSE/Desktop/photoQppro/POULIE%20ALTERNAT.JPG	20	350	\N	\N	\N	f
2940005806283	FILTRE A HUILE	2940005806283	file:/C:/Users/YOUNESSE/Desktop/photoQppro/FILTRE%20A%20HUILE.JPG	7	66	\N	\N	\N	f
10	MOTEUR	10	file:/C:/Users/YOUNESSE/Downloads/love.png	10	10	5	TOYOTA GRJ 79	CONSOMMABLE	f
MMMM	maitre cylindre mehdi	MMMM	file:/C:/Users/YOUNESSE/Downloads/love.png	10	1000	5	KAYSER M800	TRANSMISSION	f
123456789	FILTRE RR	123456789	file:/C:/Users/YOUNESSE/Desktop/photoQppro/FILTRE%20A%20HUILE.JPG	102	100	3	KAYSER M800	CONSOMMABLE/V	f
AAAAA	anass	AAAAA	file:/C:/Users/YOUNESSE/Downloads/love_PNG43.png	21	111	2	CONTINETAL M35	FREINAGE	t
ZZZZ	arbre a came	ZZZZ	file:/C:/Users/YOUNESSE/Downloads/love_PNG43.png	2	1223	3	TOYOTA GRJ 79	MOTEUR	t
VVVV	AYOUB BARAHAL	VVVV	file:/C:/Users/YOUNESSE/Downloads/love_PNG43.png	23	90	4	TATA	CONSOMMABLE	t
20	BATTERIE	29	file:/C:/Users/YOUNESSE/Downloads/29.png	29	29	4	TATA	CONSOMMABLE	f
AAAA	JKBBKL	AAAA	file:/C:/Users/YOUNESSE/Desktop/APPLICATION-Appro/APPRO/src/main/resources/fxml/FAR1.png	10	100	1	KAYZER M 900	FREINAGE	t
\.


--
-- Data for Name: pdr_historique; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pdr_historique (id_h, nomenclature, designation, qte_stock, type, date, utilisateur) FROM stdin;
1	20	BATTERIE	29	Modification	2024-09-10 00:39:43.705699	capitaine (boualam younesse)
2	AAAA	JKBBKL	10	supression	2024-09-10 00:39:59.227933	capitaine (boualam younesse)
\.


--
-- Name: fiche_stock_id_f_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fiche_stock_id_f_seq', 1, false);


--
-- Name: fichiste_id_fichiste_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fichiste_id_fichiste_seq', 3, true);


--
-- Name: mouvement_id_m_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mouvement_id_m_seq', 4, true);


--
-- Name: pdr_historique_id_h_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pdr_historique_id_h_seq', 2, true);


--
-- Name: fiche_stock fiche_stock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fiche_stock
    ADD CONSTRAINT fiche_stock_pkey PRIMARY KEY (id_f);


--
-- Name: fichiste fichiste_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fichiste
    ADD CONSTRAINT fichiste_pkey PRIMARY KEY (id_fichiste);


--
-- Name: mouvement mouvement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mouvement
    ADD CONSTRAINT mouvement_pkey PRIMARY KEY (id_m);


--
-- Name: pdr_historique pdr_historique_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pdr_historique
    ADD CONSTRAINT pdr_historique_pkey PRIMARY KEY (id_h);


--
-- Name: pdr pdr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pdr
    ADD CONSTRAINT pdr_pkey PRIMARY KEY (nomenclature);


--
-- Name: mouvement fk_nomenclature; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mouvement
    ADD CONSTRAINT fk_nomenclature FOREIGN KEY (nomenclature) REFERENCES public.pdr(nomenclature) ON DELETE SET DEFAULT;


--
-- Name: TABLE pdr; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.pdr TO younesse;


--
-- PostgreSQL database dump complete
--

