import styled from "styled-components";

export const Container = styled.footer`
  display: flex;
  align-items: center;
  justify-content: space-around;

  padding: 2rem 0;

  width: 100%;

  border-top: 0.1px solid var(--gray-02);

  div#members {
    display: flex;

    > p + p {
      margin-left: 1.5rem;
    }
  }

  > img {
    height: 3rem;
  }
`;
