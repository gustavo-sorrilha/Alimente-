import styled from "styled-components";

export const Container = styled.section`
  height: 100vh;
  display: flex;
  justify-content: space-between;
  align-items: start;

  > div {
    h1 {
      margin-top: 5rem;
      font-size: 5rem;
      max-width: 60rem;
      line-height: 6rem;
    }

    span {
      display: block;
      line-height: 2.5rem;
      margin-top: 2rem;
      max-width: 45rem;

      color: var(--gray-04);
    }
  }

  > img {
    width: 45rem;
    height: 78%;
    object-fit: cover;

    border-radius: 0.5rem;

    margin-top: 3rem;
  }
`;

export const SignUpButton = styled.button`
  width: 21rem;
  height: 7rem;

  background: var(--orange);

  cursor: pointer;

  margin-top: 4rem;
  border-radius: 0.5rem;
  border: 0;

  color: var(--white);
  font-weight: bold;
  font-size: 2rem;

  transition: all 200ms;

  &:hover {
    opacity: 0.9;
    background: var(--orange-dark);
  }
`;
