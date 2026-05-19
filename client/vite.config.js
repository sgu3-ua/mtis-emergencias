import { defineConfig } from 'vite';

export default defineConfig({
  server: {
    proxy: {
      '/cerrarIncidente': {
        target: 'http://localhost:9090',
        changeOrigin: true,
      },
    },
  },
});
