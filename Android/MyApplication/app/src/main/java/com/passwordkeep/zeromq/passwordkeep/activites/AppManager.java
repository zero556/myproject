package com.passwordkeep.zeromq.passwordkeep.activites;

/**
 * Created by VMLDEV6 on 10/10/2017.
 */
import android.app.Activity;
import java.util.Stack;

    /**
     * Created on Android Studio.
     * User： Sunday
     * Time： 2016/12/22 16:01
     * Email：
     * TODO activity堆栈式管理
     */
    public class AppManager {

        private static Stack<Activity> activityStack;

        public AppManager() {
        }

        /**
         * 单一实例
         */
        public static AppManager getInstance() {
            return SingleApp.INSTANCE;
        }

        public static class SingleApp {
            public static AppManager INSTANCE = new AppManager();
        }

        /**
         * 添加Activity到堆栈
         */
        public void addActivity(Activity activity) {
            if (activityStack == null) {
                activityStack = new Stack<Activity>();
            }
            activityStack.add(activity);
        }

        /**
         * 移除Activity
         */
        public void removeActivity(Activity activity) {
            activityStack.remove(activity);
        }

        /**
         * 获取指定的Activity
         */
        public static Activity getActivity(Class<?> cls) {
            if (activityStack != null)
                for (Activity activity : activityStack) {
                    if (activity.getClass().equals(cls)) {
                        return activity;
                    }
                }
            return null;
        }

        /**
         * 获取当前显示Activity（堆栈中最后一个传入的activity）
         */
        public Activity getLastActivity() {
            Activity activity = activityStack.lastElement();
            return activity;
        }

        /**
         * 获取所有Activity
         */
        public Stack<Activity> getAllActivityStacks() {
            return activityStack;
        }

        /**
         * 结束指定的Activity
         */
        public void finishActivity(Activity activity) {
            if (activity != null) {
                if (!activity.isFinishing()) {
                    activity.finish();
                    activityStack.remove(activity);
                }
            }
        }

        /**
         * 结束指定类名的Activity
         */
        public void finishActivity(Class<?> cls) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                    break;
                }
            }
        }

        /**
         * 结束除当前传入以外所有Activity
         */
        public void finishOthersActivity(Class<?> cls) {
            if (activityStack != null)
                for (Activity activity : activityStack) {
                    if (!activity.getClass().equals(cls)) {
                        activity.finish();
                    }
                }
        }

        /**
         * 结束所有Activity
         */
        public void finishAllActivity() {
            if (activityStack != null)
                for (Activity activity : activityStack) {
                    activity.finish();
                }
            activityStack.clear();
        }


        /**
         * 退出应用程序
         */
        public void AppExit() {
            try {
                finishAllActivity();
                android.os.Process.killProcess(android.os.Process.myPid());// 杀死该应用进程
                System.exit(0);
            } catch (Exception e) {
            }
        }

    }

