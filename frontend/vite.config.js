import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    // Codespaces forwards ports through a proxy — this makes Vite's dev
    // server accept connections from that proxy instead of only localhost
    host: true,
    port: 3000,
  },
});