export function transformDate(dateString) {
  const parts = dateString.split("/");
  const day = parseInt(parts[0], 10);
  const month = parseInt(parts[1], 10);
  const year = parseInt(parts[2], 10);

  // Verificar se o mês é válido (entre 1 e 12)
  if (month < 1 || month > 12) throw new Error("Mês inválido");

  // Verificar se o ano é válido (positivo e a pessoa tem pelo menos 6 anos)
  const currentYear = new Date().getFullYear();
  if (year < 0 || currentYear - year < 6) throw new Error("Ano inválido");

  // Verificar se o dia é válido (entre 1 e 31, considerando o mês e ano)
  const lastDayOfMonth = new Date(year, month, 0).getDate();
  if (day < 1 || day > lastDayOfMonth) throw new Error("Dia inválido");

  // Formatando a data no formato "yyyy-mm-dd"
  const transformedDate = `${year}-${month.toString().padStart(2, "0")}-${day
    .toString()
    .padStart(2, "0")}`;
  return transformedDate;
}
