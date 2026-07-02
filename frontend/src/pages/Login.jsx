import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Mail, Lock, ShoppingBag } from 'lucide-react';
import api from '../api/axiosConfig.js';
import './Login.css';

export default function Login() {
  const [formData, setFormData] = useState({ username: '', password: '' });
  const [error, setError] = useState('');
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
      // Matches AuthController's /api/auth/login endpoint and JwtResponse
      // shape exactly, so no mapping/translation layer is needed here.
      const response = await api.post('/auth/login', formData);
      localStorage.setItem('token', response.data.token);
      navigate('/');
    } catch (err) {
      const message = err.response?.data?.message || 'Login failed. Check your credentials.';
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
          <h2>Welcome back</h2>
          <p>Pick up right where you left off — your cart's waiting.</p>
        </div>
      </div>

      <div className="auth-form-panel">
        <div className="auth-form-wrap">
          <Link to="/" className="auth-logo">
            Shop<span>Sphere</span>
          </Link>

          <h1>Log in</h1>
          <p className="auth-subtitle">Enter your details to continue.</p>

          {error && <div className="auth-error">{error}</div>}

          <form onSubmit={handleSubmit}>
            <label className="input-group">
              <Mail size={18} />
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
              <Lock size={18} />
              <input
                type="password"
                name="password"
                placeholder="Password"
                value={formData.password}
                onChange={handleChange}
                required
              />
            </label>

            <button type="submit" className="btn btn-primary auth-submit" disabled={loading}>
              {loading ? 'Logging in…' : 'Log in'}
            </button>
          </form>

          <p className="auth-switch">
            New here? <Link to="/register">Create an account</Link>
          </p>
        </div>
      </div>
    </div>
  );
}