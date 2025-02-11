// Assets import
import Logo from "../../assets/Logo.svg";

// Styles import
import { Container } from "./styles";

export function LandingPageFooter() {
  return (
    <Container>
      <div>
        <strong>Membros da equipe</strong>

        <div id="members">
          <p>Vitor Rubim</p>
          <p>Gustavo Sorrilha</p>
          <p>Natan Cruz</p>
          <p>Felipe Merlo</p>
        </div>
      </div>

      <img src={Logo} alt="Logo do Alimente+" />
    </Container>
  );
}
