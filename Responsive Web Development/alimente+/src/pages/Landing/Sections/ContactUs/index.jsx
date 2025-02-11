import { useState } from "react";
import { toast } from "react-toastify";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

// Components import
import { Input, Button } from "../../../../components";

// Validation import
import { ContactUsValidation } from "../../../../utils";

// Styles import
import { Container } from "./styles";

export function ContactUs() {
  // Hooks
  const { control, handleSubmit } = useForm({
    resolver: yupResolver(ContactUsValidation),
  });

  // States
  const [loading, setLoading] = useState(false);

  const onSubmit = async ({ email, name, question }) => {
    try {
      setLoading(true);

      console.log(email, name, question);

      toast.success("Pergunta enviada com sucesso!", {
        position: "bottom-left",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    } catch (error) {
      toast.error("Não foi possível enviar sua pergunta.", {
        position: "bottom-left",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container>
      <h1>Fale conosco</h1>

      <form onSubmit={handleSubmit(onSubmit)}>
        <fieldset>
          <label htmlFor="email">Digite seu e-mail: </label>
          <Input
            label="Endereço de e-mail"
            placeholder="johndoe@example.com"
            name="email"
            type="email"
            aria-label="Input para digitar o email do usuário"
            control={control}
          />
        </fieldset>

        <fieldset>
          <label htmlFor="name">Digite seu nome: </label>
          <Input
            label="Informe seu nome completo"
            placeholder="John Doe"
            name="name"
            aria-label="Input para digitar o nome completo do usuário"
            control={control}
          />
        </fieldset>

        <fieldset>
          <label htmlFor="question">Digite sua dúvida/pergunta: </label>
          <Input
            label="Digite sua pergunta aqui"
            name="question"
            type="email"
            aria-label="Input para digitar o email do usuário"
            control={control}
            multiline
            rows={3}
            maxRows={4}
          />
        </fieldset>

        <fieldset>
          <Button isLoading={loading} size="large" type="submit">
            Enviar
          </Button>
        </fieldset>
      </form>
    </Container>
  );
}
