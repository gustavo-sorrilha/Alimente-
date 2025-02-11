import styled from "styled-components";

export const Container = styled.div`
  background: var(--background);
  padding: 3rem 0;

  header {
    width: 128rem;
    margin: 0 auto;
    padding: 0 0 16rem;
    display: flex;
    align-items: center;
    justify-content: space-between;

    nav {
      div {
        button {
          cursor: pointer;

          font-weight: 600;
          border-radius: 0.8rem;
          border: 0;
          background: var(--green);
          color: var(--white);

          display: flex;
          flex-direction: row;
          align-items: center;

          p {
            padding: 1.6rem 2.4rem;
            font-size: 1.5rem;
          }

          .icon {
            display: flex;
            padding: 1.6rem 1.6rem;
            background: var(--green-light);
            border-radius: 0 8px 8px 0;
            margin: 0 auto;

            > svg {
              fill: var(--white);
            }
          }
        }
      }
    }
  }
`;

export const Logout = styled.div`
  display: flex;
  align-items: center;

  button {
    margin-left: 3rem;
  }

  button > svg {
    color: var(--orange);
  }
`;
