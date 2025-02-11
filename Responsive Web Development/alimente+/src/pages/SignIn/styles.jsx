import styled from "styled-components";

export const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100vh;
`;

export const Container = styled.main`
  height: auto;
  width: 90%;
  max-width: 40rem;

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
  }

  form {
    input {
      color: var(--white);
    }

    fieldset {
      border: 0;
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
    cursor: pointer;

    font-size: 1.4rem;
    text-decoration: underline;
    text-align: center;

    color: var(--white);

    margin-top: 2rem;
  }
`;
