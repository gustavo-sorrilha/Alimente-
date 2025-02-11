import styled, { css } from "styled-components";

export const Container = styled.div`
  background: var(--white);
  border-radius: 0.8rem;

  cursor: pointer;

  header {
    background: #ffb84d;
    border-radius: 0.8rem 0.8rem 0px 0px;
    height: 19.2rem;
    overflow: hidden;
    transition: 0.3s opacity;
    text-align: center;

    ${(props) =>
      !props.available &&
      css`
        opacity: 0.3;
      `};

    img {
      pointer-events: none;
      user-select: none;

      width: 40.6rem;
    }
  }

  section.body {
    padding: 2rem;

    h2 {
      color: var(--gray-02);
    }

    p {
      color: var(--gray-02);
      margin-top: 1.6rem;
    }
  }

  section.footer {
    display: flex;
    justify-content: space-between;
    align-items: center;

    cursor: auto;

    padding: 2rem 3rem;
    background: #e4e4eb;
    border-radius: 0px 0px 0.8rem 0.8rem;

    div.icon-container {
      display: flex;

      button {
        cursor: pointer;

        background: #fff;
        padding: 1rem;
        border-radius: 0.8rem;
        display: flex;
        align-items: center;
        justify-content: center;
        border: none;
        transition: 0.1s;

        svg {
          color: var(--gray-03);
        }

        & + button {
          margin-left: 6px;
        }
      }
    }

    div.availability-container {
      display: flex;
      align-items: center;

      p {
        color: var(--gray-03);
      }

      .switch {
        position: relative;
        display: inline-block;
        width: 8.8rem;
        height: 3.2rem;
        margin-left: 1.2rem;

        & input {
          opacity: 0;
          width: 0;
          height: 0;
        }

        .slider {
          position: absolute;
          cursor: pointer;
          top: 0;
          left: 0;
          right: 0;
          bottom: 0;
          background-color: var(--red);
          -webkit-transition: 0.4s;
          transition: 0.4s;
          border-radius: 1.6rem;

          &:before {
            position: absolute;
            content: "";
            height: 20px;
            width: 40px;
            left: 0.8rem;
            bottom: 6px;
            background-color: var(--white);
            -webkit-transition: 0.4s;
            transition: 0.4s;
            border-radius: 1rem;
          }
        }

        input:checked + .slider {
          background-color: var(--green);
        }

        input:checked + .slider:before {
          -webkit-transform: translateX(3.2rem);
          -ms-transform: translateX(3.2rem);
          transform: translateX(3.2rem);
        }
      }
    }
  }
`;
