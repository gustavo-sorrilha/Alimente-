import styled from "styled-components";

export const Container = styled.section`
  height: 100vh;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;

  h1 {
    font-size: 5rem;
    text-align: center;
  }
`;

export const ListTopics = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  width: 100%;

  max-height: 650px;
  height: 92vh;

  > article {
    display: flex;
    flex-direction: column;
    width: calc(33.33% - 3rem);

    margin-bottom: 4rem;

    img {
      height: 12rem;
      width: 12rem;
      margin: 0 auto;
    }

    h3 {
      text-align: center;
      margin: 1rem 0;
    }

    p {
      color: var(--gray-04);
      font-size: 1.5rem;
      text-align: center;
    }
  }
`;
