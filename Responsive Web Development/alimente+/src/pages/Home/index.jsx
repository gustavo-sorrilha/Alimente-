// Components import
import { useEffect, useState } from "react";
import { Skeleton } from "@mui/material";
import { toast } from "react-toastify";

// Components import
import { Header, Donation } from "../../components";

// Service import
import { api } from "../../services/api";

// Styles import
import { Wrapper, DonationContainer } from "./styles";

export function Home() {
  // States
  const [allDonations, setAllDonations] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function getAllDonations() {
      try {
        setLoading(true);
        const { data } = await api.get("/doacao");

        setAllDonations(data);
      } catch (error) {
        toast.error("Não foi carregar as doações.", {
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

    getAllDonations();
  }, []);

  return (
    <Wrapper>
      <Header />

      <DonationContainer>
        {allDonations &&
          !loading &&
          allDonations.map((donation) => (
            <Donation donation={donation} key={donation.id} />
          ))}

        {loading && (
          <>
            {[0, 1, 2].map(() => (
              <Skeleton
                sx={{ borderRadius: "0.8rem" }}
                variant="rounded"
                height="25rem"
              />
            ))}
          </>
        )}
      </DonationContainer>
    </Wrapper>
  );
}
