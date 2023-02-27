import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "./pages/main/Main";
import Header from "./nav/header/Header";
import Footer from "./nav/footer/Footer";
import TestClient from "./pages/testClient/TestClient";
import TestStreamer from "./pages/testStreamer/TestStreamer";
import TestChat from "./pages/testChat/TestChat"

function App() {
  return (
    <BrowserRouter>
      <div>
        <Header />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/test-client" element={<TestClient />} />
          <Route path="/test-streamer" element={<TestStreamer />} />
          <Route path="/test-chat" element={<TestChat />} />
        </Routes>
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;
