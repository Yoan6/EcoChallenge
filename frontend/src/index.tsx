import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import './utils/style/generalStyle.css'
import Home from "./pages/Home/Home";
import About from "./pages/About/About";
import Game from './pages/Game/Game';
import { ModalProvider, useModals } from './utils/context/ModalContext';

function App() {
    return (
        <>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/game/:gameId" element={<Game />} />
                <Route path="/about" element={<About />} />
            </Routes>
        </>
    )
}

const Header = () => {
    const { closeAllModals } = useModals();

    return (
        <Link id="home_logo" to="/" onClick={closeAllModals}>
            <img id="home_logo" src="./assets/logo.png" alt="home" />
        </Link>
    );
};

// @ts-ignore
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    // <React.StrictMode>
    <Router>
        <ModalProvider>
            <Header />
            <App />
        </ModalProvider>
    </Router>
    // </React.StrictMode>
);
