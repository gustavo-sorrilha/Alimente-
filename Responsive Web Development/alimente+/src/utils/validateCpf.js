const hasCPFLength = (cpf) => {
  if (cpf.length !== 11) {
    throw new Error("CPF deve conter 11 dígitos.");
  }
  return true;
};

const allDigitsAreEqual = (digits) => {
  for (let i = 0; i < 10; i += 1) {
    if (digits === new Array(digits.length + 1).join(String(i))) {
      throw new Error("CPF inválido: todos os dígitos são iguais.");
    }
  }
  return false;
};

const calcFirstChecker = (firstNineDigits) => {
  let sum = 0;

  for (let i = 0; i < 9; i += 1) {
    sum += Number(firstNineDigits.charAt(i)) * (10 - i);
  }

  const lastSumChecker = sum % 11;
  return lastSumChecker < 2 ? 0 : 11 - lastSumChecker;
};

const calcSecondChecker = (cpfWithChecker1) => {
  let sum = 0;

  for (let i = 0; i < 10; i += 1) {
    sum += Number(cpfWithChecker1.charAt(i)) * (11 - i);
  }

  const lastSumChecker2 = sum % 11;
  return lastSumChecker2 < 2 ? 0 : 11 - lastSumChecker2;
};

export function validateCpf(value) {
  if (typeof value !== "string") throw new Error("CPF inválido.");

  const cleanCPF = String(value).replace(/[\s.-]/g, "");
  const firstNineDigits = cleanCPF.slice(0, 9);
  const checker = cleanCPF.slice(9, 11);

  hasCPFLength(cleanCPF);
  allDigitsAreEqual(cleanCPF);

  const checker1 = calcFirstChecker(firstNineDigits);
  const checker2 = calcSecondChecker(`${firstNineDigits}${checker1}`);

  if (checker !== `${checker1}${checker2}`) throw new Error("CPF inválido");

  return true;
}
