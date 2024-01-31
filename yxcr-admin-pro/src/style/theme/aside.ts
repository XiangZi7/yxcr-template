import { Theme } from "@/hooks/interface";

export const asideTheme: Record<Theme.ThemeType, { [key: string]: string }> = {
  light: {
    "--el-aside-logo-text-color": "#303133",
    "--el-aside-border-color": "#e4e7ed",
    "--body-color":"#dde2eb"
  },
  inverted: {
    "--el-aside-logo-text-color": "#dadada",
    "--el-aside-border-color": "#414243"
  },
  dark: {
    "--el-aside-logo-text-color": "#dadada",
    "--el-aside-border-color": "#414243",
    "--body-color":"#000"

  }
};
