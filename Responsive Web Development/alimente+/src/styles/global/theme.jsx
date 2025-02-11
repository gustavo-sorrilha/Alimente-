import { createGlobalStyle } from "styled-components";

export const GlobalStyles = createGlobalStyle`
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
}

:root {
  --space: #0B1E8A;
  --space-light: #3D68B2;
  --space-dark: #040327;
  --gray-01: #0D0E13;
  --gray-02: #2C2D3A;
  --gray-03: #60616F;
  --gray-04: #898A93;
  --gray-05: #D5D5DB;
  --orange: #E85937;
  --orange-light: #FF8C70;
  --orange-dark: #CF3F1D;
  --yellow: #F5D15F;
  --red: #c72828;
  --green: #39b100;
  --green-light: #41c900;
  --white: #f2f2f2f2;
  --background: #04032C;
  --background-section: linear-gradient(180deg, #040327 0%, #0D0E13 24.49%);
  --background-form: #0E0D40;

  font-size: 62.5%; // 1rem = 10px

  body {
    font-size: 1.6rem;
    background-color: var(--background);
    color: var(--white);
  font-family: "Heebo", sans-serif;

  }

  ::-webkit-scrollbar {
    width: 8px;
    background-color: var(--gray-02);
  }

  ::-webkit-scrollbar-track {
    background-color: var(--gray-02);
  }

  ::-webkit-scrollbar-thumb {
    background-color: var(--gray-04);
    border-radius: 4px;
  }

  ::-webkit-scrollbar-thumb:hover {
    background-color: var(--gray-03);
  }

  ::-webkit-scrollbar-thumb:hover {
    background-color: var(--gray-03);
    transition: background-color 0.3s ease-in-out;
  }
}
`;
