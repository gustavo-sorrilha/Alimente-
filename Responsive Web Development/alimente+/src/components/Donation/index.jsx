/* eslint-disable react/no-unstable-nested-components */
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { PencilSimpleLine, TrashSimple, X } from "phosphor-react";
import {
  Typography,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  DialogActions,
  Button,
  IconButton,
} from "@mui/material";
import { toast } from "react-toastify";

// Hooks import
import { useAuth } from "../../hooks/useAuth";

// Styles import
import { Container } from "./styles";
import { api } from "../../services/api";

export function Donation({ donation }) {
  // Hooks

  const { user } = useAuth();
  const navigate = useNavigate();

  // Local states
  const [loading, setLoading] = useState(false);
  const [isAvailable, setIsAvailable] = useState(
    donation.disponibilidade === 1
  );
  const [donationDetailsModalIsOpen, setDonationDetailsModalIsOpen] =
    useState(false);
  const [
    confirmDeleteDonationModalIsOpen,
    setConfirmDeleteDonationModalIsOpen,
  ] = useState(false);

  function DonationDetailsModal() {
    return (
      <Dialog
        open={donationDetailsModalIsOpen}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
        fullScreen
      >
        <DialogTitle id="alert-dialog-title">
          Detalhes da doação
          <IconButton
            aria-label="close"
            onClick={() => setDonationDetailsModalIsOpen(false)}
            sx={{ position: "absolute", right: 25, top: 20 }}
          >
            <X size={24} />
          </IconButton>
        </DialogTitle>
        <DialogContent sx={{ display: "flex", gap: 3 }}>
          <DialogContentText id="alert-dialog-description">
            <Typography variant="h2" marginTop="2rem">
              {donation.descricao}
            </Typography>
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button autoFocus variant="contained" size="large">
            Resgatar doação
          </Button>
        </DialogActions>
      </Dialog>
    );
  }

  function ConfirmDeleteDonationModal() {
    const handleDeleteDonation = async () => {
      try {
        await api.delete(`/doacao/${donation.id_doacao}`);

        toast.success("Doação apagada com sucesso!", {
          position: "bottom-left",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "light",
        });
      } catch {
        toast.error("Não foi apagar esta doação.", {
          position: "bottom-left",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "light",
        });
      }
    };

    return (
      <Dialog
        open={confirmDeleteDonationModalIsOpen}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          Tem certeza que deseja excluir essa doação {donation.nome}?
          <IconButton
            aria-label="close"
            onClick={() => setConfirmDeleteDonationModalIsOpen(false)}
            sx={{ position: "absolute", right: 25, top: 20 }}
          >
            <X size={24} />
          </IconButton>
        </DialogTitle>

        <DialogContent sx={{ display: "flex", gap: 3 }}>
          <DialogContentText id="alert-dialog-description">
            {donation.descricao}
          </DialogContentText>
        </DialogContent>

        <DialogActions>
          <Button
            autoFocus
            size="large"
            onClick={() => setConfirmDeleteDonationModalIsOpen(false)}
          >
            Não
          </Button>

          <Button
            variant="contained"
            size="large"
            onClick={handleDeleteDonation}
          >
            Sim
          </Button>
        </DialogActions>
      </Dialog>
    );
  }

  async function handleEditAvailability() {
    try {
      setLoading(true);

      await api.put(`/doacao/${donation.id_doacao}`, {
        imagem: donation.imagem,
        descricao: donation.descricao,
        disponibilidade: donation.disponibilidade === 1 ? 0 : 1,
      });

      setIsAvailable((previousState) => !previousState);
    } catch {
      toast.error("Não foi alterar a disponibilidade dessa doação.", {
        position: "bottom-left",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    } finally {
      setLoading(false);
    }
  }
  return (
    <>
      <Container
        available={isAvailable}
        onClick={() => setDonationDetailsModalIsOpen(true)}
      >
        <header>
          <img src={donation.imagem} alt={donation.name} />
        </header>

        <section className="body">
          <h2>{donation.nome}</h2>
          <p>{donation.descricao}</p>
        </section>

        {!!user.cnpj && donation.id_cliente === user.id_cliente && (
          <section
            className="footer"
            onClick={(event) => event.stopPropagation()}
          >
            <div className="icon-container">
              <button
                type="button"
                className="icon"
                onClick={() => navigate(`/donation/${donation.id_doacao}`)}
              >
                <PencilSimpleLine size={24} />
              </button>

              <button
                type="button"
                className="icon"
                onClick={() => setConfirmDeleteDonationModalIsOpen(true)}
              >
                <TrashSimple size={20} />
              </button>
            </div>

            <div className="availability-container">
              <p>{isAvailable ? "Disponível" : "Indisponível"}</p>

              <label
                htmlFor={`available-switch-${donation.id_doacao}`}
                className="switch"
              >
                <input
                  id={`available-switch-${donation.id_doacao}`}
                  type="checkbox"
                  checked={isAvailable}
                  onChange={handleEditAvailability}
                  disabled={loading}
                />
                <span className="slider" />
              </label>
            </div>
          </section>
        )}
      </Container>

      <DonationDetailsModal />
      <ConfirmDeleteDonationModal />
    </>
  );
}
