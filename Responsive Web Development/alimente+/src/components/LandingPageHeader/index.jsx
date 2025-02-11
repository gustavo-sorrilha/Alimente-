import React from "react";
import { Link } from "react-scroll";

// Assets import
import Logo from "../../assets/Logo.svg";

// Styles import
import { Container } from "./styles";

export function LandingPageHeader() {
  return (
    <Container>
      <img src={Logo} alt="Logo do Alimente+" />

      <ul>
        <li>
          <Link to="introduction" smooth duration={500}>
            Introdução
          </Link>
        </li>
        <li>
          <Link to="challenges" smooth duration={500}>
            Desafios
          </Link>
        </li>
        <li>
          <Link to="innovative-technologies" smooth duration={500}>
            Tecnologias Inovadoras
          </Link>
        </li>
        <li>
          <Link to="food-distribution" smooth duration={500}>
            Distribuição de alimentos
          </Link>
        </li>
        <li>
          <Link to="contact-us" smooth duration={500}>
            Fale conosco
          </Link>
        </li>
      </ul>
    </Container>
  );
}
