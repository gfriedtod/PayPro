
/** @type {import('tailwindcss').Config} */
module.exports = {
  presets: [require('@spartan-ng/ui-core/hlm-tailwind-preset')],
  content: [
    './src/**/*.{html,ts}',
    './REPLACE_WITH_PATH_TO_YOUR_COMPONENTS_DIRECTORY/**/*.{html,ts}',
  ],
  theme: {
    extend: {
      backgroundImage: {
        'illustration1': "url('../public/illustration1.png')",
      }
    },
  },
  plugins: [
    //require('daisyui'),
  ],
};
