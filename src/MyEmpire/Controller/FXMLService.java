package MyEmpire.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class FXMLService {
    final String fDir = "/View/";

    private HashMap<String, Controller> controllerMap;
    public Stage stage;

    public static FXMLService fxmlService = null;

    private FXMLService(Stage stage) {
        controllerMap = new HashMap<>();
        this.stage = stage;
    }

    public static void initFXMLService(Stage stage) {
        fxmlService = new FXMLService(stage);
    }

    public static FXMLService getInstance() {
        return fxmlService;
    }

    public Controller switchScene(String viewUrl) throws Exception {
        String targetDir = fDir + viewUrl;
        Controller controller = controllerMap.get(targetDir);
        FXMLLoader loader;

        // Create/load the view if it still isn't in the controllerMap
        if (controller == null) {
            loader = new FXMLLoader(getClass().getResource(targetDir));

            Parent root = loader.load();

            controller = loader.getController();
            controller.setRoot(root);
            controller.fxmlService = this;

            controllerMap.put(targetDir, controller);
        }

        Scene scene = controller.getRoot().getScene();

        if (scene == null)
            scene = new Scene(controller.getRoot(), 640, 480);

        scene.getStylesheets().add(getClass().getResource("/assets/css/sample.css").toExternalForm());

        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        return controller;
    }
}
