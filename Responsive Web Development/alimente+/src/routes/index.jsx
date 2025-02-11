import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import { PrivateRoute } from "./PrivateRoute";

// Pages import
import { Landing } from "../pages/Landing";
import { SignIn } from "../pages/SignIn";
import { SignUp } from "../pages/SignUp";
import { Home } from "../pages/Home";
import { Donation } from "../pages/Donation";

export function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" exact element={<Landing />} />
        <Route path="/sign-in" element={<SignIn />} />
        <Route path="/sign-up" element={<SignUp />} />

        <Route
          path="/home"
          element={
            <PrivateRoute>
              <Home />
            </PrivateRoute>
          }
        />

        <Route
          path="/donation/:id?"
          element={
            <PrivateRoute>
              <Donation />
            </PrivateRoute>
          }
        />
      </Routes>

      <ToastContainer />
    </BrowserRouter>
  );
}
