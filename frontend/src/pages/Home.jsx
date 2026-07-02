import Navbar from '../components/Navbar.jsx';
import Footer from '../components/Footer.jsx';
import Carousel from '../components/Carousel.jsx';
import './Home.css';

const categories = [
  { name: 'Apparel', color: 'var(--color-primary)' },
  { name: 'Accessories', color: 'var(--color-accent)' },
  { name: 'Home', color: 'var(--color-lavender)' },
];

export default function Home() {
  return (
    <>
      <Navbar />
      <Carousel />

      <section className="container home-section">
        <h2>Shop by category</h2>
        <div className="category-grid">
          {categories.map((cat) => (
            <a href="/products" key={cat.name} className="category-card">
              <div className="category-swatch" style={{ background: cat.color }} />
              <span>{cat.name}</span>
            </a>
          ))}
        </div>
      </section>

      <Footer />
    </>
  );
}