import * as yup from "yup";

export const ContactUsValidation = yup.object().shape({
  email: yup
    .string()
    .email("E-mail inválido")
    .required("O e-mail é obrigatório"),
  name: yup.string().required("O nome é obrigatório"),
  question: yup.string().required("A pergunta é obrigatória"),
});
