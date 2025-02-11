import { Button as MUIButton, CircularProgress } from "@mui/material";

export function Button({ isLoading, children, ...rest }) {
  return (
    <MUIButton
      variant="contained"
      color="primary"
      disabled={isLoading}
      {...rest}
    >
      {isLoading ? <CircularProgress size={24} /> : children}
    </MUIButton>
  );
}
