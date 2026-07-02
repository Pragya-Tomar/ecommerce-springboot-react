import { Link } from 'react-router-dom';
import { ShoppingBag, User, Search } from 'lucide-react';
import './Navbar.css';

export default function Navbar() {
  return (
    <header className="navbar">
      <div className="container navbar-inner">
        <Link to="/" className="navbar-logo">
          Shop<span>Sphere</span>
        </Link>

        <nav className="navbar-links">
          <Link to="/">Home</Link>
          <Link to="/products">Shop</Link>
          <Link to="/products?category=new">New Arrivals</Link>
        </nav>

        <div className="navbar-actions">
          <button className="icon-btn" aria-label="Search">
            <Search size={20} />
          </button>
          <Link to="/login" className="icon-btn" aria-label="Account">
            <User size={20} />
          </Link>
          <Link to="/cart" className="icon-btn" aria-label="Cart">
            <ShoppingBag size={20} />
          </Link>
        </div>
      </div>
    </header>
  );
}