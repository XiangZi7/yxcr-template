import { createRouter, createWebHashHistory } from "vue-router";
import { staticRouter } from "@/router/modules/staticRouter";
import { initDynamicRouter } from "@/router/modules/dynamicRouter";
import NProgress from "@/config/nprogress";

/**
 * @description 📚 路由参数配置简介
 * @param path ==> 路由菜单访问路径
 * @param name ==> 路由 name (对应页面组件 name, 可用作 KeepAlive 缓存标识 && 按钮权限筛选)
 * @param redirect ==> 路由重定向地址
 * @param component ==> 视图文件路径
 * @param meta ==> 路由菜单元信息
 * @param meta.icon ==> 菜单和面包屑对应的图标
 * @param meta.title ==> 路由标题 (用作 document.title || 菜单的名称)
 * @param meta.activeMenu ==> 当前路由为详情页时，需要高亮的菜单
 * @param meta.visible ==> 是否在菜单中隐藏 (通常列表详情页需要隐藏)
 * @param meta.isFull ==> 菜单是否全屏 (示例：数据大屏页面)
 * @param meta.isKeepAlive ==> 当前路由是否缓存
 * */

// 创建 router 实例
const router = createRouter({
  history: createWebHashHistory(),
  routes: staticRouter,
  strict: false,
});
/**
 * @description 路由拦截 beforeEach
 * */
router.beforeEach(async (to, from, next) => {
  const authStore = useAuthStore();
  const userStore = useUserStore();

  NProgress.start();

  // 3.判断是访问登陆页，有 Token 就在当前页面，没有 Token 重置路由到登陆页
  if (to.path.toLocaleLowerCase() === "/login") {
    if (userStore.userInfo.token) return next(from.fullPath);
    resetRouter();
    return next();
  }
  // 6.如果没有菜单列表，就重新请求菜单列表并添加动态路由
  if (!authStore.flatMenuListGet.length) {
    await initDynamicRouter();
    return next({ ...to, replace: true });
  }
  next();
});

/**
 * @description 重置路由
 * */
export const resetRouter = () => {
  const authStore = useAuthStore();
  authStore.flatMenuListGet.forEach((route) => {
    const { name } = route;
    if (name && router.hasRoute(name)) router.removeRoute(name);
  });
};

/**
 * @description 路由跳转错误
 * */
router.onError((error) => {
  NProgress.done();
  console.warn("路由错误", error.message);
});

/**
 * @description 路由跳转结束
 * */
router.afterEach(() => {
  NProgress.done();
});
export default router;
