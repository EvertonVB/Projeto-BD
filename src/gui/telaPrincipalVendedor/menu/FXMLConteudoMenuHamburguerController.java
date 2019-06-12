package gui.telaPrincipalVendedor.menu;

import com.jfoenix.controls.JFXButton;
import fachada.FachadaFuncionario;
import gui.telaPrincipalGerente.ControllerTelaPrincipalGerente;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.telaPrincipalVendedor.ControllerTelaPrincipalVendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLConteudoMenuHamburguerController implements Initializable {

    @FXML
    private JFXButton buttonCliente;

    @FXML
    private JFXButton buttonProduto;

    @FXML
    private JFXButton buttonVenda;

    @FXML
    private JFXButton buttonLucro;

    @FXML
    private JFXButton buttonSalvarESair;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    // Metodos que carregam telas.
    
    @FXML
    private void carregarTelaCliente(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/cliente/FXMLCliente.fxml"));
        ControllerTelaPrincipalVendedor.rootPaneAberta.getChildren().setAll(pane);
    }
    
    @FXML
    private void carregarTelaProduto(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/produto/FXMLProduto.fxml"));
        ControllerTelaPrincipalVendedor.rootPaneAberta.getChildren().setAll(pane);
    }

    @FXML
    private void carregarTelaVenda(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/venda/FXMLVenda.fxml"));
        ControllerTelaPrincipalVendedor.rootPaneAberta.getChildren().setAll(pane);
    }

    @FXML
    private void carregarTelaLucro(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/lucro/FXMLLucro.fxml"));
        ControllerTelaPrincipalVendedor.rootPaneAberta.getChildren().setAll(pane);
    }

    @FXML
    private void sair(ActionEvent event) {
        System.exit(0);
    }
}
