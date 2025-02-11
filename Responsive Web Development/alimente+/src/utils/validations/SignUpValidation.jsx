import * as yup from "yup";

export const SignUpValidation = yup.object().shape({
  email_usuario: yup
    .string()
    .email("E-mail inválido")
    .required("O e-mail é obrigatório"),
  cpf_usuario: yup
    .string()
    .max(15, "O CPF deve conter no máximo 8 caracteres")
    .nullable(),
  cnpj: yup
    .string()
    .max(18, "O CNPJ deve conter no máximo 14 caracteres")
    .nullable(),
  cep: yup
    .string()
    .max(9, "O CEP deve conter no máximo 08 caracteres")
    .nullable(),
  nome_usuario: yup
    .string()
    .required("O nome é obrigatório")
    .max(80, "O nome deve conter no máximo 80 caracteres"),
  senha: yup.string().required("A senha é obrigatória"),
});
