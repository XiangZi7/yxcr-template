import { defineStore } from "pinia";
import { ThemeState } from "../interface";
import piniaPersistConfig from "@/config/piniaPersist";

/**
 * 全局设置
 */
export const useThemeStore = defineStore({
  id: "useThemeStore",
  state: (): ThemeState => {
    return {
      // 布局模式 (纵向：vertical | 经典：classic | 横向：transverse | 分栏：columns)
      layout: "vertical",
      // element 组件大小
      assemblySize: "default",
      // 当前系统语言
      language: null,
      // 当前页面是否全屏
      maximize: false,
      // 主题颜色
      primary:"#409EFF",
      // 深色模式
      isDark: false,
      // 灰色模式
      isGrey: false,
      // 色弱模式
      isWeak: false,
      // 侧边栏反转
      asideInverted: false,
      // 头部反转
      headerInverted: false,
      // 折叠菜单
      isCollapse: false,
      // 菜单手风琴
      accordion: true,
      // 面包屑导航
      breadcrumb: true,
      // 面包屑导航图标
      breadcrumbIcon: true,
      // 标签页
      tabs: true,
      // 标签页图标
      tabsIcon: true,
      // 页脚
      footer: true
    };
  },
  actions: {
    // Set GlobalState
    setThemeState(...args: ObjToKeyValArray<GlobalState>) {
      this.$patch({ [args[0]]: args[1] });
    }
  },
  persist: piniaPersistConfig("useThemeStore"),
});
