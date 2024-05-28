package UITestCase.CommonFunction;
import java.awt.*;
import java.awt.event.InputEvent;

public class mouseSimulation {
    public void moveAndClick() throws AWTException, InterruptedException {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();
        int x = (int)point.getX() + 200;
        int y = (int)point.getY();
        Robot robot = new Robot();
        robot.mouseMove(x,y);
        Thread.sleep(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
