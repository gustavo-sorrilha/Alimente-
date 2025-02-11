import React, { createContext, useContext, useMemo, useState } from "react";
import { AES, enc } from "crypto-js";

const AuthContext = createContext({});

function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const token = localStorage.getItem("@ALIMENTE-MAIS/token");
    const cryptoJsKey = import.meta.env.VITE_APP_CRYPTO_JS_KEY;
    if (!token) return {};
    const decryptedBytes = AES.decrypt(token, cryptoJsKey);
    const decryptedData = JSON.parse(decryptedBytes.toString(enc.Utf8));

    if (decryptedData) return decryptedData;

    return {};
  });

  const providerValues = useMemo(() => {
    return { user, setUser };
  }, [user]);

  return (
    <AuthContext.Provider value={providerValues}>
      {children}
    </AuthContext.Provider>
  );
}

function useAuth() {
  const context = useContext(AuthContext);

  if (!context) throw new Error("useAuth must be used within a AuthProvider");

  return context;
}

export { useAuth, AuthProvider };
