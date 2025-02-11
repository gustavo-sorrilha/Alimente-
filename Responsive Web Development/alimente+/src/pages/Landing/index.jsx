import { Element } from "react-scroll";

// Components import
import { LandingPageHeader, LandingPageFooter } from "../../components";

// Landing components import
import {
  Introduction,
  ContactUs,
  InnovativeTechnologies,
  Challenges,
  FoodDistribution,
} from "./Sections";

// Styles import
import { Container } from "./styles";

export function Landing() {
  return (
    <Container>
      <LandingPageHeader />

      <Element name="introduction">
        <Introduction />
      </Element>

      <Element name="challenges">
        <Challenges />
      </Element>

      <Element name="innovative-technologies">
        <InnovativeTechnologies />
      </Element>

      <Element name="food-distribution">
        <FoodDistribution />
      </Element>

      <Element name="contact-us">
        <ContactUs />
      </Element>

      <LandingPageFooter />
    </Container>
  );
}
