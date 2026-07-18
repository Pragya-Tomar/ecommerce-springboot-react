import { Instagram, Twitter, Facebook, Mail } from 'lucide-react';
import './Footer.css';

export default function Footer() {
  return (
    <footer className="footer">
      <div className="container footer-inner">
        <div className="footer-brand">
          <h3>
            Shop<span>Sphere</span>
          </h3>
          <p>Thoughtfully chosen products, delivered with care.</p>
        </div>

        <div className="footer-links">
          <div>
            <h4>Shop</h4>
            <a href="/products">All products</a>
            <a href="/products?category=new">New arrivals</a>
            <a href="/products?category=sale">Sale</a>
          </div>
          <div>
            <h4>Support</h4>
            <a href="/contact">Contact us</a>
            <a href="/shipping">Shipping info</a>
            <a href="/returns">Returns</a>
          </div>
        </div>

        <div className="footer-social">
          <h4>Stay connected</h4>
          <div className="footer-icons">
            <a href="#" aria-label="Instagram"><Instagram size={18} /></a>
            <a href="#" aria-label="Twitter"><Twitter size={18} /></a>
            <a href="#" aria-label="Facebook"><Facebook size={18} /></a>
            <a href="#" aria-label="Email"><Mail size={18} /></a>
          </div>
        </div>
      </div>

      <div className="footer-bottom">
        <p>© 2026 ShopSphere. Built as a learning project.</p>
      </div>
    </footer>
  );
}