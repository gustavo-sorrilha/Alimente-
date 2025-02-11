import styled from "styled-components";

export const Wrapper = styled.main`
  width: 100%;
  min-height: 100vh;
  height: auto;

  background-color: #fff;
`;

export const DonationContainer = styled.div`
  width: 100%;
  max-width: 128rem;
  margin: 0 auto;
  padding: 4rem 0;
  margin-top: -14rem;

  display: grid;

  grid-template-columns: repeat(3, 1fr);
  grid-gap: 3.2rem;
`;
