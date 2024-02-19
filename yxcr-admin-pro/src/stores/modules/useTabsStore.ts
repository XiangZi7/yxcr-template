import { defineStore } from "pinia";
import { TabsMenuProps, TabsState } from "../interface";
import piniaPersistConfig from "@/config/piniaPersist";
import router from "@/router";

export const useTabsStore = defineStore({
  id: "tabsStore",
  state: (): TabsState => {
    return {
      tabsMenuList: [],
    };
  },
  actions: {
    // Add Tabs
    async addTabs(tabItem: TabsMenuProps) {
      if (this.tabsMenuList.every((item) => item.path !== tabItem.path)) {
        this.tabsMenuList.push(tabItem);
      }
    },
    // Remove Tabs
    async removeTabs(tabPath: string, isCurrent: boolean = true) {
      if (isCurrent) {
        this.tabsMenuList.forEach((item, index) => {
          if (item.path !== tabPath) return;
          const nextTab =
            this.tabsMenuList[index + 1] || this.tabsMenuList[index - 1];
          if (!nextTab) return;
          router.push(nextTab.path);
        });
      }
      // set tabs
      this.tabsMenuList = this.tabsMenuList.filter(
        (item) => item.path !== tabPath,
      );
    },
  },
  persist: piniaPersistConfig("tabs"),
});
