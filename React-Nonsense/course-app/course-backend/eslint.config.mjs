import globals from 'globals';
import plugins from '@eslint/js';

/** @type {import('eslint').Linter.Config[]} */
export default [
    {
    files: ['**/*.js, **/*.ts'],
    languageOptions: {
      sourceType: 'commonjs',
      globals: { ...globals.browser, process: 'readonly' },
    },
  },
  {
    languageOptions: {
      globals: {
        ...globals.node,
        ...globals.jest,
      },
    },
  },
  pluginJs.configs.recommended,
]