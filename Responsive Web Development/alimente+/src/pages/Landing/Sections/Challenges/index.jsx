// Assets import
import PopulationGrowthImage from "../../../../assets/population-growth.png";
import ClimateChangesImage from "../../../../assets/climate-changes.png";
import PovertyImage from "../../../../assets/poverty.png";
import FoodWasteImage from "../../../../assets/food-waste.png";
import LimitedTechImage from "../../../../assets/limited-technology.png";
import NaturalResourcesImage from "../../../../assets/natural-resources.png";

// Styles import
import { Container, ListTopics } from "./styles";

const itens = [
  {
    image: PopulationGrowthImage,
    title: "Crescimento populacional",
    description:
      "O aumento contínuo da população global demanda maior produção de alimentos para suprir as necessidades básicas.",
  },
  {
    image: ClimateChangesImage,
    title: "Mudanças climáticas",
    description:
      "Eventos climáticos extremos e alterações nos padrões sazonais afetam negativamente a produção agrícola e a disponibilidade de alimentos.",
  },
  {
    image: PovertyImage,
    title: "Pobreza e desigualdade",
    description:
      "A fome está ligada diretamente à pobreza e à desigualdade socioeconômica, impedindo acesso adequado a alimentos e contribuindo com a insegurança alimentar.",
  },
  {
    image: FoodWasteImage,
    title: "Desperdício de alimentos",
    description:
      "Quantidades substanciais de alimentos são perdidas ao longo das cadeias de produção, distribuição e consumo, contribuindo para a escassez de alimentos.",
  },
  {
    image: LimitedTechImage,
    title: "Infraestrutura e tecnologia limitadas",
    description:
      "A ausência de infraestrutura agrícola adequada e tecnologias modernas dificulta a produção eficiente de alimentos.",
  },
  {
    image: NaturalResourcesImage,
    title: "Recursos naturais",
    description:
      "A disponibilidade limitada de terras aráveis, água e outros recursos essenciais dificulta a produção e representa um desafio adicional a segurança alimentar.",
  },
];

export function Challenges() {
  return (
    <Container>
      <h1>Desafios</h1>

      <ListTopics>
        {itens.map((item) => (
          <article>
            <img src={item.image} alt={`Ícone que representa ${item.title}`} />
            <h3>{item.title}</h3>
            <p>{item.description}</p>
          </article>
        ))}
      </ListTopics>
    </Container>
  );
}
