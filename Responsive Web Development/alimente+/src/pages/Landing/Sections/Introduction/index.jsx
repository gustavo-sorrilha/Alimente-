import { useNavigate } from "react-router-dom";

// Assets import
import FoodAndPeopleImage from "../../../../assets/food-and-people.jpg";

// Styles import
import { Container, SignUpButton } from "./styles";

export function Introduction() {
  // Hooks
  const navigate = useNavigate();

  return (
    <Container id="challenges">
      <div>
        <h1>Conectando alimentos, pessoas e esperança!</h1>
        <span>
          Faça parte da grande transformação! Una-se à poderosa luta contra a
          fome e seja a diferença que o mundo precisa. Garantindo alimentos a
          todos, espalhando esperança, nutrindo vidas e construindo um futuro
          mais justo e solidário. Abrace essa causa e faça parte dessa jornada
          de impacto!
        </span>

        <SignUpButton onClick={() => navigate("/sign-up")}>
          Inscreva-se agora
        </SignUpButton>
      </div>

      <img src={FoodAndPeopleImage} alt="Forest" />
    </Container>
  );
}
