package crazyit.action;

import crazyit.ViewerFrame;
import crazyit.ViewerService;

public class NextAction implements Action {
    @Override
    public void execute(ViewerService service, ViewerFrame frame) {
        service.next(frame);
    }
}
