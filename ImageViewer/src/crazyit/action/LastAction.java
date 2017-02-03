package crazyit.action;

import crazyit.ViewerFrame;
import crazyit.ViewerService;

public class LastAction implements Action {
    @Override
    public void execute(ViewerService service, ViewerFrame frame) {
        service.last(frame);
    }
}
