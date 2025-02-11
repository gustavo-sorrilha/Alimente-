/* eslint-disable react/no-unescaped-entities */
import { Container } from "./styles";

import IAImage from "../../../../assets/ia.jpg";

export function FoodDistribution() {
  return (
    <Container>
      <img src={IAImage} alt="IA" />

      <div>
        <h1>
          Potencial das IA's generativas na otimização da distribuição de
          alimentos
        </h1>
        <span>
          A distribuição de alimentos desempenha um papel crucial na garantia do
          acesso adequado e oportuno a alimentos nutritivos para a população.
          Uma distribuição eficiente é essencial para evitar desperdícios,
          minimizar a escassez e atender às demandas em constante mudança. Nesse
          contexto, as IA's generativas surgem como uma ferramenta poderosa para
          aprimorar essa distribuição. Por meio da análise de dados em tempo
          real, previsão da demanda, otimização da logística e gestão
          inteligente de estoques, as IA's generativas podem aperfeiçoar a
          eficiência e a precisão da distribuição de alimentos, contribuindo
          para um sistema alimentar mais sustentável e resiliente.
        </span>
      </div>
    </Container>
  );
}
