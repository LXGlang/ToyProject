package crazyit.action;

import crazyit.ViewerFrame;
import crazyit.ViewerService;

public interface Action {
    /**
     * @param service 图片浏览器的业务处理类
     * @param frame 主界面对象
     */
    void execute(ViewerService service, ViewerFrame frame);
}
