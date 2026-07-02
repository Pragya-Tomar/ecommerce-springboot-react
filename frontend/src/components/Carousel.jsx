import { useState, useEffect, useCallback } from 'react';
import { ChevronLeft, ChevronRight } from 'lucide-react';
import './Carousel.css';

// Slide content lives here as data, not hardcoded JSX — so swapping in real
// product photography later just means editing this array, no layout changes.
const slides = [
  {
    id: 1,
    eyebrow: 'New season',
    title: 'Everyday essentials, elevated',
    subtitle: 'Curated pieces that work as hard as you do.',
    gradient: 'linear-gradient(135deg, #6fa8dc 0%, #b8a9e8 100%)',
  },
  {
    id: 2,
    eyebrow: 'Just dropped',
    title: 'Color, without compromise',
    subtitle: 'Bold picks for people who like to stand out.',
    gradient: 'linear-gradient(135deg, #ff6fa5 0%, #6fa8dc 100%)',
  },
  {
    id: 3,
    eyebrow: 'Limited stock',
    title: 'Quality that lasts',
    subtitle: 'Fewer, better things — made to keep.',
    gradient: 'linear-gradient(135deg, #b8a9e8 0%, #ff6fa5 100%)',
  },
];

export default function Carousel() {
  const [active, setActive] = useState(0);

  const next = useCallback(() => {
    setActive((prev) => (prev + 1) % slides.length);
  }, []);

  const prev = () => {
    setActive((current) => (current - 1 + slides.length) % slides.length);
  };

  // Auto-advance every 5s — cleared on unmount so it doesn't keep firing
  // after the component leaves the page (a common source of memory leaks
  // and console warnings in React).
  useEffect(() => {
    const timer = setInterval(next, 5000);
    return () => clearInterval(timer);
  }, [next]);

  return (
    <div className="carousel">
      {slides.map((slide, index) => (
        <div
          key={slide.id}
          className={`carousel-slide ${index === active ? 'active' : ''}`}
          style={{ background: slide.gradient }}
        >
          <div className="container carousel-content">
            <span className="carousel-eyebrow">{slide.eyebrow}</span>
            <h1>{slide.title}</h1>
            <p>{slide.subtitle}</p>
            <a href="/products" className="btn btn-accent">Shop the collection</a>
          </div>
        </div>
      ))}

      <button className="carousel-arrow left" onClick={prev} aria-label="Previous slide">
        <ChevronLeft size={22} />
      </button>
      <button className="carousel-arrow right" onClick={next} aria-label="Next slide">
        <ChevronRight size={22} />
      </button>

      <div className="carousel-dots">
        {slides.map((slide, index) => (
          <button
            key={slide.id}
            className={`dot ${index === active ? 'active' : ''}`}
            onClick={() => setActive(index)}
            aria-label={`Go to slide ${index + 1}`}
          />
        ))}
      </div>
    </div>
  );
}