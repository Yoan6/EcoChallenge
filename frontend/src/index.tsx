import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import './utils/style/generalStyle.css'
import Home from "./pages/Home/Home";
import About from "./pages/About/About";

function App() {
    return (
        <>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/about" element={<About />} />
            </Routes>
        </>
    )
}

// @ts-ignore
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Router>
            <Link id="home_logo" to="/">
                <img id="home_logo" src='./assets/logo.png' alt="home"/>
            </Link>
            <App />
        </Router>
    </React.StrictMode>
);
