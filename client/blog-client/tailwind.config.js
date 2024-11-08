/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        darkColor1: '#1E201E',
        darkColor2: '#3C3D37',
        darkColor3: '#697565',
        darkColor4: '#ECDFCC'
      },
      boxShadow: {
        'soft-white': '0 0 20px rgba(255, 255, 255, 0.2), 0 0 40px rgba(255, 255, 255, 0.1)',
      },
    },
  },
  plugins: [],
  //暗色主题
  darkMode: 'class',
  important: true
}