import { TextField } from "@mui/material";
import { useController } from "react-hook-form";
import InputMask from "react-input-mask";

export function Input({ name, control, defaultValue, mask, ...rest }) {
  const {
    field: { ref, ...inputProps },
    fieldState: { invalid, error },
  } = useController({
    name,
    control,
    defaultValue,
  });

  const renderInput = () => (
    <TextField
      variant="filled"
      inputRef={ref}
      error={invalid}
      helperText={error?.message}
      inputProps={{
        style: {
          overflowY: "hidden",
          scrollbarWidth: "none",
          "&::-webkit-scrollbar": {
            display: "none",
          },
        },
      }}
      {...inputProps}
      {...rest}
    />
  );

  return mask ? (
    <InputMask mask={mask} value={defaultValue} maskChar={null} {...inputProps}>
      {renderInput}
    </InputMask>
  ) : (
    renderInput()
  );
}
