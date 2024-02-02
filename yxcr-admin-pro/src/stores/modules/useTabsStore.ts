import { defineStore } from "pinia";
import { TabsMenuProps, TabsState } from "../interface";
import piniaPersistConfig from "@/config/piniaPersist";

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
    removeTabs(index: number) {
      this.tabsMenuList?.splice(index, 1);
    },
  },
  persist: piniaPersistConfig("tabs"),
});
