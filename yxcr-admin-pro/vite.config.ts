import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import path from "path";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import viteCompression from "vite-plugin-compression";

// eslint
import eslintPlugin from "vite-plugin-eslint";

import Icons from "unplugin-icons/vite";
import IconsResolver from "unplugin-icons/resolver";

/**
 * @description vite配置
 * @URL https://vitejs.dev/config
 */
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      imports: ["vue", "vue-router"],
      dirs: ["src/utils/**", "src/stores/modules/**", "src/hooks/**"],
      dts: "src/auto-import/imports.d.ts",
      eslintrc: {
        enabled: true,
        filepath: "src/auto-import/eslintrc-auto-import.json",
      },
      resolvers: [
        // 自动导入图标组件
        IconsResolver(),
      ],
    }),
    Components({
      dirs: ["src/components"],
      dts: "src/auto-import/components.d.ts",
      resolvers: [
        // 自动注册图标组件
        IconsResolver(),
      ],
    }),
    Icons({
      compiler: "vue3",
      autoInstall: true,
    }),
    // eslint
    eslintPlugin({
      include: [
        "src/**/*.js",
        "src/**/*.ts",
        "src/**/*.vue",
        "src/*.js",
        "src/*.ts",
        "src/*.vue",
      ],
    }),
    // 对大于 1k 的文件进行压缩
    viteCompression({
      threshold: 1000,
    }),
  ],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src"),
    },
  },
  server: {
    host: "0.0.0.0",
    port: 3344,
    // open: true
  },
  build: {
    rollupOptions: {
      // 静态资源分类打包
      output: {
        chunkFileNames: "static/js/[name]-[hash].js",
        entryFileNames: "static/js/[name]-[hash].js",
        assetFileNames: "static/[ext]/[name]-[hash].[ext]",
        // 静态资源分拆打包
        manualChunks(id) {
          if (id.includes("node_modules")) {
            if (id.toString().indexOf(".pnpm/") !== -1) {
              return id.toString().split(".pnpm/")[1].split("/")[0].toString();
            } else if (id.toString().indexOf("node_modules/") !== -1) {
              return id
                .toString()
                .split("node_modules/")[1]
                .split("/")[0]
                .toString();
            }
          }
        },
      },
    },
    sourcemap: false,
    target: "es2015",
    reportCompressedSize: false,
  },
});
