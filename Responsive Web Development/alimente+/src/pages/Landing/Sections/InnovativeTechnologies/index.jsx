/* eslint-disable react/no-unstable-nested-components */
import {
  CardActionArea,
  Card,
  CardContent,
  CardMedia,
  Typography,
  Grid,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  DialogActions,
  Button,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";
import { useState } from "react";

// Assets import
import AgriculturaVertical from "../../../../assets/agricultura-vertical.jpeg";
import AquaponiaImage from "../../../../assets/aquaponia.jpg";
import HidroponiaImage from "../../../../assets/hidroponia.jpg";

// Styles import
import { Container } from "./styles";

const itens = [
  {
    image: AgriculturaVertical,
    title: "Agricultura vertical",
    shortDescription:
      "Cultivo em camadas que maximiza espaço e recursos para produção eficiente, contribuindo para agricultura sustentável em áreas urbanas.",
    fullDescription:
      "Essa abordagem permite maximizar o uso do espaço disponível em áreas urbanas densas, reduzindo a necessidade de expansão agrícola em terras naturais. Com a agricultura vertical, é possível produzir uma quantidade significativa de alimentos de forma eficiente, conservando recursos naturais e minimizando a pegada ambiental associada à produção de alimentos.",
  },
  {
    image: AquaponiaImage,
    title: "Aquaponia",
    shortDescription:
      "Integração de aquicultura e hidroponia, criação sustentável de alimentos ao aproveitar resíduos de peixes como nutrientes para as plantas.",
    fullDescription:
      "Ao combinar a criação de peixes em sistemas de aquicultura com o cultivo de plantas em sistemas hidropônicos, a aquaponia estabelece uma simbiose sustentável. Os resíduos dos peixes são convertidos em nutrientes naturais para as plantas, eliminando a necessidade de fertilizantes químicos. Além disso, a água é recirculada, resultando em um consumo significativamente menor em comparação com a agricultura convencional. A aquaponia promove a utilização eficiente de recursos, o cultivo de alimentos frescos e saudáveis, e contribui para a agricultura sustentável.",
  },
  {
    image: HidroponiaImage,
    title: "Hidroponia",
    shortDescription:
      "Cultivo sem solo, economia de água e uso controlado de nutrientes, promovendo produção sustentável de alimentos.",
    fullDescription:
      "Com a hidroponia, o cultivo é realizado sem solo, permitindo um controle preciso dos nutrientes fornecidos às plantas. Isso resulta em um uso eficiente de nutrientes, reduzindo o desperdício e a lixiviação no meio ambiente. Além disso, a hidroponia utiliza menos água em comparação com a agricultura convencional, pois a água é recirculada e reutilizada no sistema. Essa tecnologia ajuda a alcançar a agricultura sustentável ao minimizar a dependência de recursos naturais, reduzir o uso de produtos químicos e promover uma produção mais eficiente e controlada de alimentos.",
  },
];

const benefits = [
  {
    title: "Conservação de recursos naturais",
    description:
      "Tanto a aquaponia quanto a hidroponia utilizam menos água do que a agricultura convencional, reduzindo o consumo de um recurso vital. Além disso, esses métodos minimizam a necessidade de fertilizantes químicos e pesticidas, contribuindo para a preservação da qualidade do solo e da água.",
  },
  {
    title: "Redução das emissões de gases de efeito estufa",
    description:
      "Ao produzir alimentos localmente, em áreas urbanas ou próximas a centros de consumo, a agricultura vertical reduz a necessidade de transporte de longa distância, diminuindo as emissões de gases de efeito estufa associadas ao transporte de alimentos.",
  },
  {
    title: "Aumento da produtividade e eficiência",
    description:
      "Com o controle preciso do ambiente de cultivo, essas tecnologias permitem uma produção mais eficiente, com crescimento mais rápido das plantas e colheitas mais frequentes. Isso aumenta a produtividade e a disponibilidade de alimentos frescos, especialmente em áreas urbanas onde o acesso a produtos agrícolas pode ser limitado.",
  },
  {
    title: "Conservação da biodiversidade",
    description:
      "Ao reduzir a necessidade de expansão agrícola em áreas naturais, a agricultura vertical contribui para a preservação da biodiversidade, evitando o desmatamento e a degradação de habitats.",
  },
];

export function InnovativeTechnologies() {
  const [modalIsOpen, setModalIsOpen] = useState("");

  function Modal() {
    const clickedTech = itens.find((item) => item?.title === modalIsOpen);

    if (!clickedTech) return null;

    return (
      <Dialog
        open={!!modalIsOpen}
        onClose={() => setModalIsOpen("")}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
        fullScreen
      >
        <DialogTitle id="alert-dialog-title">{clickedTech.title}</DialogTitle>
        <DialogContent sx={{ display: "flex", gap: 3 }}>
          <img
            src={`${clickedTech.image}?w=248&h=248&fit=crop&auto=format`}
            srcSet={`${clickedTech.image}?w=248&h=248&fit=crop&auto=format&dpr=2 2x`}
            alt={clickedTech.title}
            loading="lazy"
            style={{ maxWidth: "50rem", objectFit: "cover" }}
          />

          <DialogContentText id="alert-dialog-description">
            {clickedTech.fullDescription}

            <Typography variant="h2" marginTop="2rem">
              Benefícios
            </Typography>
            <List>
              {benefits.map((item) => (
                <ListItem>
                  <ListItemText
                    primary={item.title}
                    secondary={item.description}
                  />
                </ListItem>
              ))}
            </List>
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => setModalIsOpen("")}
            autoFocus
            variant="contained"
          >
            Fechar
          </Button>
        </DialogActions>
      </Dialog>
    );
  }

  return (
    <>
      <Container>
        <h1>Tecnologias inovadoras</h1>
        <Grid container spacing={1}>
          {itens.map((item) => (
            <Grid
              item
              xs={3}
              sm={4}
              md={4}
              lg={4}
              tabindex="0"
              aria-label="Ação de um IPO"
              key={item}
            >
              <Card
                sx={{ maxWidth: 345, background: "var(--background-form)" }}
                onClick={() => setModalIsOpen(item.title)}
              >
                <CardActionArea>
                  <CardMedia
                    component="img"
                    height="250"
                    image={item.image}
                    alt={`Imagem de uma ${item.title}`}
                  />
                  <CardContent style={{ height: "13rem" }}>
                    <Typography gutterBottom variant="h1">
                      {item.title}
                    </Typography>

                    <Typography variant="h3" color="text.secondary">
                      {item.shortDescription}
                    </Typography>
                  </CardContent>
                </CardActionArea>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>
      <Modal />
    </>
  );
}
