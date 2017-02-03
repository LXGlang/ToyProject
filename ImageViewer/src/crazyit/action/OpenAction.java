package crazyit.action;

import crazyit.ViewerFrame;
import crazyit.ViewerService;

public class OpenAction implements Action {
    @Override
    public void execute(ViewerService service, ViewerFrame frame) {
        service.open(frame);
    }
}
