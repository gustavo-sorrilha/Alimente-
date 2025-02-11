import styled, { css } from "styled-components";

export const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  min-height: 100vh;
  height: auto;
`;

export const Container = styled.main`
  height: auto;
  width: 90%;
  max-width: 40rem;

  padding: 3% 0;

  header {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    margin-bottom: 1.8rem;

    img {
      height: 6rem;
      margin-bottom: 2rem;
    }

    h1 {
      font-size: 3.2rem;
    }

    p {
      font-size: 1.8rem;
    }
  }

  form {
    fieldset {
      display: flex;
      flex-direction: column;

      margin-bottom: 2rem;
    }

    button {
      margin-bottom: 1rem;
    }
  }

  #links {
    display: flex;
    flex-direction: column;
  }

  #links > a {
    font-size: 1.4rem;
    text-decoration: underline;
    text-align: center;

    color: var(--white);

    margin-top: 2rem;
  }
`;

export const WhoIAm = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1;

  margin-bottom: 1rem;

  > button {
  }
`;

export const OptionButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  flex: 0.49;

  padding: 1rem 1rem;

  transition: all 200ms;

  border: 1px solid transparent;

  background-color: var(--background-form);

  > svg {
    fill: var(--orange);
  }

  cursor: pointer;

  > p {
    margin-top: 0.5rem;
    line-height: 1.5rem;
    font-size: 1.6rem;
    color: var(--white);
  }

  &:hover {
    background-color: var(--background);

    border: 1px solid var(--orange);

    > svg {
      fill: var(--orange-dark);
    }
  }

  ${({ active }) =>
    active &&
    css`
      background-color: var(--background);
      border: 1px solid var(--orange);

      > svg {
        fill: var(--orange-dark);
      }
    `}
`;

export const FieldGroup = styled.div`
  display: flex;
  justify-content: space-between;

  > fieldset:first-of-type {
    flex: 0.95;
    margin-right: 1rem;
  }

  > fieldset + fieldset {
    flex: 0.8;
  }
`;
