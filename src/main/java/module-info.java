module com.example.projetomedicamento {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.projetomedicamento to javafx.fxml; // Permite que o FXML acesse as classes neste pacote
    opens com.example.projetomedicamento.Controller to javafx.fxml; // Permite que o FXML acesse o controlador
    exports com.example.projetomedicamento; // Exporta o pacote principal
}