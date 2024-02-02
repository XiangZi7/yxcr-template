import router from "@/router";
import { RouteRecordRaw } from "vue-router";
// import { MenuOptions } from "@/stores/interface";
import { ElNotification } from "element-plus";
// 引入 views 文件夹下所有 vue 文件
const modules = import.meta.glob("@/pages/**/*.vue");

/**
 * @description 初始化动态路由
 */
export const initDynamicRouter = async () => {
  const authStore = useAuthStore();
  try {
    // 获取菜单信息
    await authStore.getAuthMenuList();
    // 2.判断当前用户有没有菜单权限
    if (!authStore.authMenuList.length) {
      ElNotification({
        title: "无权限访问",
        message: "当前账号无任何菜单权限，请联系系统管理员！",
        type: "warning",
        duration: 3000,
      });
      // userStore.setToken("");
      router.replace("/login");
      return Promise.reject("No permission");
    }
    //动态添加路由
    authStore.flatMenuListGet.forEach((item) => {
      item.children && delete item.children;
      if (item.component && typeof item.component == "string") {
        item.component = modules["/src/pages" + item.component + ".vue"];
      }
      if (item.meta.isFull) {
        router.addRoute(item as unknown as RouteRecordRaw);
      } else {
        router.addRoute("layout", item as unknown as RouteRecordRaw);
      }
    });
    // 查看路由信息
    // console.log(router.getRoutes());
  } catch (error) {
    // 当按钮 || 菜单请求出错时，重定向到登陆页
    // userStore.setToken("");
    router.replace("/login");
    return Promise.reject(error);
  }
};
