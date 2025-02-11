import { Link, useParams } from "react-router-dom";
import { ArrowLeft } from "phosphor-react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

// Assets import
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { AES } from "crypto-js";
import Logo from "../../assets/Logo.svg";

// Utils validation
import { DonationValidation } from "../../utils/validations/DonationValidation";

// Components import
import { Input, Button } from "../../components";

// Styles import
import { Container, Form, FieldGroup } from "./styles";
import { api } from "../../services/api";
import { useAuth } from "../../hooks/useAuth";

export function Donation() {
  // Hooks
  const { id } = useParams();
  const { user, setUser } = useAuth();
  const { control, handleSubmit, setValue } = useForm({
    resolver: yupResolver(DonationValidation),
  });
  const [disponibilidade, setDisponibilidade] = useState(0);

  const onSubmit = async (formData) => {
    try {
      if (id) {
        // Atualizando Doação
        const { data: doacaoResponse } = await api.put(`/doacao/${3}`, {
          imagem: formData.imagem,
          descricao: formData.descricao,
          disponibilidade,
        });

        setValue("descricao", doacaoResponse.descricao);
        setDisponibilidade(doacaoResponse.disponibilidade);
        setValue("imagem", doacaoResponse.imagem);
      } else {
        // Criando doação
        const { data: doacaoCreateResponse } = await api.post("/doacao/", {
          imagem: formData.imagem,
          descricao: formData.descricao,
          disponibilidade: 0,
        });

        console.log(doacaoCreateResponse, "criad");
      }

      const { data: userDataResponse } = await api.put(
        `/cliente/${user.id_cliente}`,
        {
          email: formData.email,
          nome: formData.name,
          senha: user.senha,
          termo: user.termo,
        }
      );

      const newUserData = Object.assign(user, userDataResponse);

      const cryptoJsKey = import.meta.env.VITE_APP_CRYPTO_JS_KEY;
      const encrypted = AES.encrypt(
        JSON.stringify(userDataResponse),
        cryptoJsKey
      ).toString();
      localStorage.setItem("@ALIMENTE-MAIS/token", encrypted);

      setUser(newUserData);

      toast.success(`Dados ${id ? "atualizados" : "criados"} com sucesso`, {
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
      toast.error(`Não foi possível realizar esta operação`, {
        position: "bottom-left",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    }
  };

  const pageTitle = id ? "Atualizar de doação" : "Cadastro de doação";
  const buttonText = id ? "Atualizar de doação" : "Cadastrar de doação";

  useEffect(() => {
    if (!id) return;

    async function getDonationData() {
      try {
        const { data } = await api.get(`doacao/${id}`);

        setValue("descricao", data.descricao);
        setDisponibilidade(data.disponibilidade);
        setValue("imagem", data.imagem);
      } catch (error) {
        toast.error(`Não foi possível buscar os dados da doação ID: ${id}`, {
          position: "bottom-left",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "light",
        });
      }
    }

    getDonationData();
  }, [id, setValue]);

  // Donor data
  useEffect(() => {
    setValue("email", user.email);
    setValue("phone", `11${user.telefone}`);
    setValue("name", user.nome);
    setValue("address", user.endereco);
  }, [id, user, setValue]);

  return (
    <Container>
      <header>
        <img src={Logo} alt="Logo do Alimente+" />

        <Link to="/home">
          <ArrowLeft size={24} />
          Voltar para Home
        </Link>
      </header>

      <Form onSubmit={handleSubmit(onSubmit)}>
        <h1>{pageTitle}</h1>

        <fieldset>
          <legend>
            <h2>Dados do doador</h2>
          </legend>

          <Input
            label="Endereço de e-mail"
            placeholder="johndoe@example.com"
            name="email"
            type="email"
            aria-label="Input para digitar o email da entidade"
            control={control}
          />
        </fieldset>

        <FieldGroup>
          <fieldset>
            <Input
              label="Whatsapp para contato"
              placeholder="(00) 00000-0000"
              name="phone"
              aria-label="Input para digitar o telefone da entidade"
              control={control}
              mask="(99) 99999-9999"
            />
          </fieldset>

          <fieldset>
            <Input
              label="Nome"
              placeholder="Jonh Doe"
              name="name"
              aria-label="Input para digitar o nome da entidade"
              control={control}
            />
          </fieldset>
        </FieldGroup>

        <fieldset>
          <legend>
            <h2>Endereço</h2>
            <span>Local de entrega da doação</span>
          </legend>

          <fieldset>
            <Input
              label="Endereço"
              placeholder="Rua tal..."
              name="address"
              aria-label="Input para digitar o endereço da entidade"
              control={control}
              disabled
            />
          </fieldset>
        </fieldset>

        <fieldset>
          <legend>
            <h2>Doação</h2>
            <span>Dados doação</span>
          </legend>

          <fieldset>
            <Input
              label="URL da imagem da doação"
              placeholder="https://"
              name="imagem"
              aria-label="Input para digitar a image da doação"
              control={control}
            />
          </fieldset>

          <fieldset>
            <Input
              placeholder="Descrição da doação"
              name="descricao"
              aria-label="Input para digitar a descricao da doação"
              control={control}
              multiline
              maxRows={4}
            />
          </fieldset>
        </fieldset>

        <Button type="submit" size="large">
          {buttonText}
        </Button>
      </Form>
    </Container>
  );
}
