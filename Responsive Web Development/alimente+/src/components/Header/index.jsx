import { useNavigate } from "react-router-dom";

// Assets import
import { IconButton } from "@mui/material";
import { SignOut } from "phosphor-react";
import Logo from "../../assets/Logo.svg";

// Hooks import
import { useAuth } from "../../hooks/useAuth";

// Styles import
import { Container, Logout } from "./styles";

export function Header() {
  // Hooks
  const { user } = useAuth();
  const navigate = useNavigate();

  function handleLogout() {
    localStorage.removeItem("@ALIMENTE-MAIS/token");
    navigate("/sign-in");
  }

  return (
    <Container>
      <header>
        <img src={Logo} alt="Logo do Alimente+" />

        <Logout>
          {user.cnpj ? (
            <nav>
              <div>
                <button type="button" onClick={() => navigate("/donation")}>
                  <p>Nova doação</p>

                  <div className="icon">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 256 256"
                    >
                      <path d="M208,32H48A16,16,0,0,0,32,48V208a16,16,0,0,0,16,16H208a16,16,0,0,0,16-16V48A16,16,0,0,0,208,32Zm0,176H48V48H208V208Zm-32-80a8,8,0,0,1-8,8H136v32a8,8,0,0,1-16,0V136H88a8,8,0,0,1,0-16h32V88a8,8,0,0,1,16,0v32h32A8,8,0,0,1,176,128Z" />
                    </svg>
                  </div>
                </button>
              </div>
            </nav>
          ) : (
            <div>
              <h3>Olá {user.nome}!</h3>
              <span>Logo abaixo estão as doações disponíveis</span>
            </div>
          )}

          <IconButton
            aria-label="delete"
            size="small"
            onClick={() => handleLogout()}
          >
            <SignOut size={24} />
          </IconButton>
        </Logout>
      </header>
    </Container>
  );
}
