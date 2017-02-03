package crazyit.action;

import crazyit.ViewerFrame;
import crazyit.ViewerService;

public class BigAction implements Action {

    @Override
    public void execute(ViewerService service, ViewerFrame frame) {
        service.zoom(frame, true);
    }
}
