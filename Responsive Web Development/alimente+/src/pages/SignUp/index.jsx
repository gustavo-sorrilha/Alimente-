/* eslint-disable react/no-unstable-nested-components */
/* eslint-disable no-param-reassign */
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import { yupResolver } from "@hookform/resolvers/yup";
import { toast } from "react-toastify";
import { AES } from "crypto-js";

// Components import
import {
  Checkbox,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  FormControlLabel,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";
import { Input, Button } from "../../components";

// Hooks import
import { useAuth } from "../../hooks/useAuth";

// Service import
import { api } from "../../services/api";

// Validation import
import { SignUpValidation, validateCpf } from "../../utils/index";

// Assets import
import Logo from "../../assets/Logo.svg";

// Style import
import { Wrapper, Container, WhoIAm, OptionButton, FieldGroup } from "./styles";

const conditions = [
  {
    title: "Aceitação dos Termos",
    description:
      "Ao prosseguir com o cadastro como beneficiário de produtos doados por empresas ou excedentes de restaurantes, você concorda em cumprir estes Termos e Condições de Uso. Caso não concorde com estes termos, solicitamos que interrompa o processo de cadastro.",
  },
  {
    title: "Doações de Produtos Próximos à Data de Validade",
    description:
      "Entenda que, ao aceitar produtos doados por empresas próximos à data de validade, você renuncia ao direito de processar a empresa doadora por qualquer problema ou consequência relacionados ao consumo desses produtos. Reconhece que tais produtos estão em boas condições, seguros para consumo e que o propósito dessa doação é combater a fome, evitando desperdício de alimentos.",
  },
  {
    title: "Doação de Excedentes por Restaurantes",
    description:
      "Ao aceitar doações de excedentes de restaurantes, você concorda em não processar os estabelecimentos doadores por quaisquer problemas ou consequências relacionadas ao consumo desses alimentos. Reconhece que esses excedentes são seguros para o consumo, embora possam estar próximos à data de validade, e que a doação tem como objetivo reduzir o desperdício de alimentos.",
  },
  {
    title: "Responsabilidade do Beneficiário",
    description:
      "Você é responsável por verificar a qualidade e a segurança dos produtos doados antes de consumi-los. Caso encontre algum indício de que o produto possa estar estragado ou inadequado para consumo, recomendamos que não o consuma e entre em contato conosco para que possamos tomar as devidas providências.",
  },
  {
    title: "Limitações de Responsabilidade",
    description:
      "Nem a empresa doadora, nem o restaurante doador serão responsáveis por quaisquer danos diretos, indiretos, incidentais, consequenciais ou punitivos decorrentes do uso dos produtos doados. Você concorda em isentar ambas as partes de qualquer responsabilidade relacionada ao consumo dos alimentos doados.",
  },
  {
    title: "Uso de Informações Pessoais",
    description:
      "Ao se cadastrar como beneficiário, você concorda que a empresa doadora e o restaurante doador possam coletar e armazenar suas informações pessoais, exclusivamente com o propósito de facilitar a doação de produtos e gerenciar o programa de combate à fome. Seus dados não serão compartilhados com terceiros, a menos que exigido por lei.",
  },
  {
    title: "Alterações nos Termos e Condições",
    description:
      "Estes Termos e Condições de Uso podem ser atualizados de tempos em tempos. Caso haja alguma alteração relevante, você será notificado sobre as mudanças por meio dos canais de comunicação fornecidos durante o cadastro.",
  },
  {
    title: "Legislação Aplicável",
    description:
      "Estes Termos e Condições de Uso serão regidos e interpretados de acordo com as leis do país em que a empresa doadora ou o restaurante doador está localizado, sem considerar conflitos de princípios legais.",
  },
  {
    title:
      "Ao prosseguir com o cadastro, você declara ter lido, entendido e concordado com estes Termos e Condições de Uso.",
    description: "",
  },
];

export function SignUp() {
  // Hooks
  const { setUser } = useAuth();
  const navigate = useNavigate();
  const { control, handleSubmit, setError, setValue, watch } = useForm({
    resolver: yupResolver(SignUpValidation),
  });

  // States
  const [loading, setLoading] = useState(false);
  const [whoIAm, setWhoIAm] = useState(null);
  const [address, setAddress] = useState({});
  const [termsAndConditionModalIsOpen, setTermsAndConditionModalIsOpen] =
    useState(false);
  const [terms, setTerms] = useState(false);

  function TermsAndConditionsModal() {
    return (
      <Dialog
        open={!!termsAndConditionModalIsOpen}
        onClose={() => setTermsAndConditionModalIsOpen(false)}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          Termos e condições de uso
        </DialogTitle>
        <DialogContent sx={{ display: "flex", gap: 3 }}>
          <DialogContentText id="alert-dialog-description">
            {/* {clickedTech.fullDescription} */}
            <List>
              {conditions.map((item) => (
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
            onClick={() => {
              setTermsAndConditionModalIsOpen(false);
              setTerms(true);
            }}
            autoFocus
            variant="contained"
          >
            Li e concordo com os termos
          </Button>
        </DialogActions>
      </Dialog>
    );
  }

  const onSubmit = async (data) => {
    try {
      setLoading(true);

      localStorage.removeItem("@ALIMENTE-MAIS/token");

      const isBeneficiary = whoIAm === "beneficiary";
      const isDonor = whoIAm === "donor";

      if (!whoIAm)
        throw new Error('Informe se você é "Doador" ou "Precisa de doação".');

      if (isBeneficiary) {
        const { cpf_usuario } = data;
        const cleanCPF = String(cpf_usuario).replace(/[\s.-]/g, "");

        if (!cpf_usuario) {
          setError("cpf_usuario", { type: "manual", message: "Informe o CPF" });
          return;
        }

        validateCpf(cpf_usuario);

        Object.assign(data, { cpf_usuario: cleanCPF, tipo: "beneficiario" });

        delete data.cnpj;
        delete data.endereco;
        delete data.cep;
        delete data.street;
        delete data.city;
        delete data.neighborhood;
        delete data.number;
        delete data.state;
      }

      if (isDonor) {
        const { cnpj, cep, number, telefone } = data;

        if (!cnpj) {
          setError("cnpj", { type: "manual", message: "Informe o CNPJ" });
          return;
        }

        if (!cep) {
          setError("cep", { type: "manual", message: "Informe o CEP" });
          return;
        }

        if (!cep) {
          setError("cep", { type: "manual", message: "Informe o CEP" });
          return;
        }

        if (!number) {
          setError("number", {
            type: "manual",
            message: "Informe o número",
          });
          return;
        }

        if (!telefone) {
          setError("telefone", {
            type: "manual",
            message: "Informe o Telefone",
          });
          return;
        }

        const endereco = `${data.cep}, ${data.city} - ${data.state} ${data.number}, ${data.neighborhood}, ${data.street}`;

        delete data.cpf_usuario;
        delete data.cep;
        delete data.street;
        delete data.city;
        delete data.neighborhood;
        delete data.number;
        delete data.state;

        const cleanCNPJ = String(cnpj).replace(/\D/g, "");
        Object.assign(data, { cnpj: cleanCNPJ, tipo: "doador", endereco });
      }

      const clientData = {
        nome: data.nome_usuario,
        email: data.email_usuario,
        senha: data.senha,
        termo: 1,
      };

      const finalResponseData = {};

      try {
        const { data: clienteResponse } = await api.post(
          "/cliente",
          clientData
        );

        if (isDonor) {
          const donorData = {
            cnpj: data.cnpj,
            telefone: data.telefone.replace(/[^\d]/g, ""),
            endereco: data.endereco,
            quantidade_doada: 0,
            id_cliente: clienteResponse.id_cliente,
          };

          const { data: donorResponse } = await api.post("/doador", donorData);

          Object.assign(finalResponseData, {
            ...donorResponse,
            ...clienteResponse,
          });
        } else {
          const beneficiaryData = {
            cpf: data.cpf_usuario,
            quantidade_recebida: 0,
            id_cliente: clienteResponse.id_cliente,
          };

          const { data: beneficiaryResponse } = await api.post(
            "/beneficiario",
            beneficiaryData
          );

          Object.assign(finalResponseData, {
            ...beneficiaryResponse,
            ...clienteResponse,
          });
        }

        const cryptoJsKey = import.meta.env.VITE_APP_CRYPTO_JS_KEY;
        const encrypted = AES.encrypt(
          JSON.stringify(finalResponseData),
          cryptoJsKey
        ).toString();

        localStorage.setItem("@ALIMENTE-MAIS/token", encrypted);

        setUser(finalResponseData);
        navigate("/home");
      } catch (error) {
        throw new Error("Não foi possível cadastrar este usuario.");
      }
    } catch (error) {
      toast.error(error.message || "Não foi possível realizar o cadastro.", {
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

  function handleClearExtraInputs() {
    setValue("cnpj", "");
    setValue("cep", "");
    setValue("cpf_usuario", "");
    setValue("cep", "");
    setValue("city", "");
    setValue("state", "");
    setValue("number", "");
    setValue("neighborhood", "");
    setValue("street", "");
    setValue("telefone", "");
  }

  async function getAddress(cep) {
    try {
      setLoading(true);
      setAddress({});

      const { data } = await api.get(`/endereco/${cep}`);
      setAddress(data);
    } catch (error) {
      toast.error("Não foi possível buscar o endereço do CEP informado.", {
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
  }

  const cep = watch("cep");
  const formattedCEP = cep?.replace("-", "");

  useEffect(() => {
    if (!formattedCEP) return;
    if (formattedCEP.length === 8) getAddress(formattedCEP);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [cep]);

  useEffect(() => {
    if (!formattedCEP) return;
    if (address.cep) {
      setValue("city", address.localidade);
      setValue("state", address.uf);
      setValue("street", address.logradouro);
      setValue("neighborhood", address.bairro);
    }
  }, [address, formattedCEP, setValue]);

  return (
    <Wrapper>
      <Container>
        <header>
          <img src={Logo} alt="Logo do Alimente+" />
          <p>Registre-se e comece a usar!</p>
        </header>

        <form onSubmit={handleSubmit(onSubmit)}>
          <fieldset>
            <Input
              label="Endereço de e-mail"
              placeholder="johndoe@example.com"
              name="email_usuario"
              type="email"
              aria-label="Input para digitar o email do usuário"
              control={control}
            />
          </fieldset>

          <fieldset>
            <Input
              label="Informe seu nome completo"
              placeholder="John Doe"
              name="nome_usuario"
              aria-label="Input para digitar o nome completo do usuário"
              control={control}
            />
          </fieldset>

          <fieldset>
            <Input
              label="Sua senha"
              placeholder="*************"
              type="password"
              name="senha"
              aria-label="Input para digitar a senha do usuário"
              control={control}
            />
          </fieldset>

          <WhoIAm>
            <OptionButton
              type="button"
              onClick={() => {
                setWhoIAm("donor");
                handleClearExtraInputs();
              }}
              active={whoIAm === "donor"}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="24"
                height="24"
                viewBox="0 0 256 256"
              >
                <path d="M230.33,141.06a24.43,24.43,0,0,0-21.24-4.23l-41.84,9.62A28,28,0,0,0,140,112H89.94a31.82,31.82,0,0,0-22.63,9.37L44.69,144H16A16,16,0,0,0,0,160v40a16,16,0,0,0,16,16H120a7.93,7.93,0,0,0,1.94-.24l64-16a6.94,6.94,0,0,0,1.19-.4L226,182.82l.44-.2a24.6,24.6,0,0,0,3.93-41.56ZM16,160H40v40H16Zm203.43,8.21-38,16.18L119,200H56V155.31l22.63-22.62A15.86,15.86,0,0,1,89.94,128H140a12,12,0,0,1,0,24H112a8,8,0,0,0,0,16h32a8.32,8.32,0,0,0,1.79-.2l67-15.41.31-.08a8.6,8.6,0,0,1,6.3,15.9ZM164,96a36,36,0,0,0,5.9-.48,36,36,0,1,0,28.22-47A36,36,0,1,0,164,96Zm60-12a20,20,0,1,1-20-20A20,20,0,0,1,224,84ZM164,40a20,20,0,0,1,19.25,14.61,36,36,0,0,0-15,24.93A20.42,20.42,0,0,1,164,80a20,20,0,0,1,0-40Z" />
              </svg>

              <p>Doador</p>
            </OptionButton>

            <OptionButton
              type="button"
              onClick={() => {
                setWhoIAm("beneficiary");
                handleClearExtraInputs();
              }}
              active={whoIAm === "beneficiary"}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="24"
                height="24"
                viewBox="0 0 256 256"
              >
                <path d="M235.32,180l-36.24-36.25L162.62,23.46A21.76,21.76,0,0,0,128,12.93,21.76,21.76,0,0,0,93.38,23.46L56.92,143.76,20.68,180a16,16,0,0,0,0,22.62l32.69,32.69a16,16,0,0,0,22.63,0L124.28,187a40.68,40.68,0,0,0,3.72-4.29,40.68,40.68,0,0,0,3.72,4.29L180,235.32a16,16,0,0,0,22.63,0l32.69-32.69A16,16,0,0,0,235.32,180ZM64.68,224,32,191.32l12.69-12.69,32.69,32.69ZM120,158.75a23.85,23.85,0,0,1-7,17L88.68,200,56,167.32l13.65-13.66a8,8,0,0,0,2-3.34l37-122.22A5.78,5.78,0,0,1,120,29.78Zm23,17a23.85,23.85,0,0,1-7-17v-129a5.78,5.78,0,0,1,11.31-1.68l37,122.22a8,8,0,0,0,2,3.34l14.49,14.49-33.4,32ZM191.32,224l-12.56-12.57,33.39-32L224,191.32Z" />
              </svg>
              <p>Preciso de doação</p>
            </OptionButton>
          </WhoIAm>

          {whoIAm !== null && (
            <div>
              {whoIAm === "donor" ? (
                <>
                  <fieldset>
                    <Input
                      label="CNPJ"
                      placeholder="00.000.000/0000-00"
                      mask="99.999.999/9999-99"
                      name="cnpj"
                      aria-label="Input para digitar o cnpj do doador"
                      control={control}
                    />
                  </fieldset>

                  <fieldset>
                    <Input
                      label="Telefone (Whatsapp)"
                      placeholder="(00) 00000-0000"
                      mask="(99) 99999-9999"
                      name="telefone"
                      aria-label="Input para digitar o telefone do doador"
                      control={control}
                    />
                  </fieldset>

                  <FieldGroup>
                    <fieldset>
                      <Input
                        label="CEP do estabelecimento"
                        placeholder="00000-000"
                        mask="99999-999"
                        name="cep"
                        aria-label="Input para digitar o cep do estabelecimento"
                        control={control}
                      />
                    </fieldset>

                    <fieldset>
                      <Input
                        label="Número"
                        placeholder="0000"
                        type="number"
                        name="number"
                        aria-label="Input para digitar o número do estabelecimento"
                        control={control}
                      />
                    </fieldset>
                  </FieldGroup>

                  {formattedCEP?.length === 8 && !!address.cep && (
                    <>
                      <FieldGroup>
                        <fieldset>
                          <Input
                            name="city"
                            aria-label="Input para digitar a cidade do estabelecimento"
                            control={control}
                            disabled
                          />
                        </fieldset>

                        <fieldset>
                          <Input
                            name="state"
                            aria-label="Input para digitar a estado do estabelecimento"
                            control={control}
                            disabled
                          />
                        </fieldset>
                      </FieldGroup>

                      <fieldset>
                        <Input
                          name="street"
                          aria-label="Input para digitar a rua do estabelecimento"
                          control={control}
                          disabled
                        />
                      </fieldset>

                      <fieldset>
                        <Input
                          name="neighborhood"
                          aria-label="Input para digitar o bairro do estabelecimento"
                          control={control}
                          disabled
                        />
                      </fieldset>
                    </>
                  )}
                </>
              ) : (
                <fieldset>
                  <Input
                    label="CPF"
                    placeholder="000.000.000-00"
                    mask="999.999.999-99"
                    name="cpf_usuario"
                    aria-label="Input para digitar o cpf do usuário"
                    control={control}
                  />
                </fieldset>
              )}
            </div>
          )}

          <fieldset>
            <FormControlLabel
              control={<Checkbox checked={terms} />}
              label="Li e concordo com os termos e condições de uso"
              onClick={() => {
                setTermsAndConditionModalIsOpen(true);
                setTerms(false);
              }}
            />
          </fieldset>

          <fieldset>
            <Button type="submit" isLoading={loading} disabled={!terms}>
              Cadastrar
            </Button>
          </fieldset>
        </form>

        <div id="links">
          <Link to="/sign-in">Ir para o login</Link>
        </div>
      </Container>

      <TermsAndConditionsModal />
    </Wrapper>
  );
}
