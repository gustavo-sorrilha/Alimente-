import { useState } from "react";
import { toast } from "react-toastify";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import { yupResolver } from "@hookform/resolvers/yup";
import { AES } from "crypto-js";

// Hooks import
// import { useAuth } from "../../hooks/useAuth";

// Service import
// import { api } from "../../services/api";

// Components import
import { Input, Button } from "../../components";

// Validation import
import { SignInValidation } from "../../utils";

// Assets import
import Logo from "../../assets/Logo.svg";

// Style import
import { Wrapper, Container } from "./styles";

// Service import
import { api } from "../../services/api";

// Hooks import
import { useAuth } from "../../hooks/useAuth";

export function SignIn() {
  // Hooks
  const { setUser } = useAuth();
  const navigate = useNavigate();
  const { control, handleSubmit } = useForm({
    resolver: yupResolver(SignInValidation),
  });

  // States
  const [loading, setLoading] = useState(false);

  const onSubmit = async ({ email, password }) => {
    try {
      setLoading(true);

      const { data } = await api.post("/cliente/login", {
        email,
        senha: password,
      });

      const cryptoJsKey = import.meta.env.VITE_APP_CRYPTO_JS_KEY;
      const encrypted = AES.encrypt(
        JSON.stringify(data),
        cryptoJsKey
      ).toString();

      localStorage.setItem("@ALIMENTE-MAIS/token", encrypted);

      setUser(data);
      navigate("/home");
    } catch (error) {
      toast.error("E-mail ou senha incorretos.", {
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
    <Wrapper>
      <Container>
        <header>
          <img src={Logo} alt="Logo do Alimente+" />
          <p>Faça login e comece a usar!</p>
        </header>

        <form onSubmit={handleSubmit(onSubmit)}>
          <fieldset>
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
            <Input
              label="Sua senha"
              placeholder="*************"
              type="password"
              name="password"
              aria-label="Input para digitar a senha do usuário"
              control={control}
            />
          </fieldset>

          <fieldset>
            <Button isLoading={loading} size="large" type="submit">
              Entrar na plataforma
            </Button>
          </fieldset>
        </form>

        <div id="links">
          <Link to="/sign-up">Não possui conta? Crie uma agora!</Link>
        </div>
      </Container>
    </Wrapper>
  );
}
