/**
 * 歌曲时间
 * @param time
 */
export const formatTime = (time: number): string => {
  const minutes = Math.floor(time / 60)
    .toString()
    .padStart(2, "0");
  const seconds = Math.floor(time % 60)
    .toString()
    .padStart(2, "0");
  return `${minutes}:${seconds}`;
};
/**
 * 歌曲时间
 * @param songdt
 */
export const formatTimes = (songdt: number): string => {
  let min: string | number = parseInt((songdt / 1000 / 60).toString());
  let sec: string | number = parseInt(((songdt / 1000) % 60).toString());
  if (min < 10) {
    min = "0" + min;
  }
  if (sec < 10) {
    sec = "0" + sec;
  }
  return min + ":" + sec;
};

/**
 * 时间戳
 * @param timestamp
 */
export function timestamp(timestamp: number): string {
  const date = new Date(timestamp);
  const normalTime = date.toLocaleString(); // 使用 toLocaleString() 方法将时间转换为本地时间格式
  return normalTime;
}

/**
 * 当前时间
 */
export function formatCurrentDateTime(): string {
  const now = new Date();
  const year: string = now.getFullYear().toString(); // 获取年份
  const month: string = String(now.getMonth() + 1).padStart(2, "0"); // 获取月份，月份是从 0 开始计算的
  const day: string = String(now.getDate()).padStart(2, "0"); // 获取日
  const hours: string = String(now.getHours()).padStart(2, "0"); // 获取小时
  const minutes: string = String(now.getMinutes()).padStart(2, "0"); // 获取分钟
  const seconds: string = String(now.getSeconds()).padStart(2, "0"); // 获取秒数

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
