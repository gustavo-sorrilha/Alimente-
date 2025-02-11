import { responsiveFontSizes, ThemeProvider, CssBaseline } from "@mui/material";

// Providers import
import { AuthProvider } from "./hooks/useAuth";

// Theme import
import { GlobalStyles } from "./styles/global/theme";
import { theme } from "./styles/global/mui";

// Routes import
import { AppRoutes } from "./routes";

function App() {
  return (
    <ThemeProvider theme={responsiveFontSizes(theme)}>
      <CssBaseline>
        <GlobalStyles />
        <AuthProvider>
          <AppRoutes />
        </AuthProvider>
      </CssBaseline>
    </ThemeProvider>
  );
}

export default App;
