import styled from "styled-components";

import { theme } from "../../styles/global/mui";

export const Container = styled.div`
  textarea {
    color: var(--gray-01);
  }

  width: 100%;
  max-width: 128rem;
  margin: 0 auto;

  > header {
    padding: 3rem 0;
    display: flex;
    align-items: center;
    justify-content: space-between;

    > a {
      color: var(--white);
      font-weight: bold;
      text-decoration: none;
      display: flex;
      align-items: center;

      > svg {
        margin-right: 1rem;
        color: var(--white);
      }
    }
  }
`;

export const Form = styled.form`
  margin: 8rem auto;
  padding: 6.4rem;
  max-width: 73rem;
  background: #fafafafa;
  border-radius: 8px;
  display: flex;
  flex-direction: column;

  color: var(--gray-01);

  label.MuiFormLabel-root {
    color: var(--orange);
  }

  input.Mui-disabled,
  .WQhBb.Mui-disabled {
    color: var(--gray-01) !important;
    cursor: not-allowed;
    -webkit-text-fill-color: var(--gray-01) !important;
  }

  input {
    color: var(--gray-01) !important;
    background-color: ${theme.palette.grey[50]};
    margin-top: 2.5rem;
  }

  button {
    margin-top: 3rem;
    padding: 1.5rem;
    font-size: 1.8rem;
  }

  > h1 {
    font-size: 5rem;
    line-height: 6rem;
    color: var(--background);
  }

  fieldset {
    border: 0;
    display: flex;
    flex-direction: column;

    > legend {
      width: 100%;
      display: flex;
      justify-content: space-between;
      align-items: center;

      h2 {
        font-size: 2.4rem;
        margin: 2rem 0;
      }
    }
  }
`;

export const FieldGroup = styled.div`
  display: flex;
  justify-content: space-between;

  fieldset + fieldset {
    flex: 0.95;
  }
`;
