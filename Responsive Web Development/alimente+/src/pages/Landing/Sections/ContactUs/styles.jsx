import styled from "styled-components";

export const Container = styled.section`
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  > h1 {
    font-size: 4rem;
  }

  > form {
    margin-top: 1rem;

    background-color: var(--background-form);

    height: 70rem;
    width: 70rem;

    padding: 5rem;

    border-radius: 0.5rem;

    > input {
      color: var(--white);
    }

    > fieldset {
      > label {
        margin-bottom: 0.5rem;
      }

      border: 0;
      display: flex;
      flex-direction: column;

      margin-bottom: 4rem;
    }

    button {
      height: 5rem;
      width: 50%;
      align-self: flex-end;
      font-size: 1.8rem;

      margin-top: 1rem;
    }
  }

  /* Small screen */
  @media screen and (max-width: 85.3rem) {
    > form {
      padding: 1rem 2.5rem;

      > h1 {
        font-size: 3rem;
      }
    }
  }

  /* Medium screen */
  @media screen and (max-width: 1366px) {
    > form {
      height: 55rem;
      width: 55rem;

      > fieldset {
        margin-bottom: 2rem;

        > label {
          margin-bottom: 0;
        }
      }

      button {
        height: 4.5rem;
        width: 50%;
      }
    }
  }
`;
