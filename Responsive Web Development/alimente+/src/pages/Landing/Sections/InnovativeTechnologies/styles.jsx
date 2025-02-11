import styled from "styled-components";

export const Container = styled.section`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  > h1 {
    margin-bottom: 2rem;
    font-size: 5rem;
  }

  > div.MuiGrid-root {
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;
