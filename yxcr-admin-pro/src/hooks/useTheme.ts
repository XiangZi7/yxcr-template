import { ElMessage } from "element-plus";
import { headerTheme } from "@/style/theme/header";
import { menuTheme } from "@/style/theme/menu";
import { asideTheme } from "@/style/theme/aside";
import { Theme } from "@/hooks/interface";

/**
 * @description 切换主题
 * */
export const useTheme = () => {
  const themeStore = useThemeStore();

  // 切换暗黑模式
  const switchDark = () => {
    const body = document.documentElement;
    themeStore.isDark
      ? body.classList.add("dark")
      : body.classList.remove("dark");
    changePrimary(themeStore.primary);
    setAsideTheme();
    setHeaderTheme();
  };

  // 修改主题颜色
  const changePrimary = (val: string) => {
    if (!val) {
      val = "#409EFF";
      ElMessage({
        type: "success",
        message: `主题颜色已重置为 #409EFF`,
      });
    }
    themeStore.setThemeState("primary", val);
    // 颜色加深
    document.documentElement.style.setProperty(
      "--el-color-primary-dark-2",
      `${getDarkColor(themeStore.primary, 0.1)}`,
    );
    document.documentElement.style.setProperty(
      "--el-color-primary",
      themeStore.primary,
    );
    // 颜色变浅
    for (let i = 1; i <= 9; i++) {
      document.documentElement.style.setProperty(
        `--el-color-primary-light-${i}`,
        `${getLightColor(themeStore.primary, i / 10)}`,
      );
    }
    themeStore.setThemeState("primary", val);
  };
  // 灰色和弱色切换
  const changeGreyOrWeak = (type: Theme.GreyOrWeakType, value: boolean) => {
    const body = document.body as HTMLElement;
    if (!value) return body.removeAttribute("style");
    const styles: Record<Theme.GreyOrWeakType, string> = {
      grey: "filter: grayscale(1)",
      weak: "filter: invert(80%)",
    };
    body.setAttribute("style", styles[type]);
    const propName = type === "grey" ? "isWeak" : "isGrey";
    themeStore.setThemeState(propName, false);
  };
  // 设置侧边栏样式
  const setAsideTheme = () => {
    let type: Theme.ThemeType = "light";
    if (themeStore.asideInverted) type = "inverted";
    if (themeStore.isDark) type = "dark";
    const theme = asideTheme[type!];
    for (const [key, value] of Object.entries(theme)) {
      document.documentElement.style.setProperty(key, value);
    }
    setMenuTheme();
  };
  // 设置头部样式
  const setHeaderTheme = () => {
    let type: Theme.ThemeType = "light";
    if (themeStore.headerInverted) type = "inverted";
    if (themeStore.isDark) type = "dark";
    const theme = headerTheme[type!];
    for (const [key, value] of Object.entries(theme)) {
      document.documentElement.style.setProperty(key, value);
    }
    setMenuTheme();
  };
  // 设置菜单样式
  const setMenuTheme = () => {
    let type: Theme.ThemeType = "light";
    if (themeStore.layout === "transverse" && themeStore.headerInverted)
      type = "inverted";
    if (themeStore.layout !== "transverse" && themeStore.asideInverted)
      type = "inverted";
    if (themeStore.isDark) type = "dark";
    const theme = menuTheme[type!];
    for (const [key, value] of Object.entries(theme)) {
      document.documentElement.style.setProperty(key, value);
    }
  };
  // init theme
  const initTheme = () => {
    switchDark();
    if (themeStore.isGrey) changeGreyOrWeak("grey", true);
    if (themeStore.isWeak) changeGreyOrWeak("weak", true);
  };

  return {
    initTheme,
    switchDark,
    changePrimary,
    changeGreyOrWeak,
    setAsideTheme,
    setHeaderTheme,
  };
};
