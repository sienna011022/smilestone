import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Header from "./nav/Header";

function App() {
  return (
    <BrowserRouter>
      <div>
        <Header />
        <Routes>{/* <Route path="/" element={< />} /> */}</Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
