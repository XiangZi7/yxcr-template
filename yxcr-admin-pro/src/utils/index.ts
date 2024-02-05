import { Menu } from "./interface";
import { MenuData } from "@/api/interface";

/**
 * @description 使用递归扁平化菜单，方便添加动态路由
 * @param {Array} menuList 菜单列表
 * @returns {Array}
 */
export function getFlatMenuList(
  menuList: Menu.MenuOptions[],
): Menu.MenuOptions[] {
  const newMenuList: Menu.MenuOptions[] = JSON.parse(JSON.stringify(menuList));
  return newMenuList.flatMap((item) => [
    item,
    ...(item.children ? getFlatMenuList(item.children) : []),
  ]);
}

/**
 * @description 使用递归找出所有面包屑存储到 pinia/vuex 中
 * @param {Array} menuList 菜单列表
 * @param {Array} parent 父级菜单
 * @param {Object} result 处理后的结果
 * @returns {Object}
 */
export const getAllBreadcrumbList = (
  menuList: Menu.MenuOptions[],
  parent = [],
  result: { [key: string]: any } = {},
) => {
  for (const item of menuList) {
    result[item.path] = [...parent, item];
    if (item.children)
      getAllBreadcrumbList(item.children, result[item.path], result);
  }
  return result;
};

/**
 * @description 查找菜单的父id和子id
 * @param data
 * @param childId
 */
export function findParentIds(data: MenuData[], childId: number): number[] {
  // 显式地定义映射表的类型，其中数字键映射到 `Node`
  const idToNodeMap: { [key: number]: MenuData } = {};

  // 一个递归函数，用于填充ID到节点的映射表
  function fillIdToNodeMap(node: MenuData) {
    idToNodeMap[node.id as number] = node;
    if (node.children) {
      node.children.forEach(fillIdToNodeMap);
    }
  }

  // 使用根节点数组初始化映射表
  data.forEach(fillIdToNodeMap);

  // 基于映射表，追踪从指定的子ID到它的所有父级ID
  const parentIds: number[] = [];
  let currentId = childId;

  while (idToNodeMap[currentId] && idToNodeMap[currentId].parentId !== null) {
    const parentId = idToNodeMap[currentId].parentId;
    if (typeof parentId === "number") {
      parentIds.unshift(parentId); // 将父ID添加到数组开头
      currentId = parentId;
    }
  }

  // 如果没有找到父级ID，返回包含单个 childId 的数组
  return parentIds.length === 0 ? [childId] : parentIds;
}
