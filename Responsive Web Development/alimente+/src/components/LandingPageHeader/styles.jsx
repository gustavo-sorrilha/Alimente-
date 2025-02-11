import styled from "styled-components";

export const Container = styled.header`
  display: flex;
  justify-content: space-between;
  padding: 2.5rem 0;

  > img {
    max-width: 15rem;
    cursor: pointer;
  }

  > ul {
    display: flex;
    justify-content: center;
    align-items: center;

    > li {
      list-style: none;
      cursor: pointer;
      margin-left: 4rem;
      border-bottom: 1px solid transparent;

      > a {
        transition: all 200ms;

        color: var(--gray-04);
        text-decoration: none;
      }

      &:hover {
        border-bottom: 1px solid var(--orange);

        > a {
          color: var(--white);
        }
      }
    }
  }

  /* Small screen */
  @media screen and (max-width: 85.3rem) {
    padding: 2rem 1rem;

    > img {
      width: 10rem;
    }

    > ul > li {
      font-size: 1.4rem;
      margin-left: 2rem;
    }
  }
`;
