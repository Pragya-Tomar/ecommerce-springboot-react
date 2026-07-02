import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { User, Mail, Lock, ShoppingBag } from 'lucide-react';
import api from '../api/axiosConfig.js';
import './Login.css'; // shares the same split-panel layout as Login

export default function Register() {
  const [formData, setFormData] = useState({ username: '', email: '', password: '' });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      // Matches RegisterRequest exactly — username, email, password.
      await api.post('/auth/register', formData);
      setSuccess(true);
      setTimeout(() => navigate('/login'), 1500);
    } catch (err) {
      const message = err.response?.data?.message || 'Registration failed. Try a different username.';
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-image-panel">
        <div className="auth-image-content">
          <ShoppingBag size={40} />
          <h2>Join ShopSphere</h2>
          <p>Create an account to track orders and save favorites.</p>
        </div>
      </div>

      <div className="auth-form-panel">
        <div className="auth-form-wrap">
          <Link to="/" className="auth-logo">
            Shop<span>Sphere</span>
          </Link>

          <h1>Create account</h1>
          <p className="auth-subtitle">Takes less than a minute.</p>

          {error && <div className="auth-error">{error}</div>}
          {success && <div className="auth-error" style={{ background: '#e6f7ee', color: '#1f8a4d' }}>
            Account created — redirecting to login…
          </div>}

          <form onSubmit={handleSubmit}>
            <label className="input-group">
              <User size={18} />
              <input
                type="text"
                name="username"
                placeholder="Username"
                value={formData.username}
                onChange={handleChange}
                required
              />
            </label>

            <label className="input-group">
              <Mail size={18} />
              <input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </label>

            <label className="input-group">
              <Lock size={18} />
              <input
                type="password"
                name="password"
                placeholder="Password (min. 8 characters)"
                value={formData.password}
                onChange={handleChange}
                minLength={8}
                required
              />
            </label>

            <button type="submit" className="btn btn-primary auth-submit" disabled={loading}>
              {loading ? 'Creating account…' : 'Create account'}
            </button>
          </form>

          <p className="auth-switch">
            Already have an account? <Link to="/login">Log in</Link>
          </p>
        </div>
      </div>
    </div>
  );
}