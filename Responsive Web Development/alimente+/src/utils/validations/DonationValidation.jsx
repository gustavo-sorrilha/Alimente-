import * as yup from "yup";

export const DonationValidation = yup.object().shape({
  phone: yup.string().required("A senha é obrigatória"),
});
