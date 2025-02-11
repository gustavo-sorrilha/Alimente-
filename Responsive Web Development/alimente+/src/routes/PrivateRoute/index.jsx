import { Navigate, useLocation } from "react-router-dom";
import { toast } from "react-toastify";

// Hooks import
import { useAuth } from "../../hooks/useAuth";

export function PrivateRoute({ children }) {
  // Hooks
  const { user } = useAuth();
  const location = useLocation();

  const token = user?.id_cliente;

  if (!token) {
    toast.error("Realize login para prosseguir.", {
      position: "bottom-left",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "light",
    });

    return <Navigate to="/sign-in" state={{ from: location }} />;
  }

  return children;
}
