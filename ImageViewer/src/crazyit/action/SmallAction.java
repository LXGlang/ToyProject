package crazyit.action;

import crazyit.ViewerFrame;
import crazyit.ViewerService;

public class SmallAction implements Action{
    @Override
    public void execute(ViewerService service, ViewerFrame frame) {
        service.zoom(frame, false);
    }
}
