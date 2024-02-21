import { ElMessageBox } from "element-plus";

export function useConfirmAction() {
  const confirmAndExecute = async (message, action) => {
    try {
      await ElMessageBox.confirm(message, "温馨提醒", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      });
      const response = await action();
      const { data, code } = response;
      // 此处处理操作成功的逻辑
      messagePro(code, data);
      return response;
    } catch (error) {
      // 此处处理用户取消操作或操作失败的逻辑
    }
  };

  return { confirmAndExecute };
}
