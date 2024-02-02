import { RouteRecordRaw } from "vue-router";

/**
 * staticRouter (静态路由)
 */
export const staticRouter: RouteRecordRaw[] = [
  {
    path: "/",
    redirect: "/home/index",
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/pages/login/index.vue"),
    meta: {
      title: "登录",
    },
  },
  {
    path: "/layout",
    name: "layout",
    component: () => import("@/layouts/index.vue"),
    redirect: "/home/index",
    children: [],
  },
  // Resolve refresh page, route warnings
  {
    path: "/:pathMatch(.*)*",
    component: () => import("@/pages/[...notFound].vue"),
  },
];
