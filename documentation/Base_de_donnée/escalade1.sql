--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.23
-- Dumped by pg_dump version 9.2.23
-- Started on 2018-01-02 16:38:15

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2104 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 33430)
-- Name: article; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE article (
    id_art SERIAL PRIMARY KEY,
    date date NOT NULL,
    titre character varying(300) NOT NULL,
    contenu text NOT NULL,
    id_user integer NOT NULL,
    photoa character varying
);


ALTER TABLE public.article OWNER TO postgres;


--
-- TOC entry 171 (class 1259 OID 33438)
-- Name: classement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE classement (
    id_class SERIAL PRIMARY KEY,
    libelle_class text NOT NULL
);


ALTER TABLE public.classement OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 33446)
-- Name: commentaire; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE commentaire (
    id_com SERIAL PRIMARY KEY,
    libelle_com text NOT NULL,
    date_com date NOT NULL,
    id_user integer NOT NULL,
    id_topo integer NOT NULL
);


ALTER TABLE public.commentaire OWNER TO postgres;


--
-- TOC entry 175 (class 1259 OID 33454)
-- Name: cotation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cotation (
    id_cotation SERIAL PRIMARY KEY,
    type_escalade character varying(200),
    libelle_cotation character varying(100) NOT NULL
);


ALTER TABLE public.cotation OWNER TO postgres;



--
-- TOC entry 177 (class 1259 OID 33459)
-- Name: ville; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ville (
    id_ville SERIAL PRIMARY KEY,
    id_pays integer NOT NULL,
    nom_ville character varying(200) NOT NULL,
    cp integer
);


ALTER TABLE public.ville OWNER TO postgres;



--
-- TOC entry 179 (class 1259 OID 33464)
-- Name: exposition; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE exposition (
    id_expo SERIAL PRIMARY KEY,
    libelle_expo text NOT NULL
);


ALTER TABLE public.exposition OWNER TO postgres;


--
-- TOC entry 181 (class 1259 OID 33472)
-- Name: pays; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pays (
    id_pays SERIAL PRIMARY KEY,
    nom_pays character varying(200)
);


ALTER TABLE public.pays OWNER TO postgres;


--
-- TOC entry 183 (class 1259 OID 33477)
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    date_resa date NOT NULL,
    id_topo integer NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.reservation OWNER TO postgres;



--
-- TOC entry 185 (class 1259 OID 33482)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE role (
    id_role SERIAL PRIMARY KEY,
    libelle_role character varying(250) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;


--
-- TOC entry 187 (class 1259 OID 33487)
-- Name: secteur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE secteur (
    id_secteur SERIAL PRIMARY KEY,
    id_site integer NOT NULL,
    nom_secteur character varying(200)
);


ALTER TABLE public.secteur OWNER TO postgres;


--
-- TOC entry 189 (class 1259 OID 33492)
-- Name: site; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE site (
    id_site SERIAL PRIMARY KEY,
    nom_site character varying(150) NOT NULL,
    id_pays integer NOT NULL,
    image character varying(200),
    id_class integer,
	id_ville integer NOT NULL
);


ALTER TABLE public.site OWNER TO postgres;


--
-- TOC entry 191 (class 1259 OID 33497)
-- Name: topo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE topo (
    id_topo SERIAL PRIMARY KEY,
    nom character varying(100),
    description character varying(600) NOT NULL,
    nbr_page integer NOT NULL,
    id_user integer NOT NULL,
    image character varying(250)
);


ALTER TABLE public.topo OWNER TO postgres;


--
-- TOC entry 193 (class 1259 OID 33505)
-- Name: topo_site; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE topo_site (
    id SERIAL PRIMARY KEY,
    id_site integer NOT NULL,
    id_topo integer
);


ALTER TABLE public.topo_site OWNER TO postgres;


--
-- TOC entry 195 (class 1259 OID 33510)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE utilisateur (
    id_user SERIAL PRIMARY KEY,
    nom character varying(200) NOT NULL,
    prenom character varying(200) NOT NULL,
    adresse character varying(300),
    tel integer,
    email character varying(250) NOT NULL,
    passw character varying(100) NOT NULL,
    dateinscription date,
    photo character varying(350),
    id_role integer NOT NULL
);


ALTER TABLE public.utilisateur OWNER TO postgres;



--
-- TOC entry 197 (class 1259 OID 33518)
-- Name: voie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE voie (
    id_voie SERIAL PRIMARY KEY,
    id_secteur integer NOT NULL,
    id_cotation integer NOT NULL,
    nom_voie character varying(200),
    altitude numeric,
    nbr_longueur integer,
    id_expo integer NOT NULL
);


ALTER TABLE public.voie OWNER TO postgres;

--
-- TOC entry 1948 (class 2606 OID 41253)
-- Name: commentaire_id_topo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commentaire
    ADD CONSTRAINT commentaire_id_topo_fkey FOREIGN KEY (id_topo) REFERENCES topo(id_topo) ON DELETE SET NULL;


--
-- TOC entry 1959 (class 2606 OID 33577)
-- Name: cotation_voie_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY voie
    ADD CONSTRAINT cotation_voie_fk FOREIGN KEY (id_cotation) REFERENCES cotation(id_cotation) ON DELETE SET NULL;


--
-- TOC entry 1949 (class 2606 OID 41238)
-- Name: pays_dep_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ville
    ADD CONSTRAINT pays_dep_fk FOREIGN KEY (id_pays) REFERENCES pays(id_pays) ON DELETE SET NULL;


--
-- TOC entry 1958 (class 2606 OID 33587)
-- Name: role_utilisateur; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT role_utilisateur FOREIGN KEY (id_role) REFERENCES role(id_role) ON DELETE SET NULL;


--
-- TOC entry 1960 (class 2606 OID 33592)
-- Name: secteur_voie_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY voie
    ADD CONSTRAINT secteur_voie_fk FOREIGN KEY (id_secteur) REFERENCES secteur(id_secteur) ON DELETE SET NULL;


--
-- TOC entry 1953 (class 2606 OID 33597)
-- Name: site_id_class_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY site
    ADD CONSTRAINT site_id_class_fkey FOREIGN KEY (id_class) REFERENCES classement(id_class) ON DELETE SET NULL;


--
-- TOC entry 1954 (class 2606 OID 33602)
-- Name: site_id_pays_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY site
    ADD CONSTRAINT site_id_pays_fkey FOREIGN KEY (id_pays) REFERENCES pays(id_pays) ON DELETE SET NULL;



ALTER TABLE ONLY site
    ADD CONSTRAINT site_id_ville_fkey FOREIGN KEY (id_ville) REFERENCES ville(id_ville) ON DELETE SET NULL;

	
--
-- TOC entry 1952 (class 2606 OID 33607)
-- Name: site_secteur_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY secteur
    ADD CONSTRAINT site_secteur_fk FOREIGN KEY (id_site) REFERENCES site(id_site) ON DELETE SET NULL;


--
-- TOC entry 1956 (class 2606 OID 33612)
-- Name: site_topo_site_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY topo_site
    ADD CONSTRAINT site_topo_site_fk FOREIGN KEY (id_site) REFERENCES site(id_site) ON DELETE SET NULL;


--
-- TOC entry 1950 (class 2606 OID 33617)
-- Name: topo_reservation_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation
    ADD CONSTRAINT topo_reservation_fk FOREIGN KEY (id_topo) REFERENCES topo(id_topo) ON DELETE SET NULL;


--
-- TOC entry 1957 (class 2606 OID 33622)
-- Name: topo_site_id_topo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY topo_site
    ADD CONSTRAINT topo_site_id_topo_fkey FOREIGN KEY (id_topo) REFERENCES topo(id_topo) ON DELETE SET NULL;


--
-- TOC entry 1946 (class 2606 OID 33627)
-- Name: utilisateur_article_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY article
    ADD CONSTRAINT utilisateur_article_fk FOREIGN KEY (id_user) REFERENCES utilisateur(id_user) ON DELETE SET NULL;


--
-- TOC entry 1947 (class 2606 OID 41248)
-- Name: utilisateur_commentaire_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commentaire
    ADD CONSTRAINT utilisateur_commentaire_fk FOREIGN KEY (id_user) REFERENCES utilisateur(id_user) ON DELETE SET NULL;


--
-- TOC entry 1951 (class 2606 OID 33637)
-- Name: utilisateur_reservation_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation
    ADD CONSTRAINT utilisateur_reservation_fk FOREIGN KEY (id_user) REFERENCES utilisateur(id_user) ON DELETE SET NULL;


--
-- TOC entry 1955 (class 2606 OID 33642)
-- Name: utilisateur_topo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY topo
    ADD CONSTRAINT utilisateur_topo_fk FOREIGN KEY (id_user) REFERENCES utilisateur(id_user) ON DELETE SET NULL;


--
-- TOC entry 2103 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-01-02 16:38:15

--
-- PostgreSQL database dump complete
--















