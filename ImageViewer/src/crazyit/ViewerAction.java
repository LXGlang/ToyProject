package crazyit;

import crazyit.action.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewerAction extends AbstractAction {
    private String actionName = "";
    private ViewerFrame frame = null;
    private Action action = null;

    public ViewerAction() {
        super();
    }

    public ViewerAction(ImageIcon icon, String actionName, ViewerFrame frame) {
        super("", icon);
        this.actionName = actionName;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ViewerService service = ViewerService.getInstance();
        Action action = getAction(this.actionName);
        action.execute(service, frame);
    }

    private Action getAction(String actionName) {
        try {
            if (this.action == null) {
                Action action = (Action) Class.forName("crazyit.action." +
                        actionName).newInstance();
                this.action = action;
            }
            return this.action;
        } catch (Exception e) {
            return null;
        }
    }


}
